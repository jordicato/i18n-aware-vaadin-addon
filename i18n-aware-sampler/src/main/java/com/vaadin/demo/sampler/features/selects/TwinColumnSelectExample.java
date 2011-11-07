package com.vaadin.demo.sampler.features.selects;

import com.opnworks.vaadin.i18n.ui.I18NVerticalLayout;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.TwinColSelect;

public class TwinColumnSelectExample extends I18NVerticalLayout implements
        Property.ValueChangeListener {

    private static final String[] cities = new String[] { "Berlin", "Brussels",
            "Helsinki", "Madrid", "Oslo", "Paris", "Stockholm" };

    public TwinColumnSelectExample() {
        setSpacing(true);

        TwinColSelect l = new TwinColSelect();
        for (int i = 0; i < cities.length; i++) {
            l.addItem(cities[i]);
        }
        l.setRows(7);
        l.setNullSelectionAllowed(true);
        l.setMultiSelect(true);
        l.setImmediate(true);
        l.addListener(this);
        l.setLeftColumnCaption("Available cities");
        l.setRightColumnCaption("Selected destinations");
        l.setWidth("350px");

        addComponent(l);
    }

    /*
     * Shows a notification when a selection is made.
     */
    public void valueChange(ValueChangeEvent event) {
        if (!event.getProperty().toString().equals("[]")) {
            getWindow().showNotification(
                    "Selected cities: " + event.getProperty());
        }
    }
}
