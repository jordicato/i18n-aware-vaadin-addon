package com.opnworks.vaadin.i18n.converter.main;

import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.opnworks.vaadin.i18n.converter.ConversionMethod;

public class I18NAwareProjectConverter {

	private static final Logger LOGGER = Logger.getLogger(I18NAwareProjectConverter.class);

	private static final String SOURCE_DIR = "sourceDir";
	private static final String METHOD = "method";
	private static final String ROLLBACK = "rollback";

	public static CommandLineOutput commandLineOutput = new CommandLineOutput();

	public static void main(String[] args) throws Exception {

		CommandLineParser parser = new GnuParser();

		Options options = createOptions();

		CommandLine line;
		try {
			line = parser.parse(options, args);
		}
		catch (ParseException e) {
			LOGGER.error(e);
			commandLineOutput.getOutput().println("Fatal error parsing command line. " + e.getMessage());
			return;
		}

		if (line.hasOption("help")) {
			help(options);
			return;
		}

		String sourceDirName = line.getOptionValue(SOURCE_DIR);

		if (sourceDirName == null) {
			commandLineOutput.getOutput().println("Expecting '" + SOURCE_DIR + "' parameter");
			return;
		}

		final File sourceDir = new File(sourceDirName);

		if (!sourceDir.exists() || !sourceDir.isDirectory()) {
			commandLineOutput.getOutput().println("Source directory not found: " + sourceDir.getAbsolutePath());
			return;
		}

		ConversionMethod conversionMethod = getConversionMethod(line);

		if (conversionMethod == null) {
			return;
		}

		boolean rollback = line.hasOption(ROLLBACK);

		if (line.hasOption("log4jConfigurationPath")) {
			String log4jConfigurationPath = line.getOptionValue("log4jConfigurationPath");
			DOMConfigurator.configure(log4jConfigurationPath);
		}

		performI18NAwareProjectConversion(sourceDir, conversionMethod, rollback);
	}

	@SuppressWarnings("static-access")
	private static Options createOptions() {

		Option helpOption = new Option("help", "print this message");
		Option sourceDirOption = OptionBuilder.withDescription("The source directory").hasArg().create(SOURCE_DIR);
		Option conversionMethodOption = OptionBuilder.withDescription("The conversion method").hasArg().create(METHOD);
		Option rollbackOption = OptionBuilder.withDescription("Rollback conversion").hasArg(false).create(ROLLBACK);

		Options options = new Options();

		options.addOption(helpOption);
		options.addOption(sourceDirOption);
		options.addOption(conversionMethodOption);
		options.addOption(rollbackOption);

		return options;
	}

	private static ConversionMethod getConversionMethod(CommandLine line) throws RuntimeException {

		final String[] choices = { ConversionMethod.aop_mode.name(), ConversionMethod.explicit_mode.name() };

		String methodValue = line.getOptionValue(METHOD);

		if (methodValue == null) {
			return ConversionMethod.aop_mode;
		}

		if (ArrayUtils.contains(choices, methodValue)) {
			return ConversionMethod.valueOf(methodValue);
		}

		commandLineOutput.getOutput().println("Invalid conversionMethod value: '" + methodValue + "', should be one of " + Arrays.toString(choices));

		return null;
	}

	/**
	 * Automatically generate the help statement
	 */
	private static void help(Options options) {

		HelpFormatter formatter = new HelpFormatter() {
			@Override
			public void printHelp(int width, String cmdLineSyntax, String header, Options opts, String footer, boolean autoUsage) {
				PrintWriter pw = null;
				try {
					pw = new PrintWriter(commandLineOutput.getOutput());
					printHelp(pw, width, cmdLineSyntax, header, opts, getLeftPadding(), getDescPadding(), footer, autoUsage);
					pw.flush();
				}
				finally {
					if (pw != null) {
						pw.close();
					}
				}
			}
		};

		formatter.printHelp("I18NAwareProjectConverter", options, true);
	}

	private static void performI18NAwareProjectConversion(File sourceDir, ConversionMethod conversionMethod, boolean rollback) {

		commandLineOutput.println("Running I18NAware project convertion:");
		commandLineOutput.println("sourceDir: " + sourceDir.getAbsolutePath());
		commandLineOutput.println("conversionMethod: " + conversionMethod.name());
		commandLineOutput.println("rollback: " + rollback);

		conversionMethod.getConverter().performI18NAwareProjectConversion(sourceDir, rollback);
	}

}
