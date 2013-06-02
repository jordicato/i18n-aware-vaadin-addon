package com.askvikrant.noticeboard.model;

import java.io.Serializable;

public class NoticeSearchParams implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    public static final String TYPE_TEXT_SEARCH = "com.askvikrant.noticeboard.model.NoticeSearchParams.Text_Search";

    public static final String TYPE_07_DAYS = "com.askvikrant.noticeboard.model.NoticeSearchParams.Last_7_days";

    public static final String TYPE_15_DAYS = "com.askvikrant.noticeboard.model.NoticeSearchParams.Last_15_days";

    public static final String TYPE_30_DAYS = "com.askvikrant.noticeboard.model.NoticeSearchParams.Last_30_days";

    private String searchType = null;

    private String searchText = null;

    private User user = null;

    public User getUser() {
        return user;
    }

    public String getSearchType() {
        return searchType;
    }

    public String getSearchText() {
        return searchText;
    }

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
