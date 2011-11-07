package com.opnworks.vaadin.i18n;

/**
 * The I18NAwareValue
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public interface I18NAwareValue extends I18NAware {

	/**
	 * Set the value message key
	 * 
	 * @param valueKey
	 *            the value message key.
	 * @param valueParams
	 *            the value message parameters.
	 */
	void setValueMessage(String valueKey, Object... valueParams);
}
