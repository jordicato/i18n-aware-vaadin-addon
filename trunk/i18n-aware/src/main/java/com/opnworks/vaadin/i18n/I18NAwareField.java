package com.opnworks.vaadin.i18n;

import com.vaadin.ui.Field;

/**
 * The I18NAwareField
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public interface I18NAwareField extends I18NAwareComponent, I18NAwareCaption, Field {

	/**
	 * Set the required error message key
	 * 
	 * @param requiredErrorKey
	 *            the requiredError message key.
	 */
	void setRequiredErrorKey(String requiredErrorKey);

	/**
	 * Set the required error message parameters
	 * 
	 * @param requiredErrorParams
	 *            the requiredError message parameters.
	 */
	void setRequiredErrorParams(Object[] requiredErrorParams);
}
