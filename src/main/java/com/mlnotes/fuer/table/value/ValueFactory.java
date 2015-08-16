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
import com.mlnotes.fuer.table.value.Value.Type;
import com.mlnotes.fuer.table.value.impl.ValueByte;
import com.mlnotes.fuer.table.value.impl.ValueChar;
import com.mlnotes.fuer.table.value.impl.ValueDouble;
import com.mlnotes.fuer.table.value.impl.ValueFloat;
import com.mlnotes.fuer.table.value.impl.ValueInt;
import com.mlnotes.fuer.table.value.impl.ValueLong;
import com.mlnotes.fuer.table.value.impl.ValueNull;
import com.mlnotes.fuer.table.value.impl.ValueShort;
import java.io.IOException;

/**
 *
 * @author Hanfeng Zhu <me@mlnotes.com>
 */
public class ValueFactory {
    public static Value readValue(Buffer buffer) throws IOException {
        Type type = Type.fromId(buffer.readInt());
        Value value;
        switch(type) {
            case NULL:
                value = new ValueNull();
                break;
            case BYTE:
                value = new ValueByte();
                break;
            case CHAR:
                value = new ValueChar();
                break;
            case SHORT:
                value = new ValueShort();
                break;
            case INT:
                value = new ValueInt();
                break;
            case LONG:
                value = new ValueLong();
                break;
            case FLOAT:
                value = new ValueFloat();
                break;
            case DOUBLE:
                value = new ValueDouble();
                break;
            default:
                return new ValueNull();
        }
        value.read(buffer);
        return value;
    }
}
