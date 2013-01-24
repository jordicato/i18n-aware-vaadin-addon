package com.opnworks.vaadin.i18n.converter.main;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * The CommandLineOutput class
 * 
 * @author Pedro Rodriguez
 * 
 */
public class CommandLineOutput {

	private static final Logger LOGGER = Logger.getLogger(CommandLineOutput.class);

	private static final String FORMAT = "yyyy-MM-dd HH:mm:ss";

	private SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT);

	private PrintStream output;

	public CommandLineOutput() {
		this(System.out);
	}

	public CommandLineOutput(PrintStream output) {
		this.output = output;
	}

	public void println(String message) {
		LOGGER.info(message);
		output.println(dateFormat.format(new Date()) + " " + message);
	}

	public PrintStream getOutput() {
		return output;
	}

	public void setOutput(PrintStream output) {
		this.output = output;
	}

}
