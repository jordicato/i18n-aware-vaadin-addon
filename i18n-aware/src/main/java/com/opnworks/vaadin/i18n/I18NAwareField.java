package com.opnworks.vaadin.i18n;

import com.vaadin.ui.Field;

/**
 * The I18NAwareField
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public interface I18NAwareField<T> extends I18NAwareComponent, I18NAwareCaption, Field<T> {

	/**
	 * Set the required error
	 * 
	 * @param requiredError
	 *            the requiredError message.
	 */
	void setRealRequiredError(String requiredError);

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
