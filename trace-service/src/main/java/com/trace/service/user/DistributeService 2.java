package com.trace.service.user;

import com.trace.api.addrpentity.Position;
import com.trace.dao.entity.Address;
import com.trace.dao.entity.AddressExample;
import com.trace.dao.repository.AddressMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author xzp
 * Created on 2021/3/19
 */
@Service
public class DistributeService {
    final Logger LOGGER = LoggerFactory.getLogger(DistributeService.class);

    @Autowired
    AddressMapper mapper;

    public List<Position> getPositionsByDay(Calendar d) {
        List<Position> positions = new ArrayList<>();
        Set<Integer> userIdSet  = new HashSet<>();
        AddressExample example = new AddressExample();
        List<Address> addresses = mapper.selectByExample(example);
        // 选出userId 不一样的， 日期在同一天的
        for (Address address : addresses) {
            Date ad = address.getTime();
            // 天数相同
            if(this.daySame(ad,d)) {
                Integer uid = address.getUserId();
                if(!userIdSet.contains(uid)) {
                    try {
                        Number lat = Float.parseFloat(address.getLat());
                        Number lng = Float.parseFloat(address.getLng());
                        Position position = new Position();
                        position.setLat(lat);
                        position.setLng(lng);
                        positions.add(position);
                    }catch (NumberFormatException e) {
                        LOGGER.error("经纬度解析失败:\n" + e.getMessage());
                    }
                }
                userIdSet.add(uid);
            }
        }
       return positions;
    }

    private boolean daySame(Date a,Calendar bC) {
       Calendar aC = Calendar.getInstance();
       aC.setTime(a);
        return aC.get(Calendar.YEAR) == bC.get(Calendar.YEAR)
                && aC.get(Calendar.MONTH) == bC.get(Calendar.MONTH)
                && aC.get(Calendar.DAY_OF_MONTH) == bC.get(Calendar.DAY_OF_MONTH);
    }
}
