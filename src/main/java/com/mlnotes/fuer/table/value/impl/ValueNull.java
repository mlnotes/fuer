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
package com.mlnotes.fuer.table.value.impl;

import com.mlnotes.fuer.data.Buffer;
import com.mlnotes.fuer.table.value.Value;
import java.io.IOException;

/**
 *
 * @author Hanfeng Zhu <me@mlnotes.com>
 */
public class ValueNull implements Value {

    @Override
    public Type getType() {
        return Type.NULL;
    }

    @Override
    public void write(Buffer buffer) throws IOException {
        
    }

    @Override
    public void read(Buffer buffer) throws IOException {
        
    }
    
}
