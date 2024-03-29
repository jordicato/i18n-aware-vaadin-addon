package com.vaadin.demo.sampler.features.commons;

import com.opnworks.vaadin.i18n.ui.I18NVerticalLayout;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;

@SuppressWarnings("serial")
public class IconsExample extends I18NVerticalLayout {

    public IconsExample() {
        setSpacing(true);

        /* Button w/ icon */
        Button button = new Button("Save");
        button.setIcon(new ThemeResource("../sampler/icons/action_save.gif"));
        addComponent(button);

        /* Label */;
        Label l = new Label("Icons are very handy");
        l.setCaption("Comment");
        l.setIcon(new ThemeResource("../sampler/icons/comment_yellow.gif"));
        addComponent(l);

        /* Panel w/ links */
        Panel p = new Panel("Handy links");
        p.setIcon(new ThemeResource("../sampler/icons/icon_info.gif"));
        addComponent(p);
        Link lnk = new Link("http://vaadin.com", new ExternalResource(
                "http://www.vaadin.com"));
        lnk.setIcon(new ThemeResource("../sampler/icons/icon_world.gif"));
        p.addComponent(lnk);
        lnk = new Link("http://vaadin.com/learn", new ExternalResource(
                "http://www.vaadin.com/learn"));
        lnk.setIcon(new ThemeResource("../sampler/icons/icon_world.gif"));
        p.addComponent(lnk);
        lnk = new Link("http://dev.vaadin.com/", new ExternalResource(
                "http://dev.vaadin.com/"));
        lnk.setIcon(new ThemeResource("../sampler/icons/icon_world.gif"));
        p.addComponent(lnk);
        lnk = new Link("http://vaadin.com/forum", new ExternalResource(
                "http://vaadin.com/forum"));
        lnk.setIcon(new ThemeResource("../sampler/icons/icon_world.gif"));
        p.addComponent(lnk);

    }
}
