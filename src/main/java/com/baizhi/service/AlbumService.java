package com.baizhi.service;
/*
 *类的描述()
 *
 *@author zq
 *@date 2019/10/28 16:56
 *
 *@version V-1.1.0
 */

import com.baizhi.entity.Album;

import java.util.List;
import java.util.Map;

public interface AlbumService {
    Map<String,Object>  selectAll(Integer page,Integer rows);

    void add(Album album);

    void del(String id);

    void update(Album album);
    //查询专辑
    List<Album> selectHot();
    //通过id 查询专辑
    Album selectById(String id);
}
