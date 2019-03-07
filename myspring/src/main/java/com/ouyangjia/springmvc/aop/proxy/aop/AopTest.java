package com.ouyangjia.springmvc.aop.proxy.aop;

import com.ouyangjia.springmvc.aop.dao.Dao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("/applicationContext.xml");
        Dao dao= (Dao) applicationContext.getBean("daoImpl");
        dao.insert();
        System.out.println("----------分割线----------");
        dao.delete();
        System.out.println("----------分割线----------");
        dao.update();
    }
}
