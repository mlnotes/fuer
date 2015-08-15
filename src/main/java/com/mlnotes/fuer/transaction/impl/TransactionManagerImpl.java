/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mlnotes.fuer.transaction.impl;

import com.mlnotes.fuer.transaction.Transaction;
import com.mlnotes.fuer.transaction.TransactionManager;

/**
 *
 * @author Hanfeng Zhu <me@mlnotes.com>
 */
public class TransactionManagerImpl implements TransactionManager {
    private ThreadLocal<Transaction> transaction = new ThreadLocal<>();

    @Override
    public void commit() {
        Transaction tx = transaction.get();
        if(tx != null) {
            tx.commit();
        }
    }
}
