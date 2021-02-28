package com.trace.service.user;

import com.trace.api.addrpentity.AddrResult;
import com.trace.api.addrpentity.BaseResult;
import com.trace.api.openid.TencentPosService;
import com.trace.dao.entity.Address;
import com.trace.dao.entity.User;
import com.trace.dao.entity.UserDetail;
import com.trace.dao.repository.AddressMapper;
import com.trace.dao.repository.UserDetailMapper;
import com.trace.dao.repository.UserMapper;
import com.trace.service.converter.DetailConverter;
import com.trace.service.entity.UserBaseMsg;
import com.trace.service.entity.UserLiveLocation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Pattern;

/**
 * @author xzp
 * Created on 2021/2/16
 */
@Service
public class UserInfoService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserDetailMapper detailMapper;

    @Autowired
    DetailConverter converter;

    @Autowired
    TencentPosService posService;

    @Autowired
    AddressMapper addressMapper;

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

    public boolean fillUserInfo(UserBaseMsg msg,Integer userId) {
        // 参数校验
        String phone = msg.getPhone();
        if(!checkPhone(phone)) {
            return false;
        }
        Number temp = msg.getBodyHeat();
        if(!checkTemp(temp)) {
            return false;
        }
        if(msg.getLocation() == null) {
            return false;
        }
        if(msg.getSymptom() == null) {
            return false;
        }

        if(!updateUser(msg.getName(),msg.getIdCard(),userId)) {
            return false;
        }

        UserDetail detail = converter.convertDetail(msg);

        UserLiveLocation location = msg.getLocation();
        if(!this.locationInsert(location,userId,detail.getAddrId())) {
            return false;
        }

        detailMapper.insertSelective(detail);
        return true;
    }
    public boolean locationInsert(UserLiveLocation location,Integer userId,Integer addrId) {
        if(StringUtils.isBlank(location.getCity()) || StringUtils.isBlank(location.getProvince())){
            return false;
        }
        String addrReqStr = location.getProvince() + location.getCity()
                + location.getCounty() + location.getDetailAddr();
        String region = StringUtils.isBlank(location.getCounty()) ? location.getCity() : location.getCounty();
        BaseResult result = posService.descParseAddr(addrReqStr,region);
        AddrResult result1 = result.getResult();
        Address address = new Address();
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+08:00"));
        address.setAdcode(Integer.parseInt(result1.getAd_info().getAdcode()));
        address.setCity(location.getCity());
        address.setCounty(location.getCounty());
        address.setDetail(location.getDetailAddr());
        address.setIdaddress(addrId);
        address.setLat(result1.getLocation().getLat().toString());
        address.setLng(result1.getLocation().getLng().toString());
        address.setProvince(location.getProvince());
        address.setTime(new Date());
        address.setUserId(userId);
        int i = addressMapper.insertSelective(address);
        return i > 0;
    }

    private boolean updateUser(String name,String cardId,Integer userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        user.setName(name);
        final  String IDCARD_EXP = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|" +
                "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";
        if(!Pattern.matches(IDCARD_EXP,cardId)) {
            return false;
        }
        user.setCardId(cardId);
        userMapper.updateByPrimaryKey(user);
        return true;
    }

    private boolean checkPhone(String phone){
        if(StringUtils.isBlank(phone)){
            return false;
        }
        final String REGEX_MOBILE = "((\\+86|0086)?\\s*)((134[0-8]\\d{7})|(((13([0-3]|[5-9]))|(14[5-9])|15([0-3]|[5-9])|(16(2|[5-7]))|17([0-3]|[5-8])|18[0-9]|19(1|[8-9]))\\d{8})|(14(0|1|4)0\\d{7})|(1740([0-5]|[6-9]|[10-12])\\d{7}))";
        if(!Pattern.matches(REGEX_MOBILE,phone)){
            return false;
        }
        return true;
    }

    private boolean checkTemp(Number temp) {
        if(temp == null) {
            return false;
        }
        float a = temp.floatValue();
        if(a > 35 && a < 41) {
            return true;
        }
        return false;
    }
}
