package com.baizhi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/*
 *类的描述()
 *
 *@author zq
 *@date 2019/10/29 14:20
 *
 *@version V-1.1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User implements Serializable {
    @Id
    private String id;
    private String image;
    private String fname;
    private String username;
    private String sex;
    private String province;
    private String city;
    private String sign;
    private String phone;
    private String password;
    private String salt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createDate;
    private Date lastUpdateDate;
    private String status;
    private String guruId;
    @Transient
    private Integer number;
}
