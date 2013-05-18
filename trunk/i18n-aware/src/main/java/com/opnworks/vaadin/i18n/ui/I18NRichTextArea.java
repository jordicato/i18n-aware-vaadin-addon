package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareFieldExpression;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.processor.GenerateInstantiateSubclassAspect;
import com.opnworks.vaadin.i18n.support.I18NAwareFieldSupport;
import com.opnworks.vaadin.i18n.support.I18NExpression;
import com.vaadin.data.Property;
import com.vaadin.ui.RichTextArea;

/**
 * The I18N RichTextArea
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
@GenerateInstantiateSubclassAspect
@SuppressWarnings("serial")
public class I18NRichTextArea extends RichTextArea implements I18NAwareFieldExpression<String> {

	private I18NAwareFieldSupport<String> i18NAwareFieldSupport;

	/**
	 * Constructs an empty <code>RichTextArea</code> with no caption.
	 */
	public I18NRichTextArea() {
		super();
	}

	/**
	 * Constructs a new <code>RichTextArea</code> that's bound to the specified <code>Property</code> and has no caption.
	 * 
	 * @param dataSource
	 *            the data source for the editor value
	 */
	public I18NRichTextArea(Property<?> dataSource) {
		super(dataSource);
	}

	/**
	 * 
	 * Constructs an empty <code>RichTextArea</code> with the given caption.
	 * 
	 * @param captionKey
	 *            the caption key for the editor.
	 */
	public I18NRichTextArea(@I18NAwareMessage String captionKey) {
		super(captionKey);
		setCaptionMessage(captionKey);
	}

	public I18NRichTextArea(I18NExpression captionExpression) {
		super(captionExpression.getStringFinal());
		setCaptionMessage(captionExpression);
	}

	/**
	 * Constructs a new <code>RichTextArea</code> that's bound to the specified <code>Property</code> and has the given caption.
	 * 
	 * @param captionKey
	 *            the caption key for the editor.
	 * @param dataSource
	 *            the data source for the editor value
	 */
	public I18NRichTextArea(@I18NAwareMessage String captionKey, Property<?> dataSource) {
		super(captionKey, dataSource);
		setCaptionMessage(captionKey);
	}

	public I18NRichTextArea(I18NExpression captionExpression, Property<?> dataSource) {
		super(captionExpression.getStringFinal(), dataSource);
		setCaptionMessage(captionExpression);
	}

	/**
	 * Constructs a new <code>RichTextArea</code> with the given caption and initial text contents.
	 * 
	 * @param captionKey
	 *            the caption key for the editor.
	 * @param value
	 *            the initial text content of the editor.
	 */
	public I18NRichTextArea(@I18NAwareMessage String captionKey, String value) {
		super(captionKey, value);
		setCaptionMessage(captionKey);
	}

	public I18NRichTextArea(I18NExpression captionExpression, String value) {
		super(captionExpression.getStringFinal(), value);
		setCaptionMessage(captionExpression);
	}

	@Override
	public void i18NUpdate(I18NService i18N) {
		getI18NAwareFieldSupport().i18NUpdate(i18N);
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
	public void setValue(@I18NAwareMessage String value) {
		if (value != "") {
			setValueMessage(value);
		}
	}

	public void setValue(I18NExpression expression) {
		setValueMessage(expression);
	}

	@Override
	public void setValueMessage(@I18NAwareMessage String valueKey, Object... valueParams) {
		getI18NAwareFieldSupport().setValueMessage(valueKey, valueParams);
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
	public void setCaptionMessage(I18NExpression expression) {
		getI18NAwareFieldSupport().setCaptionMessage(expression);
	}

	@Override
	public void setDescriptionMessage(I18NExpression expression) {
		getI18NAwareFieldSupport().setDescriptionMessage(expression);
	}

	@Override
	public void setRealValue(Object value) {
		super.setValue((String) value);
	}

	@Override
	public void setValueMessage(I18NExpression expression) {
		getI18NAwareFieldSupport().setValueMessage(expression);
	}

}
