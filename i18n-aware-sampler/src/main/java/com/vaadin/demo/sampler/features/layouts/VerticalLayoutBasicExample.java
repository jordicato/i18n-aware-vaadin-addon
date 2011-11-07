package com.vaadin.demo.sampler.features.layouts;

import com.opnworks.vaadin.i18n.ui.I18NVerticalLayout;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class VerticalLayoutBasicExample extends I18NVerticalLayout {

    public VerticalLayoutBasicExample() {
        // this is a I18NVerticalLayout
        // let's add some components
        for (int i = 0; i < 5; i++) {
            TextField tf = new TextField("Row " + (i + 1));
            tf.setWidth("300px");
            // Add the component to the I18NVerticalLayout
            addComponent(tf);
        }
    }
}
