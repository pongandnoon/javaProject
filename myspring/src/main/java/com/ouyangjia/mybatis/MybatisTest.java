package com.ouyangjia.mybatis;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MybatisTest {
    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
        LoanApplyService loanApplyService= (LoanApplyService) context.getBean("loanApplyService");
        LoanApply loanApply=loanApplyService.getByLoanNo("LA20180717000331");
        System.out.println(loanApply.toString());
    }
}
