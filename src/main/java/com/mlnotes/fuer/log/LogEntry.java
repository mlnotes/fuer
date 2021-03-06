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
package com.mlnotes.fuer.log;

import com.mlnotes.fuer.data.Buffer;
import com.mlnotes.fuer.table.Row;
import java.io.IOException;

/**
 *
 * @author Hanfeng Zhu <me@mlnotes.com>
 */
public interface LogEntry {

    int getId();
    
    int getTransactionId();

    Operation getOperation();

    Row getRow();

    public boolean checksum();
    
    public void write(Buffer buffer) throws IOException;

    public void read(Buffer buffer) throws IOException;
}
