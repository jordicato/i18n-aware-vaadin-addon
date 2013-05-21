package com.askvikrant.noticeboard.events;

import com.github.wolfie.blackboard.Event;

public class ViewNotice_Event implements Event {
	private String noticeId = null;

	public ViewNotice_Event(String s) {
		noticeId = s;
	}

	public String getNoticeId() {
		return noticeId;
	}
}
