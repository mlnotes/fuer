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
package com.mlnotes.fuer.transaction.impl;

import com.mlnotes.fuer.log.Log;
import com.mlnotes.fuer.transaction.Transaction;

/**
 *
 * @author Hanfeng Zhu <me@mlnotes.com>
 */
public class TransactionImpl implements Transaction {
    private final int id;
    private Status status = Status.BEGIN;
    
    public TransactionImpl(int id) {
        this.id = id;
    }
    
    @Override
    public int getId() {
        return id;
    }
    
    @Override
    public void commit() {
        // TODO beforeCallback
        status = Status.COMMITING;
        // TODO
        
        status = Status.COMMITTED;
        // TODO afterCallback
    }

    @Override
    public void rollback() {
        // TODO beforeCallback
        status = Status.ROLLBACKING;
        // TODO
        
        status = Status.ROLLBACKED;
        // TOOD afterCallback
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public boolean apppend(Log log) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
