package com.opnworks.vaadin.i18n;

/**
 * The I18NAware caption interface
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public interface I18NAwareCaption extends I18NAware {

	/**
	 * Set the caption message.
	 * 
	 * @param captionKey
	 *            the caption message key.
	 * @param params
	 *            the caption message parameters.
	 */
	void setCaptionMessage(@I18NAwareMessage String captionKey, Object... params);
}
