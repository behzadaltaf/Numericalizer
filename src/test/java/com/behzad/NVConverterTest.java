package com.behzad;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by Behzad Altaf
 */

public class NVConverterTest {

	private static String inputFileName;

	private static String outputFileName;
	
	private static File codeTestBook;

	private static File outputTestFile;

	private static String codeBookName;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		inputFileName = "data\\unit-test.csv";
		outputFileName = "data\\unit-test_OUTPUT.csv";
		codeBookName = "data\\unit-test_CODEBOOK.txt";
		outputTestFile =new File("data\\unit-test_OUTPUT_test.csv");
		codeTestBook = new File("data\\unit-test_CODEBOOK_test.txt");

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		new File(outputFileName).delete();
		new File(codeBookName).delete();
	}


	@Test
	public void checkIfTheContentsOfOutputFilesMatch() throws IOException {
		String[] args = { inputFileName };
		NVConverter.main(args);
		File oFile = new File(outputFileName);
		File cBook =new File(codeBookName);
		assertTrue(oFile.exists());
		assertTrue(cBook.exists());
		assertEquals(FileUtils.checksumCRC32(outputTestFile), FileUtils.checksumCRC32(oFile));
		assertEquals(FileUtils.checksumCRC32(codeTestBook), FileUtils.checksumCRC32(cBook));
	}
}
