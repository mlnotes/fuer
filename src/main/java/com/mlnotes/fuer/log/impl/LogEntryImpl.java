/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mlnotes.fuer.log.impl;

import com.mlnotes.fuer.data.Row;
import com.mlnotes.fuer.log.LogEntry;
import com.mlnotes.fuer.log.Operation;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author Hanfeng Zhu <me@mlnotes.com>
 */
public class LogEntryImpl implements LogEntry {
    private final int id;
    private final Operation operation;
    private final Row row;
    
    public LogEntryImpl(int id, Operation operation, Row row) {
        this.id = id;
        this.operation = operation;
        this.row = row;
    }
    
    @Override
    public int getId() {
        return id;
    }

    @Override
    public Operation getOperation() {
        return operation;
    }

    @Override
    public Row getRow() {
        return row;
    }

    @Override
    public void write(Writer writer) throws IOException {
        writer.write(id);
        writer.write(operation.getId());
        row.write(writer);
    }
}
