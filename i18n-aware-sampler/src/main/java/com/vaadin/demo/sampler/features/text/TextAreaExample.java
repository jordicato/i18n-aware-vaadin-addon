package com.vaadin.demo.sampler.features.text;

import com.opnworks.vaadin.i18n.ui.I18NLabel;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

@SuppressWarnings("serial")
public class TextAreaExample extends HorizontalLayout implements
        Property.ValueChangeListener {

    private static final String initialText = "The quick brown fox jumps over the lazy dog.";

    private I18NLabel plainText;
    private final com.vaadin.ui.TextArea editor;

    public TextAreaExample() {
        setSpacing(true);
        setWidth("100%");

        editor = new com.vaadin.ui.TextArea(null, initialText);
        editor.setRows(20);
        editor.setColumns(20);
        editor.addListener(this);
        editor.setImmediate(true);
        addComponent(editor);

        // the TextArea is immediate, and it's valueCahnge updates the I18NLabel,
        // so this button actually does nothing
        addComponent(new Button(">"));

        plainText = new I18NLabel(initialText);
        plainText.setContentMode(Label.CONTENT_XHTML);
        addComponent(plainText);
        setExpandRatio(plainText, 1);
    }

    /*
     * Catch the valuechange event of the textfield and update the value of the
     * I18NLabel component
     */
    public void valueChange(ValueChangeEvent event) {
        String text = (String) editor.getValue();
        if (text != null) {
            // replace newline with BR, because we're using Label.CONTENT_XHTML
            text = text.replaceAll("\n", "<br/>");
        }
        plainText.setValue(text);
    }
}
