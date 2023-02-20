package com.sh.spring.member.model.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
//Equivalent to @Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode. 
public class Member {
	
//	@Getter - 이런거 한방에 처리할수있는 어노테이션 -완전똑같진않음  
	@NonNull
	private String memberId;
	@NonNull
	private String password;
	@NonNull
	private String name;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthday; //1999-09-09 사용자 요청처리 시 필요 .. ?
	private String email;
	@NonNull
	private String phone;
	private LocalDateTime createdAt;
	private boolean enabled;
	
	

}
