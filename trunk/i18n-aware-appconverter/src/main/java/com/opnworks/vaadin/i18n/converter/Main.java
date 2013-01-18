package com.opnworks.vaadin.i18n.converter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import com.opnworks.vaadin.i18n.converter.I18NConverter.Tkey;

/**
 * Main class wrapper to convert common Vaadin apps into I18n-aware Vaadin apps
 * 
 * @author aperez (Innovasoft Proyectos y Servicios)
 * 
 */
public class Main {
	public static void main(String[] args) throws Exception {
		//String src = "C:/SVN-OpnWorks/i18n-aware-vaadin-addon/i18n-aware-sampler/src";
		String src = "C:/SVN-OpnWorks/i18n-aware-vaadin-addon/i18n-aware-demo/src";
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-s")) {
				src = args[++i];
			} 
		}

		new Main(src);
	}

	/**
	 * 
	 * @param dirbaseSrc
	 *            path to original Vaadin app
	 *            
	 */
	Main(String dirbaseSrc) {
		File dirBaseSrc = new File(dirbaseSrc);
		File delete = new File(dirbaseSrc+"/main/resources/bundle.properties");
		if (delete.exists()){
			delete.delete();
		}
		navigate(dirBaseSrc, dirbaseSrc);
	}
	
	/**
	 * 
	 * @param dirbaseSrc
	 *            path to original Vaadin app
	 * @param dirbaseDst
	 *            path to i18n-aware api
	 */
	 public void escribir(String ruta, String cadena){		  
		  File archivo = new File(ruta);
		   try {
			    FileWriter escribirArchivo = new FileWriter(archivo, true);
			    BufferedWriter buffer = new BufferedWriter(escribirArchivo);
			    buffer.write(cadena);
			    buffer.newLine();
			    buffer.close();
		   }catch (Exception ex) {
		   }
	}
	 	
	void navigate(File dirBaseSrc, String path) {
		
		I18NConverter conv = new I18NConverter();
		conv.proccessProject(dirBaseSrc, path);
		
		
		
		for (Tkey k : conv.getListKey()){
			if (k.suffix > 0){
				escribir(path+"/main/resources/bundle.properties", k.fullClassName + k.key + "_" + k.suffix + " = " + k.value);
			}else{
				escribir(path+"/main/resources/bundle.properties", k.fullClassName + k.key + " = " + k.value);
			}
		}

	}

}
