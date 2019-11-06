package com.baizhi.dao;

import com.baizhi.entity.Chapter;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ChapterDAO extends Mapper<Chapter> {
    List<Chapter> selectChapters(String albumId);
}
