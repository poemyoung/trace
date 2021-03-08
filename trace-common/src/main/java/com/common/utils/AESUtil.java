package com.common.utils;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

/**
 * @author xzp
 * Created on 2021/3/6
 */
public class AESUtil {
    private static final String DEFAULT_KEY = "onetwothreefourf";

    public static byte[] aesEncodeDef(String str) throws Exception {
        return aesEncode(str,DEFAULT_KEY);
    }

    public static String aesDeCodeDef(byte[] bytes) throws Exception {
        return aesDecode(bytes,DEFAULT_KEY);
    }

    public static byte[] aesEncode(String str, String key)
    throws Exception {
        if(str == null || key == null) {
            return null;
        }
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES"));
        byte[] bytes = cipher.doFinal(str.getBytes(StandardCharsets.UTF_8));
        return bytes;
    }

    public static String aesDecode(byte[] bytes, String key)
            throws Exception {
        if (bytes == null || key == null) return null;
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES"));
        bytes = cipher.doFinal(bytes);
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
