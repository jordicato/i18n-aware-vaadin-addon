package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareComponent;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.processor.GenerateInstantiateSubclassAspect;
import com.opnworks.vaadin.i18n.support.I18NAwareComponentCaptionSupport;
import com.vaadin.shared.ui.colorpicker.Color;
import com.vaadin.ui.ColorPickerArea;

/**
 * The I18N ColorPickerArea
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
@GenerateInstantiateSubclassAspect
@SuppressWarnings("serial")
public class I18NColorPickerArea extends ColorPickerArea implements I18NAwareComponent {

	private I18NAwareComponentCaptionSupport i18NAwareComponentCaptionSupport;

	/**
	 * Creates a new i18n ColorPickerArea. The value of the ColorPickerArea is false and it is immediate by default.
	 */
	public I18NColorPickerArea() {
		super();
	}

	/**
	 * Creates a new i18n ColorPickerArea with caption message key.
	 * 
	 * The value of the ColorPickerArea is false and it is immediate by default.
	 * 
	 * @param captionKey
	 *            the ColorPickerArea caption message key.
	 */
	public I18NColorPickerArea(@I18NAwareMessage String captionKey) {
		super(captionKey);
		getI18NAwareComponentCaptionSupport().setCaptionMessage(captionKey);
	}

	/**
	 * Creates a new i18n ColorPickerArea with caption message key and click listener.
	 * 
	 * @param captionKey
	 *            the ColorPickerArea caption message key.
	 * @param listener
	 *            the ColorPickerArea click listener.
	 */
	public I18NColorPickerArea(@I18NAwareMessage String captionKey, Color color) {
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
