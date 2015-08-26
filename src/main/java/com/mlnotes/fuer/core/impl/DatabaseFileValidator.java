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

import com.mlnotes.fuer.exception.InvalidDatabaseFileException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.zip.CRC32;

/**
 * Validate if the given file is a valid fuer database file.
 *
 * File structure |64 bits magic |64 bits data length without heder |64 bits
 * checksum |data |
 *
 * What does |data | contains ?
 *
 * @author Hanfeng Zhu <me@mlnotes.com>
 */
public class DatabaseFileValidator {

    private static final ByteBuffer MAGIC_HEADER = ByteBuffer.wrap(new byte[]{0, 'F', 0, 'U', 0, 'E', 0, 'R'});

    public static boolean validate(FileChannel file) throws IOException {
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
        CRC32 crc32 = new CRC32();
        buff = ByteBuffer.allocate(1024);
        int read;
        while ((read = file.read(buff)) > 0) {
            crc32.update(buff.array(), 0, read);
            buff.reset();
        }
        return checksum == crc32.getValue();
    }
}
