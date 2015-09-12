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

import com.mlnotes.fuer.storage.Page;

/**
 *
 * @author Hanfeng Zhu <me@mlnotes.com>
 */
public class BTreeLeafPage extends Page {

    private int prevId;
    private int nextId;
    private int parentId;
    private BTreeData[] data;

    public BTreeLeafPage() {
        super(BTREE_LEAF);
    }

    @Override
    public long calcChecksum() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getPrevId() {
        return prevId;
    }

    public void setPrevId(int prevId) {
        this.prevId = prevId;
    }

    public int getNextId() {
        return nextId;
    }

    public void setNextId(int nextId) {
        this.nextId = nextId;
    }

    public BTreeData findData(int key) {
        for (int i = 0; i < data.length; ++i) {
            if (key == data[i].getKey()) {
                return data[i];
            } else if (key < data[i].getKey()) {
                return null;
            }
        }

        return null;
    }

    public BTreeLeafPage getPrev() {
        return (BTreeLeafPage) store.findPageById(prevId);
    }

    public BTreeLeafPage getNext() {
        return (BTreeLeafPage) store.findPageById(nextId);
    }
}