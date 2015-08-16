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
package com.mlnotes.fuer.table;

import com.mlnotes.fuer.data.Buffer;
import com.mlnotes.fuer.table.impl.RowImpl;
import com.mlnotes.fuer.table.value.Value;
import com.mlnotes.fuer.table.value.impl.ValueChar;
import com.mlnotes.fuer.table.value.impl.ValueInt;
import com.mlnotes.fuer.table.value.impl.ValueLong;
import java.io.IOException;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Hanfeng Zhu <me@mlnotes.com>
 */
public class RowTest {
    private Buffer buffer;
    
    @Before
    public void setUp() {
        buffer = new Buffer(1024);
    }
    
    @Test
    public void testRowSerialize() throws IOException {
        Value[] values = new Value[3];
        values[0] = new ValueChar();
        ((ValueChar)values[0]).setValue('x');
        
        values[1] = new ValueInt();
        ((ValueInt)values[1]).setValue(1234);
        
        values[2] = new ValueLong();
        ((ValueLong)values[2]).setValue(-1L);
        
        Row row = new RowImpl(values);
        row.write(buffer);
        
        Row actual = new RowImpl(null);
        actual.read(buffer);
        
        assertEquals(row, actual);
    }
}
