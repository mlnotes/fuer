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
import java.lang.reflect.Array;

/**
 *
 * @author Hanfeng Zhu <me@mlnotes.com>
 * @param <T>
 */
public abstract class BTreePage<T extends BTreeKey> extends Page {

    protected int parentId;
    protected T[] entries;

    public BTreePage(int type) {
        super(type);
    }

    /**
     * Create a new array which contains one more val before [pos]
     *
     * @param pos
     * @param val
     */
    protected void insertAt(int pos, T val) {
        T[] newArray = (T[]) Array.newInstance(entries.getClass(), entries.length + 1);

        System.arraycopy(entries, 0, newArray, 0, pos);
        System.arraycopy(entries, pos, newArray, pos + 1, entries.length - pos);
        newArray[pos] = val;

        entries = newArray;
    }

    protected void split(int pos) {
        T[] firstArray = (T[]) Array.newInstance(entries.getClass(), pos - 1);
        T[] secondArray = (T[]) Array.newInstance(entries.getClass(), entries.length - pos);
        System.arraycopy(entries, 0, firstArray, 0, pos - 1);
        System.arraycopy(entries, pos + 1, secondArray, 0, secondArray.length);

        entries = firstArray;

        int newPageId = store.createPage();
        BTreePage second = (BTreePage) store.findPageById(newPageId);
        second.entries = secondArray;

        BTreePage parent = (BTreePage) store.findPageById(parentId);
        parent.addEntry(new BTreeEntry(entries[pos].getKey(), newPageId));
    }

    public void addEntry(T entry) {
        int i = entries.length - 1;
        for (; i >= 0; --i) {
            if (entry.getKey() == entries[i].getKey()) {
                return;
            } else if (entry.getKey() > entries[i].getKey()) {
                break;
            }
        }
        insertAt(i + 1, entry);

        if (isOutOfPage()) {
            split(entries.length / 2);
        }
    }

    public int getUsedSize() {
        int used = SIZE_INT; // size of parentId
        if (entries != null && entries.length > 0) {
            used += entries.length * entries[0].getSize();
        }

        return used;
    }

    /**
     * Whether the current reocrds is too much to store in a single page
     *
     * @return
     */
    public boolean isOutOfPage() {
        return getUsedSize() > PAGE_CAPACITY;
    }
}
