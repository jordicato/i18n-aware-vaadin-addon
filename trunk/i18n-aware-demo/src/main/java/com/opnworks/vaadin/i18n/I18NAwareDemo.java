package com.opnworks.vaadin.i18n;

import java.util.Locale;

import com.opnworks.vaadin.i18n.service_impl.I18NAwareFactory;
import com.opnworks.vaadin.i18n.service_impl.I18NServiceImpl;
import com.opnworks.vaadin.i18n.service_impl.ResourceBundleI18NMessageProvider;
import com.vaadin.Application;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * The I18NAware Demo webapp
 * 
 * @author Pedro Rodriguez
 */
public class I18NAwareDemo extends Application {

	private static final long serialVersionUID = 2326412816728518688L;

	private static final Locale DEFAULT_LOCALE = Locale.ENGLISH;

	@Override
	public void init() {

		I18NService i18NService = new I18NServiceImpl(
				new ResourceBundleI18NMessageProvider("messages"));

		Window mainWindow = createMainWindow(i18NService);

		setMainWindow(mainWindow);

		i18NService.registerI18NAware(mainWindow);

		// Set initial language
		i18NService.setLocale(DEFAULT_LOCALE);
	}

	private Window createMainWindow(final I18NService i18NService) {

		Window mainWindow = I18NAwareFactory.newWindow("main.window.title");

		TabSheet tabSheet = I18NAwareFactory.newTabSheet();

		VerticalLayout tab1Content = I18NAwareFactory.newVerticalLayout();

		tab1Content.addComponent(I18NAwareFactory
				.newTextField("tab1.textfield.caption"));

		tab1Content.addComponent(I18NAwareFactory
				.newButton("tab1.button.caption"));

		Tab tab1 = tabSheet.addTab(tab1Content);
		I18NAwareFactory.setCaptionKey(tab1, "tab1.name");

		VerticalLayout tab2Content = I18NAwareFactory.newVerticalLayout();

		tab2Content.addComponent(I18NAwareFactory
				.newTextField("tab2.textfield.caption"));

		tab2Content.addComponent(I18NAwareFactory
				.newButton("tab2.button.caption"));

		Tab tab2 = tabSheet.addTab(tab2Content);
		I18NAwareFactory.setCaptionKey(tab2, "tab2.name");

		mainWindow.addComponent(tabSheet);

		mainWindow.addComponent(createLanguageSelector(i18NService));

		return mainWindow;
	}

	@SuppressWarnings("serial")
	private NativeSelect createLanguageSelector(final I18NService i18NService) {

		NativeSelect languageSelector = I18NAwareFactory
				.newNativeSelect("language.selector");

		languageSelector.setImmediate(true);
		languageSelector.setNullSelectionAllowed(false);

		languageSelector.addItem(Locale.ENGLISH);
		languageSelector.setItemCaption(Locale.ENGLISH, "English");

		languageSelector.addItem(Locale.FRENCH);
		languageSelector.setItemCaption(Locale.FRENCH, "Français");

		languageSelector.setValue(DEFAULT_LOCALE);

		languageSelector.addListener(new Property.ValueChangeListener() {
			public void valueChange(ValueChangeEvent event) {
				Locale locale = (Locale) (event.getProperty().getValue());
				i18NService.setLocale(locale);
			}
		});

		return languageSelector;
	}

}
