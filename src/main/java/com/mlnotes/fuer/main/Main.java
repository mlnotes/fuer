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
package com.mlnotes.fuer.main;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.matcher.Matchers;
import com.mlnotes.fuer.transaction.TransactionManager;
import com.mlnotes.fuer.transaction.Transactional;
import com.mlnotes.fuer.transaction.TxInterceptor;
import com.mlnotes.fuer.transaction.impl.TransactionManagerImpl;

/**
 *
 * @author Hanfeng Zhu <me@mlnotes.com>
 */
public class Main {
    
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new Module(){
            @Override
            public void configure(Binder binder) {
                binder.bind(TransactionManager.class).to(TransactionManagerImpl.class);
                
                TxInterceptor interceptor = new TxInterceptor();
                binder.requestInjection(interceptor);
                binder.bindInterceptor(Matchers.any(),
                        Matchers.annotatedWith(Transactional.class),
                        interceptor);
            }
        });
        
        Store store = injector.getInstance(Store.class);
        store.save();
    }
}
