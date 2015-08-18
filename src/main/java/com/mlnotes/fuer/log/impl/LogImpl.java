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
package com.mlnotes.fuer.log.impl;

import com.mlnotes.fuer.data.Buffer;
import com.mlnotes.fuer.log.Log;
import com.mlnotes.fuer.log.LogEntry;
import com.mlnotes.fuer.storage.FileStorage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hanfeng Zhu <me@mlnotes.com>
 */
public class LogImpl implements Log {

    private static final int MAX_ENTRIES = 10; // For tests
    private FileStorage fileStorage;
    private Buffer buffer;

    @Override
    public void add(LogEntry entry) {
        int writePos = buffer.getWritePos();
        try {
            entry.write(buffer);
        } catch (IOException ex) {
            // Not enough space for buffer
            buffer.setWritePos(writePos); // remove already written data
            if (writePos == 0) {
                // buffer is too small
                buffer.expand();
            } else {
                save();
                buffer.reset();
            }
            add(entry);
        }
    }

    @Override
    public void save() {
        if (fileStorage == null) {
            // TODO create new file
            // TODO how to save? I need to get the byte[] array
        }

    }

    @Override
    public void load() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
