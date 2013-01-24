package com.opnworks.vaadin.i18n.converter.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

import com.opnworks.vaadin.i18n.converter.aop_mode.KeyConverter;
import com.opnworks.vaadin.i18n.converter.aop_mode.KeyConverter.Tkey;

/**
 * Main class wrapper to convert common Vaadin apps into I18n-aware Vaadin apps
 * 
 * @author aperez (Innovasoft Proyectos y Servicios)
 * 
 */
public class Main {
	public static void main(String[] args) throws Exception {
		String projectPath = "C:/SVN-OpnWorks/i18n-aware-vaadin-addon/i18n-aware-sampler/src";
		String projectDstPath = "C:/SVN-OpnWorks/i18n-aware-vaadin-addon/i18n-aware-sampler/src";
		String bundlePath = "/main/resources/";
		String bundleName = "bundle";
		// String src = "C:/SVN-OpnWorks/i18n-aware-vaadin-addon/i18n-aware-demo/src";
		for (int i = 0; i < args.length; i++ ) {
			if (args[i].equals("-s")) {
				projectPath = args[++i];
			}
		}

		// new Main(projectPath, bundlePath, bundleName);
	}

	/**
	 * 
	 * @param dirbaseSrc
	 *            path to original Vaadin app
	 * 
	 */
	Main(String dirbaseSrc, String bundlePath, String bundleName) {
		File FileBaseSrc = new File(dirbaseSrc);
		/*
		 * File delete = new File(dirbaseSrc + "/main/resources/bundle.properties"); if (delete.exists()) { delete.delete(); }
		 */

		KeyConverter conv = new KeyConverter();

		// Option to substitute texts to keys,
		// 1 = Change texts for keys, 2 = Return texts in the resource/bundle directly to the class
		conv.setChangeOptionKey(true);
		conv.proccessProject(FileBaseSrc, dirbaseSrc, bundlePath, bundleName);

		int a = conv.getContador();

		for (Tkey k : conv.getListKey() ) {
			writeFile(dirbaseSrc + bundlePath + bundleName + ".properties", k.getCompleteKey() + " = " + k.getValue());
		}

	}

	/**
	 * 
	 * @param dirbaseSrc
	 *            path to original Vaadin app
	 * @param dirbaseDst
	 *            path to i18n-aware api
	 */
	public void writeFile(String path, String param) {
		File archivo = new File(path);
		try {
			FileWriter escribirArchivo = new FileWriter(archivo, true);
			BufferedWriter buffer = new BufferedWriter(escribirArchivo);
			buffer.write(param);
			buffer.newLine();
			buffer.close();
		}
		catch (Exception ex) {
		}
	}

}
