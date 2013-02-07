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
        richText = new Label("com.vaadin.demo.sampler.features.text.LabelRichExample.h1_Rich_text_example_h1" + "com.vaadin.demo.sampler.features.text.LabelRichExample.p_The_b_quick_b_brown_fox" + "com.vaadin.demo.sampler.features.text.LabelRichExample.p_This_text_can_be_edited_wit");
        richText.setContentMode(Label.CONTENT_XHTML);
        addComponent(richText);
        b = new Button("com.vaadin.demo.sampler.features.text.LabelRichExample.Edit");
        b.addListener(this);
        addComponent(b);
        editor.setWidth("100%");
    }

    public void buttonClick(ClickEvent event) {
        if (getComponentIterator().next() == richText) {
            editor.setValue(richText.getValue());
            replaceComponent(richText, editor);
            b.setCaption("com.vaadin.demo.sampler.features.text.LabelRichExample.Apply");
        } else {
            richText.setValue(editor.getValue());
            replaceComponent(editor, richText);
            b.setCaption("com.vaadin.demo.sampler.features.text.LabelRichExample.Edit_1");
        }
    }
}
