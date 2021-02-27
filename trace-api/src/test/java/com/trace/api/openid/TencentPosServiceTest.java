package com.trace.api.openid;

import com.trace.api.addrpentity.BaseResult;
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
}