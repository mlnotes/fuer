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
package com.mlnotes.fuer.storage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 *
 * @author Hanfeng Zhu <me@mlnotes.com>
 */
public class FileStorage {

    private static final String HEADER = "--FUER STORE--";
    private static final int HEADER_LENGTH = 16*3;
    
    private FileChannel fileChannel;

    public void open(String filename) throws FileNotFoundException {
        RandomAccessFile raf = new RandomAccessFile(filename, "rw");
        fileChannel = raf.getChannel();
    }
    
    private void init() {
        // TODO check header & init for new file
    }

    public void write(byte[] buff, int offset, int length) throws IOException {
        fileChannel.write(ByteBuffer.wrap(buff, offset, length));
    }
    
    public int read(byte[] buffer, int offset, int length) throws IOException {
        return fileChannel.read(ByteBuffer.wrap(buffer, offset, length));
    }
    
    public void flush() throws IOException {
        fileChannel.force(true);
    }
}
