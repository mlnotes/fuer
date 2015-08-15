/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mlnotes.fuer.transaction;

import com.mlnotes.fuer.log.Log;

/**
 *
 * @author Hanfeng Zhu <me@mlnotes.com>
 */
public interface Transaction {
    public int getId();
    public void commit();
    public void rollback();
    public Status getStatus();
    public boolean apppend(Log log);
    
    enum Status {
        BEGIN,
        PREPARING,
        PREPARED,
        ROLLBACKING,
        ROLLBACKED,
        COMMITING,
        COMMITTED
    }
}
