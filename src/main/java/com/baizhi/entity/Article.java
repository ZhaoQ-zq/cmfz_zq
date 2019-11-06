package com.baizhi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/*
 *类的描述()
 *
 *@author zq
 *@date 2019/10/29 14:31
 *
 *@version V-1.1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "article")
@Component
public class Article implements Serializable {
    @Id
    private String id;
    private String title;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createDate;
    private String lastUpdateDate;
    private String status;
    private Date publishDate;
    private String author;
    private String guruImg;
    private String guruId;


}
