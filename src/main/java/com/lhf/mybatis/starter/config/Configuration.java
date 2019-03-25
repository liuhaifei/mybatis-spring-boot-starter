package com.lhf.mybatis.starter.config;

import lombok.Data;

import java.io.IOException;

/**
 * @ClassName Configuration
 * @Description TODO
 * @Author 刘海飞
 * @Date 2018/9/4 14:22
 * @Version 1.0
 **/
@Data
public class Configuration {

    private String scanPath;

    private MapperRegistory mapperRegistory=new MapperRegistory();

    public Configuration scanPath(String scanPath) {
        this.scanPath = scanPath;
        return this;
    }

    public void bulid() throws IOException{
        if(scanPath==null || scanPath.length()<1){
            throw new RuntimeException("scan path is required.");
        }
    }

    public static void main(String... args) throws IOException{
        new Configuration().scanPath("com/xinho/mybatis/mqppers").bulid();
    }
}
