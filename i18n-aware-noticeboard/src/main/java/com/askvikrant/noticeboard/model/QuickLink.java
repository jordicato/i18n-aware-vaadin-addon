package com.askvikrant.noticeboard.model;

import java.io.Serializable;

public class QuickLink implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name = null;
	private String url = null;

	// ===========================================================================================
	// get
	
	public String getName(){
		return name;
	}
	public String getUrl(){
		return url;
	}
	// ===========================================================================================
	// set
	public void setName(String s){
		name = s;
	}
	public void setUrl(String s){
		url = s;
	}
}