package com.pri.service.impl;


import com.pri.ioc.anno.annotation.ExtService;
import com.pri.service.OrderService;
import org.springframework.stereotype.Service;

@ExtService
public class OrderServiceImpl implements OrderService {

	public void addOrder() {
		System.out.println("addOrder");
	}

}
