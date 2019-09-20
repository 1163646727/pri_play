package com.itmayiedu.controller;

import java.util.Scanner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableAutoConfiguration
@RestController
public class IndexController {
	// 读取配置文件中的端口号  ChenQi;
	@Value("${server.port}")
	private String port;

	@RequestMapping("/")
	public String index() {
		return "springboot2.0:" + port;
	}

	public static void main(String[] args) {
		SpringApplication.run(IndexController.class, args);
	}

/*	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String port = scanner.nextLine();
		new SpringApplicationBuilder(IndexController.class)
			.properties("server.port=" + port).run(args);
	}*/


}
