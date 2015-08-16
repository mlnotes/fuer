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
package com.mlnotes.fuer.table.impl;

import com.mlnotes.fuer.data.Buffer;
import com.mlnotes.fuer.table.Row;
import com.mlnotes.fuer.table.value.Value;
import com.mlnotes.fuer.table.value.ValueFactory;
import java.io.IOException;
import java.util.Objects;

/**
 *
 * @author Hanfeng Zhu <me@mlnotes.com>
 */
public class RowImpl implements Row {

    Value[] values;

    public RowImpl(Value[] values){
        this.values = values;
    }
    
    @Override
    public void write(Buffer buffer) throws IOException {
        buffer.writeInt(values.length);
        for (Value v : values) {
            buffer.writeInt(v.getType().getId());
            v.write(buffer);
        }
    }

    @Override
    public void read(Buffer buffer) throws IOException {
        int size = buffer.readInt();
        values = new Value[size];
        for (int i = 0; i < size; ++i) {
            values[i] = ValueFactory.readValue(buffer);
        }
    }
    
    @Override
    public void setValue(int i, Value value) {
        values[i] = value;
    }
    
    @Override
    public Value getValue(int i) {
        return values[i];
    }
    
    @Override
    public boolean equals(Object other) {
        if(!(other instanceof RowImpl)) {
            return false;
        }
        
        RowImpl row = (RowImpl)other;
        if(null == values && null == row.values) {
            return true;
        }
        
        if(null == values || null == row.values
                || values.length != row.values.length) {
            return false;
        }
        
        for(int i = 0; i < values.length; ++i) {
            if(!values[i].equals(row.values[i])){
                return false;
            }
        }
        
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        // TODO how Objects.hashCode() works for array ?
        hash = 59 * hash + Objects.hashCode(this.values);
        return hash;
    }
}
