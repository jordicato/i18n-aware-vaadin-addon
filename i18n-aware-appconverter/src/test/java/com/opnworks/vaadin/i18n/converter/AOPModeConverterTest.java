package com.opnworks.vaadin.i18n.converter;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.opnworks.vaadin.i18n.converter.aop_mode.AopModeConverter;
import com.opnworks.vaadin.i18n.converter.aop_mode.KeyConverter;
import com.opnworks.vaadin.i18n.converter.aop_mode.KeyConverter.Tkey;
import com.opnworks.vaadin.i18n.converter.main.CommandLineOutput;

public class AOPModeConverterTest {

	private static CommandLineOutput commandLineOutput = new CommandLineOutput();

	private static String testSourceDir = "src/test/resources/source-test";
	private static String testSourcedest = "src/test/resources/aux-test";

	private static final String RESOURCES_DIR = testSourcedest;// testSourcedest + "/src/main/resources";
	private static final String RESOURCE_BASE_NAME = "bundle";
	private static final String DEFAULT_LANGUAGE = "en";
	private static final boolean ROLLBACK = true;

	public static void CopyDirectory(File sourceDir, File sourceDes) throws Exception {
		try {
			if (sourceDir.isDirectory()) {
				if (!sourceDes.exists()) {
					sourceDes.mkdir();
				}

				String[] children = sourceDir.list();
				for (String element : children ) {
					CopyDirectory(new File(sourceDir, element), new File(sourceDes, element));
				}
			}
			else {
				CopyFile(sourceDir, sourceDes);
			}
		}
		catch (Exception e) {
			throw e;
		}
	}

	public static void CopyFile(File sourceDir, File sourceDes) throws Exception {

		InputStream in = new FileInputStream(sourceDir);
		OutputStream out = new FileOutputStream(sourceDes);

		byte[] buffer = new byte[1024];
		int len;

		try {
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
			out.flush();
		}
		catch (Exception e) {
			throw e;
		}
		finally {
			in.close();
			out.close();
		}
	}

	public static void deleteDirectory(File sourceDir) {

		try {
			for (File sourceFile : sourceDir.listFiles() ) {
				if (sourceFile.isDirectory()) {
					deleteDirectory(sourceFile);
					sourceFile.delete();
				}
				else {
					File children[] = sourceDir.listFiles();
					for (File element : children ) {
						element.delete();
					}
				}
			}
		}
		catch (Exception e) {

		}
	}

	public static boolean existDirectoryAux(File sourceDir) {
		String[] children = sourceDir.list();
		if (children.length > 0) {
			return true;
		}
		return false;
	}

	private AopModeConverter aopMode;

	private List<Tkey> listKey;

	@After
	public void resultVerification() throws Exception {
		KeyConverter keyConverter = new KeyConverter();

		if (!ROLLBACK) {
			assertTrue(keyConverter.existBundle(RESOURCES_DIR, RESOURCE_BASE_NAME));

			keyConverter.updateListKeyWithBundle();

			this.listKey = keyConverter.getListKey();

			for (int i = 0; i < listKey.size() - 1; i++ ) {
				assertTrue(keyConverter.isKey(listKey.get(i).getCompleteKey()));
				commandLineOutput.getOutput().println(listKey.get(i).getCompleteKey());
			}
		}
	}

	@Before
	public void setup() throws Exception {

		AopModeConverter convertionMethod = new AopModeConverter();
		this.aopMode = convertionMethod;

		if (!ROLLBACK) {
			if (existDirectoryAux(new File(testSourcedest))) {
				deleteDirectory(new File(testSourcedest));
				CopyDirectory(new File(testSourceDir), new File(testSourcedest));
			}
			else {
				CopyDirectory(new File(testSourceDir), new File(testSourcedest));
			}
		}
		else {
			if (!existDirectoryAux(new File(testSourcedest))) {
				CopyDirectory(new File(testSourceDir), new File(testSourcedest));
			}
		}
	}

	@Test
	public void testAopConverterMode() throws Exception {

		File auxTestSourceFile = new File(testSourcedest);
		File axuTestResourceDir = new File(RESOURCES_DIR);

		final File sourceDir = new File(auxTestSourceFile.getAbsoluteFile().toString());

		final File resourcesDir = new File(axuTestResourceDir.getAbsoluteFile().toString());

		aopMode.performI18NAwareProjectConversion(sourceDir, resourcesDir, RESOURCE_BASE_NAME, DEFAULT_LANGUAGE, ROLLBACK);

	}

}
