package com.sh.spring.demo.model.service;

import java.util.List;

import com.sh.spring.demo.model.dto.Dev;

public interface DemoService {

	int insertDev(Dev dev);

	//여러명 불러오기
	List<Dev> selectDevList();

	//한명불러오기
	Dev selectOneDevList(int no);

	//한명수정하기 - update - int
	int updateDev(Dev dev);

	
}
