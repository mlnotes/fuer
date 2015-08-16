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
