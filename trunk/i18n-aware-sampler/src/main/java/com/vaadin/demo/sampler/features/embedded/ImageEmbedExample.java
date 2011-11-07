package com.vaadin.demo.sampler.features.embedded;

import com.opnworks.vaadin.i18n.ui.I18NVerticalLayout;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Embedded;

@SuppressWarnings("serial")
public class ImageEmbedExample extends I18NVerticalLayout {

    public ImageEmbedExample() {
        Embedded e = new Embedded("Image from a theme resource",
                new ThemeResource("../runo/icons/64/document.png"));
        addComponent(e);
    }
}
