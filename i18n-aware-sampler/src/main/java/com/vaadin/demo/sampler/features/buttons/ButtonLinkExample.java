package com.vaadin.demo.sampler.features.buttons;

import com.opnworks.vaadin.i18n.ui.I18NVerticalLayout;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.BaseTheme;

@SuppressWarnings("serial")
public class ButtonLinkExample extends I18NVerticalLayout implements
        Button.ClickListener {

    private static final String CAPTION = "Help";
    private static final String TOOLTIP = "Show help";
    private static final ThemeResource ICON = new ThemeResource(
            "../sampler/icons/icon_info.gif");
    private static final String NOTIFICATION = "Help clicked";

    public ButtonLinkExample() {
        setSpacing(true);

        // Button w/ text and tooltip
        Button b = new Button(CAPTION);
        b.setStyleName(BaseTheme.BUTTON_LINK);
        b.setDescription(TOOLTIP);
        b.addListener(this); // react to clicks
        addComponent(b);

        // Button w/ text, icon and tooltip
        b = new Button(CAPTION);
        b.setStyleName(BaseTheme.BUTTON_LINK);
        b.setDescription(TOOLTIP);
        b.setIcon(ICON);
        b.addListener(this); // react to clicks
        addComponent(b);

        // Button w/ icon and tooltip
        b = new Button();
        b.setStyleName(BaseTheme.BUTTON_LINK);
        b.setDescription(TOOLTIP);
        b.setIcon(ICON);
        b.addListener(this); // react to clicks
        addComponent(b);

    }

    /*
     * Shows a notification when a button is clicked.
     */
    public void buttonClick(ClickEvent event) {
        getWindow().showNotification(NOTIFICATION);
    }
}
