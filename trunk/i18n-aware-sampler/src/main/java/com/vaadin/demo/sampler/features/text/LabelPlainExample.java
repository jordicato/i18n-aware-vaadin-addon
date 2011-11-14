package com.vaadin.demo.sampler.features.text;

import com.opnworks.vaadin.i18n.ui.I18NVerticalLayout;
import com.vaadin.ui.Label;

@SuppressWarnings("serial")
public class LabelPlainExample extends I18NVerticalLayout {

    public LabelPlainExample() {
        setSpacing(true);

        Label plainText = new Label("This is an example of a Label"
                + " component. The content mode of this Label is set"
                + " to CONTENT_TEXT. This means that it will display"
                + " the content text as is. HTML and XML special characters"
                + " (<,>,&) are escaped properly to allow displaying them.");
        plainText.setContentMode(Label.CONTENT_TEXT);

        addComponent(plainText);
    }
}
