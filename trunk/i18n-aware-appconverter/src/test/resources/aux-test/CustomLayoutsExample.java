package com.vaadin.demo.sampler.features.layouts;

import com.opnworks.vaadin.i18n.ui.I18NVerticalLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class CustomLayoutsExample extends I18NVerticalLayout {

    public CustomLayoutsExample() {
        setMargin(true);
        CustomLayout custom = new CustomLayout("../../sampler/layouts/examplecustomlayout");
        addComponent(custom);
        TextField username = new TextField();
        custom.addComponent(username, "username");
        PasswordField password = new PasswordField();
        custom.addComponent(password, "password");
        Button ok = new Button("Login");
        custom.addComponent(ok, "okbutton");
    }
}
