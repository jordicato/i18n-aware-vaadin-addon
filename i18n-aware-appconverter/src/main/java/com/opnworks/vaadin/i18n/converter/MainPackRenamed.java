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
public class MainPackRenamed {
	public static void main(String[] args) throws Exception {
		String src = "G:/eclisewshelios/VaadinSamplerOriginal/WebContent/WEB-INF/src", dst = "G:/eclisewshelios/VaadinSamplerI18ned/src";
		String basesrcpack = "com.vaadin.demo.sampler", basedstpack = "com.vaadin.demo.i18.sampler";
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-s")) {
				src = args[++i];
			} else if (args[i].equals("-d")) {
				dst = args[++i];
			} else if (args[i].equals("-basesrcpack")) {
				basesrcpack = args[++i];
			} else if (args[i].equals("-basedstpack")) {
				basedstpack = args[++i];
			}
		}

		new MainPackRenamed(src, dst, basesrcpack, basedstpack);
	}

	private File appdirBaseSrc, appdirBaseDst;
	private String prefixSrcPackage, prefixDstPackage;

	/**
	 * 
	 * @param dirbaseSrc
	 *            path to original Vaadin app
	 * @param dirbaseDst
	 *            path to i18n-aware api
	 */
	MainPackRenamed(String dirbaseSrc, String dirbaseDst, String prefixSrcPackage, String prefixDstPackage) {
		this.prefixSrcPackage = prefixSrcPackage;
		this.prefixDstPackage = prefixDstPackage;
		File dirBaseSrc = new File(dirbaseSrc);
		File dirBaseDst = new File(dirbaseDst);
		this.appdirBaseSrc = dirBaseSrc;
		this.appdirBaseDst = dirBaseDst;
		if (!appdirBaseDst.exists()) {
			appdirBaseDst.mkdir();
		}
		recursivedelete(dirBaseDst);
		navigate(dirBaseSrc, dirBaseDst);
		System.out.println(I18NConverter.literales);
	}

	File getCurrentDstFile(String currentdstpackage, String filename) {
		String[] packcomps = currentdstpackage.split("[.]");
		File f = appdirBaseDst;
		for (String s : packcomps) {
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
		for (File filesrc : dirBaseSrc.listFiles()) {
			File filedst = new File(dirBaseDst.getAbsolutePath() + File.separatorChar + filesrc.getName());
			if (filesrc.isDirectory()) {
				navigate(filesrc, filedst);
			} else {
				try {
					String newClassContent;
					// this is only to set breakpoints
					if (filesrc.getName().equals("LinkCurrentWindow.java")) {
						newClassContent = null;
					}
					if (filesrc.getName().endsWith(".java")) {
						System.out.println(filesrc.toString());
						I18NConverter conv = new I18NConverter();
						String currentSrcPackage = getCurrentSrcPackage(filesrc);
						String currentDstPackage = I18NConverter.getCurrentDstPackage(currentSrcPackage, prefixSrcPackage, prefixDstPackage);
						conv.setRenameBasePackage(prefixSrcPackage, prefixDstPackage);
						newClassContent = conv.proccessJavaFile(filesrc.getAbsolutePath());
						filedst = getCurrentDstFile(currentDstPackage, filesrc.getName());
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
