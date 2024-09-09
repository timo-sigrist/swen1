package ch.zhaw.swen1.lightingapp.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class RGBColorPercentageTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void equalsSameObject() {
		assertEquals(RGBColorPercentage.WHITE, RGBColorPercentage.WHITE);
	}

	@Test
	public void equalsDifferentObjects() {
		assertEquals(RGBColorPercentage.WHITE, new RGBColorPercentage(100, 100, 100));
	}

}
