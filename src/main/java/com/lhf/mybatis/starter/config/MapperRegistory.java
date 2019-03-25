package com.lhf.mybatis.starter.config;


import com.lhf.mybatis.starter.bean.User;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName MapperRegistory
 * @Description TODO
 * @Author 刘海飞
 * @Date 2018/9/4 14:28
 * @Version 1.0
 **/
public class MapperRegistory {

    public static final Map<String,MapperData> methodSqlMapping=new HashMap<>();

    public MapperRegistory() {
        methodSqlMapping.put("com.lhf.mybatis.starter.mapper.TestMapper.selectByPrimaryKey",
                new MapperData("SELECT id,name,age,gender from user where id = %d", User.class));
    }

    public class MapperData<T>{
        private String sql;
        private Class<T> type;

        public MapperData(String sql, Class<T> type) {
            this.sql = sql;
            this.type = type;
        }

        public String getSql() {
            return sql;
        }

        public void setSql(String sql) {
            this.sql = sql;
        }

        public Class<T> getType() {
            return type;
        }

        public void setType(Class<T> type) {
            this.type = type;
        }
    }

    public MapperData get(String nameSpace){
        return methodSqlMapping.get(nameSpace);
    }
}
