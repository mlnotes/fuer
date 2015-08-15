/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mlnotes.fuer.main;

import com.mlnotes.fuer.transaction.Transactional;

/**
 *
 * @author Hanfeng Zhu <me@mlnotes.com>
 */
public class Store {
    @Transactional
    public void save() {
        System.out.println("This is save");
    }
    
}
