package com.opnworks.vaadin.i18n.converter.explicit_mode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Main class wrapper to convert common Vaadin apps into I18n-aware Vaadin apps
 * 
 * @author aperez (Innovasoft Proyectos y Servicios)
 * 
 */
public class MainPackRenamed {
	public static void main(String[] args) throws Exception {
		String dirsrc = "G:/eclisewshelios/VaadinSamplerOriginal/WebContent/WEB-INF/src", dirdst = "G:/eclisewshelios/VaadinSamplerI18ned/src";
		String basesrcpack = "com.vaadin.demo.sampler", basedstpack = "com.vaadin.demo.i18.sampler";
		boolean extractlits = false;
		for (int i = 0; i < args.length; i++ ) {
			if (args[i].equals("-dirsrc")) {
				dirsrc = args[++i];
			}
			else if (args[i].equals("-dirdst")) {
				dirdst = args[++i];
			}
			else if (args[i].equals("-basesrcpack")) {
				basesrcpack = args[++i];
			}
			else if (args[i].equals("-basedstpack")) {
				basedstpack = args[++i];
			}
			else if (args[i].equals("-extractlits")) {
				extractlits = Boolean.parseBoolean(args[++i]);
			}
		}

		new MainPackRenamed(dirsrc, dirdst, basesrcpack, basedstpack, extractlits);
	}

	private File appdirBaseSrc, appdirBaseDst;
	private String prefixSrcPackage, prefixDstPackage;
	private boolean extractlits = false;

	/**
	 * 
	 * @param baseSrc
	 *            path to original Vaadin app
	 * @param baseDst
	 *            path to i18n-aware api
	 */
	MainPackRenamed(String baseSrc, String baseDst, String prefixSrcPackage, String prefixDstPackage, boolean extractlits) {
		System.out.println("Called " + baseSrc + ", " + baseDst + ", " + prefixSrcPackage + ", " + prefixDstPackage + ", " + extractlits);
		this.extractlits = extractlits;
		this.prefixSrcPackage = prefixSrcPackage;
		this.prefixDstPackage = prefixDstPackage;
		File dirBaseSrc = new File(baseSrc);
		File dirBaseDst = new File(baseDst);
		this.appdirBaseSrc = dirBaseSrc;
		this.appdirBaseDst = dirBaseDst;
		if (!appdirBaseDst.exists()) {
			appdirBaseDst.mkdir();
		}
		String partialSrcPath = dirBaseSrc.getAbsolutePath().replace("\\", "/");
		if (prefixSrcPackage != null && prefixSrcPackage.trim().length() > 0 && prefixDstPackage != null && prefixDstPackage.trim().length() > 0) {
			if (!partialSrcPath.endsWith("/"))
				partialSrcPath += "/";
			partialSrcPath += prefixSrcPackage.replace(".", "/");
		}
		String partialDstPath = dirBaseDst.getAbsolutePath().replace("\\", "/");
		if (prefixSrcPackage != null && prefixSrcPackage.trim().length() > 0 && prefixDstPackage != null && prefixDstPackage.trim().length() > 0) {
			if (!partialDstPath.endsWith("/"))
				partialDstPath += "/";
			partialDstPath += prefixDstPackage.replace(".", "/");
		}
		recursivedelete(new File(partialDstPath));
		navigate(new File(partialSrcPath), new File(partialDstPath));
		System.out.println(I18NConverter.literales);
	}

	File getCurrentDstFile(String currentdstpackage, String filename) {
		String[] packcomps = currentdstpackage.split("[.]");
		File f = appdirBaseDst;
		for (String s : packcomps ) {
			f = new File(f.getAbsolutePath() + File.separatorChar + s);
			if (!f.exists()) {
				boolean b = f.mkdirs();
				if (!b)
					throw new RuntimeException("Cannot create dir");
			}
		}
		f = new File(f.getAbsolutePath() + File.separatorChar + filename);
		return f;
	}

	/**
	 * Return current package of this java file
	 * 
	 * @param currentFile
	 * @return
	 */
	String getCurrentSrcPackage(File currentFile) {
		int firstletter = currentFile.getAbsolutePath().indexOf(currentFile.getName());
		String currentdirpath = currentFile.getAbsolutePath().substring(0, firstletter);
		String currentrelativepath = currentdirpath.substring(appdirBaseSrc.getAbsolutePath().length());
		currentrelativepath = currentrelativepath.replace("\\", "/");
		if (currentrelativepath.startsWith("/"))
			currentrelativepath = currentrelativepath.substring(1);
		if (currentrelativepath.endsWith("/"))
			currentrelativepath = currentrelativepath.substring(0, currentrelativepath.length() - 1);
		String currentrelativepackage = currentrelativepath.replace("/", ".");
		return currentrelativepackage;
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
		for (File filesrc : dirBaseSrc.listFiles() ) {
			File filedst = new File(dirBaseDst.getAbsolutePath() + File.separatorChar + filesrc.getName());
			if (filesrc.isDirectory()) {
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
						String currentSrcPackage = getCurrentSrcPackage(filesrc);
						conv.setExtractlits(extractlits);
						String currentDstPackage = I18NConverter.getCurrentDstPackage(currentSrcPackage, prefixSrcPackage, prefixDstPackage);
						conv.setRenameBasePackage(prefixSrcPackage, prefixDstPackage);
						newClassContent = conv.proccessJavaFile(filesrc.getAbsolutePath());
						filedst = getCurrentDstFile(currentDstPackage, filesrc.getName());
						FileOutputStream fos = new FileOutputStream(filedst);
						fos.write(newClassContent.getBytes());
						fos.close();
					}
					else {
						byte[] b = new byte[10000];
						int leidos = 0;
						FileOutputStream fos = new FileOutputStream(filedst);
						FileInputStream fis = new FileInputStream(filesrc);
						while ((leidos = fis.read(b)) > 0)
							fos.write(b, 0, leidos);
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
	void recursivedelete(File dirBaseDst) {
		if (!dirBaseDst.exists())
			return;
		for (File file : dirBaseDst.listFiles() ) {
			if (file.isDirectory()) {
				recursivedelete(file);
				boolean deleted = file.delete();
				if (!deleted)
					throw new RuntimeException("No puedo borrar " + file.getAbsolutePath());
			}
			else {
				boolean deleted = file.delete();
				if (!deleted)
					throw new RuntimeException("No puedo borrar " + file.getAbsolutePath());
			}
		}

	}

}
