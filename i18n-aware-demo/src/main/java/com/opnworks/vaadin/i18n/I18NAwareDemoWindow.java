package com.opnworks.vaadin.i18n;

import java.util.Locale;

import com.opnworks.vaadin.i18n.service_impl.I18NAwareFactory;
import com.opnworks.vaadin.i18n.ui.I18NHorizontalLayout;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.Button;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class I18NAwareDemoWindow extends I18NHorizontalLayout {

	private static final long serialVersionUID = -2694348034991874484L;

	private I18NService i18NService;

	public I18NAwareDemoWindow(I18NService i18NService) {

		// super("main.window.title");

		this.i18NService = i18NService;

		initUI();
	}

	private void initUI() {

		TabSheet tabSheet = new TabSheet();

		VerticalLayout tab1Content = new VerticalLayout();

		tab1Content.addComponent(new TextField("tab1.textfield.caption"));

		TextField tfemail = new TextField("tab1.textfield-emailvalidated.caption");

		tab1Content.addComponent(tfemail);

		EmailValidator emailValidator = new EmailValidator("tab1.textfield-emailvalidated.error");

		tfemail.addValidator(emailValidator);

		TextField tfvalidated = new TextField("tab1.textfield-stringvalidated.caption");

		tab1Content.addComponent(tfvalidated);

		StringLengthValidator stringval = new StringLengthValidator("tab1.textfield-stringvalidated.error", 3, 6, false);

		tfvalidated.addValidator(stringval);

		tab1Content.addComponent(new Button("tab1.button.caption"));

		Tab tab1 = tabSheet.addTab(tab1Content);
		I18NAwareFactory.setCaptionMessage(tab1, "tab1.name");

		VerticalLayout tab2Content = new VerticalLayout();

		tab2Content.addComponent(new TextField("tab2.textfield.caption"));

		tab2Content.addComponent(new Button("tab2.button.caption"));

		Tab tab2 = tabSheet.addTab(tab2Content);
		I18NAwareFactory.setCaptionMessage(tab2, "tab2.name");

		addComponent(tabSheet);

		addComponent(createLanguageSelector(i18NService));

	}

	@SuppressWarnings("serial")
	private NativeSelect createLanguageSelector(final I18NService i18NService) {

		NativeSelect languageSelector = new NativeSelect("language.selector");

		languageSelector.setImmediate(true);
		languageSelector.setNullSelectionAllowed(false);

		languageSelector.addItem(Locale.ENGLISH);
		languageSelector.setItemCaption(Locale.ENGLISH, "English");

		languageSelector.addItem(Locale.FRENCH);
		languageSelector.setItemCaption(Locale.FRENCH, "Francais");

		languageSelector.setValue(i18NService.getLocale());

		languageSelector.addListener(new Property.ValueChangeListener() {
			public void valueChange(ValueChangeEvent event) {
				Locale locale = (Locale) (event.getProperty().getValue());
				i18NService.setLocale(locale);
			}
		});

		return languageSelector;
	}

}
