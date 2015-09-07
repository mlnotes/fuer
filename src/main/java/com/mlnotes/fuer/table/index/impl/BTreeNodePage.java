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
public class BTreeNodePage extends Page {

    private BTreeEntry[] entries;

    public BTreeNodePage() {
        super(BTREE_NODE);
    }

    @Override
    public long calcChecksum() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Split the original page into two pages by the key
     *
     * @param key
     * @return BTreeNodePage generated new page
     */
    public BTreeNodePage split(int key) {
        if(key < entries[0].getKey()) {
            
        }
        // TODO split page
        return null;
    }

    public BTreeNodePage merge(BTreeNodePage prev, BTreeNodePage next) {
        // TODO what if there are too many entries ?

        return null;
    }

    public void addEntry(int key) {
        if (key < entries[0].getKey()) {
            BTreeNodePage page = findBTreeNodePage(entries[0].getChildId()).split(key);
            insertBefore(new BTreeEntry(key, page.getId()), 0);
        } else if (key >= entries[entries.length - 1].getKey()) {
            BTreeNodePage page = findBTreeNodePage(entries[entries.length - 1].getChildId()).split(key);
            insertBefore(new BTreeEntry(key, page.getId()), entries.length);
        } else {
            // split two pages and merge the generated two pages
            for (int i = 0; i < entries.length - 1; ++i) {
                if (key >= entries[i].getKey() && key < entries[i + 1].getKey()) {
                    BTreeNodePage prev = findBTreeNodePage(entries[i].getChildId()).split(key);
                    BTreeNodePage next = findBTreeNodePage(entries[i+1].getChildId()).split(key);
                    BTreeNodePage page = merge(prev, next);
                    insertBefore(new BTreeEntry(key, page.getId()), i+1);
                    break;
                }
            }
        }
    }

    public void removeEntry(int key) {
        // TODO remove entry
    }

    /**
     * Insert an entry before pos in entries
     *
     * @param entry
     * @param pos
     */
    private void insertBefore(BTreeEntry entry, int pos) {
        BTreeEntry[] newEntries = new BTreeEntry[entries.length + 1];
        int length = entries.length;

        System.arraycopy(entries, 0, newEntries, 0, pos);
        System.arraycopy(entries, pos, newEntries, pos + 1, length - pos);

        newEntries[pos] = entry;
        entries = newEntries;
    }

    public BTreeLeafPage findFirstLeafPage() {
        Page page = store.findPageById(entries[0].getChildId());
        if (page instanceof BTreeLeafPage) {
            return (BTreeLeafPage) page;
        } else {
            return ((BTreeNodePage) page).findFirstLeafPage();
        }
    }

    public BTreeLeafPage findLastLeafPage() {
        Page page = store.findPageById(entries[entries.length - 1].getChildId());
        if (page instanceof BTreeLeafPage) {
            return (BTreeLeafPage) page;
        } else {
            return ((BTreeNodePage) page).findLastLeafPage();
        }
    }

    public BTreeLeafPage findBTreeLeafPage(int key) {
        for (int i = entries.length - 1; i >= 0; --i) {
            if (key >= entries[i].getKey()) {
                return (BTreeLeafPage) store.findPageById(entries[i].getChildId());
            }
        }

        return null;
    }

    public BTreeData findData(int key) {
        BTreeLeafPage leaf = findBTreeLeafPage(key);
        return leaf == null ? null : leaf.findData(key);
    }

    public BTreeNodePage findBTreeNodePage(int id) {
        return (BTreeNodePage) store.findPageById(id);
    }
}
