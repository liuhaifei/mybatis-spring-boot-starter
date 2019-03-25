package com.lhf.mybatis.starter.session;

import com.lhf.mybatis.starter.config.Configuration;
import com.lhf.mybatis.starter.config.MapperRegistory;
import com.lhf.mybatis.starter.executor.Executor;
import com.lhf.mybatis.starter.proxy.MapperProxy;

import java.lang.reflect.Proxy;

/**
 * @ClassName SqlSession
 * @Description TODO
 * @Author 刘海飞
 * @Date 2019/3/21 11:32
 * @Version 1.0
 **/
public class SqlSession {
    private Executor executor;
    private Configuration configuration;

    public SqlSession(Executor executor, Configuration configuration) {
        this.executor = executor;
        this.configuration = configuration;
    }


    public <T> T selectOne(MapperRegistory.MapperData mapperData, String paramter) throws Exception {

        return executor.query(mapperData,paramter);
    }

    public <T> T getMapper(Class<T> mapperInterface){

        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[]{mapperInterface},
                new MapperProxy<>(this,mapperInterface));
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
}
