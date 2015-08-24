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
import com.mlnotes.fuer.core.Server;
import com.mlnotes.fuer.core.Session;
import com.mlnotes.fuer.exception.InvalidDatabaseFileException;
import com.mlnotes.fuer.exception.UnexistedDatabaseException;
import com.mlnotes.fuer.exception.UnexistedSessionException;
import com.mlnotes.fuer.file.FileUtil;
import java.io.File;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Manage sessions & databases
 *
 * @author Hanfeng Zhu <me@mlnotes.com>
 */
public class ServerImpl implements Server {
    private static final String DB_SUFFIX = ".fuer_db";
    private final AtomicInteger sessionIdGenerator = new AtomicInteger(1);

    private final String dataDir;
    private Map<Integer, Session> openedSessions;
    private Map<String, Database> openedDbs;

    public ServerImpl(String dataDir) {
        this.dataDir = dataDir;
    }
    // TODO how to handle multithreads
    @Override
    public Session openSession(String dbName, boolean create)
            throws UnexistedDatabaseException, InvalidDatabaseFileException {
        Database db = null;
        if(openedDbs.containsKey(dbName)) {
            db = openedDbs.get(dbName);
        } else {
            String fileName = getFileName(dbName);
            if(!create && !FileUtil.exists(fileName)) {
                throw new UnexistedDatabaseException(dbName);
            } else {
                db = new DatabaseImpl(dbName);
                db.openFile(fileName);
                openedDbs.put(dbName, db);
            }
        }
        
        Session session = new SessionImpl(
                sessionIdGenerator.incrementAndGet(), db);
        openedSessions.put(session.getId(), session);
        
        return session;
    }

    // TODO how to handle multithreads ?
    // nio maybe used to accelerate the connection
    @Override
    public Session getSession(int sessionId)  throws UnexistedSessionException {
        if(!openedSessions.containsKey(sessionId)) {
            throw new UnexistedSessionException(sessionId);
        }
        return openedSessions.get(sessionId);
    }

    // TODO how to handle multithreads ?
    @Override
    public boolean closeSession(int sessionId) throws UnexistedSessionException {
        if(!openedSessions.containsKey(sessionId)) {
            throw new UnexistedSessionException(sessionId);
        }
        openedSessions.remove(sessionId);
        return true;
    }
    
    private String getFileName(String dbName) {
        return dataDir + File.separator + dbName + DB_SUFFIX;
    }
    
}
