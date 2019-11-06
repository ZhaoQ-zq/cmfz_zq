package com.baizhi;


import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.baizhi.dao.AdminDAO;

import com.baizhi.dao.AlbumDAO;
import com.baizhi.dao.BannerDAO;
import com.baizhi.dao.UserDAO;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Album;
import com.baizhi.entity.Article;
import com.baizhi.entity.Banner;
import io.goeasy.GoEasy;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

@SpringBootTest(classes = CmfzZqApplication.class)
@RunWith(SpringRunner.class)
public class CmfzZqApplicationTests {
    @Autowired
    private AdminDAO adminDAO;
    @Autowired
    private BannerDAO bannerDAO;
    @Autowired
    private AlbumDAO albumDAO;
    @Autowired
    private UserDAO userDAO;
    @Test
    public void contextLoads() {
       /* Admin admin = adminMapper.selectByPrimaryKey("1");
        System.out.println(admin);*/
       /* List<Admin> admins = adminDAO.selectAll();
        for (Admin admin : admins) {
            System.out.println(admin);
        }*/
     /*   List<Banner> banners = bannerDAO.selectAll();
        for (Banner banner : banners) {
            System.out.println(banner);
        }*/
        /*int count = bannerDAO.selectCount(new Banner());
        System.out.println(count);*/
        //bannerDAO.insert("");
        /*int i = bannerDAO.deleteByPrimaryKey("1");*/
        /*Banner banner = new Banner();
        banner.setCreateDate(new Date());
        bannerDAO.insert(banner);*/
        userDAO.deleteByPrimaryKey("1a8d0710-58d6-4651-8cac-26fc0f12a08d");
        /*new Banner("2","dsa","/image/shouye.png","w","2019-10-09 15:00:00","2019-10-09 15:00:00","很好")*/
    }

    @Test
    public void TestPOI()throws Exception{
        List<Album> albums = albumDAO.selectAll();
        //创建工作簿
        Workbook workbook = new HSSFWorkbook();
        //创建样式
        CellStyle cellStyle = workbook.createCellStyle();
        //创建字体
        Font font = workbook.createFont();
        font.setBold(true);
        font.setColor(Font.COLOR_RED);
        font.setFontName("楷体");
        font.setItalic(true);
        font.setUnderline(Font.U_DOUBLE_ACCOUNTING);
        cellStyle.setFont(font);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        //创建样式
        CellStyle cellStyle1 = workbook.createCellStyle();
        //创建日期格式
        DataFormat dataFormat = workbook.createDataFormat();
        short format = dataFormat.getFormat("yyyy年MM月dd日 HH时mm分ss秒");
        cellStyle1.setDataFormat(format);

        //创建工作表
        Sheet sheet = workbook.createSheet("article");
        //设置列宽  下标，宽度*256
        sheet.setColumnWidth(3,50*256);
        //创建第一行  参数列表为下标  第几行
        Row row = sheet.createRow(0);
        String[] s = {"编号","标题","作者","创建日期"};
        for (int i = 0; i < s.length; i++) {
            //创建单元格
            Cell cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
            //为单元格赋值
            cell.setCellValue(s[i]);
        }
        for (int i = 0; i < albums.size(); i++) {
            Row row1 = sheet.createRow(i + 1);
            //创建单元格
            row1.createCell(0).setCellValue(albums.get(i).getId());
            row1.createCell(1).setCellValue(albums.get(i).getTitle());
            row1.createCell(2).setCellValue(albums.get(i).getAuthor());
            Cell cell = row1.createCell(3);
            cell.setCellStyle(cellStyle1);
            cell.setCellValue(albums.get(i).getCreateDate());
        }
        //写出到磁盘
        workbook.write(new FileOutputStream("D:/article.xls"));

    }

    //导入
    @Test
    public void testIOPImport()throws Exception{
        Workbook workbook = new HSSFWorkbook(new FileInputStream("D:/article.xls"));
        Sheet sheet = workbook.getSheet("article");
        int lastRowNum = sheet.getLastRowNum();
        for (int i = 0; i < lastRowNum; i++) {
            Row row = sheet.getRow(i + 1);
            String id = row.getCell(0).getStringCellValue();
            String title = row.getCell(1).getStringCellValue();
            String author = row.getCell(2).getStringCellValue();
            Date createDate = row.getCell(3).getDateCellValue();
            Article article = new Article();
            article.setId(id);
            article.setTitle(title);
            article.setAuthor(author);
            article.setCreateDate(createDate);
            System.out.println(article);
        }

    }

    @Test
    public void testGoEasy(){
        GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io", "BC-9f4afe2dcb1146269756b2881da44cd1");
                goEasy.publish("zq", "Hello, GoEasy!");
    }


}

