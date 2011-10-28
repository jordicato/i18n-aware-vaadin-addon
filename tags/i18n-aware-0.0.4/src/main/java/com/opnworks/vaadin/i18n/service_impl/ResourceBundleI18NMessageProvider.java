package com.opnworks.vaadin.i18n.service_impl;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.opnworks.vaadin.i18n.I18NMessageProvider;

/**
 * The Java ResourceBundle I18NMessageProvider implementation
 * 
 * @author Pedro Rodriguez
 */
public class ResourceBundleI18NMessageProvider implements I18NMessageProvider {

	private String baseName;
	private ClassLoader classLoader;

	protected Locale locale;
	
	private ResourceBundle bundle;

	public ResourceBundleI18NMessageProvider(String baseName) {
		this(baseName, null);
	}
	
	public ResourceBundleI18NMessageProvider(String baseName,
			ClassLoader classLoader) {

		this.baseName = baseName;
		this.classLoader = classLoader;
	}

	public void setLocale(Locale locale) {

		this.locale = locale;
		
		if (classLoader == null) {
			bundle = ResourceBundle.getBundle(baseName, locale);
		} else {
			bundle = ResourceBundle.getBundle(baseName, locale, classLoader);
		}
	}

	@Override
	public String getMessage(String key, Object... args) {
		try {
			MessageFormat messageFormat = new MessageFormat(
					bundle.getString(key), locale);
			return messageFormat.format(args, new StringBuffer(), null)
					.toString();
		} catch (MissingResourceException e) {
			return key;
		}
	}

}
