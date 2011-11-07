package com.opnworks.vaadin.i18n.ui;

import java.util.Date;

import com.opnworks.vaadin.i18n.I18NAwareField;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.support.I18NAwareFieldSupport;
import com.vaadin.data.Property;
import com.vaadin.ui.DateField;

/**
 * The I18NDateField
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NDateField extends DateField implements I18NAwareField {

	private static final long serialVersionUID = 1555819731368279548L;

	private I18NAwareFieldSupport i18NAwareFieldSupport = new I18NAwareFieldSupport(this);

	/**
	 * Constructs an empty i18n <code>DateField</code> with no caption.
	 */
	public I18NDateField() {
		super();
	}

	/**
	 * Constructs an empty i18n <code>DateField</code> with caption message key.
	 * 
	 * @param captionKey
	 *            the caption message key of the datefield.
	 */
	public I18NDateField(String captionKey) {
		super(captionKey);
		i18NAwareFieldSupport.setCaptionMessage(captionKey);
	}

	/**
	 * Constructs a new <code>DateField</code> that's bound to the specified
	 * <code>Property</code> and has the given caption <code>String</code>.
	 * 
	 * @param captionKey
	 *            the caption message key of the datefield.
	 * @param dataSource
	 *            the Property to be edited with this editor.
	 */
	public I18NDateField(String captionKey, Property dataSource) {
		super(captionKey, dataSource);
		i18NAwareFieldSupport.setCaptionMessage(captionKey);
	}

	/**
	 * Constructs a new <code>DateField</code> that's bound to the specified
	 * <code>Property</code> and has no caption.
	 * 
	 * @param dataSource
	 *            the Property to be edited with this editor.
	 */
	public I18NDateField(Property dataSource) throws IllegalArgumentException {
		super(dataSource);
	}

	/**
	 * Constructs a new <code>DateField</code> with the given caption and
	 * initial text contents. The editor constructed this way will not be bound
	 * to a Property unless
	 * {@link com.vaadin.data.Property.Viewer#setPropertyDataSource(Property)}
	 * is called to bind it.
	 * 
	 * @param captionKey
	 *            the caption message key of the datefield.
	 * @param value
	 *            the Date value.
	 */
	public I18NDateField(String captionKey, Date value) {
		super(captionKey, value);
		i18NAwareFieldSupport.setCaptionMessage(captionKey);
	}

	@Override
	public void setRequiredErrorMessage(String requiredErrorKey, Object... requiredErrorParams) {
		i18NAwareFieldSupport.setRequiredErrorMessage(requiredErrorKey, requiredErrorParams);
	}

	@Override
	public void setCaptionMessage(String captionKey, Object... params) {
		i18NAwareFieldSupport.setCaptionMessage(captionKey, params);
	}

	@Override
	public void setDescriptionMessage(String descriptionKey,
			Object... descriptionParams) {
		i18NAwareFieldSupport.setDescriptionMessage(descriptionKey, descriptionParams);
	}
	
	@Override
	public void i18NUpdate(I18NService i18N) {

		setLocale(i18N.getLocale());

		i18NAwareFieldSupport.i18NUpdate(i18N);
	}

}
