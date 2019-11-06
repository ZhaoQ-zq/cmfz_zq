package com.baizhi.service;

import com.baizhi.dao.ArticleDAO;
import com.baizhi.entity.Article;
import com.baizhi.entity.Chapter;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/*
 *类的描述()
 *
 *@author zq
 *@date 2019/10/29 14:43
 *
 *@version V-1.1.0
 */
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService{
    @Autowired
    private ArticleDAO articleDAO;

    @Override
    public Map<String, Object> selectAll(Integer page, Integer rows) {
        Map<String,Object> map = new HashMap<String,Object>();
        Article article = new Article();
        RowBounds rowBounds = new RowBounds((page-1)*rows,rows);
        List<Article> articles = articleDAO.selectByRowBounds(article,rowBounds);
        int count = articleDAO.selectCount(article);
        Integer pageCount=count%rows==0?count/rows:count/rows+1;
        map.put("page",page);
        map.put("total",pageCount);
        map.put("records",count);
        map.put("rows",articles);

        return map;
    }

    @Override
    public void add(Article article) {
        article.setId(UUID.randomUUID().toString());
        article.setCreateDate(new Date());
        //System.out.println("@@@@@@@@@@@@@@@@22"+article);
        articleDAO.insert(article);
    }

    @Override
    public void update(Article article) {
        articleDAO.updateByPrimaryKeySelective(article);
    }

    @Override
    public void del(String id) {
        articleDAO.deleteByPrimaryKey(id);
    }

    @Override
    public List<Article> selectNew(String uid) {
        List<Article> articles = articleDAO.selectNew(uid);
        return articles;
    }

    @Override
    public List<Article> selectArticles(String uid) {
        List<Article> articles = articleDAO.selectArticles(uid);

        return articles;
    }
}
