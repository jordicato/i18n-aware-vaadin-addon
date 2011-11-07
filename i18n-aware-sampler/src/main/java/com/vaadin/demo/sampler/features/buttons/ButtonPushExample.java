package com.vaadin.demo.sampler.features.buttons;

import com.opnworks.vaadin.i18n.ui.I18NButton;
import com.opnworks.vaadin.i18n.ui.I18NHorizontalLayout;
import com.opnworks.vaadin.i18n.ui.I18NLabel;
import com.opnworks.vaadin.i18n.ui.I18NNativeButton;
import com.opnworks.vaadin.i18n.ui.I18NVerticalLayout;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;

@SuppressWarnings("serial")
public class ButtonPushExample extends I18NHorizontalLayout implements
        Button.ClickListener {

    private static final String CAPTION = "ButtonPushExample.CAPTION";
    private static final String TOOLTIP = "ButtonPushExample.TOOLTIP";
    private static final ThemeResource ICON = new ThemeResource(
            "../sampler/icons/action_save.gif");
    private static final String NOTIFICATION = "ButtonPushExample.NOTIFICATION";

    public ButtonPushExample() {

        // Normal buttons (more themable)
    	I18NVerticalLayout buttons = new I18NVerticalLayout();
        buttons.setSpacing(true);
        buttons.setMargin(false, true, false, false);
        addComponent(buttons);

        buttons.addComponent(new I18NLabel("<h3>Normal buttons</h3>",
                Label.CONTENT_XHTML));

        // Button w/ text and tooltip
        I18NButton b = new I18NButton(CAPTION);
        b.setDescriptionMessage(TOOLTIP);
        b.addListener(this); // react to clicks
        buttons.addComponent(b);

        // Button w/ text, icon and tooltip
        b = new I18NButton(CAPTION);
        b.setDescriptionMessage(TOOLTIP);
        b.setIcon(ICON);
        b.addListener(this); // react to clicks
        buttons.addComponent(b);

        // Button w/ icon and tooltip
        b = new I18NButton();
        b.setDescriptionMessage(TOOLTIP);
        b.setIcon(ICON);
        b.addListener(this); // react to clicks
        buttons.addComponent(b);

        // NativeButtons
        buttons = new I18NVerticalLayout();
        buttons.setSpacing(true);
        buttons.setMargin(false, false, false, true);
        addComponent(buttons);

        buttons.addComponent(new I18NLabel("<h3>Native buttons</h3>",
                Label.CONTENT_XHTML));

        // NativeButton w/ text and tooltip
        I18NNativeButton nb = new I18NNativeButton(CAPTION);
        nb.setDescriptionMessage(TOOLTIP);
        nb.addListener(this); // react to clicks
        buttons.addComponent(nb);

        // NativeButton w/ text, icon and tooltip
        nb = new I18NNativeButton(CAPTION);
        nb.setDescriptionMessage(TOOLTIP);
        nb.setIcon(ICON);
        nb.addListener(this); // react to clicks
        buttons.addComponent(nb);

        // NativeButton w/ icon and tooltip
        nb = new I18NNativeButton();
        nb.setDescriptionMessage(TOOLTIP);
        nb.setIcon(ICON);
        nb.addListener(this); // react to clicks
        buttons.addComponent(nb);

    }

    /*
     * Shows a notification when a button is clicked.
     */
    public void buttonClick(ClickEvent event) {
        getWindow().showNotification(NOTIFICATION);
    }
}
