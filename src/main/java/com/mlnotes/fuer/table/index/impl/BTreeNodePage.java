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

    private static final int MAX_ENTRIES = 1000; // TODO change to a meaningful number

    private int parentId;
    private BTreeEntry[] entries;

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

    /**
     * Split the original page into two pages by the index Entry at the index belongs to the second
     * half
     *
     * @param index
     */
    public void split(int index) {
        BTreeEntry[] firstEntries = new BTreeEntry[index];
        BTreeEntry[] secondEntries = new BTreeEntry[entries.length - index];
        System.arraycopy(entries, 0, firstEntries, 0, firstEntries.length);
        System.arraycopy(entries, index, secondEntries, 0, secondEntries.length);

        // the second half goes to a new page
        int pageId = store.createPage();
        BTreeNodePage page = new BTreeNodePage(pageId, secondEntries);

        // the first half remains in the old page
        entries = firstEntries;

        // a new entry will be added to parent
        BTreeEntry newEntry = new BTreeEntry(secondEntries[0].getKey(), pageId);
        BTreeNodePage parent = (BTreeNodePage) store.findPageById(parentId);
        parent.addEntry(newEntry);
    }

    public void addEntry(BTreeEntry entry) {
        int i;
        for (i = entries.length - 1; i >= 0; --i) {
            if (entry.getKey() == entries[i].getKey()) {
                return;
            } else if (entry.getKey() > entries[i].getKey()) {
                break;
            }
        }
        insertBefore(entry, i);

        if (entries.length > MAX_ENTRIES) {
            split(entries.length / 2);
        }
    }

    public void addKey(int key) {

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
