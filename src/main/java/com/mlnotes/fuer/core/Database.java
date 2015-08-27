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
package com.mlnotes.fuer.core;

import com.mlnotes.fuer.exception.InvalidDatabaseFileException;
import com.mlnotes.fuer.exception.UnexistedTableException;
import com.mlnotes.fuer.table.Table;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author Hanfeng Zhu <me@mlnotes.com>
 */
public interface Database {
    int getId();
    String getName();
    String getFileName();
    String setFileName(String fileName);
    Date getCreateTime();
    Date getModifyTime();
    Table getTable(String name) throws UnexistedTableException;
    boolean dropTable(String name) throws UnexistedTableException;
    // TODO need a sql parser
    Table createTable(String sql);
    void openFile(String fileName, boolean create) throws InvalidDatabaseFileException, IOException;
}
