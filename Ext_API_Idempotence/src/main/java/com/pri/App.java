package com.pri;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * className: App <BR>
 * description: <BR>
 * remark: <BR>
 * auther: ChenQi <BR>
 * date: 2019/10/31 23:37 <BR>
 * version 1.0 jdk1.8 <BR>
 */
@MapperScan(basePackages = "com.pri.dao")
@SpringBootApplication
public class App{
    public static void main(String[] args) {
        SpringApplication.run (App.class,args);
    }
}
