package com.behzad.converters;

/**
 * Created by Behzad Altaf
 */

public interface Converter <T> {

    public T convert(String datum, int columnNumber);

}
