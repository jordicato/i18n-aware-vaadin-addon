package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareComponentExpression;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.processor.GenerateInstantiateSubclassAspect;
import com.opnworks.vaadin.i18n.support.I18NAwareComponentExpressionSupport;
import com.opnworks.vaadin.i18n.support.I18NExpression;
import com.vaadin.shared.ui.colorpicker.Color;
import com.vaadin.ui.ColorPicker;

/**
 * The I18N ColorPicker
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
@GenerateInstantiateSubclassAspect
@SuppressWarnings("serial")
public class I18NColorPicker extends ColorPicker implements I18NAwareComponentExpression {

	private I18NAwareComponentExpressionSupport i18NAwareComponentExpressionSupport;

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
		setCaptionMessage(captionKey);
	}

	public I18NColorPicker(I18NExpression captionExpression) {
		super(captionExpression.getStringFinal());
		setCaptionMessage(captionExpression);
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
		setCaptionMessage(captionKey);
	}

	public I18NColorPicker(I18NExpression captionExpression, Color color) {
		super(captionExpression.getStringFinal(), color);
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
	public void setRealCaption(String caption) {
		super.setCaption(caption);
	}

	@Override
	public void setRealDescription(String description) {
		super.setDescription(description);
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
