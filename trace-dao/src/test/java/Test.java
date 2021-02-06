import entity.User;
import entity.UserExample;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import repository.UserMapper;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author xzp
 * Created on 2021/2/3
 */

@RunWith(JUnit4.class)
public class Test {
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
    public void test2(){
//        System.out.println(UserInfoMapper.class.getClassLoader().getResource(""));
    }
}
