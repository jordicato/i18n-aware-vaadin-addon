package com.askvikrant.noticeboard.events;

import com.askvikrant.noticeboard.model.User;
import com.github.wolfie.blackboard.Event;

public class LoginSuccess_Event implements Event {
	private User user = null;

	public LoginSuccess_Event(User u) {
		user = u;
	}

	public User getUser() {
		return user;
	}
}
