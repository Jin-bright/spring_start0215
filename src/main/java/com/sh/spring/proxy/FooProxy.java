package com.sh.spring.proxy;

public class FooProxy implements Foo {

	private Foo target; //주업무로직을 가진 객체 
	private Aspect aspect; //aop객체(advice가 안에잇음 )


	public FooProxy(Foo target, Aspect aspect) {
		this.target = target;
		this.aspect = aspect;
	}
	
	
	
	
	@Override
	public String bar(String id) {
		aspect.before();
		String name = target.bar(id);
		aspect.after();
		return name;
	}




	
	// 내가한건데 이러면 부가업무로직이 없는게 되나  ?
/**
 	public String getText() {
		aspect.before();
		String texts = target.getText().toUpperCase();
		aspect.after();
		return texts;
	}
**/
	@Override
	public String getText() {
		aspect.before();
		String text = target.getText();
		return aspect.toUpper(text);
	}
	
	
}
