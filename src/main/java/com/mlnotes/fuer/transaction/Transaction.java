/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mlnotes.fuer.transaction;

/**
 *
 * @author Hanfeng Zhu <me@mlnotes.com>
 */
public interface Transaction {
    public void start();
    public void commit();
    public void rollback();
    public Status getStatus();
    public boolean addResource(Resource res);
    public boolean removeResource(Resource res);
    
    enum Status {
        PREPARING,
        PREPARED,
        ROLLBACKING,
        ROLLBACKED,
        COMMITING,
        COMMITTED
    }
}
