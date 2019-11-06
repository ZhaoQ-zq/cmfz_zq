package com.baizhi.dao;

import com.baizhi.entity.Album;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AlbumDAO extends Mapper<Album> {
    //最新专辑
    List<Album> selectHot();
    //通过id 查询专辑
    Album selectById(String id);
}
