package com.behzad;

import com.behzad.constants.NVConstants;
import com.behzad.formatter.CSVFormatter;
import org.apache.commons.io.FilenameUtils;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.spi.StringArrayOptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Behzad Altaf
 */

public class NVConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(NVConverter.class);

    @Option(name = "-n", metaVar = NVConstants.LIST, handler = StringArrayOptionHandler.class, usage = NVConstants.NUMERICAL_FEATURE_MSG)
    private static List<String> numericalFeatures = new ArrayList<>();

    @Option(name = "-d", metaVar = NVConstants.LIST, handler = StringArrayOptionHandler.class, usage = NVConstants.DATE_FEATURE_MSG)
    private static List<String> dateFeatures = new ArrayList<>();

    @Option(name = "-h", metaVar = NVConstants.LIST, handler = StringArrayOptionHandler.class, usage = NVConstants.HOUR_FEATURE_MSG)
    private static List<String> hourFeatures = new ArrayList<>();

    @Option(name = "-df", metaVar = NVConstants.STRING, usage = NVConstants.DATE_FORMAT_MSG)
    private static String dateFormat = "dd/MM/yyyy";

    @Option(name = "-hf", metaVar = NVConstants.STRING, usage = NVConstants.HOUR_FORMAT_MSG)
    private static String hourFormat = "HH:mm";

    @Option(name = "-s", metaVar = NVConstants.STRING, usage = NVConstants.CSV_SPLIT_BY_MSG)
    private static String csvSplitBy = ",";

    private static String csvFile;

    private static File outputFile;

    private static File codeBook;

    @Argument
    private static List<String> arguments = new ArrayList<>();

    public static List<String> getNumericalFeatures() {
        return numericalFeatures;
    }

    public static List<String> getDateFeatures() {
        return dateFeatures;
    }

    public static List<String> getHourFeatures() {
        return hourFeatures;
    }

    public static String getDateFormat() {
        return dateFormat;
    }

    public static String getHourFormat() {
        return hourFormat;
    }

    public static String getCsvSplitBy() {
        return csvSplitBy;
    }

    public static String getCsvFile() {
        return csvFile;
    }

    public static File getOutputFile() {
        return outputFile;
    }

    public static File getCodeBook() {
        return codeBook;
    }

    public static void main(String[] args) {
        new NVConverter().run(args);
    }

    private static void getUsage(CmdLineParser parser, Exception e) {
        LOGGER.error(e.getMessage());
        LOGGER.error(NVConstants.USAGE_MSG);
        parser.printUsage(System.err);
        LOGGER.error(NVConstants.EXAMPLE_MSG);
    }

    private static void printArguments() {
        LOGGER.info("Numerical Features\t: {}", numericalFeatures);
        LOGGER.info("Date Features\t: {}", dateFeatures);
        LOGGER.info("Hour Features\t: {}", hourFeatures);
        LOGGER.info("Date Format\t: {}", dateFormat);
        LOGGER.info("Hour Format\t: {}", hourFormat);
        LOGGER.info("CSV Split By\t: {}", csvSplitBy);
    }

    private static void setFiles(String csvFileReceived) {
        String ext = FilenameUtils.getExtension(csvFileReceived);
        String path = FilenameUtils.getPath(csvFileReceived);
        String baseName = FilenameUtils.getBaseName(csvFileReceived);
        csvFile = arguments.get(0);
        if (ext == null || ext.isEmpty()) {
            outputFile = new File(path + baseName + NVConstants.OUTPUT + ext);
        } else {
            outputFile = new File(path + baseName + NVConstants.OUTPUT + NVConstants.DOT + ext);
        }
        codeBook = new File(path + baseName + NVConstants.CODEBOOK);
        LOGGER.info("\n\tInput File\t: {} \n\toutputFile\t: {} \n\tOutput CodeBook\t: {}", csvFile, outputFile, codeBook);
    }

    private void run(String[] args) {
        long startTime = System.currentTimeMillis();
        CmdLineParser parser = new CmdLineParser(this);
        parser.getProperties().withUsageWidth(120);
        try {
            parser.parseArgument(args);
            processArguments(parser);
            LOGGER.info(NVConstants.SPLASH_MSG);
            printArguments();
            CSVFormatter.process();
            LOGGER.info("Total time take by the process : {} ms", System.currentTimeMillis() - startTime);
        } catch (CmdLineException | IOException e) {
            getUsage(parser, e);
        }
    }

    @SuppressWarnings("deprecation")
    protected void processArguments(CmdLineParser parser) throws CmdLineException, IOException {
        if (arguments.isEmpty()) {
            throw new CmdLineException(parser, NVConstants.NO_ARGS_MSG);
        } else if (!(new File(arguments.get(0)).exists())) {
            throw new IOException(NVConstants.NO_INPUTFILE_MSG);
        } else {
            setFiles(arguments.get(0));
        }
    }

}
