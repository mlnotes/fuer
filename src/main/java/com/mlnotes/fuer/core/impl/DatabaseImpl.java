/*
 * Copyright 2015 Hanfeng Zhu <me@mlnotes.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mlnotes.fuer.core.impl;

import com.mlnotes.fuer.core.Database;
import com.mlnotes.fuer.exception.InvalidDatabaseFileException;
import com.mlnotes.fuer.exception.UnexistedTableException;
import com.mlnotes.fuer.file.FileUtil;
import com.mlnotes.fuer.table.Table;
import com.mlnotes.fuer.table.impl.TableImpl;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.CRC32;

/**
 *
 * @author Hanfeng Zhu <me@mlnotes.com>
 */
public class DatabaseImpl implements Database {

    private static final ByteBuffer MAGIC_HEADER = ByteBuffer.wrap(new byte[]{0, 'F', 0, 'U', 0, 'E', 0, 'R'});
    private static final int FILE_HEADER = 8 * 3; // bytes
    private int id;
    private String name;
    private Date createTime;
    private Date modifyTime;
    private final Map<String, Table> tables = new HashMap<>();

    public DatabaseImpl(String name) {
        this.name = name;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public Date getModifyTime() {
        return modifyTime;
    }

    // TODO handle multithreads
    @Override
    public Table getTable(String name) throws UnexistedTableException {
        checkExistence(name);
        return tables.get(name);
    }

    // TODO handle multithreads
    @Override
    public boolean dropTable(String name) throws UnexistedTableException {
        checkExistence(name);
        Table table = tables.remove(name);
        // TODO drop table 
        return true;
    }

    @Override
    public Table createTable(String sql) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void checkExistence(String name) throws UnexistedTableException {
        if (!tables.containsKey(name)) {
            throw new UnexistedTableException(name);
        }
    }

    @Override
    public String getFileName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String setFileName(String fileName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void openFile(String fileName, boolean create) throws InvalidDatabaseFileException, IOException {
        if (FileUtil.exists(fileName)) {
            openFile(fileName);
        } else if (!create) {
            throw new IOException("File: " + fileName + "not exists");
        } else {
            // create new file
            createTime = new Date();
            modifyTime = createTime;
            FileChannel file = FileUtil.openFile(fileName);
            // write magic
            file.write(MAGIC_HEADER);
            // write length
            FileUtil.writeLong(file, 8 * 2);
            // write checksum
            FileUtil.writeLong(file, 0);
            // write ctime
            FileUtil.writeLong(file, createTime.getTime());
            // write mtime
            FileUtil.writeLong(file, modifyTime.getTime());
            // update checksum
            file.position(8*3);
            long checksum = calcChecksum(file);
            file.position(8);
            FileUtil.writeLong(file, checksum);
        }
    }

    /**
     * file consists of two parts: header + data data is ctime + mtime + a list
     * of string pair table name table file
     *
     * @param fileName
     * @throws InvalidDatabaseFileException
     * @throws IOException
     */
    private void openFile(String fileName) throws InvalidDatabaseFileException, IOException {
        FileChannel file = FileUtil.openFile(fileName);
        if (!validate(file)) {
            throw new InvalidDatabaseFileException(fileName);
        }

        // skip the file header
        file.position(FILE_HEADER);

        // ctime
        long ctime = FileUtil.readLong(file);
        createTime = new Date(ctime);

        // mtime
        long mtime = FileUtil.readLong(file);
        modifyTime = new Date(mtime);

        // tables
        while (file.position() < file.size()) {
            String tableName = FileUtil.readString(file);
            String tableFileName = FileUtil.readString(file);
            Table table = new TableImpl(tableName);
            table.openFile(tableFileName);
            tables.put(tableName, table);
        }
    }

    private long calcChecksum(FileChannel file) throws IOException {
        CRC32 crc32 = new CRC32();
        ByteBuffer buff = ByteBuffer.allocate(1024);
        int read;
        while ((read = file.read(buff)) > 0) {
            crc32.update(buff.array(), 0, read);
            buff.reset();
        }
        return crc32.getValue();
    }

    private boolean validate(FileChannel file) throws IOException {
        if (file.size() < 64 * 3) {
            return false;
        }

        // validate magic
        ByteBuffer buff = ByteBuffer.allocate(8);
        file.read(buff);
        if (!buff.equals(MAGIC_HEADER)) {
            return false;
        }

        // validate data length
        buff.reset();
        file.read(buff);
        long length = buff.getLong();
        if (file.size() - 64 * 3 != length) {
            return false;
        }

        // validate checksum
        buff.reset();
        file.read(buff);
        long checksum = buff.getLong();
        // crc32 could be used to calculate checksum
        return calcChecksum(file) == checksum;
    }
}
