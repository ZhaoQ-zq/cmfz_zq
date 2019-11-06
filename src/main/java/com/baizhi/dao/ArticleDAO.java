package com.baizhi.dao;

import com.baizhi.entity.Article;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ArticleDAO extends Mapper<Article> {
    List<Article> selectNew(String uid);
    //查询所有师傅文章
    List<Article> selectArticles(String uid);

}
