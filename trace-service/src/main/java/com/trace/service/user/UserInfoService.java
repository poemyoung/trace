package com.trace.service.user;

import com.trace.dao.entity.User;
import com.trace.dao.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xzp
 * Created on 2021/2/16
 */
@Service
public class UserInfoService {

    @Autowired
    UserMapper userMapper;

    public boolean isInfoAccessible(int userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        // 两次请求太快，数据库来不及插入就发起了第二次请求
        if (user == null) {
            return false;
        }
        if (user.getCardId() == null || user.getName() == null) {
            // 个人信息未完善
            return false;
        }else {
            return true;
        }
    }
}