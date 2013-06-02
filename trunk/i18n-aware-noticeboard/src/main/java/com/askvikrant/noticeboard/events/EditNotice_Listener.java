package com.askvikrant.noticeboard.events;

import com.github.wolfie.blackboard.Listener;
import com.github.wolfie.blackboard.annotation.ListenerMethod;

public interface EditNotice_Listener extends Listener {

    @ListenerMethod
    void onEditNotice(EditNotice_Event event);
}
