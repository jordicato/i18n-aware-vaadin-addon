package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareComponentValue;
import com.opnworks.vaadin.i18n.I18NAwareFieldValue;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NAwareValue;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.processor.GenerateInstantiateSubclassAspect;
import com.opnworks.vaadin.i18n.support.I18NAwareFieldValueSupport;
import com.vaadin.data.Property;
import com.vaadin.ui.TextArea;

/**
 * The I18NTextArea
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
@GenerateInstantiateSubclassAspect
@SuppressWarnings("serial")
public class I18NTextArea extends TextArea implements I18NAwareFieldValue<String>, I18NAwareComponentValue, I18NAwareValue {

	private I18NAwareFieldValueSupport<String> i18NAwareFieldValueSupport;

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
		getI18NAwareFieldValueSupport().setCaptionMessage(captionKey);
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
		getI18NAwareFieldValueSupport().setCaptionMessage(captionKey);
	}

	/**
	 * Constructs a i18n TextArea with given caption and value.
	 * 
	 * @param captionKey
	 *            the caption message key for the field.
	 * @param value
	 *            the value for the field
	 */
	public I18NTextArea(@I18NAwareMessage String captionKey, String value) {
		super(captionKey, value);
		getI18NAwareFieldValueSupport().setCaptionMessage(captionKey);
	}

	@Override
	public void i18NUpdate(I18NService i18N) {
		getI18NAwareFieldValueSupport().i18NUpdate(i18N);
	}

	@Override
	public void setCaption(@I18NAwareMessage String captionKey) {
		setCaptionMessage(captionKey);
	}

	@Override
	public void setCaptionMessage(@I18NAwareMessage String captionKey, Object... params) {
		getI18NAwareFieldValueSupport().setCaptionMessage(captionKey, params);
	}

	@Override
	public void setDescription(@I18NAwareMessage String descriptionKey) {
		setDescriptionMessage(descriptionKey);
	}

	@Override
	public void setDescriptionMessage(@I18NAwareMessage String descriptionKey, Object... descriptionParams) {
		getI18NAwareFieldValueSupport().setDescriptionMessage(descriptionKey, descriptionParams);
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
		getI18NAwareFieldValueSupport().setRequiredErrorMessage(requiredErrorKey, requiredErrorParams);
	}
	
	@Override
	public void setValue(@I18NAwareMessage String value) {
		if (value != "") {
			setValueMessage(value);
		}
	}
	
	@Override
	public void setValueMessage(@I18NAwareMessage String valueKey, Object... valueParams) {
		getI18NAwareFieldValueSupport().setValueMessage(valueKey, valueParams);
	}

	@Override
	public void setRealValue(String value) {
		super.setValue(value);
	}
	
	private I18NAwareFieldValueSupport<String> getI18NAwareFieldValueSupport() {

		if (i18NAwareFieldValueSupport == null) {
			i18NAwareFieldValueSupport = new I18NAwareFieldValueSupport<String>(this);
		}

		return i18NAwareFieldValueSupport;
	}
}
