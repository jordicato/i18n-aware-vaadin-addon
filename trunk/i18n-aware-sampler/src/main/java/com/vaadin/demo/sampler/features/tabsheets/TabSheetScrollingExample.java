package com.vaadin.demo.sampler.features.tabsheets;

import com.opnworks.vaadin.i18n.ui.I18NLabel;
import com.opnworks.vaadin.i18n.ui.I18NVerticalLayout;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.Tab;

@SuppressWarnings("serial")
public class TabSheetScrollingExample extends I18NVerticalLayout implements
        TabSheet.SelectedTabChangeListener {

    private static final ThemeResource icon1 = new ThemeResource(
            "../sampler/icons/action_save.gif");
    private static final ThemeResource icon2 = new ThemeResource(
            "../sampler/icons/comment_yellow.gif");
    private static final ThemeResource icon3 = new ThemeResource(
            "../sampler/icons/icon_info.gif");

    private TabSheet t;

    public TabSheetScrollingExample() {
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
        // Tab 4 content
        I18NVerticalLayout l4 = new I18NVerticalLayout();
        l4.setMargin(true);
        l4.addComponent(new I18NLabel("There are no comments."));
        // Tab 5 content
        I18NVerticalLayout l5 = new I18NVerticalLayout();
        l5.setMargin(true);
        l5.addComponent(new I18NLabel("There is no new feedback."));

        t = new TabSheet();
        t.setHeight("200px");
        t.setWidth("400px");
        t.addTab(l1, "Saved actions", icon1);
        t.addTab(l2, "Notes", icon2);
        t.addTab(l3, "Issues", icon3);
        t.addTab(l4, "Comments", icon2);
        t.addTab(l5, "Feedback", icon2);
        t.addListener(this);

        addComponent(t);
    }

    public void selectedTabChange(SelectedTabChangeEvent event) {
        TabSheet tabsheet = event.getTabSheet();
        Tab tab = tabsheet.getTab(tabsheet.getSelectedTab());
        if (tab != null) {
            getWindow().showNotification("Selected tab: " + tab.getCaption());
        }
    }
}
