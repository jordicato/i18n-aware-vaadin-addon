package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareComponentValueExpression;
import com.opnworks.vaadin.i18n.I18NAwareField;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.processor.GenerateInstantiateSubclassAspect;
import com.opnworks.vaadin.i18n.support.I18NAwareComponentExpressionSupport;
import com.opnworks.vaadin.i18n.support.I18NAwareComponentValueExpressionSupport;
import com.opnworks.vaadin.i18n.support.I18NAwareFieldSupport;
import com.opnworks.vaadin.i18n.support.I18NExpressions;
import com.vaadin.data.Property;
import com.vaadin.ui.TextField;

/**
 * The I18NTextField
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
@GenerateInstantiateSubclassAspect
@SuppressWarnings("serial")
public class I18NTextField extends TextField implements I18NAwareField<String>, I18NAwareComponentValueExpression {

	private I18NAwareFieldSupport<String> i18NAwareFieldSupport;
	private I18NAwareComponentExpressionSupport i18NAwareComponentExpressionSupport;
	private I18NAwareComponentValueExpressionSupport i18NAwareComponentValueExpressionSupport;

	/**
	 * Constructs an empty i18n <code>TextField</code> with no caption.
	 */
	public I18NTextField() {
		super();
	}

	/**
	 * Constructs a new i18n <code>TextField</code> that's bound to the specified <code>Property</code> and has no caption.
	 * 
	 * @param dataSource
	 *            the Property to be edited with this editor.
	 */
	public I18NTextField(Property<?> dataSource) {
		super(dataSource);
	}

	/**
	 * Constructs an empty i18n <code>TextField</code> with given caption message key.
	 * 
	 * @param captionKey
	 *            the caption message key for the editor.
	 */
	public I18NTextField(@I18NAwareMessage String captionKey) {
		super(captionKey);
		setCaptionMessage(captionKey);
	}

	public I18NTextField(I18NExpressions captionExpression) {
		super(captionExpression.getStringFinal());
		setCaptionMessage(captionExpression.getObjectlist());
	}

	public I18NTextField(I18NExpressions captionExpression, I18NExpressions valueExpression) {
		super(captionExpression.getStringFinal(), valueExpression.getStringFinal());
		setCaptionMessage(captionExpression.getObjectlist());
		setValueMessage(valueExpression.getObjectlist());
	}

	public I18NTextField(I18NExpressions captionExpression, Property<?> dataSource) {
		super(captionExpression.getStringFinal(), dataSource);
		setCaptionMessage(captionExpression.getObjectlist());
	}

	/**
	 * Constructs a new i18n <code>TextField</code> that's bound to the specified <code>Property</code> and has the given caption <code>String</code>.
	 * 
	 * @param captionKey
	 *            the caption message key for the editor.
	 * @param dataSource
	 *            the Property to be edited with this editor.
	 */
	public I18NTextField(@I18NAwareMessage String captionKey, Property<?> dataSource) {
		super(captionKey, dataSource);
		setCaptionMessage(captionKey);
	}

	public I18NTextField(@I18NAwareMessage String captionKey, String value) {
		super(captionKey, value);
		setCaptionMessage(captionKey);
	}

	@Override
	public void i18NUpdate(I18NService i18N) {
		getI18NAwareFieldSupport().i18NUpdate(i18N);
		getI18NAwareComponentExpressionSupport().i18NUpdate(i18N);
		getI18NAwareComponentValueExpressionSupport().i18NUpdate(i18N);
	}

	public void setCaption(Object... expression) {
		setCaptionMessage(expression);
	}

	public void setDescription(Object... expression) {
		setDescriptionMessage(expression);
	}

	public void setValue(Object... expression) {
		setValueMessage(expression);
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

	private I18NAwareFieldSupport<String> getI18NAwareFieldSupport() {

		if (i18NAwareFieldSupport == null) {
			i18NAwareFieldSupport = new I18NAwareFieldSupport<String>(this);
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

	private I18NAwareComponentValueExpressionSupport getI18NAwareComponentValueExpressionSupport() {

		if (i18NAwareComponentValueExpressionSupport == null) {
			i18NAwareComponentValueExpressionSupport = new I18NAwareComponentValueExpressionSupport(this);
		}

		return i18NAwareComponentValueExpressionSupport;
	}

	@Override
	public void setValueMessage(Object... expression) {
		getI18NAwareComponentValueExpressionSupport().setValueMessage(expression);
	}

	@Override
	public void setRealValue(String value) {
		super.setValue(value);
	}
}
