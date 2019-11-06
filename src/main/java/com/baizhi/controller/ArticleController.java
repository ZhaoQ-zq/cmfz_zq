package com.baizhi.controller;

import com.baizhi.dao.ArticleDAO;
import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.net.URLEncoder;
import java.util.*;

/*
 *类的描述()
 *
 *@author zq
 *@date 2019/10/29 14:44
 *
 *@version V-1.1.0
 */
@RestController
@RequestMapping("article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleDAO articleDAO;

    @ResponseBody
    @RequestMapping("selectAll")
    public Map<String,Object> selectAll(Integer page,Integer rows)throws Exception{
        Map<String, Object> map = articleService.selectAll(page, rows);
        return map;
    }


    @RequestMapping("edit")
    public void edit(String oper,Article article)throws Exception{

        if ("add".equals(oper)){
            System.out.println(article);
            articleService.add(article);
        }else if ("edit".equals(oper)){
            if ("".equals(article.getContent())){
                article.setContent(null);
            }
            articleService.update(article);
        }else if ("del".equals(oper)){
            articleService.del(article.getId());
        }
    }

    @RequestMapping("browser")
    public Map<String,Object> browser(HttpServletRequest request)throws Exception{
       Map<String,Object> map = new HashMap<>();
       //http://localhost:8080/cmfz_zq/image
       String url = "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/image/";
       // System.out.println(url);
        File file = new File(request.getSession().getServletContext().getRealPath("/image"));
        File[] files = file.listFiles();
        ArrayList<Object> list = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            File file1 = files[i];
            Map<String,Object> photo = new HashMap<String, Object>();
            photo.put("is_dir",false);
            photo.put("has_file",false);
            photo.put("filesize",file1.length());
            photo.put("is_photo",true);
            //System.out.println("后缀"+FilenameUtils.getExtension(file1.getName()));
            photo.put("filetype", FilenameUtils.getExtension(file1.getName()));
            photo.put("filename",file1.getName());
            photo.put("datetime",new Date());
            list.add(photo);
        }
        map.put("current_url",url);
        map.put("total_count",files.length);
        map.put("file_list",list);
       return map;
    }

    @RequestMapping("upload")
    public Map<String,Object> upload(MultipartFile imgFile,HttpServletRequest request)throws Exception {
        Map<String, Object> map = new HashMap<>();
        File file = new File(request.getSession().getServletContext().getRealPath("/image"), imgFile.getOriginalFilename());
        imgFile.transferTo(file);
        //给响应
        String url = "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/image/"+imgFile.getOriginalFilename();
        map.put("error",0);
        map.put("url",url);
        return map;
    }

    @RequestMapping("exportArticle")
    public void  exportArticle(HttpServletResponse response)throws Exception{
        List<Article> articles = articleDAO.selectAll();
        Workbook workbook = new HSSFWorkbook();
        CellStyle cellStyle = workbook.createCellStyle();
        DataFormat dataFormat = workbook.createDataFormat();
        short format = dataFormat.getFormat("yyyy-MM-dd HH:mm:ss");
        cellStyle.setDataFormat(format);
        Sheet sheet = workbook.createSheet();
        sheet.setColumnWidth(3,50*256);
        String[] strings = {"编号","标题","作者","创建日期"};
        Row row = sheet.createRow(0);
        for (int i = 0; i < strings.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(strings[i]);
        }
        for (int i=0;i<articles.size();i++){
            Row row1 = sheet.createRow(i + 1);
            row1.createCell(0).setCellValue(articles.get(i).getId());
            row1.createCell(1).setCellValue(articles.get(i).getTitle());
            row1.createCell(2).setCellValue(articles.get(i).getAuthor());
            Cell cell = row1.createCell(3);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(articles.get(i).getCreateDate());
        }
        String fileName = URLEncoder.encode("article", "UTF-8");
        response.setHeader("content-disposition","attachment;filename"+fileName);
        response.setContentType("application/vnd.ms-excel");
         workbook.write(response.getOutputStream());
    }
}
