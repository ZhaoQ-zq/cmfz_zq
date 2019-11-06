package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.dao.AlbumDAO;
import com.baizhi.dao.ChapterDAO;
import com.baizhi.entity.Album;
import com.baizhi.entity.Banner;
import com.baizhi.entity.Chapter;
import com.baizhi.service.AlbumService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/*
 *类的描述()
 *
 *@author zq
 *@date 2019/10/28 17:00
 *
 *@version V-1.1.0
 */
@Controller
@RequestMapping("album")
public class AlbumController {

    @Autowired
    private AlbumService albumService;
    @Autowired
    private AlbumDAO albumDAO;
    @Autowired
    private ChapterDAO chapterDAO;

    @ResponseBody
    @RequestMapping("selectAllAlbum")
    public Map<String,Object> selectAllChaptersByAlbumId(Integer page,Integer rows)throws Exception{
        Map<String, Object> map = albumService.selectAll(page, rows);
        return map;
    }

    @RequestMapping("edit")
    @ResponseBody
    public void edit(String oper, Album album,HttpSession session)throws Exception{
        if ("add".equals(oper)){
            session.setAttribute("album",album);
            albumService.add(album);
        }else if ("edit".equals(oper)){
            albumService.update(album);
        }else if ("del".equals(oper)){
            albumService.del(album.getId());
        }
    }

    @RequestMapping("upload")
    public void upload(MultipartFile upload, HttpSession session)throws Exception{
        if (!upload.isEmpty()) {
            Album album = (Album) session.getAttribute("album");
            String realPath = session.getServletContext().getRealPath("/image");
            File file = new File(realPath + "/" + upload.getOriginalFilename());
            upload.transferTo(file);
            album.setCover(upload.getOriginalFilename());
            albumService.update(album);
        }else {

        }
    }

    @RequestMapping("exportAlbum")
    public void exportAlbum(HttpServletResponse response)throws Exception{
        List<Album> albums = albumDAO.selectAll();
        Chapter chapter = new Chapter();
        for (Album album : albums) {
            album.setCover("D:\\第三阶段\\框架阶段\\1_IDEA\\My代码\\cmfz_zq\\src\\main\\webapp\\image\\"+album.getCover());
            chapter.setAlbumId(album.getId());
            List<Chapter> chapters = chapterDAO.selectAll();
            album.setChapters(chapters);
        }
        String fileName = URLEncoder.encode("album.xls", "UTF-8");
        response.setHeader("content-disposition","attachment;filename="+fileName);
        response.setContentType("application/vnd.ms-excel");
        Workbook  workbook= ExcelExportUtil.exportExcel(new ExportParams("专辑信息管理","专辑","所有专辑"),Album.class,albums);
        workbook.write(response.getOutputStream());
    }


}
