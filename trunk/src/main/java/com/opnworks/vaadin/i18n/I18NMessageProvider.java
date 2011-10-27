package com.opnworks.vaadin.i18n;

import java.util.Locale;

/**
 * The I18NMessageProvider interface
 * 
 * @author Pedro Rodriguez
 */
public interface I18NMessageProvider {

	/**
	 * Change current Locale
	 * 
	 * @param locale
	 */
	void setLocale(Locale locale);
	
	/**
	 * Retrieve a message ( may contain {@link java.text.MessageFormat}
	 * arguments).
	 * 
	 * @param key
	 *            the message key
	 * @param args
	 *            the message arguments (if any).
	 * @return the resolved message
	 */
	String getMessage(String key, Object... args);

}
