package com.vaadin.demo.sampler.features.embedded;

import com.opnworks.vaadin.i18n.ui.I18NVerticalLayout;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Embedded;

@SuppressWarnings("serial")
public class WebEmbedExample extends I18NVerticalLayout {

    public WebEmbedExample() {
        Embedded e = new Embedded("Google Search", new ExternalResource(
                "http://www.google.com"));
        e.setType(Embedded.TYPE_BROWSER);
        e.setWidth("100%");
        e.setHeight("400px");
        addComponent(e);
    }
}
