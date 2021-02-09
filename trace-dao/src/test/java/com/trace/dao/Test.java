package com.trace.dao;

import com.trace.dao.entity.User;
import com.trace.dao.entity.UserExample;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import com.trace.dao.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author xzp
 * Created on 2021/2/3
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class Test {
    @Autowired
    UserMapper mapper;

    @org.junit.Test
    public void test() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = factory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        UserExample example = new UserExample();
        long a = mapper.countByExample(example);
        System.out.println(a);
    }
    @org.junit.Test
    @Rollback(false)
    public void test2(){
        User user = new User();
        user.setId(12333333);
        mapper.insertSelective(user);
    }
}
