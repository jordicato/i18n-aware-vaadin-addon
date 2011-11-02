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
public interface I18NService extends I18NMessageProvider {

	/**
	 * @return current Locale
	 */
	Locale getLocale();
	
	/**
	 * Register a top level I18NAware
	 * 
	 * @param i18NAware A I18NAware
	 */
	<T> void registerI18NAware(T i18NAware);
}
