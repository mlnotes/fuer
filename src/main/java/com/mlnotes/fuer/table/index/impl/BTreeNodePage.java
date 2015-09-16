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
package com.mlnotes.fuer.table.index.impl;

/**
 *
 * @author Hanfeng Zhu <me@mlnotes.com>
 */
public class BTreeNodePage extends BTreePage<BTreeEntry> {

    public BTreeNodePage() {
        super(BTREE_NODE);
    }

    public BTreeNodePage(int id) {
        super(BTREE_NODE);
        this.id = id;
    }

    public BTreeNodePage(int id, BTreeEntry[] entries) {
        super(BTREE_NODE);
        this.id = id;
        this.entries = entries;
    }

    @Override
    public long calcChecksum() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isOutOfPage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
