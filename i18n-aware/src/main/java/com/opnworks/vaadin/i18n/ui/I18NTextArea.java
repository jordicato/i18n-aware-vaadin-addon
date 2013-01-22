package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareField;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.processor.GenerateInstantiateSubclassAspect;
import com.opnworks.vaadin.i18n.support.I18NAwareFieldSupport;
import com.vaadin.data.Property;
import com.vaadin.ui.TextArea;

/**
 * The I18NTextArea
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
@GenerateInstantiateSubclassAspect
@SuppressWarnings({"serial","unchecked"})
public class I18NTextArea extends TextArea implements I18NAwareField {

	private I18NAwareFieldSupport i18NAwareFieldSupport;

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
	public I18NTextArea(Property dataSource) {
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
		getI18NAwareFieldSupport().setCaptionMessage(captionKey);
	}

	/**
	 * Constructs a i18n TextArea with given caption and property data source.
	 * 
	 * @param captionKey
	 *            the caption message key for the field.
	 * @param dataSource
	 *            the data source for the field
	 */
	public I18NTextArea(@I18NAwareMessage String captionKey, Property dataSource) {
		super(captionKey, dataSource);
		getI18NAwareFieldSupport().setCaptionMessage(captionKey);
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
		getI18NAwareFieldSupport().setCaptionMessage(captionKey);
	}

	@Override
	public void i18NUpdate(I18NService i18N) {
		getI18NAwareFieldSupport().i18NUpdate(i18N);
	}

	@Override
	public void setCaption(String captionKey) {
		setCaptionMessage(captionKey);
	}

	@Override
	public void setCaptionMessage(@I18NAwareMessage String captionKey, Object... params) {
		getI18NAwareFieldSupport().setCaptionMessage(captionKey, params);
	}

	@Override
	public void setDescription(String descriptionKey) {
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
	public void setRequiredError(String requiredErrorKey) {
		setRequiredErrorMessage(requiredErrorKey);
	}

	@Override
	public void setRequiredErrorMessage(@I18NAwareMessage String requiredErrorKey, Object... requiredErrorParams) {
		getI18NAwareFieldSupport().setRequiredErrorMessage(requiredErrorKey, requiredErrorParams);
	}

	private I18NAwareFieldSupport getI18NAwareFieldSupport() {

		if (i18NAwareFieldSupport == null) {
			i18NAwareFieldSupport = new I18NAwareFieldSupport(this);
		}

		return i18NAwareFieldSupport;
	}
}
