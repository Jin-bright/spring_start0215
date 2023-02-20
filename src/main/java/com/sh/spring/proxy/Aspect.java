package com.sh.spring.proxy;

public class Aspect {
	
	public void before() {
		System.out.println("Aspect#before");
	}
	
	public void after() {
		System.out.println("Aspect#after");
	}
	
	// 소문자->대문자로 변환하는 부가기능 advice 
	public String toUpper(String text) {
		return text.toUpperCase();
	}
}
