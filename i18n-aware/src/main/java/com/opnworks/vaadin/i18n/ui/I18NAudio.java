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
import com.vaadin.ui.Audio;

/**
 * The I18N Audio
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
@GenerateInstantiateSubclassAspect
@SuppressWarnings("serial")
public class I18NAudio extends Audio implements I18NAwareComponentExpression, I18NAwareAltText {

	private I18NAwareComponentExpressionSupport i18NAwareComponentExpressionSupport;
	private I18NAwareComponentAltTextSupport i18NAwareComponentAltTextSupport;

	/**
	 * Creates a new i18n audio. The value of the audio is false and it is immediate by default.
	 */
	public I18NAudio() {
		super();
	}

	/**
	 * Creates a new i18n audio with caption message key.
	 * 
	 * The value of the audio is false and it is immediate by default.
	 * 
	 * @param captionKey
	 *            the audio caption message key.
	 */
	public I18NAudio(@I18NAwareMessage String captionKey) {
		super(captionKey);
		getI18NAwareComponentExpressionSupport().setCaptionMessage(captionKey);
	}

	public I18NAudio(I18NExpression captionExpression) {
		super(captionExpression.getStringFinal());
		setCaptionMessage(captionExpression);
	}

	/**
	 * Creates a new i18n audio with caption message key and click listener.
	 * 
	 * @param captionKey
	 *            the audio caption message key.
	 * @param listener
	 *            the audio click listener.
	 */
	public I18NAudio(@I18NAwareMessage String captionKey, Resource resource) {
		super(captionKey, resource);
		getI18NAwareComponentExpressionSupport().setCaptionMessage(captionKey);
	}

	public I18NAudio(I18NExpression captionExpression, Resource resource) {
		super(captionExpression.getStringFinal(), resource);
		setCaptionMessage(captionExpression);
	}

	@Override
	public void i18NUpdate(I18NService i18N) {
		getI18NAwareComponentExpressionSupport().i18NUpdate(i18N);
	}

	@Override
	public void setCaption(@I18NAwareMessage String captionKey) {
		setCaptionMessage(captionKey);
	}

	public void setCaption(I18NExpression expression) {
		setCaptionMessage(expression);
	}

	public void setDescription(I18NExpression expression) {
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
	public void setAltText(@I18NAwareMessage String altTextKey) {
		setAltTextMessage(altTextKey);
	}

	@Override
	public void setAltTextMessage(String altTextKey, Object... params) {
		getI18NAwareComponentAltTextSupport().setAltTextMessage(altTextKey, params);
	}

	@Override
	public void setRealAltText(String altText) {
		super.setAltText(altText);
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
	public void setCaptionMessage(I18NExpression expression) {
		getI18NAwareComponentExpressionSupport().setCaptionMessage(expression);
	}

	@Override
	public void setDescriptionMessage(I18NExpression expression) {
		getI18NAwareComponentExpressionSupport().setDescriptionMessage(expression);
	}

	private I18NAwareComponentExpressionSupport getI18NAwareComponentExpressionSupport() {

		if (i18NAwareComponentExpressionSupport == null) {
			i18NAwareComponentExpressionSupport = new I18NAwareComponentExpressionSupport(this);
		}

		return i18NAwareComponentExpressionSupport;
	}

}
