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

import com.mlnotes.fuer.table.Column;
import com.mlnotes.fuer.table.value.Value;

/**
 *
 * @author Hanfeng Zhu <me@mlnotes.com>
 */
public class ColumnImpl implements Column {
    private Value value;
    private boolean nullable;
    
    @Override
    public boolean isNullable() {
        return nullable;
    }

    @Override
    public Value getValue() {
        return value;
    }
}
