package com.sh.spring.demo.model.dao;

import java.util.List;

import com.sh.spring.demo.model.dto.Dev;

public interface DemoDao {

	int insertDev(Dev dev);

	List<Dev> selectDevList();

	//한명불러오기
	Dev selectOneDevList(int no);

	//한명수정하기 
	int updateDev(Dev dev);

}
