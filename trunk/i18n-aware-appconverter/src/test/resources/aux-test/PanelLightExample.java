package com.vaadin.demo.sampler.features.panels;

import com.opnworks.vaadin.i18n.ui.I18NVerticalLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.themes.Reindeer;

@SuppressWarnings("serial")
public class PanelLightExample extends I18NVerticalLayout implements ClickListener {

    private Panel panel;

    public PanelLightExample() {
        setSpacing(true);
        setSpacing(true);
        panel = new Panel("This is a light Panel");
        panel.setStyleName(Reindeer.PANEL_LIGHT);
        panel.setHeight("200px");
        I18NVerticalLayout layout = (I18NVerticalLayout) panel.getContent();
        layout.setMargin(true);
        layout.setSpacing(true);
        addComponent(panel);
        for (int i = 0; i < 20; i++) {
            panel.addComponent(new Label("The quick brown fox jumps over the lazy dog."));
        }
        Button b = new Button("Toggle caption");
        b.addListener(this);
        addComponent(b);
    }

    public void buttonClick(ClickEvent event) {
        if (panel.getCaption() == null) {
            panel.setCaption("This is a light Panel");
        } else {
            panel.setCaption(null);
        }
    }
}
