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
    volatile static  SqlSession sqlSession = null;

    public static SqlSession getSqlSession(){
        if(sqlSession == null) {
            synchronized (SqlSessionGet.class) {
                if(sqlSession == null) {
                    try {
                        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
                        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
                        sqlSession = factory.openSession(true);
                        return sqlSession;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return sqlSession;
    }
}
