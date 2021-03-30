package com.trace.api.oss;

import com.trace.api.entity.AFile;
import com.trace.api.entity.ImageReq;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author xzp
 * Created on 2021/3/30
 */
public class ImageDealTest {
    ImageDeal deal = new ImageDeal();

    @Test
    public void doTest() throws Exception {
        System.out.println(deal.getAccessToken());
    }
    @Test
    public void test() throws Exception {
        ImageReq req = new ImageReq();
        AFile file1 = new AFile();
        file1.setFileid("cloud://wenrun-book-6666.7765-wenrun-book-6666-1300001131/11d6e3dc-ccfc-4012-9ae4-0b2bd9e887f3.png");
        file1.setMax_age(7200);
        AFile file2 = new AFile();
        file2.setFileid("cloud://wenrun-book-6666.7765-wenrun-book-6666-1300001131/471dc77f-2584-4933-86dc-7ae0d69ce0da.png");
        file2.setMax_age(7200);
        List<AFile> list = Lists.newArrayList(file1,file2);
        req.setList(list);
        req.setEnv("wenrun-book-6666");
        System.out.println(deal.imageDownloadAddr(req));
    }
}