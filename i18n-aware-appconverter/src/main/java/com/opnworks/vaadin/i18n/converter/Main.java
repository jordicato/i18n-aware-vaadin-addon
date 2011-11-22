package com.opnworks.vaadin.i18n.converter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Main class wrapper to convert common Vaadin apps into I18n-aware Vaadin apps
 * 
 * @author aperez (Innovasoft Proyectos y Servicios)
 * 
 */
public class Main {
	public static void main(String[] args) throws Exception {
		String src = "G:/eclisewshelios/VaadinSamplerOriginal/WebContent/WEB-INF/src", dst = "G:/eclisewshelios/VaadinSamplerI18ned/src";
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-s")) {
				src = args[++i];
			} else if (args[i].equals("-d")) {
				dst = args[++i];
			}
		}

		new Main(src, dst);
	}

	/**
	 * 
	 * @param dirbaseSrc
	 *            path to original Vaadin app
	 * @param dirbaseDst
	 *            path to i18n-aware api
	 */
	Main(String dirbaseSrc, String dirbaseDst) {
		File dirBaseSrc = new File(dirbaseSrc);
		File dirBaseDst = new File(dirbaseDst);
		recursivedelete(dirBaseDst);
		navigate(dirBaseSrc, dirBaseDst);
		System.out.println(I18NConverter.literales);
	}

	/**
	 * 
	 * @param dirbaseSrc
	 *            path to original Vaadin app
	 * @param dirbaseDst
	 *            path to i18n-aware api
	 */
	void navigate(File dirBaseSrc, File dirBaseDst) {
		if (!dirBaseDst.exists()) {
			dirBaseDst.mkdirs();
		}
		for (File filesrc : dirBaseSrc.listFiles()) {
			File filedst = new File(dirBaseDst.getAbsolutePath() + File.separatorChar + filesrc.getName());
			if (filesrc.isDirectory()) {
				boolean created = filedst.mkdir();
				if (!created)
					throw new RuntimeException("No puedo crear " + filedst.getAbsolutePath());
				navigate(filesrc, filedst);
			} else {
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
					} else {
						byte[] b = new byte[10000];
						int leidos = 0;
						FileOutputStream fos = new FileOutputStream(filedst);
						FileInputStream fis = new FileInputStream(filesrc);
						while ((leidos = fis.read(b)) > 0)
							fos.write(b, 0, leidos);
						fos.close();
						fis.close();
					}
				} catch (Exception e) {
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
	void recursivedelete(File dirBaseDst) {
		if (!dirBaseDst.exists())
			return;
		for (File file : dirBaseDst.listFiles()) {
			if (file.isDirectory()) {
				recursivedelete(file);
				boolean deleted = file.delete();
				if (!deleted)
					throw new RuntimeException("No puedo borrar " + file.getAbsolutePath());
			} else {
				boolean deleted = file.delete();
				if (!deleted)
					throw new RuntimeException("No puedo borrar " + file.getAbsolutePath());
			}
		}

	}

}
