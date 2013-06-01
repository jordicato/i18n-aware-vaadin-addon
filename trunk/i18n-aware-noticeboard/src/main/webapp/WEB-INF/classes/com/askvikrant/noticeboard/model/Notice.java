package com.askvikrant.noticeboard.model;

import java.io.Serializable;
import java.util.Date;

public class Notice implements Serializable{

	private static final long serialVersionUID = 1L;

	private String id = null;
	private String title = null;
	private String body = null;
	private Date date = null;
	private String userId = null;
	private String userName = null;
	private String department = null;
	private int days = 0;

	// ===========================================================================================
	// get
	
	public String getId(){
		return id;
	}
	public String getTitle(){
		return title;
	}
	public String getBody(){
		return body;
	}
	public Date getDate(){
		return date;
	}
	public String getUserId(){
		return userId;
	}
	public String getUserName(){
		return userName;
	}
	public String getDepartment(){
		return department;
	}
	public int getDays(){
		return days;
	}
	// ===========================================================================================
	// set
	public void setId(String s){
		id = s;
	}
	public void setTitle(String s){
		title = s;
	}
	public void setBody(String s){
		body = s;
	}
	public void setDate(Date d){
		date = d;
	}
	public void setUserId(String s){
		userId = s;
	}
	public void setUserName(String s){
		userName = s;
	}
	public void setDepartment(String s){
		department = s;
	}
	public void setDays(int i){
		days = i;
	}
}