package com.toni;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Junit5Test {

	@Test
	void testJunit5() {
		HelloWorld helloWorld = new HelloWorld();
		assertEquals("Hello World", helloWorld.getHello());
	}

}
