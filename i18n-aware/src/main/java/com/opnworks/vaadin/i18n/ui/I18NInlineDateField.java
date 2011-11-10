package com.opnworks.vaadin.i18n.ui;

import java.util.Date;

import com.opnworks.vaadin.i18n.I18NAwareField;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.support.I18NAwareFieldSupport;
import com.vaadin.data.Property;
import com.vaadin.ui.InlineDateField;

/**
 * The I18NInlineDateField
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NInlineDateField extends InlineDateField implements
		I18NAwareField {

	private static final long serialVersionUID = 8673251203466814145L;

	private I18NAwareFieldSupport i18NAwareFieldSupport = new I18NAwareFieldSupport(
			this);

	/**
	 * Constructs an empty i18n <code>I18NInlineDateField</code> with no
	 * caption.
	 */
	public I18NInlineDateField() {
		super();
	}

	/**
	 * Constructs an i18n <code>I18NInlineDateField</code> with a dataSource.
	 * 
	 * @param dataSource
	 */
	public I18NInlineDateField(Property dataSource)
			throws IllegalArgumentException {
		super(dataSource);
	}

	/**
	 * Constructs an i18n <code>I18NInlineDateField</code> with caption message
	 * key and initial value.
	 * 
	 * @param captionKey
	 *            the caption message key of the I18NInlineDateField.
	 * @param value
	 */
	public I18NInlineDateField(@I18NAwareMessage String captionKey, Date value) {
		super(captionKey, value);
		i18NAwareFieldSupport.setCaptionMessage(captionKey);
	}

	/**
	 * Constructs an i18n <code>I18NInlineDateField</code> with caption message
	 * key and dataSource.
	 * 
	 * @param captionKey
	 *            the caption message key of the I18NInlineDateField.
	 * @param dataSource
	 */
	public I18NInlineDateField(@I18NAwareMessage String captionKey, Property dataSource) {
		super(captionKey, dataSource);
		i18NAwareFieldSupport.setCaptionMessage(captionKey);
	}

	/**
	 * Constructs an empty i18n <code>I18NInlineDateField</code> with caption
	 * message key.
	 * 
	 * @param captionKey
	 *            the caption message key of the I18NInlineDateField.
	 */
	public I18NInlineDateField(@I18NAwareMessage String captionKey) {
		super(captionKey);
		i18NAwareFieldSupport.setCaptionMessage(captionKey);
	}

	@Override
	public void setRequiredErrorMessage(@I18NAwareMessage String requiredErrorKey, Object... requiredErrorParams) {
		i18NAwareFieldSupport.setRequiredErrorMessage(requiredErrorKey, requiredErrorParams);
	}

	@Override
	public void setCaptionMessage(@I18NAwareMessage String captionKey, Object... params) {
		i18NAwareFieldSupport.setCaptionMessage(captionKey, params);
	}

	@Override
	public void setDescriptionMessage(@I18NAwareMessage String descriptionKey,
			Object... descriptionParams) {
		i18NAwareFieldSupport.setDescriptionMessage(descriptionKey, descriptionParams);
	}
	
	@Override
	public void i18NUpdate(I18NService i18N) {

		setLocale(i18N.getLocale());
		i18NAwareFieldSupport.i18NUpdate(i18N);
	}

}
