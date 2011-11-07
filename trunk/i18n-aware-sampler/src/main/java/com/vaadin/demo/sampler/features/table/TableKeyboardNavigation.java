package com.vaadin.demo.sampler.features.table;

import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.FeatureSet;
import com.vaadin.demo.sampler.NamedExternalResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.Table;

@SuppressWarnings("serial")
public class TableKeyboardNavigation extends Feature {

    @Override
    public String getDescription() {
        return "You can use the keyboard to view and edit the table selection. To move in the table use the up and "
                + "down arrow keys. By holding the CTRL key down you can move the selection head up and down "
                + "and by pressing SPACE while holding the CTRL key down you can select multiple items. To select a range "
                + "of items hold down SHIFT and move up or down using the arrow keys.";
    }

    @Override
    public String getName() {
        return "Table, keyboard navigation";
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
        return new FocusedTableExample();
    }

    private class FocusedTableExample extends TableMainFeaturesExample {

        @Override
        public void attach() {
            super.attach();
            Table table = (Table) getComponentIterator().next();
            table.focus();
        }
    }

}
