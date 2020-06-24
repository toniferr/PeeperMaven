package com.toni;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PeeperMavenTestMain {
	
	@Test
    public void testMaven(){
	    assertTrue("5 is greater then 4", 5 > 4);
    }

	@Test
    public void testMaven2() {
	    assertFalse("5 is not greater then 6", 5 > 6);
    }

}
