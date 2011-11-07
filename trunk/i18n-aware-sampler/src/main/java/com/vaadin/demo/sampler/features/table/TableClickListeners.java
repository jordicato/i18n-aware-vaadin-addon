package com.vaadin.demo.sampler.features.table;

import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.FeatureSet;
import com.vaadin.demo.sampler.NamedExternalResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.Table;

@SuppressWarnings("serial")
public class TableClickListeners extends Feature {

    @Override
    public String getDescription() {
        return "You can assign a click listener to the column headers and footers to handle user mouse clicks.";
    }

    @Override
    public String getName() {
        return "Table, click listeners";
    }

    @Override
    public APIResource[] getRelatedAPI() {
        return new APIResource[] { new APIResource(Table.class) };
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends Feature>[] getRelatedFeatures() {
        return new Class[] { FeatureSet.Tables.class };
    }

    @Override
    public NamedExternalResource[] getRelatedResources() {
        return null;
    }

    @Override
    public Version getSinceVersion() {
        return Version.V64;
    }

    @Override
    public Component getExample() {
        return new TableClickListenersExample();
    }

}
