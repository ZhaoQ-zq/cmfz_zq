package com.baizhi.service;

import com.baizhi.dao.UserDAO;
import com.baizhi.entity.User;
import com.baizhi.util.Md5UUIDSaltUtil;
import io.goeasy.GoEasy;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/*
 *类的描述()
 *
 *@author zq
 *@date 2019/10/29 14:41
 *
 *@version V-1.1.0
 */
@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDAO userDAO;

    @Override
    public Map<String, Object> selectAll(Integer page,Integer rows) {
        Map<String,Object> map = new HashMap<String,Object>();
        User user = new User();

        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<User> users = userDAO.selectByRowBounds(user, rowBounds);
        int count = userDAO.selectCount(user);
        Integer pageCount=count%rows==0?count/rows:count/rows+1;
        map.put("page",page);
        map.put("total",pageCount);
        map.put("records",count);
        map.put("rows",users);
        return map;
    }

    @Override
    public void add(User user) {

        String salt = Md5UUIDSaltUtil.getSalt();
        String password = Md5UUIDSaltUtil.createMd5Code(user.getPassword()+salt);
        user.setId(UUID.randomUUID().toString());
        user.setCreateDate(new Date());
        user.setPassword(password);
        int i = userDAO.insert(user);
        if (i==1){
            GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io", "BC-9f4afe2dcb1146269756b2881da44cd1");
            goEasy.publish("zq", "添加成功");
        }
    }

    @Override
    public void update(User user) {
        userDAO.updateByPrimaryKeySelective(user);
    }

    @Override
    public void del(String id) {
        userDAO.deleteByPrimaryKey(id);
    }

    @Override
    public Integer userCount(Integer day, String sex) {
        Integer userCount = userDAO.UserCount(day, sex);
        return userCount;
    }

    @Override
    public List<Object> queryCountByPro(String sex) {

        List<Object> list = new ArrayList<>();

        List<User> users = userDAO.queryCountByPro(sex);
        for (User user : users) {
            Map<String, Object> map = new HashMap<>();
            map.put("name",user.getProvince());
            map.put("value",user.getNumber());
            list.add(map);
        }

        return list;
    }

    @Override
    public User selectUser(User user) {
        User one = userDAO.selectOne(user);
        return one;
    }

    @Override
    public List<User> selectUsers() {
        List<User> users = userDAO.selectUsers();
        System.out.println("==================");
        for (User user : users) {
            System.out.println(user);
        }
        return users;
    }
}
