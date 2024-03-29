package com.vaadin.demo.sampler.themes;

import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.NamedExternalResource;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.Embedded;

public class ChameleonThemeExample extends Feature {

    @Override
    public String getName() {
        return "Chameleon Theme";
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public NamedExternalResource[] getRelatedResources() {
        return null;
    }

    @Override
    public APIResource[] getRelatedAPI() {
        return null;
    }

    @Override
    public Class<? extends Feature>[] getRelatedFeatures() {
        return null;
    }

    @Override
    public Version getSinceVersion() {
        return Version.V67;
    }

    @Override
    public Component getExample() {
        Embedded e = new Embedded(null, new ExternalResource(
                "http://demo.vaadin.com/chameleontheme?restartApplication"));
        e.setType(Embedded.TYPE_BROWSER);
        e.setSizeFull();
        return e;
    }

    @Override
    public String getSource() {
        return null;
    }

}
