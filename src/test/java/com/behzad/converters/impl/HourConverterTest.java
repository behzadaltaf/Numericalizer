/**
 * 
 */
package com.behzad.converters.impl;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

/**
 * Created by Behzad Altaf
 */

public class HourConverterTest {

	private HourConverter hourConverter;
	
	@Before
	public void setUp() throws Exception {
		hourConverter= HourConverter.getInstance();
	}
	
	@Test
	public final void getInstanceShouldRetunTheSameInstance() {
		assertSame(hourConverter, HourConverter.getInstance());
	}

	@Test
	public void hourConverterShouldReturnLongFrom24Hrs() {
		long zero=hourConverter.convert("24:00", -1);
		long twentyFourHrs=hourConverter.convert("00:00", -1);
		long noonHrs=hourConverter.convert("12:00", -1);
		long badData=hourConverter.convert("2500", -1);
		assertEquals(0l, zero);
		assertEquals(86400000l, twentyFourHrs);
		assertEquals(43200000l, noonHrs);
		assertEquals(0l, badData);
	}

}
