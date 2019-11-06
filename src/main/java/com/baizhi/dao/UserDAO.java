package com.baizhi.dao;

import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserDAO extends Mapper<User> {
    Integer UserCount(@Param("day")Integer day,@Param("sex")String sex);

    List<User> queryCountByPro(String sex);
    //查询所有用户
    List<User> selectUsers();
}
