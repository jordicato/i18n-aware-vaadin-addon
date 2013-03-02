package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareComponent;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.processor.GenerateInstantiateSubclassAspect;
import com.opnworks.vaadin.i18n.support.I18NAwareComponentCaptionSupport;
import com.vaadin.shared.ui.colorpicker.Color;
import com.vaadin.ui.ColorPicker;

/**
 * The I18N ColorPicker
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
@GenerateInstantiateSubclassAspect
@SuppressWarnings("serial")
public class I18NColorPicker extends ColorPicker implements I18NAwareComponent {

	private I18NAwareComponentCaptionSupport i18NAwareComponentCaptionSupport;

	/**
	 * Creates a new i18n ColorPicker. The value of the ColorPicker is false and it is immediate by default.
	 */
	public I18NColorPicker() {
		super();
	}

	/**
	 * Creates a new i18n ColorPicker with caption message key.
	 * 
	 * The value of the ColorPicker is false and it is immediate by default.
	 * 
	 * @param captionKey
	 *            the ColorPicker caption message key.
	 */
	public I18NColorPicker(@I18NAwareMessage String captionKey) {
		super(captionKey);
		getI18NAwareComponentCaptionSupport().setCaptionMessage(captionKey);
	}

	/**
	 * Creates a new i18n ColorPicker with caption message key and click listener.
	 * 
	 * @param captionKey
	 *            the ColorPicker caption message key.
	 * @param listener
	 *            the ColorPicker click listener.
	 */
	public I18NColorPicker(@I18NAwareMessage String captionKey, Color color) {
		super(captionKey, color);
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

}
