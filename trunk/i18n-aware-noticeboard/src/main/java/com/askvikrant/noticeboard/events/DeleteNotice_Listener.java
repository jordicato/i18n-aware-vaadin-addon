package com.askvikrant.noticeboard.events;

import com.github.wolfie.blackboard.Listener;
import com.github.wolfie.blackboard.annotation.ListenerMethod;

public interface DeleteNotice_Listener extends Listener {
	@ListenerMethod
	void onDeleteNotice(DeleteNotice_Event event);
}
