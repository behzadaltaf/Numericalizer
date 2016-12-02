package com.behzad.converters.impl;

import com.behzad.NVConverter;
import com.behzad.constants.NVConstants;
import com.behzad.converters.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Behzad Altaf
 */

public class CategoricalConverter implements Converter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoricalConverter.class);

    private Map<Integer, Map<String, Integer>> columnsCategoricalCodes;

    private Map<Integer, Integer> maximumCodeOfColumn;

    private static CategoricalConverter codeConverter;

    private CategoricalConverter() {
        columnsCategoricalCodes = new HashMap<>();
        maximumCodeOfColumn = new HashMap<>();
    }

    public static CategoricalConverter getInstance() {
        if (codeConverter == null) {
            codeConverter = new CategoricalConverter();
        }
        return codeConverter;
    }

    @Override
    public Integer convert(String datum, int columnNumber) {
        Map<String, Integer> associatedMapOfColumn = columnsCategoricalCodes.get(columnNumber);
        if (associatedMapOfColumn == null) {
            associatedMapOfColumn = new LinkedHashMap<>();
            associatedMapOfColumn.put(datum, 1);
            maximumCodeOfColumn.put(columnNumber, 1);
            columnsCategoricalCodes.put(columnNumber, associatedMapOfColumn);
            return 1;
        } else if (associatedMapOfColumn.get(datum) == null) {
            Integer maxValue = maximumCodeOfColumn.get(columnNumber);
            maximumCodeOfColumn.put(columnNumber, ++maxValue);
            associatedMapOfColumn.put(datum, maxValue);
            return maxValue;
        } else {
            return associatedMapOfColumn.get(datum);
        }
    }

    public static void generateCodeBook(String[] header) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(NVConverter.getCodeBook()))) {
            codeConverter.columnsCategoricalCodes.forEach((columnNumber, associatedMapOfColumn) -> {
                StringBuilder outputColumnHeader = new StringBuilder()
                        .append(NVConstants.NEWLINE)
                        .append(NVConstants.COLUMN_NUMBER)
                        .append(columnNumber + 1)
                        .append(NVConstants.COLUMN_NAME)
                        .append(header[columnNumber])
                        .append(NVConstants.NEWLINE)
                        .append(NVConstants.CODE_VALUE);
                try {
                    bufferedWriter.write(outputColumnHeader.toString());
                    associatedMapOfColumn.forEach((category, numericalValue) -> {
                        try {
                            StringBuilder outputColumnValues = new StringBuilder()
                                    .append(numericalValue)
                                    .append(NVConstants.TAB)
                                    .append(NVConstants.TAB)
                                    .append(category)
                                    .append(NVConstants.NEWLINE);
                            bufferedWriter.write(outputColumnValues.toString());
                        } catch (IOException e) {
                            LOGGER.error("Error creating codebook.", e);
                        }
                    });
                } catch (IOException e) {
                    LOGGER.error("Error creating codebook.", e);
                }
            });
        }
    }
}
