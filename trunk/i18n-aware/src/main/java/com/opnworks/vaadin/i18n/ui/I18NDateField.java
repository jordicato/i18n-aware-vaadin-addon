package com.opnworks.vaadin.i18n.ui;

import java.util.Date;

import com.opnworks.vaadin.i18n.I18NAwareField;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.processor.GenerateInstantiateSubclassAspect;
import com.opnworks.vaadin.i18n.support.I18NAwareFieldSupport;
import com.vaadin.data.Property;
import com.vaadin.ui.DateField;

/**
 * The I18NDateField
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
@GenerateInstantiateSubclassAspect
@SuppressWarnings("serial")
public class I18NDateField extends DateField implements I18NAwareField {

	private I18NAwareFieldSupport i18NAwareFieldSupport;

	/**
	 * Constructs an empty i18n <code>DateField</code> with no caption.
	 */
	public I18NDateField() {
		super();
	}

	/**
	 * Constructs a new <code>DateField</code> that's bound to the specified <code>Property</code> and has no caption.
	 * 
	 * @param dataSource
	 *            the Property to be edited with this editor.
	 */
	public I18NDateField(Property dataSource) {
		super(dataSource);
	}

	/**
	 * Constructs an empty i18n <code>DateField</code> with caption message key.
	 * 
	 * @param captionKey
	 *            the caption message key of the datefield.
	 */
	public I18NDateField(@I18NAwareMessage String captionKey) {
		super(captionKey);
		getI18NAwareFieldSupport().setCaptionMessage(captionKey);
	}

	/**
	 * Constructs a new <code>DateField</code> with the given caption and initial text contents. The editor constructed this way will not be bound to
	 * a Property unless {@link com.vaadin.data.Property.Viewer#setPropertyDataSource(Property)} is called to bind it.
	 * 
	 * @param captionKey
	 *            the caption message key of the datefield.
	 * @param value
	 *            the Date value.
	 */
	public I18NDateField(@I18NAwareMessage String captionKey, Date value) {
		super(captionKey, value);
		getI18NAwareFieldSupport().setCaptionMessage(captionKey);
	}

	/**
	 * Constructs a new <code>DateField</code> that's bound to the specified <code>Property</code> and has the given caption <code>String</code>.
	 * 
	 * @param captionKey
	 *            the caption message key of the datefield.
	 * @param dataSource
	 *            the Property to be edited with this editor.
	 */
	public I18NDateField(@I18NAwareMessage String captionKey, Property dataSource) {
		super(captionKey, dataSource);
		getI18NAwareFieldSupport().setCaptionMessage(captionKey);
	}

	@Override
	public void i18NUpdate(I18NService i18N) {

		setLocale(i18N.getLocale());

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