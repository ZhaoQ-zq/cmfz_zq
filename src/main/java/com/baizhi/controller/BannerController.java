package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.*;

@Controller
@RequestMapping("banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @ResponseBody
    @RequestMapping("showBanners")
    public Map<String,Object> showBanners(Integer page,Integer rows){
        Map<String,Object> map = new HashMap<String,Object>();
        List<Banner> banners = bannerService.selectAll(page, rows);
        Integer count = bannerService.selectCount();
        Integer pageCount=count%rows==0?count/rows:count/rows+1;
        map.put("total",pageCount);
        map.put("records",count);
        map.put("page",page);
        map.put("rows",banners);

        return map;
    }

    @ResponseBody
    @RequestMapping("nb")
    public void nb(String oper, Banner banner,HttpSession session)throws Exception{
        if ("add".equals(oper)){
            session.setAttribute("banner",banner);
            bannerService.insert(banner);
        }else if ("edit".equals(oper)){
            bannerService.update(banner);
        }else if ("del".equals(oper)){
            //System.out.println(banner.getId());
            bannerService.delete(banner.getId());
        }

    }

    @RequestMapping("upload")
    public void upload(MultipartFile upload, HttpSession session)throws Exception{

        Banner banner=(Banner) session.getAttribute("banner");
        String realPath = session.getServletContext().getRealPath("/image");
        File file = new File(realPath + "/" + upload.getOriginalFilename());
        upload.transferTo(file);
        banner.setImage(upload.getOriginalFilename());

        bannerService.update(banner);
    }
}
