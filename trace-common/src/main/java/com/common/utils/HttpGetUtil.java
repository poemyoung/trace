package com.common.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * @author xzp
 * Created on 2021/2/11
 */
public class HttpGetUtil {

    public String doGet(String baseUrl, Map<String,String> map) throws Exception {
        // 封装参数
        if(!map.isEmpty()) {
            baseUrl += "?";
        }
        for (Map.Entry entry : map.entrySet()) {
            baseUrl += entry.getKey() + "=" + entry.getValue() + "&";
        }
        baseUrl = baseUrl.substring(0,baseUrl.length()-1);
        return getGeneralUrl(baseUrl);
    }

    private static String getGeneralUrl(String generalUrl) throws Exception{
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        String result = null;// 返回结果字符串
        try {
            URL url = new URL(generalUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(15000);
            // 设置读取远程返回的数据时间：60000毫秒
            connection.setReadTimeout(60000);
            connection.connect();
            if(connection.getResponseCode() == 200) {
                is = connection.getInputStream();
                br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
                StringBuffer sbf = new StringBuffer();
                String temp = null;
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(br != null) {
                br.close();
            }
            if(is != null) {
                is.close();
            }
        }
        return result;
    }
}
