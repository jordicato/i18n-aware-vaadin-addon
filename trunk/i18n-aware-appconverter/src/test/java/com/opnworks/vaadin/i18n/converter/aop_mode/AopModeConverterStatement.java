package com.opnworks.vaadin.i18n.converter.aop_mode;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.rules.TemporaryFolder;
import org.junit.runners.model.Statement;

public class AopModeConverterStatement extends Statement {

	public static File getTestcasesRootDirectory() {
		File result = new File(new File("target" + File.separatorChar + "test-classes").getAbsoluteFile(), "aop-mode-converter-testcases");
		if (!result.exists()) {
			fail("Missing testcases root directory: " + result.getAbsolutePath());
		}
		return result;
	}

	public static String[] listTestCases() {
		return getTestcasesRootDirectory().list();
	}

	private TemporaryFolder temporaryFolder = new TemporaryFolder();

	private final Statement base;

	private final AopModeConverterTestData data;

	public AopModeConverterStatement(Statement base, AopModeConverterTestData data) {
		super();
		this.base = base;
		this.data = data;
	}

	@Override
	public void evaluate() throws Throwable {

		try {
			temporaryFolder.create();

			String testcase = data.getTestcaseName();

			// Locate testcase root directory in project test resources
			File testcaseRootDirectory = getTestcaseRootDirectory(testcase);

			readTestcaseConfiguration(testcaseRootDirectory);

			File testcaseInputDirectory = new File(testcaseRootDirectory, "input");
			if (!testcaseInputDirectory.exists()) {
				fail("Missing testcase input directory: " + testcaseInputDirectory.getAbsolutePath());
			}

			// Make a full directory copy of tests resources that will be modified
			copyTestResources(testcaseInputDirectory);

			File testcaseExpectedOutputDirectory = new File(testcaseRootDirectory, "expected-output");
			if (!testcaseExpectedOutputDirectory.exists()) {
				fail("Missing testcase expected output directory: " + testcaseExpectedOutputDirectory.getAbsolutePath());
			}

			// Locate testcase expectations in project test resources
			data.setExpectedSourceDir(getExpectedOutput(testcaseExpectedOutputDirectory, "src"));
			data.setExpectedBundleDir(getExpectedOutput(testcaseExpectedOutputDirectory, "bundle"));

			// Perform the test
			base.evaluate();
		}
		finally {
			temporaryFolder.delete();
		}
	}

	private void copyTestResources(File testcaseRootDirectory) throws IOException {

		File sourceDir = new File(testcaseRootDirectory, "src");
		if (!sourceDir.exists()) {
			fail("Missing test source directory: " + sourceDir.getAbsolutePath());
		}

		File bundleDir = new File(testcaseRootDirectory, "bundle");
		if (!bundleDir.exists()) {
			fail("Missing test bundle directory: " + bundleDir.getAbsolutePath());
		}

		File sourceDirCopy = temporaryFolder.newFolder("src");
		FileUtils.copyDirectory(sourceDir, sourceDirCopy);
		data.setSourceDirCopy(sourceDirCopy);

		File resourcesDirCopy = temporaryFolder.newFolder("bundle");
		FileUtils.copyDirectory(bundleDir, resourcesDirCopy);
		data.setBundleDirCopy(resourcesDirCopy);
	}

	private File getExpectedOutput(File expectedOutputDir, String dirName) {

		File result = new File(expectedOutputDir, dirName);

		if (!result.exists()) {
			return null;
		}

		return result;
	}

	private File getTestcaseRootDirectory(String testcase) {
		File result = new File(getTestcasesRootDirectory(), testcase);
		if (!result.exists()) {
			fail("Missing testcase root directory: " + result.getAbsolutePath());
		}
		return result;
	}

	private void readTestcaseConfiguration(File testcaseRootDirectory) throws IOException {

		Properties properties = new Properties();

		InputStream input = null;
		try {
			input = new FileInputStream(new File(testcaseRootDirectory, "config.properties"));
			properties.load(input);
		}
		catch (FileNotFoundException e) {
			fail("Missing config file: " + e.getMessage());
		}
		finally {
			IOUtils.closeQuietly(input);
		}

		data.setResourceBaseName(properties.getProperty("resourceBaseName"));
		data.setDefaultLanguage(properties.getProperty("defaultLanguage"));
		data.setRollback(Boolean.parseBoolean(properties.getProperty("rollback")));

	}

}
