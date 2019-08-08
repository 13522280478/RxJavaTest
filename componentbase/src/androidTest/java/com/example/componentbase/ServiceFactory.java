package com.example.componentbase;

import com.example.componentbase.empty_service.EmptyAccountService;
import com.example.componentbase.service.IAccountService;

/**
 * @author YueShuai
 * @date 2019-08-01
 * @Describe <p>
 * Email : yueshuai@pupupula.com
 */
public class ServiceFactory {

    // 单例 私有化构造方法
    private ServiceFactory() {
    }

    private IAccountService accountService;

    /**
     * 通过静态内部类实现serviceFactory的单例
     *
     * @return
     */
    public static ServiceFactory getInstance() {
        return Innter.serviceFactory;
    }

    private static class Innter {
        private static ServiceFactory serviceFactory = new ServiceFactory();
    }


    /**
     * 🉑接收组件实现的Service实例
     *
     * @param accountService
     */
    public void setAccountService(IAccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * 返回Login组件的Service实例
     *
     * @return
     */
    public IAccountService getAccountService() {
        if (accountService == null) {
            accountService = new EmptyAccountService();
        }

        return accountService;
    }
}
