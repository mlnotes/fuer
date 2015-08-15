/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
