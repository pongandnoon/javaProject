package com.ouyangjia.springmvc.aop.proxy.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

public class TimeHandler {
    public void printTime(ProceedingJoinPoint pjp) {
        Signature signature=pjp.getSignature();
        if(signature instanceof MethodSignature){
            MethodSignature methodSignature= (MethodSignature) signature;
            Method method=methodSignature.getMethod();
            System.out.println(method.getName() + "()方法开始时间：" + System.currentTimeMillis());
            try {
                pjp.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }finally {
                System.out.println(method.getName() + "()方法结束时间：" + System.currentTimeMillis());
            }
        }
    }
}
