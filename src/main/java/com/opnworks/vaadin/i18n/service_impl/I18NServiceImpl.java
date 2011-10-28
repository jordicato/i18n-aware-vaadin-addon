package com.opnworks.vaadin.i18n.service_impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.opnworks.vaadin.i18n.I18NAware;
import com.opnworks.vaadin.i18n.I18NMessageProvider;
import com.opnworks.vaadin.i18n.I18NService;

/**
 * The I18NService implementation
 * 
 * @author prodriguez
 */
public class I18NServiceImpl implements I18NService {

	private I18NMessageProvider i18NMessageProvider;

	private List<I18NAware> i18NAwares = new ArrayList<I18NAware>();

	public I18NServiceImpl(I18NMessageProvider i18NMessageProvider) {
		this.i18NMessageProvider = i18NMessageProvider;
	}

	public <T> void registerI18NAware(T i18NAware) {

		if (!(i18NAware instanceof I18NAware)) {
			throw new IllegalArgumentException("Expecting a I18NAware");
		}

		i18NAwares.add((I18NAware) i18NAware);
	}

	public void setLocale(Locale locale) {
		i18NMessageProvider.setLocale(locale);
		i18nUpdate();
	}

	private void i18nUpdate() {
		for (I18NAware i18NAware : i18NAwares) {
			i18NAware.i18NUpdate(this);
		}
	}

	public String getMessage(String key, Object... args) {
		return i18NMessageProvider.getMessage(key, args);
	}
}
