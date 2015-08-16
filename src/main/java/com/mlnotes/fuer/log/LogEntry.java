/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mlnotes.fuer.log;

import com.mlnotes.fuer.data.Row;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author Hanfeng Zhu <me@mlnotes.com>
 */
public interface LogEntry {
    public int getId();
    public Operation getOperation();
    public Row getRow();
    public void write(Writer writer)  throws IOException;
}
