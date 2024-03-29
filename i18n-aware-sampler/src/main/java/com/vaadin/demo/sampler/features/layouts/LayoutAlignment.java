package com.vaadin.demo.sampler.features.layouts;

import com.opnworks.vaadin.i18n.ui.I18NVerticalLayout;
import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.NamedExternalResource;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;

@SuppressWarnings("serial")
public class LayoutAlignment extends Feature {

    @Override
    public Version getSinceVersion() {
        return Version.OLD;
    }

    @Override
    public String getName() {
        return "Component alignment";
    }

    @Override
    public String getDescription() {
        return "GridLayout, I18NVerticalLayout, and HorizontalLayout, "
                + "which are tabular layouts consisting of cells, "
                + "support alignment of components within the layout cells. "
                + "The alignment of a component within its respective cell "
                + "is set with setComponentAlignment().";
    }

    @Override
    public APIResource[] getRelatedAPI() {
        return new APIResource[] { new APIResource(I18NVerticalLayout.class),
                new APIResource(HorizontalLayout.class),
                new APIResource(GridLayout.class), };
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends Feature>[] getRelatedFeatures() {
        return new Class[] { HorizontalLayoutBasic.class,
                VerticalLayoutBasic.class };
    }

    @Override
    public NamedExternalResource[] getRelatedResources() {
        return null;
    }
}
