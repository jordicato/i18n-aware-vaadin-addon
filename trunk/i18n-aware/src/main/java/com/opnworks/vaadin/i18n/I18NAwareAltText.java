package com.opnworks.vaadin.i18n;

/**
 * The I18NAware altText interface
 * 
 * @author Sandy Rodriguez Garcia ( OpnWorks )
 */
public interface I18NAwareAltText extends I18NAware {

	/**
	 * Set the caption message.
	 * 
	 * @param altTextKey
	 *            the altText message key.
	 * @param params
	 *            the altText message parameters.
	 */
	void setAltTextMessage(@I18NAwareMessage String altTextKey, Object... params);

	/**
	 * Set the real altText
	 * 
	 * @param altText
	 *            the altText
	 */
	void setRealAltText(String altText);
}
