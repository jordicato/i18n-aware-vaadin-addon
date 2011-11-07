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

        // Create the custom layout and set it as a component in
        // the current layout
        CustomLayout custom = new CustomLayout(
                "../../sampler/layouts/examplecustomlayout");
        addComponent(custom);

        // Create components and bind them to the location tags
        // in the custom layout.
        TextField username = new TextField();
        custom.addComponent(username, "username");

        PasswordField password = new PasswordField();
        custom.addComponent(password, "password");

        Button ok = new Button("Login");
        custom.addComponent(ok, "okbutton");
    }
}