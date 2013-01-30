package com.opnworks.vaadin.i18n.converter;

import java.io.File;
import org.junit.Test;

import com.opnworks.vaadin.i18n.converter.aop_mode.AopModeConverter;
import com.opnworks.vaadin.i18n.converter.explicit_mode.ExplicitModeConverter;
import com.opnworks.vaadin.i18n.converter.main.CommandLineOutput;

public class AOPModeConverterTest {

	private static CommandLineOutput commandLineOutput = new CommandLineOutput();

	private static final String SOURCE_DIR = "C:/SVN-OpnWorks/i18n-aware-vaadin-addon/i18n-aware-sampler/src/main";
	private static final String RESOURCES_DIR = "C:/SVN-OpnWorks/i18n-aware-vaadin-addon/i18n-aware-sampler/src/main/resources";
	private static final String RESOURCE_BASE_NAME = "bundle";
	private static final String DEFAULT_LANGUAGE = "en";
	private static final boolean ROLLBACK = true;

	@Test
	public void testAopConverterMode() throws Exception {

		final File sourceDir = new File(SOURCE_DIR);

		final File resourcesDir = new File(RESOURCES_DIR);

		AopModeConverter convertionMethod = new AopModeConverter();

		convertionMethod.performI18NAwareProjectConversion(sourceDir, resourcesDir, RESOURCE_BASE_NAME, DEFAULT_LANGUAGE, ROLLBACK);

	}

	/*@Test
	public void testExplicitConverterMode() throws Exception {

		final File sourceDir = new File(SOURCE_DIR);

		final File resourcesDir = new File(RESOURCES_DIR);

		ExplicitModeConverter convertionMethod = new ExplicitModeConverter();

		convertionMethod.performI18NAwareProjectConversion(sourceDir, resourcesDir, RESOURCE_BASE_NAME, DEFAULT_LANGUAGE, ROLLBACK);

	}*/
	
}
