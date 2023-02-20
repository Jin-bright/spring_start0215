package com.sh.spring.proxy;

public class ProxyMain {

	public static void main(String[] args) {
	
//		Foo foo = new FooImpl();// 스프링에서는 이부분이 @Autowired로 의존주입받는 부분임 
//		Foo foo = new FooProxy(new FooImpl(), new Aspect());
//		String name = foo.bar("honggd");
//		System.out.println( "name = " + name ); 

		
		//대문자 처리 proxy 만들기 
		Foo foo = new FooProxy(new FooImpl(), new Aspect());
		String text = foo.getText();
		System.out.println( "text = "+ text );
	}
	
	
	

}
