package com.behzad.converters.impl;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

/**
 * Created by Behzad Altaf
 */

public class CategoricalConverterTest {
	
	private CategoricalConverter categoricalConverter;

	@Before
	public void setUp() throws Exception {
		categoricalConverter = CategoricalConverter.getInstance();
	}

	@Test
	public final void getInstanceShouldRetunTheSameInstance() {
		assertSame(categoricalConverter, CategoricalConverter.getInstance());
	}

	@Test
	public final void codeGenerationShouldBeALinerProgression() {
		int a= categoricalConverter.convert("ONE", 1);
		int b= categoricalConverter.convert("TWO", 1);
		int c= categoricalConverter.convert("THREE", 2);
		int d= categoricalConverter.convert("FOUR", 2);
		assertEquals(1, a);
		assertEquals(2, b);
		assertEquals(1, c);
		assertEquals(2, d);
	}
	
	@Test
	public final void sameCodeShouldBeReturnedForTheSameDataOfAColumn() {
		Integer a= categoricalConverter.convert("ONE", 1);
		Integer b= categoricalConverter.convert("TWO", 1);
		Integer c= categoricalConverter.convert("THREE", 2);
		Integer d= categoricalConverter.convert("FOUR", 2);
		assertEquals(a, categoricalConverter.convert("ONE", 1));
		assertEquals(b, categoricalConverter.convert("TWO", 1));
		assertEquals(c, categoricalConverter.convert("THREE", 2));
		assertEquals(d, categoricalConverter.convert("FOUR", 2));
	}

}
