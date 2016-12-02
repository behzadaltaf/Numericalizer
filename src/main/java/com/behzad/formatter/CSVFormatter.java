package com.behzad.formatter;

import com.behzad.NVConverter;
import com.behzad.constants.NVConstants;
import com.behzad.converters.impl.CategoricalConverter;
import com.behzad.util.ConvertersEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Behzad Altaf
 */

public class CSVFormatter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CSVFormatter.class);

    private static String[] columnProcessCode = null;

    private CSVFormatter() {
    }

    public static void process() throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(NVConverter.getCsvFile()));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(NVConverter.getOutputFile()))) {
            String header = bufferedReader.readLine();
            checkIfHeaderIsNull(header);
            bufferedWriter.write(header + NVConstants.NEWLINE);
            String[] splitHeader = header.split(NVConverter.getCsvSplitBy());
            generateColumnProcessCodes(splitHeader.length);
            StringBuilder modifiedLine;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] splitLine = line.split(NVConverter.getCsvSplitBy(), -1);
                modifiedLine = new StringBuilder();
                for (int columnNumber = 0; columnNumber < splitLine.length; columnNumber++) {
                    String datum = splitLine[columnNumber];
                    if (datum.isEmpty()) {
                        modifiedLine.append(0).append(NVConverter.getCsvSplitBy());
                    } else if (NVConstants.NUMERICAL.equals(columnProcessCode[columnNumber])) {
                        modifiedLine.append(datum).append(NVConverter.getCsvSplitBy());
                    } else {
                        modifiedLine.append(ConvertersEnum.getConverter(columnProcessCode[columnNumber]).convert(datum, columnNumber)).append(NVConverter.getCsvSplitBy());
                    }
                }
                bufferedWriter.write(modifiedLine.deleteCharAt(modifiedLine.length() - 1)
                        .append(NVConstants.NEWLINE).toString());
                bufferedWriter.flush();
            }
            CategoricalConverter.generateCodeBook(splitHeader);
        }
    }

    private static void checkIfHeaderIsNull(String header) {
        if (header == null || header.isEmpty())
            throw new IllegalArgumentException("Empty header provided. Please check input file.");
    }

    private static void generateColumnProcessCodes(int length) {
        columnProcessCode = new String[length];

        performColumnProcessCodeTransformation(NVConverter.getHourFeatures(), NVConstants.HOUR);
        performColumnProcessCodeTransformation(NVConverter.getDateFeatures(), NVConstants.DATE);
        performColumnProcessCodeTransformation(NVConverter.getNumericalFeatures(), NVConstants.NUMERICAL);

        columnProcessCode = Arrays.stream(columnProcessCode)
                .map(x -> x == null ? x = NVConstants.CATEGORICAL : x)
                .toArray(String[]::new);

        LOGGER.debug("Process Codes: " + Arrays.toString(columnProcessCode));
    }

    private static void performColumnProcessCodeTransformation(List<String> features, String processType) {
        features.stream()
                .map(Integer::valueOf)
                .filter(columnNumber -> columnNumber < columnProcessCode.length)
                .forEach(columnNumber -> columnProcessCode[columnNumber - 1] = processType);
    }
}
