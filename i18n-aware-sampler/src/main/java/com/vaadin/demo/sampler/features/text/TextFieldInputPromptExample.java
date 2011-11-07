package com.vaadin.demo.sampler.features.text;

import com.opnworks.vaadin.i18n.ui.I18NVerticalLayout;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class TextFieldInputPromptExample extends I18NVerticalLayout implements
        Property.ValueChangeListener {

    public TextFieldInputPromptExample() {
        // add som 'air' to the layout
        setSpacing(true);
        setMargin(true, false, false, false);

        // Username field + input prompt
        TextField username = new TextField();
        username.setInputPrompt("Username");
        // configure & add to layout
        username.setImmediate(true);
        username.addListener(this);
        addComponent(username);

        // Password field + input prompt
        PasswordField password = new PasswordField();
        password.setInputPrompt("Password");
        // configure & add to layout
        password.setImmediate(true);
        password.addListener(this);
        addComponent(password);

        // Comment field + input prompt
        com.vaadin.ui.TextArea comment = new com.vaadin.ui.TextArea();
        comment.setInputPrompt("Comment");
        // configure & add to layout
        comment.setRows(3);
        comment.setImmediate(true);
        comment.addListener(this);
        addComponent(comment);

    }

    public void valueChange(ValueChangeEvent event) {
        getWindow().showNotification("Received " + event.getProperty());

    }

}
