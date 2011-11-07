package com.vaadin.demo.sampler.features.tabsheets;

import com.opnworks.vaadin.i18n.ui.I18NLabel;
import com.opnworks.vaadin.i18n.ui.I18NVerticalLayout;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.Tab;

@SuppressWarnings("serial")
public class TabSheetDisabledExample extends I18NVerticalLayout implements
        TabSheet.SelectedTabChangeListener, Button.ClickListener {
    private static final ThemeResource icon1 = new ThemeResource(
            "../sampler/icons/action_save.gif");
    private static final ThemeResource icon2 = new ThemeResource(
            "../sampler/icons/comment_yellow.gif");
    private static final ThemeResource icon3 = new ThemeResource(
            "../sampler/icons/icon_info.gif");

    private TabSheet t;
    private Button toggleEnabled;
    private Button toggleVisible;
    private I18NVerticalLayout l1;
    private I18NVerticalLayout l2;
    private I18NVerticalLayout l3;
    @SuppressWarnings("unused")
    private Tab t1, t2, t3;

    public TabSheetDisabledExample() {
        setSpacing(true);

        // Tab 1 content
        l1 = new I18NVerticalLayout();
        l1.setMargin(true);
        l1.addComponent(new I18NLabel("There are no previously saved actions."));
        // Tab 2 content
        l2 = new I18NVerticalLayout();
        l2.setMargin(true);
        l2.addComponent(new I18NLabel("There are no saved notes."));
        // Tab 3 content
        l3 = new I18NVerticalLayout();
        l3.setMargin(true);
        l3.addComponent(new I18NLabel("There are currently no issues."));

        t = new TabSheet();
        t.setHeight("200px");
        t.setWidth("400px");
        t1 = t.addTab(l1, "Saved actions", icon1);
        t2 = t.addTab(l2, "Notes", icon2);
        t3 = t.addTab(l3, "Issues", icon3);
        t.addListener(this);

        toggleEnabled = new Button("Disable 'Notes' tab");
        toggleEnabled.addListener(this);

        toggleVisible = new Button("Hide 'Issues' tab");
        toggleVisible.addListener(this);

        HorizontalLayout hl = new HorizontalLayout();
        hl.setSpacing(true);
        hl.addComponent(toggleEnabled);
        hl.addComponent(toggleVisible);

        addComponent(t);
        addComponent(hl);
    }

    public void selectedTabChange(SelectedTabChangeEvent event) {
        String c = t.getTab(event.getTabSheet().getSelectedTab()).getCaption();
        getWindow().showNotification("Selected tab: " + c);
    }

    public void buttonClick(ClickEvent event) {
        if (toggleEnabled.equals(event.getButton())) {
            // toggleEnabled clicked
            t2.setEnabled(!t2.isEnabled());
            toggleEnabled.setCaption((t2.isEnabled() ? "Disable" : "Enable")
                    + " 'Notes' tab");

        } else {
            // toggleVisible clicked
            t3.setVisible(!t3.isVisible());
            toggleVisible.setCaption((t3.isVisible() ? "Hide" : "Show")
                    + " 'Issues' tab");

        }
        t.requestRepaint();
    }
}
