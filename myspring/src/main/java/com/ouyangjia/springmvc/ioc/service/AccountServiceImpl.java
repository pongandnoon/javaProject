package com.ouyangjia.springmvc.ioc.service;

import com.ouyangjia.springmvc.ioc.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
@Service("accountService")
public class AccountServiceImpl implements AccountService{

    /**
     * 需要注入的对象
     */
   // @Resource(name="bankAccountDao")
    @Autowired
    @Qualifier("bankAccountDao")
    private AccountDao accountDao;


    public void doSomething() {
        System.out.println("aacountServiceImpl#doSomething....");
        if(accountDao instanceof AccountDao){
            System.out.println("accountDao instanceof AccountDao:true");
        }
        accountDao.addAccount();
    }

}
