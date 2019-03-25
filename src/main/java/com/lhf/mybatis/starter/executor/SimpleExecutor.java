package com.lhf.mybatis.starter.executor;


import com.lhf.mybatis.starter.config.Configuration;
import com.lhf.mybatis.starter.config.MapperRegistory;
import com.lhf.mybatis.starter.statement.StatementHandler;

/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/7/2717:39
 */
public class SimpleExecutor implements Executor {

    private Configuration configuration;

    public SimpleExecutor(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <E> E query(MapperRegistory.MapperData mapperData, String paramter) throws Exception {
        //初始化 statementHandler->ParamterHandler->ResultSetHandler
        StatementHandler handler=new StatementHandler(configuration);
        return (E)handler.query(mapperData,paramter);
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
}
