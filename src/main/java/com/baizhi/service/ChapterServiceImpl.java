package com.baizhi.service;
/*
 *类的描述()
 *
 *@author zq
 *@date 2019/10/28 16:58
 *
 *@version V-1.1.0
 */

import com.baizhi.dao.AlbumDAO;
import com.baizhi.dao.ChapterDAO;

import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.*;
@Service
@Transactional
public class ChapterServiceImpl implements ChapterService{
    @Autowired
    private ChapterDAO chapterDAO;
    @Autowired
    private AlbumDAO albumDAO;
    @Autowired
    private HttpSession session;
    @Autowired
    private AlbumService albumService;

    @Override
    public Map<String, Object> selectAll(String id,Integer page, Integer rows) {

        Map<String,Object> map = new HashMap<String,Object>();
        Chapter chapter = new Chapter();
        chapter.setAlbumId(id);
        RowBounds rowBounds = new RowBounds((page-1)*rows,rows);
        List<Chapter> chapters = chapterDAO.selectByRowBounds(chapter, rowBounds);
        int count = chapterDAO.selectCount(chapter);
        Integer pageCount=count%rows==0?count/rows:count/rows+1;

        map.put("page",page);
        map.put("total",pageCount);
        map.put("records",count);
        map.put("rows",chapters);
        return map;

    }

    @Override
    public void add(Chapter chapter) {
        chapter.setId(UUID.randomUUID().toString());
        chapter.setCreateDate(new Date());
        session.setAttribute("chapter",chapter);
        Album album = albumDAO.selectByPrimaryKey(chapter.getAlbumId());
        album.setChapterCount(album.getChapterCount()+1);
        albumService.update(album);
        chapterDAO.insert(chapter);
    }

    @Override
    public void del(String id) {
        Chapter chapter = chapterDAO.selectByPrimaryKey(id);
        Album album = albumDAO.selectByPrimaryKey(chapter.getAlbumId());
        album.setChapterCount(album.getChapterCount()-1);
        albumService.update(album);
        chapterDAO.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Chapter chapter) {
        session.setAttribute("chapter",chapter);
        chapterDAO.updateByPrimaryKeySelective(chapter);
    }

    @Override
    public List<Chapter> selectChapters(String albumId) {
        List<Chapter> chapters = chapterDAO.selectChapters(albumId);
        return chapters;
    }
}
