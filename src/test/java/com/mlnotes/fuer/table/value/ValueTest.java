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
package com.mlnotes.fuer.table.value;

import com.mlnotes.fuer.data.Buffer;
import com.mlnotes.fuer.table.value.impl.ValueInt;
import com.mlnotes.fuer.table.value.impl.ValueShort;
import java.io.IOException;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Hanfeng Zhu <me@mlnotes.com>
 */
public class ValueTest {
    private Buffer buffer;
    
    @Before
    public void setUp() {
        buffer = new Buffer(1024);
    }
    
    @Test
    public void testShort() throws IOException {
        ValueShort valueShort = new ValueShort();
        valueShort.setValue((short)-1);
        
        buffer.writeInt(valueShort.getType().getId());
        valueShort.write(buffer);
        
        assertEquals(valueShort, ValueFactory.readValue(buffer));
    }
    
    @Test
    public void testValueInt() throws IOException {
        ValueInt valueInt = new ValueInt();
        valueInt.setValue(-199);
        
        buffer.writeInt(valueInt.getType().getId());
        valueInt.write(buffer);
        
        assertEquals(valueInt, ValueFactory.readValue(buffer));
    }
}
