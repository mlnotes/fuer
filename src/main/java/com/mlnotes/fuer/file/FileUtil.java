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
package com.mlnotes.fuer.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 *
 * @author Hanfeng Zhu <me@mlnotes.com>
 */
public final class FileUtil {
    public static boolean exists(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }
    
    public static FileChannel openFile(String fileName) throws FileNotFoundException {
        return openFile(fileName, false);
    }
    
    public static FileChannel openFile(String fileName, boolean create) throws FileNotFoundException {
        String mode = "r";
        if(create) {
            mode += "w";
        }
        
        RandomAccessFile file = new RandomAccessFile(fileName, mode);
        return file.getChannel();
    }
}
