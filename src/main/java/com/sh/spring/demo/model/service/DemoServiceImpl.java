package com.sh.spring.demo.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.spring.demo.model.dao.DemoDao;
import com.sh.spring.demo.model.dto.Dev;

@Service
public class DemoServiceImpl implements DemoService {
	// 1 - 절대 인터페이스에 붙이면 안되고(99퍼), 구현클래스에 어노테이션붙여야됨   
	// 2 의존 주입 필요 - 절대 인터페이스에 붙이면 안되고(99퍼), 구현클래스에 어노테이션붙여야됨  
	
	@Autowired
	private DemoDao demoDao;

	/**
	 * SqlSession
	 * - 트랜잭션처리, sqlSession 생성 및 반환 모두 생략
	 */
	@Override
	public int insertDev(Dev dev) {
		// TODO Auto-generated method stub
		return demoDao.insertDev(dev);
	}

	@Override
	public List<Dev> selectDevList() {
		// TODO Auto-generated method stub
		return demoDao.selectDevList();
	}

	//한명불러오기
	@Override
	public Dev selectOneDevList(int no) {
		// TODO Auto-generated method stub
		return demoDao.selectOneDevList(no);
	}

	//한명수정하기
	@Override
	public int updateDev(Dev dev) {
		// TODO Auto-generated method stub
		return demoDao.updateDev(dev);
	}


}
