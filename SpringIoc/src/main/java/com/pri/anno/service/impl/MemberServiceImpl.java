package com.pri.anno.service.impl;

import com.pri.anno.annotation.ExtService;
import com.pri.service.MemberService;
@ExtService
public class MemberServiceImpl implements MemberService {
	public void memberAdd() {
		System.out.println("memberAdd");
	}

}
