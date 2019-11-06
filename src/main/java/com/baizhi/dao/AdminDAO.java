package com.baizhi.dao;

import com.baizhi.entity.Admin;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;


public interface AdminDAO extends Mapper<Admin> {
    Admin login(@Param("name")String name,@Param("password")String password);
}
