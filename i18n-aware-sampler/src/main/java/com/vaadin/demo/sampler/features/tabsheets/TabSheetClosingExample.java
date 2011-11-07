package com.vaadin.demo.sampler.features.tabsheets;

import com.opnworks.vaadin.i18n.ui.I18NLabel;
import com.opnworks.vaadin.i18n.ui.I18NVerticalLayout;
import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.Tab;

@SuppressWarnings("serial")
public class TabSheetClosingExample extends I18NVerticalLayout implements
        TabSheet.SelectedTabChangeListener, TabSheet.CloseHandler {

    private TabSheet t;

    public TabSheetClosingExample() {
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

        final Tab saved = t.addTab(l1, "Saved actions");
        saved.setClosable(true);
        final Tab notes = t.addTab(l2, "Notes");
        notes.setClosable(true);
        final Tab issues = t.addTab(l3, "Issues");
        issues.setClosable(true);
        final Tab comments = t.addTab(l4, "Comments");
        comments.setClosable(true);
        final Tab feedback = t.addTab(l5, "Feedback");
        feedback.setClosable(true);

        t.addListener(this);
        t.setCloseHandler(this);

        addComponent(t);
    }

    public void selectedTabChange(SelectedTabChangeEvent event) {
        TabSheet tabsheet = event.getTabSheet();
        Tab tab = tabsheet.getTab(tabsheet.getSelectedTab());
        if (tab != null) {
            getWindow().showNotification("Selected tab: " + tab.getCaption());
        }
    }

    public void onTabClose(TabSheet tabsheet, Component tabContent) {
        getWindow().showNotification(
                "Closed tab: " + tabsheet.getTab(tabContent).getCaption());
        tabsheet.removeComponent(tabContent);
    }
}
