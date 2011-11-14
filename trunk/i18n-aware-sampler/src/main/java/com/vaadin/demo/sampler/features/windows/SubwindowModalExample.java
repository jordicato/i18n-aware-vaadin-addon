package com.vaadin.demo.sampler.features.windows;

import com.opnworks.vaadin.i18n.ui.I18NVerticalLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class SubwindowModalExample extends I18NVerticalLayout {

    Window subwindow;

    public SubwindowModalExample() {

        // Create the window...
        subwindow = new Window("A modal subwindow");
        // ...and make it modal
        subwindow.setModal(true);

        // Configure the windws layout; by default a I18NVerticalLayout
        I18NVerticalLayout layout = (I18NVerticalLayout) subwindow.getContent();
        layout.setMargin(true);
        layout.setSpacing(true);

        // Add some content; a Label and a close-button
        Label message = new Label("This is a modal subwindow.");
        subwindow.addComponent(message);

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
        layout.setComponentAlignment(close, Alignment.TOP_RIGHT);

        // Add a button for opening the subwindow
        Button open = new Button("Open modal window",
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