package com.behzad.constants;

/**
 * Created by Behzad Altaf
 */

public class NVConstants {

    public static final String NEWLINE = "\n";

    public static final String TAB = "\t";

    public static final String EMPTY = "";

    public static final String SPLASH_MSG = "This program produces two outputfiles. Transformed numericalized file and an accompanying codebook.";

    public static final String USAGE_MSG = "Usage: java -jar NVConverter.jar <csvFile> [options...]";

    public static final String EXAMPLE_MSG = "Example:  java -jar NVConverter.jar dataFile.csv -n 1 16 -d 2 4 -df mm/dd/yyyy -h 7 8 -s |";

    public static final String NO_ARGS_MSG = "No arguments supplied. Please provide at least the csv file.";

    public static final String DATE_USAGE_MSG = "DATE FORMAT ERROR: Please provide a valid java date format.";

    public static final String NUMERICAL_FEATURE_MSG = "Numerical features column numbers e.g. 1 16, column numbers separated by space";

    public static final String DATE_FEATURE_MSG = "Date features column numbers e.g. 2 5, column numbers separated by space";

    public static final String HOUR_FEATURE_MSG = "Hour Features Column numbers e.g. 4 9, column numbers separated by space";

    public static final String DATE_FORMAT_MSG = "Java Date Format e.g. dd/MM/YYYY";

    public static final String HOUR_FORMAT_MSG = "Hour Format e.g. HH:mm";

    public static final String CSV_SPLIT_BY_MSG = "CSV Split String e.g. |/, etc";

    public static final String LIST = " List";

    public static final String STRING = "String";

    public static final String HOUR24 = "24:00";

    public static final String COLUMN_NUMBER = "Column Number: ";

    public static final String COLUMN_NAME = " Column Name: ";

    public static final String OUTPUT = "_OUTPUT";

    public static final String DOT = ".";

    public static final String CODEBOOK = "_CODEBOOK.txt";

    public static final String NUMERICAL = "N";

    public static final String DATE = "D";

    public static final String HOUR = "H";

    public static final String CATEGORICAL = "C";

    public static final String CODE_VALUE = "Code\tValue\n";

    public static final String NO_INPUTFILE_MSG = "Input file not found. Please check input.";

    private NVConstants() {
    }
}