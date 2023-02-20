package com.sh.spring.demo.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sh.spring.demo.model.dto.Dev;

// 얘는 dao라는 어노세이션ㅌ없고 repository 해야됨 
@Repository
public class DemoDaoImpl implements DemoDao {
	@Autowired
	private SqlSessionTemplate session;
	
	//insert - dml - sqlsession을 의존주입받는다 
	@Override
	public int insertDev(Dev dev) {
		// TODO Auto-generated method stub
		return session.insert("demo.insertDev", dev); //여기서 매퍼로 걸라념
	}

	// 여러명 select
	@Override
	public List<Dev> selectDevList() {
		return session.selectList("demo.selectDevList");
	}

	//한명 select 
	@Override
	public Dev selectOneDevList(int no) {
		// TODO Auto-generated method stub
		return session.selectOne("demo.selectOneDevList", no);
	}

	//한명 update 
	@Override
	public int updateDev(Dev dev) {
		// TODO Auto-generated method stub
		return session.update("demo.updateDev", dev);
	}

}
