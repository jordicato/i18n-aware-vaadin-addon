package com.vaadin.demo.sampler.features.layouts;

import com.opnworks.vaadin.i18n.ui.I18NVerticalLayout;
import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.NamedExternalResource;
import com.vaadin.ui.HorizontalLayout;

@SuppressWarnings("serial")
public class ApplicationLayout extends Feature {

    @Override
    public Version getSinceVersion() {
        return Version.OLD;
    }

    @Override
    public String getName() {
        return "Application-style layout";
    }

    @Override
    public String getDescription() {
        return "It can be helpful to distinguish between <i>web-style</i> and"
                + " <i>application-style</i> layouting (although this is a"
                + " simplification). Both styles are supported, and can be used"
                + " simultaneously.<br/> Application-style layouting uses relatively"
                + " -sized components, growing dynamically with the window, and"
                + " causing scrollbars to appear on well-defined areas within the"
                + " window."
                + "<br/>Try resizing the window to see how the content reacts.";
    }

    @Override
    public APIResource[] getRelatedAPI() {
        return new APIResource[] { new APIResource(HorizontalLayout.class),
                new APIResource(I18NVerticalLayout.class) };
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends Feature>[] getRelatedFeatures() {
        return new Class[] { WebLayout.class, CustomLayouts.class };
    }

    @Override
    public NamedExternalResource[] getRelatedResources() {
        return null;
    }
}
