package com.pri.anno.service.impl;

import com.pri.anno.annotation.ExtService;
import com.pri.anno.service.OrderService;

@ExtService
public class OrderServiceImpl implements OrderService {
	public void addOrder() {
		System.out.println("addOrder");
	}
}
