package com.ouyangjia.springmvc.aop.proxy.jdk;

import com.ouyangjia.springmvc.aop.dao.Dao;
import com.ouyangjia.springmvc.aop.dao.DaoImpl;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理的实现——整个设计都是针对接口做的代理，如果是普通的类，我们无法通过这个方式代理对象
 * @Data 2019/03/05
 */
public class LogInvocationHandler implements InvocationHandler {
    private Object obj;

    public LogInvocationHandler(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName=method.getName();
        if("insert".equals(methodName)||"update".equals(methodName)){
            System.out.println(methodName + "()方法开始时间：" + System.currentTimeMillis());
            method.invoke(obj,args);
            System.out.println(methodName + "()方法结束时间：" + System.currentTimeMillis());
        }else {
            method.invoke(obj,args);
        }
        return null;
    }
    //原理：
    //1、拿到被代理对象的引用，并且获取到它的所有的接口，反射获取
    //2、JDK Proxy类重新生成一个新的类、同时新的类要实现被代理类所有实现的所有的接口
    //3、动态生成Java代码，把新加的业务逻辑方法由一定的逻辑代码去调用（在代码中体现）
    //4、编译新生成的Java代码.class
    //5、再重新加载到JVM中运行
    //以上这个过程就叫字节码重组
    public static void main(String[] args) {
        Dao daoImpl=new DaoImpl();
        //AOP的代理类是如何生成的--------字节码重组
        Dao proxyDao= (Dao) Proxy.newProxyInstance(DaoImpl.class.getClassLoader(), new Class<?>[]{Dao.class},new LogInvocationHandler(daoImpl));

        proxyDao.insert();
        System.out.println("----------分割线----------");
        proxyDao.delete();
        System.out.println("----------分割线----------");
        proxyDao.update();

        //生成代理类的class文件
        byte[] bytes=ProxyGenerator.generateProxyClass("$Proxy0",new Class<?>[]{proxyDao.getClass()});
        FileOutputStream os=null;
        try {
            os=new FileOutputStream("D://ideaProjects//javaProject//myspring//src//main//java//com//ouyangjia//springmvc//aop//proxy//$Proxy0.class");
            os.write(bytes);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //代码类代码
//        public final void method() throws  {
//            try {
//                //super.h就是保存在父类Proxy中的InvocationHandler对象
//                super.h.invoke(this, m3, (Object[])null);
//            } catch (RuntimeException | Error var2) {
//                throw var2;
//            } catch (Throwable var3) {
//                throw new UndeclaredThrowableException(var3);
//            }
//        }

    }
}
