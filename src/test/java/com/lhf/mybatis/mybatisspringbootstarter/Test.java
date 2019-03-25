package com.lhf.mybatis.mybatisspringbootstarter;

import com.lhf.mybatis.starter.bean.User;
import com.lhf.mybatis.starter.config.Configuration;
import com.lhf.mybatis.starter.executor.ExecutorFactory;
import com.lhf.mybatis.starter.mapper.TestMapper;
import com.lhf.mybatis.starter.session.ExecutorType;
import com.lhf.mybatis.starter.session.SqlSession;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import java.io.IOException;

/**
 * @ClassName Test
 * @Description TODO
 * @Author 刘海飞
 * @Date 2019/3/21 16:22
 * @Version 1.0
 **/
public class Test {

    public static void main(String[] args) throws IOException {
        Configuration configuration=new Configuration();
        configuration.setScanPath("com.lhf.mybatis.starter.mapper");

            configuration.bulid();

        SqlSession sqlSession=new SqlSession(ExecutorFactory.get(ExecutorType.REUSE.name(),configuration)
                ,configuration);
        TestMapper testMapper=sqlSession.getMapper(TestMapper.class);
        long start=System.currentTimeMillis();
        User test=testMapper.selectByPrimaryKey(1);
        System.out.println(test);
        System.out.println("cost:"+(System.currentTimeMillis()-start));
    }
}
