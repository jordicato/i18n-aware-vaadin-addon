package com.askvikrant.noticeboard.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.vaadin.server.StreamResource.StreamSource;

@SuppressWarnings("serial")
public class FileSource implements StreamSource {

	transient private File file = null;

	@Override
	public InputStream getStream() {
		// TODO Auto-generated method stub
		InputStream is = null;
		try {
			if (file != null) {
				is = new FileInputStream(file);
			}
		}
		catch (Exception ex) {
			System.out.println("ERROR: FileSource: " + ex.getMessage());
		}
		return is;
	}

	public void setFile(File file) {
		this.file = file;
	}
}
