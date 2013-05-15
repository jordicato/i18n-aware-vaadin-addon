package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareAltText;
import com.opnworks.vaadin.i18n.I18NAwareComponentExpression;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.processor.GenerateInstantiateSubclassAspect;
import com.opnworks.vaadin.i18n.support.I18NAwareComponentAltTextSupport;
import com.opnworks.vaadin.i18n.support.I18NAwareComponentExpressionSupport;
import com.opnworks.vaadin.i18n.support.I18NExpression;
import com.vaadin.server.Resource;
import com.vaadin.ui.Flash;

/**
 * The I18N Flash
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
@GenerateInstantiateSubclassAspect
@SuppressWarnings("serial")
public class I18NFlash extends Flash implements I18NAwareComponentExpression, I18NAwareAltText {

	private I18NAwareComponentExpressionSupport i18NAwareComponentExpressionSupport;
	private I18NAwareComponentAltTextSupport i18NAwareComponentAltTextSupport;

	/**
	 * Creates a new i18n Flash. The value of the Flash is false and it is immediate by default.
	 */
	public I18NFlash() {
		super();
	}

	/**
	 * Creates a new i18n Flash with caption message key.
	 * 
	 * The value of the Flash is false and it is immediate by default.
	 * 
	 * @param captionKey
	 *            the Flash caption message key.
	 */
	public I18NFlash(@I18NAwareMessage String captionKey) {
		super(captionKey);
		setCaptionMessage(captionKey);
	}

	public I18NFlash(I18NExpression captionExpression) {
		super(captionExpression.getStringFinal());
		setCaptionMessage(captionExpression.getObjectlist());
	}

	/**
	 * Creates a new i18n Flash with caption message key and click listener.
	 * 
	 * @param captionKey
	 *            the Flash caption message key.
	 * @param listener
	 *            the Flash click listener.
	 */
	public I18NFlash(@I18NAwareMessage String captionKey, Resource resource) {
		super(captionKey, resource);
		setCaptionMessage(captionKey);
	}

	public I18NFlash(I18NExpression captionExpression, Resource resource) {
		super(captionExpression.getStringFinal(), resource);
		setCaptionMessage(captionExpression.getObjectlist());
	}

	@Override
	public void i18NUpdate(I18NService i18N) {
		getI18NAwareComponentExpressionSupport().i18NUpdate(i18N);
		getI18NAwareComponentAltTextSupport().i18NUpdate(i18N);
	}

	@Override
	public void setCaption(@I18NAwareMessage String captionKey) {
		setCaptionMessage(captionKey);
	}

	public void setCaption(Object... expression) {
		setCaptionMessage(expression);
	}

	public void setDescription(Object... expression) {
		setDescriptionMessage(expression);
	}

	@Override
	public void setCaptionMessage(@I18NAwareMessage String captionKey, Object... params) {
		getI18NAwareComponentExpressionSupport().setCaptionMessage(captionKey, params);
	}

	@Override
	public void setDescription(@I18NAwareMessage String descriptionKey) {
		setDescriptionMessage(descriptionKey);
	}

	@Override
	public void setDescriptionMessage(@I18NAwareMessage String descriptionKey, Object... descriptionParams) {
		getI18NAwareComponentExpressionSupport().setDescriptionMessage(descriptionKey, descriptionParams);
	}

	@Override
	public void setAlternateText(@I18NAwareMessage String altTextKey) {
		setAltTextMessage(altTextKey);
	}

	@Override
	public void setAltTextMessage(String altTextKey, Object... params) {
		getI18NAwareComponentAltTextSupport().setAltTextMessage(altTextKey, params);
	}

	@Override
	public void setRealAltText(String altText) {
		super.setAlternateText(altText);
	}

	@Override
	public void setRealCaption(String caption) {
		super.setCaption(caption);
	}

	@Override
	public void setRealDescription(String description) {
		super.setDescription(description);
	}

	private I18NAwareComponentAltTextSupport getI18NAwareComponentAltTextSupport() {

		if (i18NAwareComponentAltTextSupport == null) {
			i18NAwareComponentAltTextSupport = new I18NAwareComponentAltTextSupport(this);
		}

		return i18NAwareComponentAltTextSupport;
	}

	@Override
	public void setCaptionMessage(Object... expression) {
		getI18NAwareComponentExpressionSupport().setCaptionMessage(expression);
	}

	@Override
	public void setDescriptionMessage(Object... expression) {
		getI18NAwareComponentExpressionSupport().setDescriptionMessage(expression);
	}

	private I18NAwareComponentExpressionSupport getI18NAwareComponentExpressionSupport() {

		if (i18NAwareComponentExpressionSupport == null) {
			i18NAwareComponentExpressionSupport = new I18NAwareComponentExpressionSupport(this);
		}

		return i18NAwareComponentExpressionSupport;
	}
}
