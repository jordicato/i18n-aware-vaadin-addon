package com.opnworks.vaadin.i18n;

import java.util.Locale;

/**
 * This is the main integration interface of this addon.
 * 
 * Add a I18N object to your Vaadin Application.
 * 
 * The setLocale() method force a i18N update of all top level I18NAware items
 * registered and the update gets propagated to all related I18NAware items.
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public interface I18NService {

	/**
	 * Change current Locale
	 * 
	 * @param locale
	 */
	void setLocale(Locale locale);

	/**
	 * @return current Locale
	 */
	Locale getLocale();
	
	/**
	 * Retrieve a message using the current locale ( may contain {@link java.text.MessageFormat}
	 * arguments).
	 * 
	 * @param key
	 *            the message key
	 * @param args
	 *            the message arguments (if any).
	 * @return the resolved message
	 */
	String getMessage(String key, Object... args);
	
	/**
	 * Retrieve a message using an specific locale ( may contain {@link java.text.MessageFormat}
	 * arguments).
	 * 
	 * @param locale
	 *            the locale
	 * @param key
	 *            the message key
	 * @param args
	 *            the message arguments (if any).
	 * @return the resolved message
	 */
	String getMessage(Locale locale, String key, Object... args);
	
	
	/**
	 * Register a top level I18NAware
	 * 
	 * @param i18NAware A I18NAware
	 */
	<T> void registerI18NAware(T i18NAware);
}
