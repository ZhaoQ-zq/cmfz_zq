package com.baizhi;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.junit.Test;

/*
 *类的描述()
 *
 *@author zq
 *@date 2019/11/4 18:49
 *
 *@version V-1.1.0
 */
public class test1 {

    @Test
    public void testMsg(){
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4FqbKBcbv1N6VKyLMUuF", "USJLfvXUGUnZSZmj0KYdF7DSfq5Xd4");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", "17836163892");
        request.putQueryParameter("SignName", "千羽");
        request.putQueryParameter("TemplateCode", "SMS_176911976");
        request.putQueryParameter("TemplateParam", "{\"code\":\"123456\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
