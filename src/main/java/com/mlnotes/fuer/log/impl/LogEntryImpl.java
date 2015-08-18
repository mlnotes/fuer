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
package com.mlnotes.fuer.log.impl;

import com.mlnotes.fuer.data.Buffer;
import com.mlnotes.fuer.table.Row;
import com.mlnotes.fuer.log.LogEntry;
import com.mlnotes.fuer.log.Operation;
import com.mlnotes.fuer.table.impl.RowImpl;
import java.io.IOException;
import java.util.Objects;

/**
 *
 * @author Hanfeng Zhu <me@mlnotes.com>
 */
public class LogEntryImpl implements LogEntry {

    private int id;
    private int transactionId;
    private Operation operation;
    private Row row;

    public LogEntryImpl(int id, int transactionId,
            Operation operation, Row row) {
        this.id = id;
        this.transactionId = transactionId;
        this.operation = operation;
        this.row = row;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getTransactionId() {
        return transactionId;
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
    public void write(Buffer buffer) throws IOException {
        buffer.writeInt(id);
        buffer.writeInt(transactionId);
        buffer.writeInt(operation.getId());
        row.write(buffer);
    }

    @Override
    public void read(Buffer buffer) throws IOException {
        this.id = buffer.readInt();
        this.transactionId = buffer.readInt();
        this.operation = Operation.fromId(buffer.readInt());
        Row r = new RowImpl(null);
        r.read(buffer);
        this.row = r;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof LogEntryImpl)) {
            return false;
        }

        LogEntryImpl entry = (LogEntryImpl) other;
        if (id != entry.id ||transactionId != entry.transactionId 
                || operation != entry.operation) {
            return false;
        }

        if (row == null && entry.row == null) {
            return true;
        } else if (row == null || entry.row == null) {
            return false;
        }

        return row.equals(entry.row);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.id;
        hash = 67 * hash + this.transactionId;
        hash = 67 * hash + Objects.hashCode(this.operation);
        hash = 67 * hash + Objects.hashCode(this.row);
        return hash;
    }

    @Override
    public boolean checksum() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
