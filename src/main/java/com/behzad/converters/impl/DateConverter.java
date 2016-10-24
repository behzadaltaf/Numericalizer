package com.behzad.converters.impl;

import com.behzad.NVConverter;
import com.behzad.converters.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Behzad Altaf
 */

public class DateConverter implements Converter {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateConverter.class);

    private static DateConverter dateConverter;

    private SimpleDateFormat dateFormatter;

    private Date currentDate;

    private DateConverter() {
        dateFormatter = new SimpleDateFormat(NVConverter.getDateFormat());
        currentDate = new Date();
    }

    public static DateConverter getInstance() {
        if (dateConverter == null) {
            dateConverter = new DateConverter();
        }
        return dateConverter;
    }

    @Override
    public Long convert(String dateAsString, int columnNumber) {
        Date date = null;
        try {
            date = dateFormatter.parse(dateAsString);
        } catch (ParseException e) {
            LOGGER.warn("Unparsable Date data: {} in Column: {}. Making it zero.", dateAsString, columnNumber);
            return 0L;
        }
        return currentDate.getTime() - date.getTime();
    }
}
