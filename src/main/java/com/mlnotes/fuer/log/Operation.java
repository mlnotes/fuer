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
package com.mlnotes.fuer.log;

/**
 *
 * @author Hanfeng Zhu <me@mlnotes.com>
 */
public enum Operation {
    UNKOWN(0),
    INSERT(1),
    DELETE(2),
    UPDATE(3),
    COMMIT(4),
    ROLLBACK(5);
    
    private final int id;
    
    Operation(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    
    public static Operation fromId(int id) {
        for(Operation op : values()) {
            if(op.id == id) {
                return op;
            }
        }
        return Operation.UNKOWN;
    }
}
