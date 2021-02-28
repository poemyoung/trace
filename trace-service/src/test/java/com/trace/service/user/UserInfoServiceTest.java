package com.trace.service.user;

import com.trace.service.entity.UserLiveLocation;
import com.trace.user.SpringConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author xzp
 * Created on 2021/2/28
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class UserInfoServiceTest {
    UserInfoService service =new UserInfoService();

    @Test
    public void doTest() {
        UserLiveLocation location = new UserLiveLocation();
        location.setProvince("四川");
        location.setCity("成都");
        location.setCounty("双流区");
        location.setDetailAddr("四川大学(江安校区)");
        service.locationInsert(location,1505084195,1024190809);
    }
}