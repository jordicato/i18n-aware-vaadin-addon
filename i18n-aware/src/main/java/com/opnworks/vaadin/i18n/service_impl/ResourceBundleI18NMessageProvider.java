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
		
		bundle = getBundle(locale);
	}

	@Override
	public String getMessage(String key, Object... args) {
		return getMessage( bundle, key, args);
	}

	@Override
	public String getMessage(Locale locale, String key, Object... args) {
		
		ResourceBundle localeBundle = null;
		
		if( locale.equals( this.locale ) ) {
			localeBundle = bundle;
		}
		else {
			localeBundle = getBundle(locale) ;
		}
		
		return getMessage( localeBundle, key, args);
	}

	private String getMessage(ResourceBundle localeBundle, String key, Object... args) {
		try {
			MessageFormat messageFormat = new MessageFormat(
					localeBundle.getString(key), locale);
			return messageFormat.format(args, new StringBuffer(), null)
					.toString();
		} catch (MissingResourceException e) {
			return key;
		}
	}
	
	private ResourceBundle getBundle(Locale locale) {
		
		if (classLoader == null) {
			return ResourceBundle.getBundle(baseName, locale);
		}
		
		return ResourceBundle.getBundle(baseName, locale, classLoader);
	}
}
