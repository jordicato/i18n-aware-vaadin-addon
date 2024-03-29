package com.vaadin.demo.sampler.features.layouts;

import com.opnworks.vaadin.i18n.ui.I18NVerticalLayout;
import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.NamedExternalResource;

@SuppressWarnings("serial")
public class VerticalLayoutBasic extends Feature {

    @Override
    public Version getSinceVersion() {
        return Version.OLD;
    }

    @Override
    public String getName() {
        return "Vertical layout";
    }

    @Override
    public String getDescription() {
        return "The I18NVerticalLayout arranges components vertically. "
                + " It is 100% wide by default, which is nice in many cases,"
                + " but something to be aware of if trouble arises.<br/>It supports all basic features, plus some advanced stuff - including spacing, margin, alignment, and expand ratios.";
    }

    @Override
    public APIResource[] getRelatedAPI() {
        return new APIResource[] { new APIResource(I18NVerticalLayout.class) };
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends Feature>[] getRelatedFeatures() {
        return new Class[] { HorizontalLayoutBasic.class, LayoutSpacing.class,
                LayoutAlignment.class, };
    }

    @Override
    public NamedExternalResource[] getRelatedResources() {
        return null;
    }
}
