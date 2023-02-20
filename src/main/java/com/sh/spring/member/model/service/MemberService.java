package com.sh.spring.member.model.service;

import com.sh.spring.member.model.dto.Member;

public interface MemberService {

	// 가입 - dml int
	int memberEnroll(Member member);

	
	// 멤버1명조회
	Member selectOneMember(String memberId);


	// 멤버1명 업데이트 
	int updateMember(Member member);

}
