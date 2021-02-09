package com.trace.dao;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.defaults.DefaultSqlSession;

import java.io.InputStream;

/**
 * @author xzp
 * Created on 2021/2/7
 */
public class SqlSessionGet {
    public static SqlSession getSqlSession(){
        try {
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession session = factory.openSession(true);
            return session;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
