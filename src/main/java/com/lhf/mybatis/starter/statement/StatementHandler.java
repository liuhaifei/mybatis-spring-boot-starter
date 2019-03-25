package com.lhf.mybatis.starter.statement;


import com.lhf.mybatis.starter.config.Configuration;
import com.lhf.mybatis.starter.config.MapperRegistory;
import com.lhf.mybatis.starter.result.ResultSetHandler;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @ClassName StatementHandler
 * @Description TODO
 * @Author 刘海飞
 * @Date 2018/9/4 14:53
 * @Version 1.0
 **/
public class StatementHandler {

    private final Configuration configuration;
    private final ResultSetHandler resultSetHandler;

    public StatementHandler(Configuration configuration) {
        this.configuration = configuration;
        resultSetHandler = new ResultSetHandler(configuration);
    }

    public <E> E query(MapperRegistory.MapperData mapperData, Object parameter) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        try {
            Connection conn=getConnection();
            PreparedStatement pstmt=conn.prepareStatement(String.format(mapperData.getSql(),
                    Integer.parseInt(String.valueOf(parameter))));
            pstmt.execute();

            return (E)resultSetHandler.handle(pstmt.getResultSet(),mapperData.getType());
        }catch (SQLException e){
            e.printStackTrace();;
        }
        return null;
    }

    public Connection getConnection() {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String username = "root";
        String password = "123456";
        Connection conn = null;
        try {
            Class.forName(driver); //classLoader,加载对应驱动
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
