package com.lhf.mybatis.starter.session;

/**
 * @ClassName ExecutorType
 * @Description 执行器模式
 * @Author 刘海飞
 * @Date 2019/3/21 11:37
 * @Version 1.0
 **/
public enum  ExecutorType {

    //为每个语句的执行创建一个新的预处理语句
    SIMPLE,
    //可以服用预处理语句
    REUSE,
    //这个执行器会批量执行所有更新语句，如果SELECT在它们中间执行还会标定它们是必须的，来保证一个简单并易于理解的行为。
    BATCH;
}
