package com.baizhi;


//import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(value = "com.baizhi.dao")
public class CmfzZqApplication {

    public static void main(String[] args) { SpringApplication.run(CmfzZqApplication.class, args);
    }

}
