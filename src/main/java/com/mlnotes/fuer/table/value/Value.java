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
import java.io.IOException;

/**
 *
 * @author Hanfeng Zhu <me@mlnotes.com>
 */
public interface Value {

    public Type getType();

    // write data without type
    public void write(Buffer buffer) throws IOException;

    // read data without type
    public void read(Buffer buffer) throws IOException;

    enum Type {

        NULL(0),
        SHORT(1),
        INT(2),
        LONG(3),
        FLOAT(4),
        DOUBLE(5),
        BYTE(6),
        CHAR(7);

        private final int id;

        Type(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public static Type fromId(int id) {
            for (Type t : values()) {
                if (t.id == id) {
                    return t;
                }
            }
            return NULL;
        }
    }
}
