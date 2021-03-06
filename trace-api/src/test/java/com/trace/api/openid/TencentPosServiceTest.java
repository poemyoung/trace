package com.trace.api.openid;

import com.trace.api.addrpentity.BaseResult;
import com.trace.api.reverseaddrprs.RevBaseMsg;
import com.trace.api.reverseaddrprs.RevResult;
import org.junit.Test;

/**
 * @author xzp
 * Created on 2021/2/27
 */
public class TencentPosServiceTest {
    TencentPosService service = new TencentPosService();

    @Test
    public void doTest() {
        String address = "四川省成都市双流区四川大学江安校区";
        BaseResult result = service.descParseAddr(address, "成都市");
        System.out.println(result);
    }
    @Test
    public void doTest1() {
        Number lat = 30.5702;
        Number lgt = 104.06476;
        RevBaseMsg revResult = service.latLongToAddr(lat, lgt);
        System.out.println(revResult);
    }
}