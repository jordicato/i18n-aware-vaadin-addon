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
	public void performI18NAwareProjectConversion(File sourceDir, boolean rollback) {

		KeyConverter keyConverter = new KeyConverter();

		keyConverter.setChangeOptionKey(rollback);

		keyConverter.proccessProject(sourceDir, sourceDir.getAbsolutePath(), "/main/resources/", "bundle");

		for (Tkey k : keyConverter.getListKey() ) {
			writeFile(sourceDir.getAbsolutePath() + "/main/resources/" + "bundle" + ".properties", k.getCompleteKey() + " = " + k.getValue());
		}
	}

	private void writeFile(String path, String param) {
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
