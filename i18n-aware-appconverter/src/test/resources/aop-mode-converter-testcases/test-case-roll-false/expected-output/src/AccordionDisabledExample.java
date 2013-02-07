package com.vaadin.demo.sampler.features.accordions;

import com.opnworks.vaadin.i18n.ui.I18NVerticalLayout;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.Tab;

@SuppressWarnings("serial")
public class AccordionDisabledExample extends I18NVerticalLayout implements Accordion.SelectedTabChangeListener, Button.ClickListener {

    private Accordion a;

    private Button b1;

    private Button b2;

    private Label l1;

    private Label l2;

    private Label l3;

    @SuppressWarnings("unused")
    private Tab t1;

    private Tab t2;

    private Tab t3;

    private static final ThemeResource icon1 = new ThemeResource("../sampler/icons/action_save.gif");

    private static final ThemeResource icon2 = new ThemeResource("../sampler/icons/comment_yellow.gif");

    private static final ThemeResource icon3 = new ThemeResource("../sampler/icons/icon_info.gif");

    public AccordionDisabledExample() {
        setSpacing(true);
        l1 = new Label("com.vaadin.demo.sampler.features.accordions.AccordionDisabledExample.There_are_no_previously_saved");
        l2 = new Label("com.vaadin.demo.sampler.features.accordions.AccordionDisabledExample.There_are_no_saved_notes");
        l3 = new Label("com.vaadin.demo.sampler.features.accordions.AccordionDisabledExample.There_are_currently_no_issues");
        a = new Accordion();
        a.setHeight("300px");
        a.setWidth("400px");
        t1 = a.addTab(l1, "com.vaadin.demo.sampler.features.accordions.AccordionDisabledExample.Saved_actions", icon1);
        t2 = a.addTab(l2, "com.vaadin.demo.sampler.features.accordions.AccordionDisabledExample.Notes", icon2);
        t3 = a.addTab(l3, "com.vaadin.demo.sampler.features.accordions.AccordionDisabledExample.Issues", icon3);
        a.addListener(this);
        b1 = new Button("com.vaadin.demo.sampler.features.accordions.AccordionDisabledExample.Disable_Notes_tab");
        b2 = new Button("com.vaadin.demo.sampler.features.accordions.AccordionDisabledExample.Hide_Issues_tab");
        b1.addListener(this);
        b2.addListener(this);
        HorizontalLayout hl = new HorizontalLayout();
        hl.setSpacing(true);
        hl.addComponent(b1);
        hl.addComponent(b2);
        addComponent(a);
        addComponent(hl);
    }

    public void selectedTabChange(SelectedTabChangeEvent event) {
        String c = a.getTab(event.getTabSheet().getSelectedTab()).getCaption();
        getWindow().showNotification("Selected tab: " + c);
    }

    public void buttonClick(ClickEvent event) {
        if (b1.equals(event.getButton())) {
            if (t2.isEnabled()) {
                t2.setEnabled(false);
                b1.setCaption("com.vaadin.demo.sampler.features.accordions.AccordionDisabledExample.Enable_Notes_tab");
            } else {
                t2.setEnabled(true);
                b1.setCaption("com.vaadin.demo.sampler.features.accordions.AccordionDisabledExample.Disable_Notes_tab_1");
            }
        } else {
            if (t3.isVisible()) {
                t3.setVisible(false);
                b2.setCaption("com.vaadin.demo.sampler.features.accordions.AccordionDisabledExample.Show_Issues_tab");
            } else {
                t3.setVisible(true);
                b2.setCaption("com.vaadin.demo.sampler.features.accordions.AccordionDisabledExample.Hide_Issues_tab_1");
            }
        }
        a.requestRepaint();
    }
}
