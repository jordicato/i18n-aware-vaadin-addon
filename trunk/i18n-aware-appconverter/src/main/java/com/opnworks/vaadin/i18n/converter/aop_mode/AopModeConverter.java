package com.opnworks.vaadin.i18n.converter.aop_mode;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import com.opnworks.vaadin.i18n.converter.Converter;
import com.opnworks.vaadin.i18n.converter.aop_mode.KeyConverter.Tkey;

/**
 * The AOP mode conversion implementation
 */
public class AopModeConverter implements Converter {

	private static AopModeConverter singleton = new AopModeConverter();

	public static AopModeConverter getInstance() {
		return singleton;
	}

	@Override
	public void performI18NAwareProjectConversion(File sourceDir, File resourcesDir, String resourceBaseName, String defaultLanguage, boolean rollback) {
		KeyConverter keyConverter = new KeyConverter();
		keyConverter.setChangeOptionKey(rollback);
		keyConverter.proccessProject(sourceDir, sourceDir.getAbsolutePath(), resourcesDir.getAbsolutePath(), resourceBaseName, defaultLanguage);
		keyConverter.clearBundleFile(resourcesDir.getAbsolutePath() + "/" + resourceBaseName);
		// if (!rollback) {
		for (Tkey k : keyConverter.getListKey() ) {
			writeFile(resourcesDir.getAbsolutePath() + "/" + resourceBaseName + ".properties", k.getCompleteKey() + " = " + k.getValue());
		}
		// }
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
		}
	}
}
