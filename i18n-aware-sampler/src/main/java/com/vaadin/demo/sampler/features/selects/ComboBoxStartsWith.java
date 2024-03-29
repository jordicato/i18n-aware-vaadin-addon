package com.vaadin.demo.sampler.features.selects;

import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.NamedExternalResource;
import com.vaadin.ui.ComboBox;

@SuppressWarnings("serial")
public class ComboBoxStartsWith extends Feature {

    @Override
    public Version getSinceVersion() {
        return Version.OLD;
    }

    @Override
    public String getName() {
        return "Combobox, suggesting (starts-with)";
    }

    @Override
    public String getDescription() {
        return "A drop-down selection component with single item selection.<br/>"
                + " A 'starts-with' filter has been used in this example,"
                + " so you can key in some text and only the options"
                + " beginning with your input will be shown.<br/>"
                + " Because there are so many options, they are loaded on-demand"
                + " (\"lazy-loading\") from the server when paging or"
                + " filtering. This behavior is built-in and requires no extra"
                + " code.";
    }

    @Override
    public APIResource[] getRelatedAPI() {
        return new APIResource[] { new APIResource(ComboBox.class) };
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends Feature>[] getRelatedFeatures() {
        return new Class[] { ComboBoxPlain.class, ComboBoxContains.class,
                ComboBoxNewItems.class };
    }

    @Override
    public NamedExternalResource[] getRelatedResources() {
        // TODO Auto-generated method stub
        return null;
    }

}
