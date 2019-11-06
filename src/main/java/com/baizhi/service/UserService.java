package com.baizhi.service;

import com.baizhi.entity.User;

import java.util.List;
import java.util.Map;

/*
 *类的描述()
 *
 *@author zq
 *@date 2019/10/29 14:41
 *
 *@version V-1.1.0
 */
public interface UserService {
    Map<String,Object> selectAll(Integer page,Integer rows);
    void add(User user);
    void update(User user);
    void del(String id);
    //查询注册的用户数量
    Integer userCount(Integer day,String sex);

    //查询每个省份注册用户的数量
    List<Object> queryCountByPro(String sex);

    //查询用户 单个
    User selectUser(User user);
    //查询所有用户
    List<User> selectUsers();
}
