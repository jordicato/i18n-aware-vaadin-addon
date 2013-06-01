package com.askvikrant.noticeboard.model;

import java.io.File;
import java.io.Serializable;

public class Attachment  implements Serializable{

	private static final long serialVersionUID = 1L;
	private String id = null;
	private File file = null;
	private String contentType = null;
	
	//get
	public String getId(){
		return id;
	}
	public File getFile(){
		return file;
	}
	public String getContentType(){
		return contentType;
	}
	
	//set
	public void setId(String s){
		id = s;
	}
	public void setFile(File f){
		file = f;
	}
	public void setContentType(String s){
		contentType = s;
	}
}
