package com.ouyangjia.springmvc.aop.dao;

/**
 * 装饰者模式写法  核心：实现Dao接口并持有Dao接口的引用
 *
 * 缺点：
 * 1.输出日志的逻辑还是无法复用
 * 2.输出日志的逻辑与代码有耦合，如果我要对delete()方法前后同样输出时间，需要修改LogDao
 * @Data 2019/03/05
 */
public class LogDao implements Dao{
    private Dao dao;

    public LogDao(Dao dao) {
        this.dao = dao;
    }

    @Override
    public void insert() {
        System.out.println("before insert......");
        dao.insert();
        System.out.println("after insert......");
    }

    @Override
    public void delete() {
        dao.delete();
    }

    @Override
    public void update() {
        System.out.println("before update......");
        dao.update();
        System.out.println("after update......");
    }

    public static void main(String[] args) {
        Dao dao=new LogDao(new DaoImpl());
        dao.insert();
    }
}
