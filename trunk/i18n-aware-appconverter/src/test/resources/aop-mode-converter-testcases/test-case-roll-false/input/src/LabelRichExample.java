package com.vaadin.demo.sampler.features.text;

import com.opnworks.vaadin.i18n.ui.I18NVerticalLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.RichTextArea;

@SuppressWarnings("serial")
public class LabelRichExample extends I18NVerticalLayout implements ClickListener {

    private Button b;
    private Label richText;

    private final RichTextArea editor = new RichTextArea();

    public LabelRichExample() {
        setSpacing(true);

        richText = new Label(
                "<h1>Rich text example</h1>"
                        + "<p>The <b>quick</b> brown fox jumps <sup>over</sup> the <b>lazy</b> dog.</p>"
                        + "<p>This text can be edited with the <i>Edit</i> -button</p>");
        richText.setContentMode(Label.CONTENT_XHTML);

        addComponent(richText);

        b = new Button("Edit");
        b.addListener(this);
        addComponent(b);

        editor.setWidth("100%");
    }

    public void buttonClick(ClickEvent event) {
        if (getComponentIterator().next() == richText) {
            editor.setValue(richText.getValue());
            replaceComponent(richText, editor);
            b.setCaption("Apply");
        } else {
            richText.setValue(editor.getValue());
            replaceComponent(editor, richText);
            b.setCaption("Edit");
        }
    }

}
