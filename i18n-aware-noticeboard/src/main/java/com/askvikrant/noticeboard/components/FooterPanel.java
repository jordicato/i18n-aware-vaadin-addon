package com.askvikrant.noticeboard.components;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class FooterPanel extends Panel {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    public FooterPanel() {
        VerticalLayout vLayout = new VerticalLayout();
        setContent(vLayout);
        Link link = new Link("askVikrant.com", new ExternalResource("http://askVikrant.com/"));
        vLayout.addComponent(link);
        vLayout.setComponentAlignment(link, Alignment.MIDDLE_CENTER);
    }
}
