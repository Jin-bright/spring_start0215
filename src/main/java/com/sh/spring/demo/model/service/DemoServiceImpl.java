package com.sh.spring.demo.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.spring.demo.model.dao.DemoDao;

@Service
public class DemoServiceImpl implements DemoService {

	@Autowired
	private DemoDao demoDao;

	// 1 - 절대 인터페이스에 붙이면 안되고(99퍼), 구현클래스에 어노테이션붙여야됨   
	// 2 의존 주입 필요 - 절대 인터페이스에 붙이면 안되고(99퍼), 구현클래스에 어노테이션붙여야됨  
}
