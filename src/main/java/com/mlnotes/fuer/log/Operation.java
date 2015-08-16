/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mlnotes.fuer.log;

/**
 *
 * @author Hanfeng Zhu <me@mlnotes.com>
 */
public enum Operation {
    INSERT(0),
    DELETE(1),
    UPDATE(2);
    
    private final int id;
    
    Operation(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
