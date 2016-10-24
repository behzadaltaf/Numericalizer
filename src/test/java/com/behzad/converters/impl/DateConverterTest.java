package com.behzad.converters.impl;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Behzad Altaf
 */

public class DateConverterTest {

	
	private DateConverter dateConverter;
	
	@Before
	public void setUp() throws Exception {
		dateConverter = DateConverter.getInstance();
	}
	
	@Test
	public final void getInstanceShouldRetunTheSameInstance() {
		assertSame(dateConverter, DateConverter.getInstance());
	}

	@Test
	public void dateConverterShouldReturnValidStringNotEqualToZeroForValidDates() {
		long date = dateConverter.convert("10/10/2012", -1);
		assertNotEquals(0l, date);
	}

	
	@Test
	public void dateConverterShouldReturnInValidStringEqualToZeroForInvalidDate() {
		long badDate = dateConverter.convert("13-13-2012", -1);
		assertEquals(0l, badDate);
	}
}
