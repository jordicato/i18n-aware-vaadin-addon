package com.vaadin.demo.sampler.features.selects;

import com.opnworks.vaadin.i18n.ui.I18NVerticalLayout;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.NativeSelect;

@SuppressWarnings("serial")
public class NativeSelectionExample extends I18NVerticalLayout implements
        Property.ValueChangeListener {

    private static final String[] cities = new String[] { "Berlin", "Brussels",
            "Helsinki", "Madrid", "Oslo", "Paris", "Stockholm" };

    public NativeSelectionExample() {
        setSpacing(true);

        NativeSelect l = new NativeSelect("Please select a city");
        for (int i = 0; i < cities.length; i++) {
            l.addItem(cities[i]);
        }

        l.setNullSelectionAllowed(false);
        l.setValue("Berlin");
        l.setImmediate(true);
        l.addListener(this);

        addComponent(l);
    }

    /*
     * Shows a notification when a selection is made.
     */
    public void valueChange(ValueChangeEvent event) {
        getWindow().showNotification("Selected city: " + event.getProperty());

    }
}
