package com.sh.spring.member.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.sh.spring.member.model.dto.Member;

/**
 *  mapper.xml로 연결 
 *  추상메소드에 @insert @update @delete @select 를 통해 간단한 쿼리 직접 작성 
 * @author brigh
 *
 */

@Mapper
public interface MemberDao {

	int memberEnroll(Member member);

	//member1명조회
	Member selectOneMember(String memberId);

	//member 1명 업데이트 
	int updateMember(Member member);

}
