package com.opnworks.vaadin.i18n;

import com.vaadin.ui.Field;

/**
 * The I18NAwareField
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public interface I18NAwareField extends I18NAwareComponent, I18NAwareCaption, Field {

	/**
	 * Set the required error message
	 * 
	 * @param requiredErrorKey
	 *            the requiredError message key.
	 * @param requiredErrorParams
	 *            the requiredError message parameters.
	 */
	void setRequiredErrorMessage(@I18NAwareMessage String requiredErrorKey, Object... requiredErrorParams);
}
