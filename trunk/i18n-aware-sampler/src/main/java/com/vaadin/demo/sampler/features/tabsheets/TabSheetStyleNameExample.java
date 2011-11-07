package com.vaadin.demo.sampler.features.tabsheets;

import com.opnworks.vaadin.i18n.ui.I18NLabel;
import com.opnworks.vaadin.i18n.ui.I18NVerticalLayout;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.TabSheet;

public class TabSheetStyleNameExample extends I18NVerticalLayout {
    // Icons for the table
    private static final ThemeResource icon1 = new ThemeResource(
            "../sampler/icons/action_save.gif");
    private static final ThemeResource icon2 = new ThemeResource(
            "../sampler/icons/comment_yellow.gif");
    private static final ThemeResource icon3 = new ThemeResource(
            "../sampler/icons/icon_info.gif");

    public TabSheetStyleNameExample() {
        TabSheet t = new TabSheet();

        // Tab 1 content
        I18NVerticalLayout l1 = new I18NVerticalLayout();
        l1.setMargin(true);
        l1.addComponent(new I18NLabel("There are no previously saved actions."));
        // Tab 2 content
        I18NVerticalLayout l2 = new I18NVerticalLayout();
        l2.setMargin(true);
        l2.addComponent(new I18NLabel("There are no saved notes."));
        // Tab 3 content
        I18NVerticalLayout l3 = new I18NVerticalLayout();
        l3.setMargin(true);
        l3.addComponent(new I18NLabel("There are currently no issues."));

        t = new TabSheet();
        t.setHeight("200px");
        t.setWidth("400px");

        t.addTab(l1, "Saved actions", icon1).setStyleName("saved");
        t.addTab(l2, "Notes", icon2).setStyleName("notes");
        t.addTab(l3, "Issues", icon3).setStyleName("issues");

        addComponent(t);
    }

}
