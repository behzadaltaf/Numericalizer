package com.behzad.converters.impl;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

/**
 * Created by Behzad Altaf
 */

public class CatogericalConverterTest {
	
	private CatogericalConverter catogericalConverter;

	@Before
	public void setUp() throws Exception {
		catogericalConverter= CatogericalConverter.getInstance();
	}

	@Test
	public final void getInstanceShouldRetunTheSameInstance() {
		assertSame(catogericalConverter, CatogericalConverter.getInstance());
	}

	@Test
	public final void codeGenerationShouldBeALinerProgression() {
		int a=catogericalConverter.convert("ONE", 1);
		int b=catogericalConverter.convert("TWO", 1);
		int c=catogericalConverter.convert("THREE", 2);
		int d=catogericalConverter.convert("FOUR", 2);
		assertEquals(1, a);
		assertEquals(2, b);
		assertEquals(1, c);
		assertEquals(2, d);
	}
	
	@Test
	public final void sameCodeShouldBeReturnedForTheSameDataOfAColumn() {
		Integer a=catogericalConverter.convert("ONE", 1);
		Integer b=catogericalConverter.convert("TWO", 1);
		Integer c=catogericalConverter.convert("THREE", 2);
		Integer d=catogericalConverter.convert("FOUR", 2);
		assertEquals(a, catogericalConverter.convert("ONE", 1));
		assertEquals(b, catogericalConverter.convert("TWO", 1));
		assertEquals(c, catogericalConverter.convert("THREE", 2));
		assertEquals(d, catogericalConverter.convert("FOUR", 2));
	}

}
