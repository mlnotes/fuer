/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mlnotes.fuer.transaction;

import java.util.LinkedList;
import java.util.List;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 *
 * @author zhf
 */
public class TxInterceptor implements MethodInterceptor {
    private final List<BeforeCommit> beforeCommits = new LinkedList<>();
    private final List<AfterCommit> afterCommits = new LinkedList<>();
    
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
        
        Object result = mi.proceed();
        
        for(AfterCommit ac : afterCommits) {
            ac.run();
        }
        
        return result;
    }
}
