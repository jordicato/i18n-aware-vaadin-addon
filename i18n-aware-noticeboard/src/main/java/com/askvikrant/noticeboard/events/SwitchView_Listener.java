package com.askvikrant.noticeboard.events;

import com.github.wolfie.blackboard.Listener;
import com.github.wolfie.blackboard.annotation.ListenerMethod;

public interface SwitchView_Listener extends Listener {

    @ListenerMethod
    void onSwitchView(SwitchView_Event event);
}
