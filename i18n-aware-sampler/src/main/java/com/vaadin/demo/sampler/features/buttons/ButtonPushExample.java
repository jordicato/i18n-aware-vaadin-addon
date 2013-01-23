package com.vaadin.demo.sampler.features.buttons;

import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ButtonPushExample extends HorizontalLayout implements
        Button.ClickListener {

    private static final String CAPTION = "ButtonPushExample.CAPTION";
    private static final String TOOLTIP = "ButtonPushExample.TOOLTIP";
    private static final ThemeResource ICON = new ThemeResource(
            "../sampler/icons/action_save.gif");
    private static final String NOTIFICATION = "ButtonPushExample.NOTIFICATION";

    public ButtonPushExample() {

        // Normal buttons (more themable)
    	VerticalLayout buttons = new VerticalLayout();
        buttons.setSpacing(true);
        buttons.setMargin(false, true, false, false);
        addComponent(buttons);

        buttons.addComponent(new Label("<h3>Normal buttons 123</h3>",
                Label.CONTENT_XHTML));

        // Button w/ text and tooltip
        Button b = new Button(CAPTION);
        b.setDescription(TOOLTIP);
        b.addListener(this); // react to clicks
        buttons.addComponent(b);

        // Button w/ text, icon and tooltip
        b = new Button(CAPTION);
        b.setDescription(TOOLTIP);
        b.setIcon(ICON);
        b.addListener(this); // react to clicks
        buttons.addComponent(b);

        // Button w/ icon and tooltip
        b = new Button();
        b.setDescription(TOOLTIP);
        b.setIcon(ICON);
        b.addListener(this); // react to clicks
        buttons.addComponent(b);

        // NativeButtons
        buttons = new VerticalLayout();
        buttons.setSpacing(true);
        buttons.setMargin(false, false, false, true);
        addComponent(buttons);

        buttons.addComponent(new Label("<h3>Native buttons</h3>",
                Label.CONTENT_XHTML));

        // NativeButton w/ text and tooltip
        NativeButton nb = new NativeButton(CAPTION);
        nb.setDescription(TOOLTIP);
        nb.addListener(this); // react to clicks
        buttons.addComponent(nb);

        // NativeButton w/ text, icon and tooltip
        nb = new NativeButton(CAPTION);
        nb.setDescription(TOOLTIP);
        nb.setIcon(ICON);
        nb.addListener(this); // react to clicks
        buttons.addComponent(nb);

        // NativeButton w/ icon and tooltip
        nb = new NativeButton();
        nb.setDescription(TOOLTIP);
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
