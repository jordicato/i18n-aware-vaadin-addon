package com.vaadin.demo.sampler.features.text;

import com.opnworks.vaadin.i18n.ui.I18NLabel;
import com.opnworks.vaadin.i18n.ui.I18NVerticalLayout;

@SuppressWarnings("serial")
public class LabelPreformattedExample extends I18NVerticalLayout {

    public LabelPreformattedExample() {
        setSpacing(true);

        I18NLabel preformattedText = new I18NLabel(
                "This is an example of a I18NLabel component.\n"
                        + "\nThe content mode of this I18NLabel is set"
                        + "\nto CONTENT_PREFORMATTED. This means"
                        + "\nthat it will display the content text"
                        + "\nusing a fixed-width font. You also have"
                        + "\nto insert the line breaks yourself.\n"
                        + "\n\tHTML and XML special characters"
                        + "\n\t(<,>,&) are escaped properly to"
                        + "\n\tallow displaying them.");
        preformattedText.setContentMode(I18NLabel.CONTENT_PREFORMATTED);

        addComponent(preformattedText);
    }
}
