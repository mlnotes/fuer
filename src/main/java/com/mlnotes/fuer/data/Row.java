/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mlnotes.fuer.data;

import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author Hanfeng Zhu <me@mlnotes.com>
 */
public interface Row {
    public void write(Writer writer)  throws IOException ;
}
