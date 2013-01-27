package com.opnworks.vaadin.i18n.converter.explicit_mode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.opnworks.vaadin.i18n.converter.Converter;

/**
 * The Explicit mode conversion implementation
 */
public class ExplicitModeConverter implements Converter {

	private static ExplicitModeConverter singleton = new ExplicitModeConverter();

	public static ExplicitModeConverter getInstance() {
		return singleton;
	}

	@Override
	public void performI18NAwareProjectConversion(File sourceDir, boolean rollback) {
		recursivedelete(sourceDir);
		navigate(sourceDir, sourceDir);
	}

	private void navigate(File dirBaseSrc, File dirBaseDst) {
		if (!dirBaseDst.exists()) {
			dirBaseDst.mkdirs();
		}
		for (File filesrc : dirBaseSrc.listFiles() ) {
			File filedst = new File(dirBaseDst.getAbsolutePath() + File.separatorChar + filesrc.getName());
			if (filesrc.isDirectory()) {
				boolean created = filedst.mkdir();
				if (!created) {
					throw new RuntimeException("No puedo crear " + filedst.getAbsolutePath());
				}
				navigate(filesrc, filedst);
			}
			else {
				try {
					String newClassContent;
					// this is only to set breakpoints
					if (filesrc.getName().equals("AccordionDisabledExample.java")) {
						newClassContent = null;
					}
					if (filesrc.getName().endsWith(".java")) {
						System.out.println(filesrc.toString());
						I18NConverter conv = new I18NConverter();
						conv.setExtractlits(false);
						newClassContent = conv.proccessJavaFile(filesrc.getAbsolutePath());
						FileOutputStream fos = new FileOutputStream(filedst);
						fos.write(newClassContent.getBytes());
						fos.close();
					}
					else {
						byte[] b = new byte[10000];
						int leidos = 0;
						FileOutputStream fos = new FileOutputStream(filedst);
						FileInputStream fis = new FileInputStream(filesrc);
						while ((leidos = fis.read(b)) > 0) {
							fos.write(b, 0, leidos);
						}
						fos.close();
						fis.close();
					}
				}
				catch (Exception e) {
					// don't interrupt processing, but print trace
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * 
	 * @param dirBaseDst
	 *            deletes this folder and descendant folders
	 */
	private void recursivedelete(File dirBaseDst) {
		if (!dirBaseDst.exists()) {
			return;
		}
		for (File file : dirBaseDst.listFiles() ) {
			if (file.isDirectory()) {
				recursivedelete(file);
				boolean deleted = file.delete();
				if (!deleted) {
					throw new RuntimeException("No puedo borrar " + file.getAbsolutePath());
				}
			}
			else {
				boolean deleted = file.delete();
				if (!deleted) {
					throw new RuntimeException("No puedo borrar " + file.getAbsolutePath());
				}
			}
		}

	}

}
