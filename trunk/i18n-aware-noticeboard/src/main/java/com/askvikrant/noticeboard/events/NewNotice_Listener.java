package com.askvikrant.noticeboard.events;

import com.github.wolfie.blackboard.Listener;
import com.github.wolfie.blackboard.annotation.ListenerMethod;

public interface NewNotice_Listener extends Listener {

    @ListenerMethod
    void onNewNotice(NewNotice_Event event);
}
