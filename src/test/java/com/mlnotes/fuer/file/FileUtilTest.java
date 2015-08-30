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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.channels.FileChannel;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author Hanfeng Zhu <me@mlnotes.com>
 */
public class FileUtilTest {
    private static final String FILENAME = "/tmp/file_util_test";
    
    @Test
    public void testWriteReadLong() throws FileNotFoundException, IOException {
        FileChannel file = FileUtil.openFile(FILENAME, true);
        long writeNum = 0x123456;
        FileUtil.writeLong(file, writeNum);
        file.close();
        
        file = FileUtil.openFile(FILENAME);
        long readNum = FileUtil.readLong(file);
        
        Assert.assertEquals("Read num should equals to write num", readNum, writeNum);
    }
}
