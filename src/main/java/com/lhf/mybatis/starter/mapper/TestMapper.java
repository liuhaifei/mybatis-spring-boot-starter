package com.lhf.mybatis.starter.mapper;

import com.lhf.mybatis.starter.bean.User;

/**
 * @ClassName TestMapper
 * @Description TODO
 * @Author 刘海飞
 * @Date 2019/3/21 16:24
 * @Version 1.0
 **/
public interface TestMapper {

    User selectByPrimaryKey(Integer userId);
}
