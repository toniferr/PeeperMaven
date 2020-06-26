package com.toni;

import static org.junit.Assert.*;

import org.junit.Test;

public class Junit4Test {

	@Test
	public void getHello() {
		HelloWorld helloWorld = new HelloWorld();
		assertEquals("Hello World", helloWorld.getHello());
	}

}