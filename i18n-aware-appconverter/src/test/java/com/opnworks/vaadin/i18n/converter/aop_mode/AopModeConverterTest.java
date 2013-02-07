package com.opnworks.vaadin.i18n.converter.aop_mode;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import junitx.framework.FileAssert;

import org.apache.commons.io.DirectoryWalker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)
public class AopModeConverterTest {

	public class ExpectedDirectoryWalker extends DirectoryWalker<Void> {

		private File expectedDir;
		private File actualDir;

		public void assertEquals(File expectedDir, File actualDir) throws IOException {
			this.expectedDir = expectedDir;
			this.actualDir = actualDir;
			walk(expectedDir, null);
		}

		private File getMatchingActualFile(File expectedFile) throws IOException {

			String expectedFileRelativePath = getRelativePath(expectedDir, expectedFile);

			File actualFile = new File(actualDir, expectedFileRelativePath);

			Assert.assertTrue("File '" + expectedFileRelativePath + "' expected in directory: " + actualDir.getAbsolutePath(), actualFile.exists());

			return actualFile;
		}

		private List<String> getPathList(File file) throws IOException {
			List<String> result = new ArrayList<String>();
			File r = file.getCanonicalFile();
			while (r != null) {
				result.add(r.getName());
				r = r.getParentFile();
			}
			return result;
		}

		private String getRelativePath(File home, File file) throws IOException {

			if (home == null) {
				fail("The 'home' param can't be null");
			}

			if (file == null) {
				fail("The 'file' param can't be null");
			}

			List<String> homelist = getPathList(home);

			if (homelist.isEmpty()) {
				fail("The homelist was empty for: " + home.getAbsolutePath());
			}

			List<String> filelist = getPathList(file);

			if (filelist.isEmpty()) {
				fail("The filelist was empty for: " + file.getAbsolutePath());
			}

			return matchPathLists(homelist, filelist);
		}

		private String matchPathLists(List<String> r, List<String> f) {

			if (r == null || r.isEmpty()) {
				fail("The homelist can't be empty");
			}

			if (f == null || f.isEmpty()) {
				fail("The filelist can't be empty");
			}

			// Start at the beginning of the lists iterate while both lists are
			// equal
			StringBuilder stringBuilder = new StringBuilder();

			int i = r.size() - 1;
			int j = f.size() - 1;

			// first eliminate common root
			while ((i >= 0) && (j >= 0) && (r.get(i).equals(f.get(j)))) {
				i--;
				j--;
			}

			// for each remaining level in the home path, add a ..
			for (; i >= 0; i-- ) {
				stringBuilder.append(".." + File.separator);
			}

			// for each level in the file path, add the path
			for (; j >= 1; j-- ) {
				stringBuilder.append(f.get(j) + File.separator);
			}

			if (j >= 0) {
				// file name
				stringBuilder.append(f.get(j));
			}

			return stringBuilder.toString();
		}

		@Override
		protected void handleFile(File expectedFile, int depth, Collection<Void> results) throws IOException {

			File actualFile = getMatchingActualFile(expectedFile);

			FileAssert.assertEquals(expectedFile, actualFile);
		}

	}

	@Parameters(name = "{index}: {0}")
	public static Collection<Object[]> data() {

		String[] testCases = AopModeConverterStatement.listTestCases();

		List<Object[]> result = new ArrayList<Object[]>(testCases.length);

		for (String testCase : testCases ) {
			result.add(new Object[] { testCase });
		}

		return result;
	}

	private AopModeConverter aopModeConverter;

	@Rule
	public AopModeConverterTestData testData = new AopModeConverterTestData();

	public AopModeConverterTest(String testcaseName) throws IOException {
		testData.setTestcaseName(testcaseName);
	}

	@Before
	public void setup() throws Exception {
		aopModeConverter = new AopModeConverter();
	}

	@Test
	public void testPerformI18NAwareProjectConversion() throws IOException {

		File sourceDirCopy = testData.getSourceDirCopy();
		File bundleDirCopy = testData.getBundleDirCopy();

		aopModeConverter.performI18NAwareProjectConversion(sourceDirCopy, bundleDirCopy, testData.getResourceBaseName(),
				testData.getDefaultLanguage(), testData.isRollback());

		assertDirectoryEquals(testData.getExpectedSourceDir(), sourceDirCopy);
		assertDirectoryEquals(testData.getExpectedBundleDir(), bundleDirCopy);
	}

	private void assertDirectoryEquals(File expectedDir, File actualDir) throws IOException {

		if (expectedDir == null || !expectedDir.exists()) {
			return;
		}

		new ExpectedDirectoryWalker().assertEquals(expectedDir, actualDir);
	}
}
