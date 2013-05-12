package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareComponentExpression;
import com.opnworks.vaadin.i18n.I18NAwareField;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.processor.GenerateInstantiateSubclassAspect;
import com.opnworks.vaadin.i18n.support.I18NAwareComponentExpressionSupport;
import com.opnworks.vaadin.i18n.support.I18NAwareFieldSupport;
import com.vaadin.ui.Slider;

/**
 * The I18NSlider
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
@GenerateInstantiateSubclassAspect
@SuppressWarnings("serial")
public class I18NSlider extends Slider implements I18NAwareField<Double>, I18NAwareComponentExpression {

	private I18NAwareFieldSupport<Double> i18NAwareFieldSupport;
	private I18NAwareComponentExpressionSupport i18NAwareComponentExpressionSupport;

	public I18NSlider() {
		super();
	}

	/**
	 * Create a new slider with given range and resolution
	 * 
	 * @param min
	 * @param max
	 * @param resolution
	 */
	public I18NSlider(double min, double max, int resolution) {
		super(min, max, resolution);
	}

	/**
	 * Create a new slider with given range
	 * 
	 * @param min
	 * @param max
	 */
	public I18NSlider(int min, int max) {
		super(min, max);
	}

	/**
	 * Create a new slider with the caption given as parameter. All slider values set to defaults.
	 * 
	 * @param caption
	 *            The caption for this Slider (e.g. "Volume").
	 */
	public I18NSlider(@I18NAwareMessage String captionKey) {
		super(captionKey);
		setCaptionMessage(captionKey);
	}

	/**
	 * Create a new slider with given caption and range
	 * 
	 * @param caption
	 * @param min
	 * @param max
	 */
	public I18NSlider(@I18NAwareMessage String captionKey, int min, int max) {
		super(captionKey, min, max);
		setCaptionMessage(captionKey);
	}

	@Override
	public void i18NUpdate(I18NService i18N) {
		getI18NAwareFieldSupport().i18NUpdate(i18N);
		getI18NAwareComponentExpressionSupport().i18NUpdate(i18N);
	}

	@Override
	public void setCaption(@I18NAwareMessage String captionKey) {
		setCaptionMessage(captionKey);
	}

	@Override
	public void setCaptionMessage(@I18NAwareMessage String captionKey, Object... params) {
		getI18NAwareFieldSupport().setCaptionMessage(captionKey, params);
	}

	@Override
	public void setDescription(@I18NAwareMessage String descriptionKey) {
		setDescriptionMessage(descriptionKey);
	}

	@Override
	public void setDescriptionMessage(@I18NAwareMessage String descriptionKey, Object... descriptionParams) {
		getI18NAwareFieldSupport().setDescriptionMessage(descriptionKey, descriptionParams);
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
	public void setRealRequiredError(String requiredMessage) {
		super.setRequiredError(requiredMessage);
	}

	@Override
	public void setRequiredError(@I18NAwareMessage String requiredErrorKey) {
		setRequiredErrorMessage(requiredErrorKey);
	}

	@Override
	public void setRequiredErrorMessage(@I18NAwareMessage String requiredErrorKey, Object... requiredErrorParams) {
		getI18NAwareFieldSupport().setRequiredErrorMessage(requiredErrorKey, requiredErrorParams);
	}

	private I18NAwareFieldSupport<Double> getI18NAwareFieldSupport() {

		if (i18NAwareFieldSupport == null) {
			i18NAwareFieldSupport = new I18NAwareFieldSupport<Double>(this);
		}

		return i18NAwareFieldSupport;
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
