package com.vaadin.demo.sampler.features.blueprints;

import com.opnworks.vaadin.i18n.ui.I18NVerticalLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.themes.BaseTheme;

@SuppressWarnings("serial")
public class ProminentPrimaryActionExample extends I18NVerticalLayout implements Button.ClickListener {

    public ProminentPrimaryActionExample() {
        setSpacing(true);
        {
            HorizontalLayout horiz = new HorizontalLayout();
            horiz.setCaption("Save/cancel example:");
            horiz.setSpacing(true);
            horiz.setMargin(true);
            addComponent(horiz);
            Button secondary = new Button("Cancel", this);
            secondary.setStyleName(BaseTheme.BUTTON_LINK);
            horiz.addComponent(secondary);
            Button primary = new Button("Save", this);
            horiz.addComponent(primary);
        }
        {
            HorizontalLayout horiz = new HorizontalLayout();
            horiz.setCaption("Sign up example:");
            horiz.setSpacing(true);
            horiz.setMargin(true);
            addComponent(horiz);
            Button primary = new Button("Sign up", this);
            primary.addStyleName("primary");
            horiz.addComponent(primary);
            Button secondary = new Button("or Sign in", this);
            secondary.setStyleName(BaseTheme.BUTTON_LINK);
            horiz.addComponent(secondary);
            horiz.setComponentAlignment(secondary, Alignment.MIDDLE_LEFT);
        }
        {
            I18NVerticalLayout vert = new I18NVerticalLayout();
            vert.setCaption("Login example:");
            vert.setSizeUndefined();
            vert.setSpacing(true);
            vert.setMargin(true);
            addComponent(vert);
            Button primary = new Button("Login", this);
            vert.addComponent(primary);
            vert.setComponentAlignment(primary, Alignment.BOTTOM_RIGHT);
            Button secondary = new Button("Forgot your password?", this);
            secondary.setStyleName(BaseTheme.BUTTON_LINK);
            vert.addComponent(secondary);
            vert.setComponentAlignment(secondary, Alignment.BOTTOM_RIGHT);
        }
    }

    public void buttonClick(ClickEvent event) {
        getWindow().showNotification(""" + event.getButton().getCaption() + "" clicked");
    }
}
