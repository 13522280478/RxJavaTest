package com.example.componentbase.service;

/**
 * @author YueShuai
 * @date 2019-08-01
 * @Describe <p>
 * Email : yueshuai@pupupula.com
 */
public interface IAccountService {
    /**
     * 是否已经登录
     * @return
     */
    boolean isLogin();

    /**
     * 获取登录用户的 AccountId
     * @return
     */
    String getAccountId();

}
