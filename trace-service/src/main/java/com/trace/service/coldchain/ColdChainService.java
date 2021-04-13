package com.trace.service.coldchain;

import com.trace.dao.repository.ColdChainMapper;
import com.trace.service.entity.recentity.ColdChainBaseMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xzp
 * Created on 2021/4/13
 */
@Service
public class ColdChainService {

    @Autowired
    ColdChainMapper ccMapper;

    public String declareAColdChain(ColdChainBaseMsg msg) {
        // 插入冷链，生成冷链二维码、插入地址信息（单独分出来）

        return "";
    }
}
