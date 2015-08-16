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
package com.mlnotes.fuer.data;

import java.io.IOException;

/**
 *
 * @author Hanfeng Zhu <me@mlnotes.com>
 */
public class Buffer {

    private byte[] data;
    private int readPos = 0;
    private int writePos = 0;

    public byte readByte() throws IOException {
        if (readPos + 1 > writePos) {
            throw new IOException("cannot read int from buffer");
        }

        byte x = data[readPos];
        readPos += 1;
        return x;
    }

    public void writeByte(byte x) throws IOException {
        if (writePos + 1 > data.length) {
            throw new IOException("not enought buffer");
        }

        data[writePos] = x;
        writePos += 1;
    }
    
    public char readChar() throws IOException {
        return (char)readByte();
    }
    
    public void writeChar(char c) throws IOException {
        writeByte((byte)c);
    }

    public short readShort() throws IOException {
        if(readPos + 2 > writePos) {
            throw new IOException("cannot read int from buffer");
        }
        
        short x = (short)((data[readPos] << 8) + data[readPos+1]);
        return x;
    }
    
    public void writeShort(short value) throws IOException {
        if(writePos + 2 > data.length) {
            throw new IOException("cannot read int from buffer");
        }
        
        data[writePos] = (byte)(value >> 8);
        data[writePos+1] = (byte)value;
        writePos += 2;
    }
    
    public int readInt() throws IOException {
        if (readPos + 4 > writePos) {
            throw new IOException("cannot read int from buffer");
        }

        int x = (data[readPos] << 24)
                + (data[readPos + 1] << 16)
                + (data[readPos + 2] << 8)
                + (data[readPos + 3]);
        readPos += 4;
        return x;
    }

    public void writeInt(int num) throws IOException {
        if (writePos + 4 > data.length) {
            throw new IOException("not enought buffer");
        }

        data[writePos] = (byte) (num >> 24);
        data[writePos + 1] = (byte) (num >> 16);
        data[writePos + 2] = (byte) (num >> 8);
        data[writePos + 3] = (byte) (num);
        writePos += 4;
    }
    
    public long readLong() throws IOException {
        return ((long)readInt() << 32) + readInt();
    }
    
    // TODO not sure when to use >>> or >> 
    public void writeLong(long num) throws IOException {
        writeInt((int) (num >>> 32));
        writeInt((int)num);
    }
}
