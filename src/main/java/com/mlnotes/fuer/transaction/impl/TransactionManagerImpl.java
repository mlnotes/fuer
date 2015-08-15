/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mlnotes.fuer.transaction.impl;

import com.mlnotes.fuer.transaction.Transaction;
import com.mlnotes.fuer.transaction.TransactionManager;
import java.util.BitSet;

/**
 *
 * @author Hanfeng Zhu <me@mlnotes.com>
 */
public class TransactionManagerImpl implements TransactionManager {
    // TODO What if there are multi processes ?
    private final ThreadLocal<Transaction> transaction = new ThreadLocal<>();
    private final BitSet openedTransactions = new BitSet();

    @Override
    public Transaction begin() {
        Transaction tx = transaction.get();
        if(tx == null) {
            // TODO not so clear how nextClearBit works
            int txId = openedTransactions.nextClearBit(1);
            tx = new TransactionImpl(txId);
            openedTransactions.set(txId);
            transaction.set(tx);
        }
        return tx;
    }
    
    @Override
    public void commit() {
        Transaction tx = transaction.get();
        if(tx != null) {
            tx.commit();
            transaction.remove();
        }
    }

    @Override
    public Transaction getTransaction() {
        Transaction tx = transaction.get();
        return tx;
    }
}
