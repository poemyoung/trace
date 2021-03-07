package com.common.utils;

import com.common.enums.Colors;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * @author xzp
 * Created on 2021/3/7
 */
public class QRCodeTest {
    QRCode code = new QRCode();
    @Test
    public void doTest() throws IOException {
        BufferedImage bufferedImage = QRCode.createImageByColor("Test if is right", Colors.GREEN);
        CreateFileUtil.createDir("/Users/xzp/Desktop/img-test");
        ImageIO.write(bufferedImage,"jpg",new File("/Users/xzp/Desktop/img-test/test1.jpg"));
    }


}