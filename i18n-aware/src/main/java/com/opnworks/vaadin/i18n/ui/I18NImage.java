package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareAltText;
import com.opnworks.vaadin.i18n.I18NAwareComponent;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.processor.GenerateInstantiateSubclassAspect;
import com.opnworks.vaadin.i18n.support.I18NAwareComponentAltTextSupport;
import com.opnworks.vaadin.i18n.support.I18NAwareComponentCaptionSupport;
import com.vaadin.server.Resource;
import com.vaadin.ui.Image;

/**
 * The I18N Image
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
@GenerateInstantiateSubclassAspect
@SuppressWarnings("serial")
public class I18NImage extends Image implements I18NAwareComponent, I18NAwareAltText {

	private I18NAwareComponentCaptionSupport i18NAwareComponentCaptionSupport;
	
	private I18NAwareComponentAltTextSupport i18NAwareComponentAltTextSupport;

	/**
	 * Creates a new i18n Image. The value of the Image is false and it is immediate by default.
	 */
	public I18NImage() {
		super();
	}

	/**
	 * Creates a new i18n Image with caption message key.
	 * 
	 * The value of the Image is false and it is immediate by default.
	 * 
	 * @param captionKey
	 *            the Image caption message key.
	 */
	public I18NImage(@I18NAwareMessage String captionKey) {
		super(captionKey);
		getI18NAwareComponentCaptionSupport().setCaptionMessage(captionKey);
	}

	/**
	 * Creates a new i18n Image with caption message key and click listener.
	 * 
	 * @param captionKey
	 *            the Image caption message key.
	 * @param listener
	 *            the Image click listener.
	 */
	public I18NImage(@I18NAwareMessage String captionKey, Resource resource) {
		super(captionKey, resource);
		getI18NAwareComponentCaptionSupport().setCaptionMessage(captionKey);
	}

	@Override
	public void i18NUpdate(I18NService i18N) {
		getI18NAwareComponentCaptionSupport().i18NUpdate(i18N);
	}

	@Override
	public void setCaption(@I18NAwareMessage String captionKey) {
		setCaptionMessage(captionKey);
	}

	@Override
	public void setCaptionMessage(@I18NAwareMessage String captionKey, Object... params) {
		getI18NAwareComponentCaptionSupport().setCaptionMessage(captionKey, params);
	}

	@Override
	public void setDescription(@I18NAwareMessage String descriptionKey) {
		setDescriptionMessage(descriptionKey);
	}

	@Override
	public void setDescriptionMessage(@I18NAwareMessage String descriptionKey, Object... descriptionParams) {
		getI18NAwareComponentCaptionSupport().setDescriptionMessage(descriptionKey, descriptionParams);
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

	private I18NAwareComponentCaptionSupport getI18NAwareComponentCaptionSupport() {

		if (i18NAwareComponentCaptionSupport == null) {
			i18NAwareComponentCaptionSupport = new I18NAwareComponentCaptionSupport(this);
		}

		return i18NAwareComponentCaptionSupport;
	}

	private I18NAwareComponentAltTextSupport getI18NAwareComponentAltTextSupport() {

		if (i18NAwareComponentAltTextSupport == null) {
			i18NAwareComponentAltTextSupport = new I18NAwareComponentAltTextSupport(this);
		}

		return i18NAwareComponentAltTextSupport;
	}
}
