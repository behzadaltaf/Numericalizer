[![Build Status](https://travis-ci.org/behzadaltaf/Numericalizer.svg?branch=master)](https://travis-ci.org/behzadaltaf/Numericalizer) [![Dependency Status](https://www.versioneye.com/user/projects/58429ba36f3ee40a6d50e547/badge.svg?style=flat-square)](https://www.versioneye.com/user/projects/58429ba36f3ee40a6d50e547)

# Numericalizer Tool

There are many [Machine Learning](https://en.wikipedia.org/wiki/Machine_learning) libraries that take data only in numeric format. 

This mandates that non numeric data should be preprocessed and converted into numeric data before the library is effectively used. 

This tool creates a file of numerical [feature vectors](https://en.wikipedia.org/wiki/Feature_vector) with an accompanying [codebook](https://en.wikipedia.org/wiki/Codebook) for data that is provided in non-numeric format. 

This tool produces a [categorical](https://en.wikipedia.org/wiki/Categorical_variable) list from the different values. 

It also has the ability to skip columns (that are marked `continuous` or are already `categorical`).

The user also has an option to input the column types as ***DATE/HOUR***. 

The date columns are converted into numerical using today's date as `pivot` date and finding a difference with the provided date.

The hour columns are subtracted by 24:00 HRS.

## Prerequisites

Please ensure you have the following installed on your system before proceeding.

[Git](https://git-scm.com/downloads)

[Apache Maven 3.x](https://maven.apache.org/index.html)

[Java 1.8.x ](https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html)

## Usage Steps

+ Clone the repo

`$ git clone https://github.com/behzadaltaf/Numericalizer.git
`

+  Build the jar

`$ mvn package
`

+ Run the jar


The above step would create an `uber` jar in the `target` folder


`Usage: java -jar NVConverter-jar-with-dependencies.jar <csvFile> [options...]`

```bash
    inputFile
        input file the customer dataset, required
    -d  <value>
        Date features column numbers e.g. 2 5, column numbers separated by space
    -df <value>
        Java Date Format e.g. dd/MM/YYYY (default: dd/MM/yyyy)
    -h  <value>
        Hour Features Column numbers e.g. 4 9, column numbers separated by space
    -hf <value>
        Hour Format e.g. HH:mm (default: HH:mm)
    -n  <value>
        Numerical features column numbers e.g. 1 16, column numbers separated by space
    -s  <value>
        CSV Split String e.g. |/, etc (default: ,)
```

## Example  

`java -jar target/NVConverter-jar-with-dependencies.jar dataFile.csv -n 1 16 -d 2 4 -df mm/dd/yyyy -h 7 8 -s |`

or

    java -jar target/NVConverter-jar-with-dependencies.jar \
        dataFile.csv \
        -n 1 16 \
        -d 2 4 \
        -df mm/dd/yyyy \
        -h 7 8 \
        -s |


## Notes

+ Dates are numericalized by taking the difference from today's date (run date) as pivot date.

+ Column Numbers start from `1` i.e. `1 to n` where `n` is total number of columns.

+ Columns that exceed the length of the csv headers are ***dropped***.

+ Blanks are converted to `0`.

+ The `csvfile` is the first argument, options are later.

+ Cells that have a `csvSplitBy (-s)` value are split accordingly thereby creating ***error*** condition.

+ Column numbers if repeated in options have the following ***precedence***
	
	+ Numeric
	
	+ Date
	
	+ Hour
