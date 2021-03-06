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
package com.mlnotes.fuer.core;

import com.mlnotes.fuer.core.impl.DatabaseImpl;
import com.mlnotes.fuer.exception.InvalidDatabaseFileException;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Hanfeng Zhu <me@mlnotes.com>
 */
public class DatabaseTest {
    private Database db;
    
    @Before
    public void setUp() {
        db = new DatabaseImpl("/tmp");
    }
    
    @Test
    public void testCreateFile() throws InvalidDatabaseFileException, IOException {
        db.openFile("/tmp/hanfeng", true);
    }
}
