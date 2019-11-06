package com.baizhi.service;

import com.baizhi.entity.Article;

import java.util.List;
import java.util.Map;

/*
 *类的描述()
 *
 *@author zq
 *@date 2019/10/29 14:42
 *
 *@version V-1.1.0
 */
public interface ArticleService {
    Map<String,Object> selectAll(Integer page,Integer rows);
    void add(Article article);
    void update(Article article);
    void del(String id);
    //查询最新两篇文章
    List<Article> selectNew(String uid);
    //查询所有师傅文章
    List<Article> selectArticles(String uid);
}
