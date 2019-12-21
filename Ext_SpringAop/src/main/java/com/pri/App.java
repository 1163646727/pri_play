package com.pri;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
// import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
// import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * className: App <BR>
 * description: 项目启动入口<BR>
 * remark: <BR>
 * auther: ChenQi <BR>
 * date: 2019/9/22 21:24 <BR>
 * version 1.0 jdk1.8 <BR>
 */
// @ComponentScan("com.pri")
@SpringBootApplication
@Configuration
// @EnableAspectJAutoProxy
public class App extends SpringBootServletInitializer{
    public static void main(String[] args) {
        SpringApplication.run (App.class,args);
    }
}
