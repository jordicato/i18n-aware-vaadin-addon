package com.vaadin.demo.sampler.features.shortcuts;

import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.NamedExternalResource;

@SuppressWarnings("serial")
public class ShortcutBasics extends Feature {

    @Override
    public Version getSinceVersion() {
        return Version.V63;
    }

    @Override
    public String getName() {
        return "Shortcuts, basics";
    }

    @Override
    public String getDescription() {
        return "A simple example of shorcuts attached directly"
                + " to fields.<br/>Such a shortcut is window-global, "
                + "and is conveniently removed if the component is "
                + "removed. " //
                + "<br/><br/>Note, that all browsers don't work "
                + "well with all keyboard shortcuts. This is a problem "
                + "for the whole web application industry. Opera is "
                + "the most intolerant on them and basically only "
                + "ALT-SHIFT based shortcuts are working. Shortcuts here are "
                + "chosen so that they should work on most common browsers.";
    }

    @Override
    public APIResource[] getRelatedAPI() {
        return new APIResource[] {};
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends Feature>[] getRelatedFeatures() {
        return new Class[] {};
    }

    @Override
    public NamedExternalResource[] getRelatedResources() {
        // TODO Auto-generated method stub
        return null;
    }

}
