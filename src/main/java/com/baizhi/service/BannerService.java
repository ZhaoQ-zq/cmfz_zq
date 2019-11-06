package com.baizhi.service;

import com.baizhi.entity.Banner;

import java.util.List;

public interface BannerService {
    //查所有
    List<Banner> selectAll(Integer page,Integer rows);
    //查条数
    Integer selectCount();
    //添加
    void insert(Banner banner);
    //删除
    void delete(String id);
    //修改
    void update(Banner banner);

    List<Banner> selectNew();
}
