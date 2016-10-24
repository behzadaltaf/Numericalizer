package com.behzad.util;

import static org.junit.Assert.assertSame;


import org.junit.Before;
import org.junit.Test;

import com.behzad.converters.impl.CatogericalConverter;
import com.behzad.converters.impl.DateConverter;
import com.behzad.converters.impl.HourConverter;

/**
 * Created by Behzad Altaf
 */

public class ConvertersEnumTest {

	private CatogericalConverter catogericalConverter;

	private DateConverter dateConverter;

	private HourConverter hourConverter;

	@Before
	public void setUp() throws Exception {
		catogericalConverter = CatogericalConverter.getInstance();
		dateConverter = DateConverter.getInstance();
		hourConverter = HourConverter.getInstance();
	}

	@Test
	public void getConverterShouldReturnTheSameObjectBasedOnSuppliedString() {
		assertSame(catogericalConverter, ConvertersEnum.getConverter("C"));
		assertSame(dateConverter, ConvertersEnum.getConverter("D"));
		assertSame(hourConverter, ConvertersEnum.getConverter("H"));
	}

}
