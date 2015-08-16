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
    
    public int readInt() throws IOException {
        if(readPos + 4 > writePos) {
            throw new IOException("cannot read int from buffer");
        }
            
        int x = (data[readPos] << 24) +
                (data[readPos+1] << 16) +
                (data[readPos+2] << 8) +
                (data[readPos+3]);
        readPos += 4;
        return x;
    }
    
    public void writeInt(int num) throws IOException {
        if(writePos + 4 > data.length) {
            throw new IOException("not enought buffer");
        }
        
        data[writePos] = (byte)(num >> 24);
        data[writePos+1] = (byte)(num >> 16);
        data[writePos+2] = (byte)(num >> 8);
        data[writePos+3] = (byte)(num);
        writePos += 4;
    }
}
