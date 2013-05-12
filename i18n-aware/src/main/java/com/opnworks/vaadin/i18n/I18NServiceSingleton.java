package com.opnworks.vaadin.i18n;

import java.util.Locale;

import com.opnworks.vaadin.i18n.service_impl.I18NServiceImpl;
import com.opnworks.vaadin.i18n.service_impl.ResourceBundleI18NMessageProvider;

/**
 * The I18NServiceSingleton class
 * 
 * @author Sandy Rodriguez Garcia ( OpnWorks )
 */
public class I18NServiceSingleton {

	private I18NService i18NService;

	static I18NServiceSingleton singleton = new I18NServiceSingleton();

	public static I18NServiceSingleton getInstance() {
		return singleton;
	}

	public I18NService setInitParams(String namespace, Locale locale) {
		i18NService = new I18NServiceImpl(new ResourceBundleI18NMessageProvider(namespace));
		I18NServiceImpl.setInstance(i18NService);
		i18NService.setLocale(locale);
		return i18NService;
	}

	public I18NService getI18NServive() {
		return i18NService;
	}
}
