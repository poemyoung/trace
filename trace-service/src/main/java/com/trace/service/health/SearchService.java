package com.trace.service.health;

import com.trace.dao.repository.UserMapper;
import com.trace.service.entity.recentity.ConditionEntity;
import com.trace.service.entity.retentity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author xzp
 * Created on 2021/4/1
 */
@Service
public class SearchService {

    @Autowired
    UserMapper userMapper;

    public List<Person> search(ConditionEntity conditions) {
        if(conditions == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> lists = new ArrayList<>();
        if(conditions.getName() !=null || conditions.getCardId() != null) {
            // 姓名身份证号过滤
            lists.add(this.searchByNameIdCard(conditions.getName(),conditions.getCardId()));
        }
        if(conditions.getSymptom() != null) {

            lists.add(this.searchBySymtom(conditions.getSymptom()));
        }
        if (conditions.getPassPlace() != null ||
        conditions.getStartDate() != null ||
        conditions.getEndDate() != null) {
            lists.add(this.searchByPassPlace(conditions.getPassPlace()
            ,conditions.getStartDate(),conditions.getEndDate()));
        }
        if(conditions.getLivePlace() != null) {
            lists.add(this.searchByLivePlace(conditions.getLivePlace()));
        }
        // 求交集算法
        List<Integer> interSeac = findInterSeac(lists);
        // 数据库反查Person列表
        if(interSeac == null || interSeac.isEmpty()){
            return new ArrayList<>();
        }
        return this.findPersons(interSeac);
    }

    private List<Integer> searchByNameIdCard(String name,String cardId){

        return null;
    }

    private List<Integer> searchBySymtom(boolean symptom) {
        return null;
    }
    private List<Integer> searchByPassPlace(String place, Date startDate,Date endDate) {
        return null;
    }
    private List<Integer> searchByLivePlace(String place) {
        return null;
    }
    private List<Integer> findInterSeac(List<List<Integer>> gather) {

        return null;
    }
    private List<Person> findPersons(List<Integer> list) {
        return null;
    }

}
