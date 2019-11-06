package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "album")
public class Album implements Serializable {
    @Id
    private String id;
    @Excel(name = "标题",needMerge = true)
    private String title;
    @Excel(name = "封面",type = 2,width = 40,height = 20,needMerge = true)
    private String cover;

    private Integer chapterCount;

    private Double score;

    private String author;

    private String broadcast;

    private Date publishDate;

    private String brief;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createDate;

    private Date lastUpdateDate;

    private String status;

    private String guruId;

    @ExcelCollection(name = "所有章节")
    private List<Chapter> chapters;


}