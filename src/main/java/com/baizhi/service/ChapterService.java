package com.baizhi.service;

import com.baizhi.entity.Chapter;

import java.util.List;
import java.util.Map;

public interface ChapterService {
    Map<String,Object> selectAll(String id,Integer page, Integer rows);

    void add(Chapter chapter);

    void del(String id);

    void update(Chapter chapter);
    //查询所有文章
    List<Chapter> selectChapters(String albumId);
}
