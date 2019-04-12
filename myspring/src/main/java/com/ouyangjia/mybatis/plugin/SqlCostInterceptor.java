package com.ouyangjia.mybatis.plugin;


import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.defaults.DefaultSqlSession;

import java.beans.Statement;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Intercepts({@Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class}),
             @Signature(type = StatementHandler.class, method = "update", args = {Statement.class}),
             @Signature(type = StatementHandler.class, method = "batch", args = { Statement.class })})
public class SqlCostInterceptor implements Interceptor {
    public Object intercept(Invocation invocation) throws Throwable {
        long startTime=System.currentTimeMillis();
        Object target=invocation.getTarget();

        StatementHandler statementHandler= (StatementHandler) target;
        try{
            invocation.proceed();
        }finally {
            long endTime=System.currentTimeMillis();
            long sqlCost=endTime-startTime;
            BoundSql boundSql=statementHandler.getBoundSql();
            String sql=boundSql.getSql();
            Object parameterObject=boundSql.getParameterObject();
            List<ParameterMapping> parameterMappingList=boundSql.getParameterMappings();
            // 格式化Sql语句，去除换行符，替换参数
            sql = formatSql(sql, parameterObject, parameterMappingList);
            System.out.println("SQL：[" + sql + "]执行耗时[" + sqlCost + "ms]");
        }
        return null;
    }

    private String formatSql(String sql, Object parameterObject, List<ParameterMapping> parameterMappingList) throws Exception {
        if(sql==null||sql.length()==0){
            return "";
        }
        //
        sql=sql.replaceAll("[\\s\n ]+"," ");
        // 不传参数的场景，直接把Sql美化一下返回出去
        if (parameterObject == null || parameterMappingList == null || parameterMappingList.size() == 0) {
           return sql;
         }
        if(parameterMappingList!=null){
            Class<?> parameterObjectClass=parameterObject.getClass();
            if(isStrictMap(parameterObjectClass)){
                DefaultSqlSession.StrictMap<Collection<?>> strictMap=(DefaultSqlSession.StrictMap<Collection<?>>)parameterObject;
                if(isList(strictMap.get("list").getClass())){
                    sql = handleListParameter(sql, strictMap.get("list"));
                }
            }else if (isMap(parameterObjectClass)) {
                // 如果参数是Map则直接强转，通过map.get(key)方法获取真正的属性值
                // 这里主要是为了处理<insert>、<delete>、<update>、<select>时传入parameterType为map的场景
                Map<?, ?> paramMap = (Map<?, ?>) parameterObject;
                sql = handleMapParameter(sql, paramMap, parameterMappingList);
            } else {
                // 通用场景，比如传的是一个自定义的对象或者八种基本数据类型之一或者String
                sql = handleCommonParameter(sql, parameterMappingList, parameterObjectClass, parameterObject);
            }
        }
        return sql;
    }

    /**
     * 处理参数为List的场景
     */
    private String handleListParameter(String sql, Collection<?> col) {
        if (col != null && col.size() != 0) {
            for (Object obj : col) {
                String value = null;
                Class<?> objClass = obj.getClass();

                // 只处理基本数据类型、基本数据类型的包装类、String这三种
                // 如果是复合类型也是可以的，不过复杂点且这种场景较少，写代码的时候要判断一下要拿到的是复合类型中的哪个属性
                if (isPrimitiveOrPrimitiveWrapper(objClass)) {
                    value = obj.toString();
                } else if (objClass.isAssignableFrom(String.class)) {
                    value = "\"" + obj.toString() + "\"";
                }

                sql = sql.replaceFirst("\\?", value);
            }
        }

        return sql;
    }
    /**
     * 处理参数为Map的场景
     */
    private String handleMapParameter(String sql, Map<?, ?> paramMap, List<ParameterMapping> parameterMappingList) {
        for (ParameterMapping parameterMapping : parameterMappingList) {
            Object propertyName = parameterMapping.getProperty();
            Object propertyValue = paramMap.get(propertyName);
            if (propertyValue != null) {
                if (propertyValue.getClass().isAssignableFrom(String.class)) {
                    propertyValue = "\"" + propertyValue + "\"";
                }

                sql = sql.replaceFirst("\\?", propertyValue.toString());
            }
        }

        return sql;
    }

    /**
     * 处理通用的场景
     */
    private String handleCommonParameter(String sql, List<ParameterMapping> parameterMappingList, Class<?> parameterObjectClass,
                                         Object parameterObject) throws Exception {
        for(ParameterMapping parameterMapping:parameterMappingList){
            String propertyValue=null;
            if(isPrimitiveOrPrimitiveWrapper(parameterObjectClass)){
                propertyValue=parameterObject.toString();
            }else {
                String propertyName=parameterMapping.getProperty();
                Field field=parameterObjectClass.getField(propertyName);
                // 要获取Field中的属性值，这里必须将私有属性的accessible设置为true
                field.setAccessible(true);
                propertyValue = String.valueOf(field.get(parameterObject));
                if (parameterMapping.getJavaType().isAssignableFrom(String.class)) {
                    propertyValue = "\"" + propertyValue + "\"";
                }
            }
            sql = sql.replaceFirst("\\?", propertyValue.toString());
        }
        return sql;
    }

    /**
     * 是否基本数据类型或者基本数据类型的包装类
     */
    private boolean isPrimitiveOrPrimitiveWrapper(Class<?> parameterObjectClass) {
        return parameterObjectClass.isPrimitive() ||
                (parameterObjectClass.isAssignableFrom(Byte.class) || parameterObjectClass.isAssignableFrom(Short.class) ||
                        parameterObjectClass.isAssignableFrom(Integer.class) || parameterObjectClass.isAssignableFrom(Long.class) ||
                        parameterObjectClass.isAssignableFrom(Double.class) || parameterObjectClass.isAssignableFrom(Float.class) ||
                        parameterObjectClass.isAssignableFrom(Character.class) || parameterObjectClass.isAssignableFrom(Boolean.class));
    }

    private boolean isList(Class<?> clazz) {
        Class<?>[] interfaceClasses=clazz.getInterfaces();
        for(Class<?> calss:interfaceClasses){
            if(calss.isAssignableFrom(List.class)){
                return true;
            }
        }
        return false;
    }
    /**
     * 是否Map的实现类
     */
    private boolean isMap(Class<?> parameterObjectClass) {
        Class<?>[] interfaceClasses = parameterObjectClass.getInterfaces();
        for (Class<?> interfaceClass : interfaceClasses) {
            if (interfaceClass.isAssignableFrom(Map.class)) {
                return true;
            }
        }

        return false;
    }

    private boolean isStrictMap(Class<?> parameterObjectClass) {
        return parameterObjectClass.isAssignableFrom(DefaultSqlSession.StrictMap.class);
    }

    //Plugin的wrap方法会取@Intercepts与@Signature这两个注解里面参数
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    //可以将一些配置属性配置在<plugin></plugin>的子标签<property />中，所有的配置属性会在形参Properties中，setProperties方法可以拿到配置的属性进行需要的处理
    public void setProperties(Properties properties) {

    }
}
