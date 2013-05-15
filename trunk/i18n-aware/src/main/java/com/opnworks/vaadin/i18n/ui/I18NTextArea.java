package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareFieldExpression;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.processor.GenerateInstantiateSubclassAspect;
import com.opnworks.vaadin.i18n.support.I18NAwareFieldSupport;
import com.opnworks.vaadin.i18n.support.I18NExpression;
import com.vaadin.data.Property;
import com.vaadin.ui.TextArea;

/**
 * The I18NTextArea
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
@GenerateInstantiateSubclassAspect
@SuppressWarnings("serial")
public class I18NTextArea extends TextArea implements I18NAwareFieldExpression<String> {

	private I18NAwareFieldSupport<String> i18NAwareFieldSupport;

	/**
	 * Constructs an empty i18n TextArea.
	 */
	public I18NTextArea() {
		super();
	}

	/**
	 * Constructs a i18n TextArea with given property data source.
	 * 
	 * @param dataSource
	 *            the data source for the field
	 */
	public I18NTextArea(Property<?> dataSource) {
		super(dataSource);
	}

	/**
	 * Constructs an empty i18n TextArea with given caption message key.
	 * 
	 * @param captionKey
	 *            the caption message key for the field.
	 */
	public I18NTextArea(@I18NAwareMessage String captionKey) {
		super(captionKey);
		setCaptionMessage(captionKey);
	}

	public I18NTextArea(I18NExpression captionExpression) {
		super(captionExpression.getStringFinal());
		setCaptionMessage(captionExpression.getObjectlist());
	}

	/**
	 * Constructs a i18n TextArea with given caption and property data source.
	 * 
	 * @param captionKey
	 *            the caption message key for the field.
	 * @param dataSource
	 *            the data source for the field
	 */
	public I18NTextArea(@I18NAwareMessage String captionKey, Property<?> dataSource) {
		super(captionKey, dataSource);
		setCaptionMessage(captionKey);
	}

	public I18NTextArea(I18NExpression captionExpression, Property<?> dataSource) {
		super(captionExpression.getStringFinal(), dataSource);
		setCaptionMessage(captionExpression.getObjectlist());
	}

	/**
	 * Constructs a i18n TextArea with given caption and value.
	 * 
	 * @param captionKey
	 *            the caption message key for the field.
	 * @param value
	 *            the value for the field
	 */
	public I18NTextArea(@I18NAwareMessage String captionKey, @I18NAwareMessage String value) {
		super(captionKey, value);
		setCaptionMessage(captionKey);
		if (value != "") {
			setValueMessage(value);
		}
	}

	public I18NTextArea(I18NExpression captionExpression, String value) {
		super(captionExpression.getStringFinal(), value);
		setCaptionMessage(captionExpression.getObjectlist());
		if (value != "") {
			setValueMessage(value);
		}
	}

	@Override
	public void i18NUpdate(I18NService i18N) {
		getI18NAwareFieldSupport().i18NUpdate(i18N);
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

	@Override
	public void setValue(@I18NAwareMessage String value) {
		if (value != "") {
			setValueMessage(value);
		}
	}

	public void setValue(Object... expression) {
		setValueMessage(expression);
	}

	@Override
	public void setValueMessage(@I18NAwareMessage String valueKey, Object... valueParams) {
		getI18NAwareFieldSupport().setValueMessage(valueKey, valueParams);
	}

	private I18NAwareFieldSupport<String> getI18NAwareFieldSupport() {

		if (i18NAwareFieldSupport == null) {
			i18NAwareFieldSupport = new I18NAwareFieldSupport<String>(this);
		}

		return i18NAwareFieldSupport;
	}

	@Override
	public void setCaptionMessage(Object... expression) {
		getI18NAwareFieldSupport().setCaptionMessage(expression);
	}

	@Override
	public void setDescriptionMessage(Object... expression) {
		getI18NAwareFieldSupport().setDescriptionMessage(expression);
	}

	@Override
	public void setRealValue(Object value) {
		super.setValue((String) value);
	}

	@Override
	public void setValueMessage(Object... expression) {
		getI18NAwareFieldSupport().setValueMessage(expression);
	}
}
