/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mlnotes.fuer.main;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.matcher.Matchers;
import com.mlnotes.fuer.transaction.TxInterceptor;

/**
 *
 * @author Hanfeng Zhu <me@mlnotes.com>
 */
public class Main {
    
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new Module(){
            @Override
            public void configure(Binder binder) {
                binder.bindInterceptor(Matchers.any(),
                        Matchers.any(),
                        new TxInterceptor());
            }
        });
        
        Store store = injector.getInstance(Store.class);
        store.save();
    }
}
