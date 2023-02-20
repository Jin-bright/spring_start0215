package com.sh.spring.demo.model.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Dev {
	private int no; // pk로 사용할 필드임 
	private String name;
	private int career;
	private String email;
	private Gender gender;
	private String[] lang;
	private LocalDateTime createdAt;
	
	//boilerplate - getset 기본+매개변수 생성자 이런 코드 맨날 맨날 뻔한 코드 
	// 그래서 lombok을 설치함 
	

}
