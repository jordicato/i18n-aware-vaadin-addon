package com.vaadin.demo.sampler.features.text;

import com.opnworks.vaadin.i18n.ui.I18NLabel;
import com.opnworks.vaadin.i18n.ui.I18NVerticalLayout;

@SuppressWarnings("serial")
public class LabelPlainExample extends I18NVerticalLayout {

    public LabelPlainExample() {
        setSpacing(true);

        I18NLabel plainText = new I18NLabel("This is an example of a I18NLabel"
                + " component. The content mode of this I18NLabel is set"
                + " to CONTENT_TEXT. This means that it will display"
                + " the content text as is. HTML and XML special characters"
                + " (<,>,&) are escaped properly to allow displaying them.");
        plainText.setContentMode(I18NLabel.CONTENT_TEXT);

        addComponent(plainText);
    }
}
