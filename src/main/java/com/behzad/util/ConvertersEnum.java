package com.behzad.util;

import com.behzad.converters.Converter;
import com.behzad.converters.impl.CatogericalConverter;
import com.behzad.converters.impl.DateConverter;
import com.behzad.converters.impl.HourConverter;

/**
 * Created by Behzad Altaf
 */

public class ConvertersEnum {

    public enum Converters {
        D {
            @Override
            public Converter getConverter() {
                return DateConverter.getInstance();
            }
        },
        H {
            @Override
            public Converter getConverter() {
                return HourConverter.getInstance();
            }
        },
        C {
            @Override
            public Converter getConverter() {
                return CatogericalConverter.getInstance();
            }
        };

        public abstract Converter getConverter();
    }

    public static Converter getConverter(String converterType) {
        return Converters.valueOf(converterType).getConverter();
    }
}
