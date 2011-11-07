package com.vaadin.demo.sampler.features.windows;

import com.opnworks.vaadin.i18n.ui.I18NVerticalLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class SubwindowAutoSizedExample extends I18NVerticalLayout {

    Window subwindow;

    public SubwindowAutoSizedExample() {

        // Create the window
        subwindow = new Window("Automatically sized subwindow");

        // Configure the windws layout; by default a I18NVerticalLayout
        I18NVerticalLayout layout = (I18NVerticalLayout) subwindow.getContent();
        layout.setMargin(true);
        layout.setSpacing(true);
        // make it undefined for auto-sizing window
        layout.setSizeUndefined();

        // Add some content;
        for (int i = 0; i < 7; i++) {
            TextField tf = new TextField();
            tf.setWidth("400px");
            subwindow.addComponent(tf);
        }

        Button close = new Button("Close", new Button.ClickListener() {
            // inline click-listener
            public void buttonClick(ClickEvent event) {
                // close the window by removing it from the parent window
                (subwindow.getParent()).removeWindow(subwindow);
            }
        });
        // The components added to the window are actually added to the window's
        // layout; you can use either. Alignments are set using the layout
        layout.addComponent(close);
        layout.setComponentAlignment(close, Alignment.BOTTOM_RIGHT);

        // Add a button for opening the subwindow
        Button open = new Button("Open sized window",
                new Button.ClickListener() {
                    // inline click-listener
                    public void buttonClick(ClickEvent event) {
                        if (subwindow.getParent() != null) {
                            // window is already showing
                            getWindow().showNotification(
                                    "Window is already open");
                        } else {
                            // Open the subwindow by adding it to the parent
                            // window
                            getWindow().addWindow(subwindow);
                        }
                    }
                });
        addComponent(open);

    }

}