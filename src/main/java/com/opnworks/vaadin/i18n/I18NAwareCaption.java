package com.opnworks.vaadin.i18n;

/**
 * The I18NAware caption interface
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public interface I18NAwareCaption extends I18NAware {

	/**
	 * Set the caption message key.
	 * 
	 * @param captionKey
	 *            the caption message key.
	 */
	void setCaptionKey(String captionKey);

	/**
	 * Set the caption message parameters.
	 * 
	 * @param params
	 *            the caption message parameters.
	 */
	void setCaptionParams(Object... params);
}
