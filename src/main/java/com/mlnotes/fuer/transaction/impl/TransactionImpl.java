/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mlnotes.fuer.transaction.impl;

import com.mlnotes.fuer.transaction.Resource;
import com.mlnotes.fuer.transaction.Transaction;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zhf
 */
public class TransactionImpl implements Transaction {
    private Status status;
    private final List<Resource> resources = new ArrayList<>();
    
    @Override
    public void start() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void commit() {
        // TODO beforeCallback
        status = Status.COMMITING;
        for(Resource res : resources) {
            res.commit();
        }
        status = Status.COMMITTED;
        // TODO afterCallback
    }

    @Override
    public void rollback() {
        // TODO beforeCallback
        status = Status.ROLLBACKING;
        for(Resource res: resources) {
            res.rollback();
        }
        status = Status.ROLLBACKED;
        // TOOD afterCallback
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public boolean addResource(Resource res) {
        return resources.add(res);
    }

    @Override
    public boolean removeResource(Resource res) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
