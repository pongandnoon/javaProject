package com.ouyangjia.springmvc.aop.dao;
/**
 * @Data 2019/03/05
 */
public class DaoImpl implements  Dao{
    @Override
    public void insert() {
        System.out.println("DaoImpl.insert()");
    }

    @Override
    public void delete() {
        System.out.println("DaoImpl.delete()");
    }

    @Override
    public void update() {
        System.out.println("DaoImpl.update()");
    }
}
