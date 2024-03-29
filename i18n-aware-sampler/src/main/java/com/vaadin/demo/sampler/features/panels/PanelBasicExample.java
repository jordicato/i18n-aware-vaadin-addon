package com.vaadin.demo.sampler.features.panels;

import com.opnworks.vaadin.i18n.ui.I18NVerticalLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;

@SuppressWarnings("serial")
public class PanelBasicExample extends I18NVerticalLayout implements ClickListener {

    private Panel panel;

    public PanelBasicExample() {
        setSpacing(true);

        // Panel 1 - with caption
        panel = new Panel("This is a standard Panel");
        panel.setHeight("200px"); // we want scrollbars

        // let's adjust the panels default layout (a I18NVerticalLayout)
        I18NVerticalLayout layout = (I18NVerticalLayout) panel.getContent();
        layout.setMargin(true); // we want a margin
        layout.setSpacing(true); // and spacing between components
        addComponent(panel);

        // Let's add a few rows to provoke scrollbars:
        for (int i = 0; i < 20; i++) {
            panel.addComponent(new Label(
                    "The quick brown fox jumps over the lazy dog."));
        }

        // Caption toggle:
        Button b = new Button("Toggle caption");
        b.addListener(this);
        addComponent(b);

    }

    public void buttonClick(ClickEvent event) {
        if (panel.getCaption().equals("")) {
            panel.setCaption("This is a standard Panel");
        } else {
            panel.setCaption("");
        }
    }
}
