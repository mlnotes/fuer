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
import com.mlnotes.fuer.exception.UnexistedTableException;
import com.mlnotes.fuer.table.Table;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author Hanfeng Zhu <me@mlnotes.com>
 */
public class DatabaseImpl implements Database {
    private int id;
    private String name;
    private Date createTime;
    private Map<String, Table> tables;

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
        if(!tables.containsKey(name)) {
            throw new UnexistedTableException(name);
        }
    }
}
