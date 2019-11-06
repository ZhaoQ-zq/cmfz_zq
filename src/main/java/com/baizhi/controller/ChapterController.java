package com.baizhi.controller;

import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.math.BigDecimal;
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
@RequestMapping("chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;
    //selectAllChaptersByAlbumId
    @RequestMapping("selectAllChaptersByAlbumId")
    @ResponseBody
    public Map<String,Object> showAll(String id,Integer page,Integer rows)throws Exception{

        Map<String, Object> map = chapterService.selectAll(id,page, rows);

        return map;
    }

    @RequestMapping("edit")
    @ResponseBody
    public void edit(String oper, Chapter chapter)throws Exception{
        if ("add".equals(oper)){
            chapterService.add(chapter);
        }else if ("edit".equals(oper)){
            chapterService.update(chapter);
        }else if ("del".equals(oper)){
            chapterService.del(chapter.getId());
        }
    }

    @RequestMapping("upload")
    @ResponseBody
    public void upload(MultipartFile upload,HttpSession session)throws Exception{
        Chapter chapter=(Chapter)session.getAttribute("chapter");
        String realPath = session.getServletContext().getRealPath("/music");
        File file = new File(realPath + "/" + upload.getOriginalFilename());
        if (!file.exists())upload.transferTo(file);
        if (!upload.getOriginalFilename().equals("")){
            Encoder encoder = new Encoder();
            MultimediaInfo info = encoder.getInfo(file);
            long duration = info.getDuration();

            //BigDecimal bigDecimal = new BigDecimal();
            Double size = file.length()/1000/1000.0;
            chapter.setSize(size);
            String minute=duration/60000>=10?duration/60000+"":"0"+duration/60000;
            String second=(duration/1000-duration/60000*60)>10?(duration/1000-duration/60000*60)+"":"0"+(duration/1000-duration/60000*60);
            chapter.setDuration(minute+":"+second);
            chapter.setUrl(upload.getOriginalFilename());
        }
        chapterService.update(chapter);
    }
}
