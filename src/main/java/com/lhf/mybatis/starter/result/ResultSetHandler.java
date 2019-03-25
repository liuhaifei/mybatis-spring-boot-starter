package com.lhf.mybatis.starter.result;

import com.lhf.mybatis.starter.config.Configuration;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @ClassName ResultSetHandler
 * @Description TODO
 * @Author 刘海飞
 * @Date 2019/3/21 11:57
 * @Version 1.0
 **/
public class ResultSetHandler {

    private final Configuration configuration;

    public ResultSetHandler(Configuration configuration) {
        this.configuration = configuration;
    }

    public <E> E handle(ResultSet rs, Class type) throws SQLException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Object resultObject=new DefaultObjectFactory().create(type);
        if(rs.next()){
            int i=0;
            for(Field field:resultObject.getClass().getDeclaredFields()){
                setValue(resultObject,field,rs,i);
            }
        }
        return (E)resultObject;
    }

    private void setValue(Object resultObject, Field field, ResultSet rs, int i) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, SQLException {
        Method setMethod=resultObject.getClass().getMethod("set"+upperCapital(field.getName()),field.getType());

        setMethod.invoke(resultObject,getResult(field,rs));
    }

    private Object getResult(Field field, ResultSet rs) throws SQLException {
        Class<?> type=field.getType();
        if(Integer.class==type){
            try {
                return rs.getInt(field.getName());
            } catch (SQLException e) {
                if ("S0022".endsWith(e.getSQLState())){
                    return null;
                }else{
                    throw new SQLException(e);
                }
            }
        }
        if(String.class==type){
            try {
                return rs.getString(field.getName());
            } catch (SQLException e) {
                if ("S0022".endsWith(e.getSQLState())){
                    return null;
                }else{
                    throw new SQLException(e);
                }
            }
        }
        if(Double.class==type){
            try {
                return rs.getDouble(field.getName());
            } catch (SQLException e) {
                if ("S0022".endsWith(e.getSQLState())){
                    return null;
                }else{
                    throw new SQLException(e);
                }
            }
        }
        return rs.getString(field.getName());
    }

    private String upperCapital(String name) {
        String first=name.substring(0,1);
        String tail=name.substring(1);

        return first.toUpperCase()+tail;
    }
}
