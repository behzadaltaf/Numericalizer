package com.behzad.converters.impl;

import com.behzad.constants.NVConstants;
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

public class HourConverter implements Converter {

    private static final Logger LOGGER = LoggerFactory.getLogger(HourConverter.class);

    private static HourConverter hourConverter;

    private Date hour;

    private SimpleDateFormat hourFormatter;

    private HourConverter() {
        try {
            hourFormatter = new SimpleDateFormat(NVConverter.getHourFormat());
            hour = hourFormatter.parse(NVConstants.HOUR24);
        } catch (ParseException e) {
            LOGGER.error("Error parsing master data, message is {}", e.getMessage());
        }
    }

    public static HourConverter getInstance() {
        if (hourConverter == null) {
            hourConverter = new HourConverter();
        }

        return hourConverter;
    }

    @Override
    public Long convert(String hourString, int columnNumber) {
        Date date = null;
        try {
            date = hourFormatter.parse(hourString);
        } catch (ParseException e) {
            LOGGER.warn("Unparsable Hour data: {} in Column: {}. Making it zero.", hourString, columnNumber);
            return 0L;
        }
        return hour.getTime() - date.getTime();
    }

}
