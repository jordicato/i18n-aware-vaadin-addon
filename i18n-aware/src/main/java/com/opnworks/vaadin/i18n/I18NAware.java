package com.opnworks.vaadin.i18n;

import java.util.Locale;

/**
 * This interface is used to propagate i18n messages
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public interface I18NAware {

	void setLocale( Locale locale );
	
	Locale getLocale();

	/**
	 * Performs the i18N update
	 * 
	 * @param i18NService
	 *            the I18NService.
	 */
	void i18NUpdate(I18NService i18NService);

}
