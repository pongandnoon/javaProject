package com.ouyangjia.springmvc.ioc.dao;

public class BankAccountDaoImpl implements AccountDao {
    @Override
    public void addAccount() {
        System.out.println("add a bank account...");
    }
}
