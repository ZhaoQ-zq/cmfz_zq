package com.baizhi.service;/*
 *类的描述()
 *
 *@author zq
 *@date 2019/10/28 16:57
 *
 *@version V-1.1.0
 */

import com.baizhi.dao.AlbumDAO;
import com.baizhi.entity.Album;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService{
    @Autowired
    private AlbumDAO albumDAO;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Map<String, Object> selectAll(Integer page, Integer rows) {
        Map<String,Object> map = new HashMap<String,Object>();
        Album album = new Album();
        RowBounds rowBounds = new RowBounds((page-1)*rows,rows);
        List<Album> albums = albumDAO.selectByRowBounds(album, rowBounds);
        int count = albumDAO.selectCount(album);
        Integer pageCount=count%rows==0?count/rows:count/rows+1;

        map.put("page",page);
        map.put("total",pageCount);
        map.put("records",count);
        map.put("rows",albums);
        return map;
    }

    @Override
    public void add(Album album) {
        album.setId(UUID.randomUUID().toString());
        album.setCreateDate(new Date());
        albumDAO.insert(album);
    }

    @Override
    public void del(String id) {
        albumDAO.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Album album) {
        albumDAO.updateByPrimaryKeySelective(album);
    }

    @Override
    public List<Album> selectHot() {
        List<Album> albums = albumDAO.selectHot();
        return albums;
    }

    @Override
    public Album selectById(String id) {
        Album album = albumDAO.selectById(id);
        return album;
    }
}
