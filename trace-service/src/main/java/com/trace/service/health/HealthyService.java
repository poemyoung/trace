package com.trace.service.health;

import com.trace.dao.entity.User;
import com.trace.dao.entity.UserAndAddr;
import com.trace.dao.entity.UserAndAddrExample;
import com.trace.dao.entity.UserDetail;
import com.trace.dao.repository.AddressMapper;
import com.trace.dao.repository.UserAndAddrMapper;
import com.trace.dao.repository.UserDetailMapper;
import com.trace.dao.repository.UserMapper;
import com.trace.service.entity.retentity.AddrRetEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author xzp
 * Created on 2021/3/8
 */


@Service
public class HealthyService {

    @Autowired
    UserAndAddrMapper uaaMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserDetailMapper detailMapper;

    @Autowired
    AddressMapper addressMapper;


    public int howHealth(Integer userId) {
        // 查数据库判断健康状况 1 2 3 4
        // 1是绿色健康码 => 最近一次报备时间 <= 14天，且报备时无症状
        // 2是蓝色健康码 => 最近一次报备时间 > 14天，且报备时无症状
        // 3黄色 => 有症状但管理员未标记风险
        // 4红色 => 有症状且管理员已标记风险
        boolean f1 = this.isSymptom(userId);
        boolean f2 = this.isMarked(userId);
        boolean f3 = this.isLongNoUp(userId);
        if(!f1 && !f3 && !f2) {
            return 1;
        }else if(f3 && !f1 && !f2) {
            return 2;
        }else if(!f2) {
            return 3;
        }else {
            return 4;
        }
    }

    private boolean isSymptom(Integer userId) {
        // 判断报备症状
        UserDetail detail = detailMapper.selectByPrimaryKey(userId);
        if(detail == null) {
            return false;
        }
        Integer riskFlag = detail.getRiskFlag();
        if(riskFlag == null || riskFlag == 0) {
            return false;
        }else {
            return true;
        }
    }
    private boolean isMarked(Integer userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        if(user == null) {
            return false;
        }
        Integer userType = user.getUserType();
        if(userType == null || userType == 0) {
            return false;
        }else {
            return true;
        }
    }
    private boolean isLongNoUp(Integer userId) {
        // 选出地址信息
        List<AddrRetEntity> list = new LinkedList<>();
        UserAndAddrExample example = new UserAndAddrExample();
        example.createCriteria().andUidEqualTo(userId);
        List<UserAndAddr> userAndAddrs = uaaMapper.selectByExample(example);
        if(userAndAddrs == null || userAndAddrs.size() == 0){
            return false;
        }
        UserAndAddr max = Collections.max(userAndAddrs, new Comparator<UserAndAddr>() {
            @Override
            public int compare(UserAndAddr o1, UserAndAddr o2) {
                if (o1 == null && o2 != null) {
                    return -1;
                }
                if (o1 != null && o2 == null) {
                    return 1;
                }
                if (o1 == null) {
                    return 0;
                }
                if (o1.getTime().before(o2.getTime())) {
                    return -1;
                } else if (o2.getTime().before(o1.getTime())) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        Date time = max.getTime();
        long last = time.getTime();
        long now = new Date().getTime();
        return (now - last) >= 14 * 24 * 60 * 60 * 1000;
    }
}
