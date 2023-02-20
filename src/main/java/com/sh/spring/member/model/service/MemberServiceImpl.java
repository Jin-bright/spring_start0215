package com.sh.spring.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.spring.member.model.dao.MemberDao;
import com.sh.spring.member.model.dto.Member;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao memberDao;

	@Override
	public int memberEnroll(Member member) {
		// TODO Auto-generated method stub
		return memberDao.memberEnroll(member);
	}

	//1명조회
	@Override
	public Member selectOneMember(String memberId) {
		// TODO Auto-generated method stub
		return memberDao.selectOneMember(memberId);
	}

	
	//1명 업데이트 
	@Override
	public int updateMember(Member member) {
		// TODO Auto-generated method stub
		return memberDao.updateMember(member);
	}
	
}
