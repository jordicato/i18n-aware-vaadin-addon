package com.vaadin.demo.sampler.features.buttons;

import com.opnworks.vaadin.i18n.ui.I18NVerticalLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeButton;

public class ButtonDisableOnClickExample extends I18NVerticalLayout implements
        ClickListener {

    public ButtonDisableOnClickExample() {

        final Button saveButton = new Button("Save");
        saveButton.setDisableOnClick(true);
        saveButton.addListener(this);

        addComponent(saveButton);

        final NativeButton nativeSaveButton = new NativeButton("Save");
        nativeSaveButton.setDisableOnClick(true);
        nativeSaveButton.addListener(this);

        addComponent(nativeSaveButton);
    }

    public void buttonClick(ClickEvent event) {
        // Simulate an operation that takes three seconds
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }

        // Show text that the save operation has been completed
        addComponent(new Label("Save completed successfully!"));

        // Re-enable the button
        event.getButton().setEnabled(true);

    }
}
