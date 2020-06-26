package com.toni;

public class HelloWorldTest {

	public void testgetHello(){
		HelloWorld helloWorld = new HelloWorld();
        assert("Hello World".equals(helloWorld.getHello()));
	}

}
