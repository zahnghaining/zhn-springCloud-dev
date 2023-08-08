package com.zhn.common.utils.Msg;



import org.apache.http.HttpResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * @author
 * @version 1.0.0
 * @ClassName MsgUtil.java
 * @Description TODO
 * @createTime 2022年05月26日 15:49:00
 */
public class MsgUtil {


    public static void sendMsg(String phone,String code){
        String host = "http://dingxin.market.alicloudapi.com";
        String path = "/dx/sendSms";
        String method = "POST";
        String appcode = "6519301ba878422488063fe815c9910";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("mobile", phone);
        querys.put("param", "code:"+code);
        querys.put("tpl_id", "TP1711063");
        Map<String, String> bodys = new HashMap<String, String>();

        try {

            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
