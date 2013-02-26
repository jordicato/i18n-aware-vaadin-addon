package com.opnworks.vaadin.i18n.ui;

import java.util.Date;

import com.opnworks.vaadin.i18n.I18NAwareField;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.processor.GenerateInstantiateSubclassAspect;
import com.opnworks.vaadin.i18n.support.I18NAwareFieldSupport;
import com.vaadin.data.Property;
import com.vaadin.ui.InlineDateField;

/**
 * The I18NInlineDateField
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
@GenerateInstantiateSubclassAspect
@SuppressWarnings("serial")
public class I18NInlineDateField extends InlineDateField implements I18NAwareField<Date> {

	private I18NAwareFieldSupport<Date> i18NAwareFieldSupport;

	/**
	 * Constructs an empty i18n <code>I18NInlineDateField</code> with no caption.
	 */
	public I18NInlineDateField() {
		super();
	}

	/**
	 * Constructs an i18n <code>I18NInlineDateField</code> with a dataSource.
	 * 
	 * @param dataSource
	 */
	public I18NInlineDateField(Property<?> dataSource) {
		super(dataSource);
	}

	/**
	 * Constructs an empty i18n <code>I18NInlineDateField</code> with caption message key.
	 * 
	 * @param captionKey
	 *            the caption message key of the I18NInlineDateField.
	 */
	public I18NInlineDateField(@I18NAwareMessage String captionKey) {
		super(captionKey);
		getI18NAwareFieldSupport().setCaptionMessage(captionKey);
	}

	/**
	 * Constructs an i18n <code>I18NInlineDateField</code> with caption message key and initial value.
	 * 
	 * @param captionKey
	 *            the caption message key of the I18NInlineDateField.
	 * @param value
	 */
	public I18NInlineDateField(@I18NAwareMessage String captionKey, Date value) {
		super(captionKey, value);
		getI18NAwareFieldSupport().setCaptionMessage(captionKey);
	}

	/**
	 * Constructs an i18n <code>I18NInlineDateField</code> with caption message key and dataSource.
	 * 
	 * @param captionKey
	 *            the caption message key of the I18NInlineDateField.
	 * @param dataSource
	 */
	public I18NInlineDateField(@I18NAwareMessage String captionKey, Property<?> dataSource) {
		super(captionKey, dataSource);
		getI18NAwareFieldSupport().setCaptionMessage(captionKey);
	}

	@Override
	public void i18NUpdate(I18NService i18N) {

		setLocale(i18N.getLocale());
		getI18NAwareFieldSupport().i18NUpdate(i18N);
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

	private I18NAwareFieldSupport<Date> getI18NAwareFieldSupport() {

		if (i18NAwareFieldSupport == null) {
			i18NAwareFieldSupport = new I18NAwareFieldSupport<Date>(this);
		}

		return i18NAwareFieldSupport;
	}
}
