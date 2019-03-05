package com.ouyangjia.springmvc.ioc;

import com.ouyangjia.springmvc.ioc.service.AccountService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IocTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
        AccountService accountService= (AccountService) context.getBean("accountService");
        accountService.doSomething();
    }
}
