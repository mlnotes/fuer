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
package com.mlnotes.fuer.storage;

import java.util.Date;

/**
 *
 * @author Hanfeng Zhu <me@mlnotes.com>
 */
public abstract class Page {

    public static final int BTREE_NODE = 1;
    public static final int BTREE_LEAF = 2;
    
    // page capacity in bytes, default is 4K
    public static final int PAGE_CAPACITY = 4*1024;

    protected int id;
    protected int type;
    protected long mtime;
    protected long size;
    protected long checksum;
    protected PageStore store;

    public Page(int type) {
        this.type = type;
        mtime = new Date().getTime();
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getMtime() {
        return mtime;
    }

    public void setMtime(long mtime) {
        this.mtime = mtime;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getChecksum() {
        return checksum;
    }

    public void setChecksum(long checksum) {
        this.checksum = checksum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PageStore getStore() {
        return store;
    }

    public void setStore(PageStore store) {
        this.store = store;
    }

    public abstract long calcChecksum();
}
