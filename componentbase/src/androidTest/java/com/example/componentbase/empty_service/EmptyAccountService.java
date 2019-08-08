package com.example.componentbase.empty_service;

import com.example.componentbase.service.IAccountService;

import org.jetbrains.annotations.NotNull;

/**
 * @author YueShuai
 * @date 2019-08-01
 * @Describe <p>
 * Email : yueshuai@pupupula.com
 */
public class EmptyAccountService implements IAccountService {



    @Override
    public boolean isLogin() {
       return false;
    }

    @NotNull
    @Override
    public String getAccountId() {
        return null;
    }

    public void test(){
//        ServiceFactory serviceFactory = ServiceFactory;
    }
}
