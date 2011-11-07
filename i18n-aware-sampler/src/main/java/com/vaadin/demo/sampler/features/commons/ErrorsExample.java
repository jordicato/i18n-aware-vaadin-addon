package com.vaadin.demo.sampler.features.commons;

import com.opnworks.vaadin.i18n.ui.I18NLabel;
import com.opnworks.vaadin.i18n.ui.I18NVerticalLayout;
import com.vaadin.terminal.UserError;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class ErrorsExample extends I18NVerticalLayout {

    public ErrorsExample() {
        setSpacing(true);

        addComponent(new I18NLabel("<h3>Errors in caption</h3>",
                Label.CONTENT_XHTML));
        addComponent(new I18NLabel(
                "Error indicators are usually placed on the right side of the component's caption."));

        TextField input = new TextField("Field caption");
        input.setComponentError(new UserError("This field is never satisfied"));
        addComponent(input);

        addComponent(new I18NLabel("<h3>Errors without caption</h3>",
                Label.CONTENT_XHTML));
        addComponent(new I18NLabel(
                "If the component has no caption, the error indicator is usually placed on the right side of the component."));

        input = new TextField();
        input.setInputPrompt("This field has an error");
        input.setComponentError(new UserError("This field is never satisfied."));
        addComponent(input);

        addComponent(new I18NLabel(
                "<h3>Error icon placement depends on the layout</h3>",
                Label.CONTENT_XHTML));
        addComponent(new I18NLabel(
                "FormLayout for example places the error between the component caption and the actual field."));

        FormLayout fl = new FormLayout();
        fl.setMargin(false);
        fl.setSpacing(false);
        addComponent(fl);
        input = new TextField("Field caption");
        input.setInputPrompt("This field has an error");
        input.setComponentError(new UserError("This field is never satisfied."));
        fl.addComponent(input);

    }
}
