package com.trace.service.test;

import com.trace.dao.entity.TestMysqlUsable;
import com.trace.dao.entity.TestMysqlUsableExample;
import com.trace.dao.repository.TestMysqlUsableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

/**
 * @author xzp
 * Created on 2021/2/26
 */
@Service
public class TestMysqlUsableService {
    @Autowired
    TestMysqlUsableMapper mapper;
    String sData = Calendar.getInstance().get(Calendar.MONTH) + "月" +
            Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "日";
    Integer iData = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);

    public boolean isMysqlAvaInsert() {
        TestMysqlUsable usable = new TestMysqlUsable();
        usable.setDataVarchar(sData);
        usable.setDataInt(iData);
        mapper.insert(usable);
        TestMysqlUsableExample example = new TestMysqlUsableExample();
        TestMysqlUsableExample.Criteria criteria = example.createCriteria();
        criteria.andDataIntIsNotNull().andDataVarcharIsNotNull();
        List<TestMysqlUsable> testMysqlUsables = mapper.selectByExample(example);
        return !testMysqlUsables.isEmpty();
    }
}