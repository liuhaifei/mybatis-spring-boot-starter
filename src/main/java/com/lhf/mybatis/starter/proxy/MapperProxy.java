package com.lhf.mybatis.starter.proxy;


import com.lhf.mybatis.starter.config.MapperRegistory;
import com.lhf.mybatis.starter.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/7/2717:49
 */
public class MapperProxy<T> implements InvocationHandler {
    private final SqlSession sqlSession;
    private final Class<T> mapperInterfaces;

    public MapperProxy(SqlSession sqlSession, Class<T> mapperInterfaces) {
        this.sqlSession = sqlSession;
        this.mapperInterfaces = mapperInterfaces;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MapperRegistory.MapperData mapperData= sqlSession
                                            .getConfiguration()
                                            .getMapperRegistory()
                                            .get(method.getDeclaringClass().getName()+"."+method.getName());
        if(mapperData!=null){
            System.out.println(String.format("SQL [ %s ], parameter [%s] ", mapperData.getSql(), args[0]));
            return sqlSession.selectOne(mapperData,String.valueOf(args[0]));
        }
        return method.invoke(proxy,args);
    }
}
