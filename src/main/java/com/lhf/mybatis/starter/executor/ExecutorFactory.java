package com.lhf.mybatis.starter.executor;


import com.lhf.mybatis.starter.config.Configuration;

/**
 * @ClassName ExecutorFactory
 * @Description TODO
 * @Author 刘海飞
 * @Date 2018/9/4 16:40
 * @Version 1.0
 **/
public class ExecutorFactory {

    private static final String SIMPLE="SIMPLE";
    private static final String REUSE="REUSE";
    /**
     * @Description: 默认方式
     * @param configuration
     * @author lhf
     * @return
     * @date 2018/9/4 16:42
     */
    public static Executor DEFAULT(Configuration configuration){return get(SIMPLE,configuration);}

    public static Executor get(String key, Configuration configuration) {
        if(SIMPLE.equalsIgnoreCase(key)){
            return new SimpleExecutor(configuration);
        }
        if(REUSE.equalsIgnoreCase(key)){
            return new CachingExecutor(new SimpleExecutor(configuration));
        }
        throw new RuntimeException("no executor found");
    }

}
