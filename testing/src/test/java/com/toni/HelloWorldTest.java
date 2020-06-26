package com.toni;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HelloWorldTest {

	@Test
	void getHello() {
		HelloWorld helloWorld = new HelloWorld();
		assertEquals("Hello World", helloWorld.getHello());
	}

}
