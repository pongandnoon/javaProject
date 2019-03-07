package com.ouyangjia.springmvc.aop.proxy.cglib;

import com.ouyangjia.springmvc.aop.dao.Dao;
import com.ouyangjia.springmvc.aop.dao.DaoImpl;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLIB代理实现
 * @Data 2019/03/07
 */
public class DaoProxy implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        String methodName=method.getName();
        if ("insert".equals(methodName) || "update".equals(methodName)) {
            System.out.println(methodName + "()方法开始时间：" + System.currentTimeMillis());
            methodProxy.invokeSuper(o,objects);
            System.out.println(methodName + "()方法结束时间：" + System.currentTimeMillis());
        }else {
            methodProxy.invokeSuper(o,objects);
        }
        return o;
    }

    public static void main(String[] args) {
        DaoProxy daoProxy = new DaoProxy();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(DaoImpl.class);
        enhancer.setCallback(daoProxy);

        Dao dao = (Dao) enhancer.create();
        dao.insert();
        System.out.println("----------分割线----------");
        dao.delete();
        System.out.println("----------分割线----------");
        dao.update();
    }
}
