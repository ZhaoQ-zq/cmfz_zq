package com.baizhi.dao;

import com.baizhi.entity.Banner;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BannerDAO extends Mapper<Banner> {
    List<Banner> selectNew();
}
