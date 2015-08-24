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
import com.mlnotes.fuer.core.Session;
import java.util.Date;

/**
 *
 * @author Hanfeng Zhu <me@mlnotes.com>
 */

/**
 * 
 * Server side session object, which contains information about a connection
 */
public class SessionImpl implements Session {
    private int id;
    private Date start;
    private Date end;
    private Database database;

    public SessionImpl(int id, Database database) {
        this.id = id;
        this.database = database;
        start = new Date();
    }
    
    @Override
    public int getId() {
        return id;
    }

    @Override
    public Database getDatabase() {
        return database;
    }

    @Override
    public boolean close() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Date getStart() {
        return start;
    }

    @Override
    public Date getEnd() {
        return end;
    }
    
}
