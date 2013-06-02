package com.askvikrant.noticeboard.components;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

import com.askvikrant.digitalclock.DigitalClock;
import com.askvikrant.noticeboard.model.QuickLink;
import com.opnworks.vaadin.i18n.I18NServiceSingleton;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class HeaderPanel extends Panel {

    private static final long serialVersionUID = 1L;

    private HorizontalLayout logoHLayout = new HorizontalLayout();

    private HorizontalLayout linksHLayout = new HorizontalLayout();

    public HeaderPanel() {
        VerticalLayout vLayout = new VerticalLayout();
        setContent(vLayout);
        logoHLayout.setHeight(60, Unit.PIXELS);
        linksHLayout.setHeight(20, Unit.PIXELS);
        linksHLayout.setSpacing(true);
        Label nbLabel = new Label("<div align=\"center\" style=\"font-size:14pt;\">Notice Board</div>");
        nbLabel.setContentMode(ContentMode.HTML);
        nbLabel.setHeight(25, Unit.PIXELS);
        Label emptyLabel = new Label("");
        emptyLabel.setHeight(5, Unit.PIXELS);
        DigitalClock clock = new DigitalClock();
        clock.setWidth(100, Unit.PERCENTAGE);
        NativeSelect languageSelector = createLanguageSelector();
        vLayout.addComponent(languageSelector);
        vLayout.addComponent(logoHLayout);
        vLayout.addComponent(clock);
        vLayout.addComponent(emptyLabel);
        vLayout.addComponent(linksHLayout);
        vLayout.addComponent(nbLabel);        
        vLayout.setComponentAlignment(logoHLayout, Alignment.MIDDLE_CENTER);
        vLayout.setComponentAlignment(linksHLayout, Alignment.MIDDLE_CENTER);
        refreshLogo();
    }

    private NativeSelect createLanguageSelector() {
        NativeSelect languageSelector = new NativeSelect("com.askvikrant.noticeboard.components.HeaderPanel.Language");
        languageSelector.setImmediate(true);
        languageSelector.setNullSelectionAllowed(false);
        addLocale(Locale.ENGLISH, languageSelector);
        addLocale(Locale.FRENCH, languageSelector);
        addLocale(new Locale("es"), languageSelector);
        languageSelector.setValue(I18NServiceSingleton.getInstance().getI18NServive().getLocale());
        languageSelector.addValueChangeListener(new ValueChangeListener() {

            private static final long serialVersionUID = 1L;

            @Override
            public void valueChange(ValueChangeEvent event) {
                Locale locale = (Locale) (event.getProperty().getValue());
                I18NServiceSingleton.getInstance().getI18NServive().setLocale(locale);
                getUI().requestRepaintAll();
            }
        });
        return languageSelector;
    }

    private void addLocale(Locale locale, NativeSelect languageSelector) {
        languageSelector.addItem(locale);
        languageSelector.setItemCaption(locale, locale.getDisplayLanguage(locale).substring(0, 1).toUpperCase() + locale.getDisplayLanguage(locale).substring(1));
    }

    public void refreshLogo() {
        String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
        FileResource resource = new FileResource(new File(basepath + "/WEB-INF/images/logo.png"));
        Image image = new Image(null, resource);
        logoHLayout.removeAllComponents();
        logoHLayout.addComponent(image);
    }

    public void setQuickLinks(ArrayList<QuickLink> quickLinks) {
        linksHLayout.removeAllComponents();
        for (int i = 0; i < quickLinks.size(); i++) {
            Link link = new Link();
            link.setCaption(quickLinks.get(i).getName());
            link.setResource(new ExternalResource(quickLinks.get(i).getUrl()));
            linksHLayout.addComponent(link);
        }
    }
}
