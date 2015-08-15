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
public interface Resource {
    public void commit();
    public void rollback();
}
