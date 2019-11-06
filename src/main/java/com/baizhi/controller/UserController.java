package com.baizhi.controller;

import com.baizhi.dao.UserDAO;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *类的描述()
 *
 *@author zq
 *@date 2019/10/29 14:44
 *
 *@version V-1.1.0
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("selectAll")
    public Map<String,Object> selectAll(Integer page,Integer rows)throws Exception{
        Map<String, Object> map = userService.selectAll(page, rows);
        return map;
    }

    @ResponseBody
    @RequestMapping("edit")
    public void edit(String oper, User user,HttpSession session)throws Exception{
        if ("add".equals(oper)){
            session.setAttribute("user",user);
             userService.add(user);
        }else if ("edit".equals(oper)){
            System.out.println(user);
            userService.update(user);
        }else if ("del".equals(oper)){
            System.out.println("@@@@@@@@@@@@@@@@@");
            session.setAttribute("user",user);
            System.out.println("susudsdsada"+user);
            userService.del(user.getId());
        }
    }

    @ResponseBody
    @RequestMapping("upload")
    public void upload(MultipartFile upload, HttpSession session)throws Exception{
        if (!upload.isEmpty()){
            User user=(User) session.getAttribute("user");
            String realPath = session.getServletContext().getRealPath("/image");
            File file = new File(realPath + "/" + upload.getOriginalFilename());
            upload.transferTo(file);
            user.setImage(upload.getOriginalFilename());
            userService.update(user);
        }else {

        }
    }

    @RequestMapping("selectUserCount")
    public Map<String,Object> selectUserCount()throws Exception{
        Integer count1 = userService.userCount(7, "女");
        Integer count2 = userService.userCount(7, "男");
        Integer count3 = userService.userCount(14, "女");
        Integer count4 = userService.userCount(14, "男");
        Integer count5 = userService.userCount(21, "女");
        Integer count6 = userService.userCount(21, "男");
        Map<String, Object> map = new HashMap<>();
        map.put("nv1",count1);
        map.put("nan1",count2);
        map.put("nv2",count3);
        map.put("nan2",count4);
        map.put("nv3",count5);
        map.put("nan3",count6);
        return map;
    }

    @RequestMapping("queryCountByPro")
    private Map<String,Object> queryCountByPro()throws Exception{
        List<Object> users = userService.queryCountByPro("男");
        List<Object> users1 = userService.queryCountByPro("女");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("nan",users);
        map.put("nv",users1);
        return map;
    }
}
