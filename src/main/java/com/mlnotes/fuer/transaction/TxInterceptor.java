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
package com.mlnotes.fuer.transaction;

import com.google.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 *
 * @author Hanfeng Zhu <me@mlnotes.com>
 */
public class TxInterceptor implements MethodInterceptor {
    private final List<BeforeCommit> beforeCommits = new LinkedList<>();
    private final List<AfterCommit> afterCommits = new LinkedList<>();
    
    @Inject
    private TransactionManager transactionManager;
    
    public boolean registerBeforeCommit(BeforeCommit bc) {
        return beforeCommits.add(bc);
    }
    
    public boolean unregisterBeforeCommit(BeforeCommit bc) {
        return beforeCommits.remove(bc);
    }
    
    public boolean registerAfterCommit(AfterCommit ac) {
        return afterCommits.add(ac);
    }
    
    public boolean unregisterAfterCommit(AfterCommit ac) {
        return afterCommits.remove(ac);
    }
    
    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        Transactional tx = mi.getMethod().getAnnotation(Transactional.class);
        if(tx == null) {
            return mi.proceed();
        }
        
        for(BeforeCommit bc : beforeCommits) {
            bc.run();
        }
        
        transactionManager.begin();
        Object result = mi.proceed();
        transactionManager.commit();
        
        for(AfterCommit ac : afterCommits) {
            ac.run();
        }
        
        return result;
    }
}

