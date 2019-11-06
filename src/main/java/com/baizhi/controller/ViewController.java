package com.baizhi.controller;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.baizhi.dao.ChapterDAO;
import com.baizhi.entity.*;
import com.baizhi.service.*;
import com.baizhi.util.CreateValidateCode;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.*;

/*
 *类的描述()
 *
 *@author zq
 *@date 2019/11/4 16:06
 *
 *@version V-1.1.0
 */
@RestController
public class ViewController {
    @Autowired
    private BannerService bannerService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private ChapterService chapterService;
    @Autowired
    private UserService userService;

    @RequestMapping("first_page")
    public Map<String,Object> first_page(String uid,String type,String sub_type){

        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map1 = new HashMap<>();
        if ("all".equals(type)){
            try {
                List<Album> albums = albumService.selectHot();
                List<Banner> banners = bannerService.selectNew();
                List<Article> articles = articleService.selectNew(uid);

                map.put("code",200);
                map.put("header",banners);
                map1.put("albums",albums);
                map1.put("articles",articles);
                map.put("body",map1);

                return map;
            }catch (Exception e){
                map.put("code",500);
                map.put("msg","参数错误");
                e.printStackTrace();
            }

        }if ("wen".equals(type)){
            try {
                List<Album> albums = albumService.selectHot();
                map.put("code",200);
                map1.put("albums",albums);
                map.put("body",map1);
                return map;
            }catch (Exception e){
                map.put("code",500);
                map.put("msg","参数错误");
                e.printStackTrace();
            }

        }if ("si".equals(type)){
            if ("ssyj".equals(sub_type)){
                try {

                    List<Article> articles = articleService.selectNew(uid);
                    map.put("code",200);
                    map1.put("articles",articles);
                    map.put("body",map1);
                    return map;
                }catch (Exception e){
                    map.put("code",500);
                    map.put("msg","参数错误");
                    e.printStackTrace();
                }
            }if ("xmfy".equals(sub_type)){
                try {
                    List<Article> articles = articleService.selectArticles(uid);
                    map.put("code",200);
                    map1.put("articles",articles);
                    map.put("body",map1);
                    return map;
                }catch (Exception e){
                    map.put("code",500);
                    map.put("msg","参数错误");
                    e.printStackTrace();
                }
            }
        }
            return map;
    }

   @RequestMapping("si")
    public Map<String,Object> si(String id,String uidc){
       Map<String, Object> map = new HashMap<>();
           try {
               map.put("code",200);
               map.put("link","");
               map.put("id",id);
               map.put("ext","");
               return map;
           }catch (Exception e){
               map.put("code",500);
               map.put("msg","参数错误");
               e.printStackTrace();
           }
                return map;
    }

    @RequestMapping("wen")
    public Map<String,Object> wen(String id,String uid,String albumId){
        Map<String, Object> map = new HashMap<>();

        try {
            Album album = albumService.selectById(id);
            List<Chapter> chapters = chapterService.selectChapters(albumId);
            map.put("code",200);
            map.put("introduction",album);
            map.put("list",chapters);
        }catch (Exception e){
            map.put("code",500);
            map.put("msg","参数错误");
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping("login")
    public Map<String,Object> login(String phone,String password){
        Map<String, Object> map = new HashMap<>();
        try {
            User user = new User();
            user.setPhone(phone);
            user.setPassword(password);
            User user1 = userService.selectUser(user);

            map.put("password",user1.getPhone());
            map.put("farmington",user1.getFname());
            map.put("uid",user1.getId());
            map.put("nickname",user1.getUsername());
            map.put("gender",user1.getSex());
            map.put("photo",user1.getImage());
            map.put("location",user1.getProvince()+user1.getCity());
            map.put("province",user1.getProvince());
            map.put("city",user1.getCity());
            map.put("description",user1.getSign());
            map.put("phone",user1.getPhone());
        }catch (Exception e){
            map.put("error",-200);
            map.put("errmsg","密码错误");
            e.printStackTrace();
        }
        return map;
    }


    @RequestMapping("register")
    public Map<String,Object> register(String phone,String password){
        Map<String, Object> map = new HashMap<>();
        try {
            User user = new User();
            user.setId(UUID.randomUUID().toString());
            user.setPhone(phone);
            user.setPassword(password);
            User user1 = userService.selectUser(user);
            if (user1==null){
                userService.add(user);
                map.put("password",password);
                map.put("uid", user.getId());
                map.put("phone",phone);
            }

        }catch (Exception e){
            map.put("error","-200");
            map.put("errmsg","该手机号已存在");
        }
        return map;
    }

    @RequestMapping("update")
    private Map<String,Object> update(User user){
        Map<String, Object> map = new HashMap<>();
        try {

            userService.update(user);
            User user2 = new User();
            user2.setId(user.getId());
            User user1 = userService.selectUser(user2);
            map.put("password",user1.getPassword());
            map.put("farmington",user1.getFname());
            map.put("uid",user1.getId());
            map.put("nickname",user1.getUsername());
            map.put("gender",user.getSex());
            map.put("photo",user1.getImage());
            //map.put("location",);
            map.put("province",user1.getProvince());
            map.put("city",user1.getCity());
            map.put("description",user1.getSign());
            map.put("phone",user1.getPhone());
        }catch (Exception e){
            map.put("error",-200);
            map.put("errmsg","该手机已存在");
        }
        return map;
    }
    //获取短信验证码接口
    @RequestMapping("obtain")
    public void obtain(HttpSession session,String phone){
        Random random = new Random();
        int i = random.nextInt(1000000);
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4FqbKBcbv1N6VKyLMUuF", "USJLfvXUGUnZSZmj0KYdF7DSfq5Xd4");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "千羽");
        request.putQueryParameter("TemplateCode", "SMS_176911976");
        request.putQueryParameter("TemplateParam", "{\"code\":"+i+"}");
        session.setAttribute("code",phone+i);
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
    //短信校验接口
    @RequestMapping("check")
    public String check(String phone,String code,HttpSession session){
        String code1 = (String)session.getAttribute("code");
        if (code1.equals(phone+code)){
            return "success";
        }else {
            return "error";
        }
    }
    //查询所有用户
    @RequestMapping("list")
    public List<Object> list(String id){
        Map<String, Object> map = new HashMap<>();

        List<User> users = userService.selectUsers();
        List<Object> users1 = new ArrayList<>();
        try {
            for (User user : users) {
                Map<String, Object> map1 = new HashMap<>();

                map1.put("farmington",user.getFname());
                map1.put("nickname",user.getUsername());
                map1.put("gender",user.getSex());
                map1.put("photo",user.getImage());
                map1.put("province",user.getProvince());
                map1.put("city",user.getCity());
                map1.put("description",user.getSign());
                map1.put("phone",user.getPhone());
                users1.add(map1);
            }

        }catch (Exception e){
            map.put("error",-200);
            map.put("errmsg","会员列表为空");
            users1.add(map);

        }
       return users1;
    }





}
