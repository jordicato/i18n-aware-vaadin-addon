package com.askvikrant.noticeboard.model;

import java.io.Serializable;

public class NoticeSearchParams implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String TYPE_TEXT_SEARCH = "Text Search";
	public static final String TYPE_07_DAYS = "Last 7 days";
	public static final String TYPE_15_DAYS = "Last 15 days";
	public static final String TYPE_30_DAYS = "Last 30 days";

	private String searchType = null;
	private String searchText = null;
	private User user = null;

	// ===========================================================================================
	// get

	public User getUser() {
		return user;
	}

	public String getSearchType() {
		return searchType;
	}

	public String getSearchText() {
		return searchText;
	}

	// ===========================================================================================
	// set

	public void setUser(final User u) {
		user = u;
	}

	public void setSearchType(String s) {
		searchType = s;
	}

	public void setSearchText(String s) {
		searchText = s;
	}
}