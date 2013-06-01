package com.askvikrant.noticeboard.events;

import com.github.wolfie.blackboard.Listener;
import com.github.wolfie.blackboard.annotation.ListenerMethod;

public interface ViewNotice_Listener extends Listener {
	@ListenerMethod
	void onViewNotice(ViewNotice_Event event);
}
