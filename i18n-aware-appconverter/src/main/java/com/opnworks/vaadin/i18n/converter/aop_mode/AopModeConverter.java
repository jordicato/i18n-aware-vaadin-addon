package com.opnworks.vaadin.i18n.converter.aop_mode;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import com.neodoo.maven.plugin.LocalizeMojo;
import com.opnworks.vaadin.i18n.converter.Converter;
import com.opnworks.vaadin.i18n.converter.aop_mode.KeyConverter.Key;
import com.opnworks.vaadin.i18n.converter.main.CommandLineOutput;


/**
 * The AOP mode conversion implementation
 */
public class AopModeConverter implements Converter {

	private static AopModeConverter singleton = new AopModeConverter();
	
	public static CommandLineOutput commandLineOutput = new CommandLineOutput();
	
	public static AopModeConverter getInstance() {
		return singleton;
	}

	@Override
	public void performI18NAwareProjectConversion(File sourceDir, File resourcesDir, String resourceBaseName, String defaultLanguage, String targetLanguages, boolean rollback) {
		KeyConverter keyConverter = new KeyConverter();
		keyConverter.setChangeOptionKey(rollback);
		keyConverter.proccessProject(sourceDir, sourceDir.getAbsolutePath(), resourcesDir.getAbsolutePath(), resourceBaseName, defaultLanguage);
		keyConverter.clearBundleFile(resourcesDir.getAbsolutePath() + "/" + resourceBaseName, defaultLanguage);
		
		for (Key k : keyConverter.getListKey() ) {
			writeFile(resourcesDir.getAbsolutePath() + "/" + resourceBaseName + "_" + defaultLanguage + ".properties", k.getCompleteKey() + " = " + k.getValue());
		}		
		
		if (!rollback && (targetLanguages != null)) {		
			try {
				generateBundlesFiles(resourcesDir, resourceBaseName, defaultLanguage, targetLanguages, rollback);
			}
			catch (MojoExecutionException e) {
				commandLineOutput.getOutput().println("Mojo execution exception caused by: " +
				e.getMessage());
			}
			catch (MojoFailureException e) {
				commandLineOutput.getOutput().println("Mojo failure exception caused by: " +
				e.getMessage());
			}
		}		
	}
		
	private void generateBundlesFiles(File resourcesDir, String resourceBaseName, String defaultLanguage, String targetLanguages, boolean rollback) throws MojoExecutionException, MojoFailureException {	
		LocalizeMojo localizeMojo = new LocalizeMojo();		
		
		localizeMojo.setShowProgress(true);
		localizeMojo.setApiKey("491CDC3769B93528E0388F1C1A8DB54349BBD132");
		localizeMojo.setDebug(true);
		localizeMojo.setSmartSync(true);
		localizeMojo.setSmartSyncChangeFile("bundlechangefile");
		localizeMojo.setSourceTranslationPath(resourcesDir);
		localizeMojo.setDestinationPath(resourcesDir);
		localizeMojo.setLanguageFilePattern(resourceBaseName + "_{0}.properties");
		localizeMojo.setSourceLanguage(defaultLanguage);
		localizeMojo.setTargetLanguages(targetLanguages);
		localizeMojo.setDestinationFileEncoding("8859_1");
		localizeMojo.setDestinationFileEncodingIncludeBOM(true);
				
		localizeMojo.execute();
	}
	
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
			commandLineOutput.getOutput().println("FileWriter Failed: " + ex.getMessage());
		}
	}
}
