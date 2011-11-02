package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareField;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.support.I18NAwareFieldSupport;
import com.vaadin.data.Property;
import com.vaadin.ui.TextField;

/**
 * The I18NTextField
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
@SuppressWarnings("unchecked")
public class I18NTextField extends TextField implements I18NAwareField {

	private static final long serialVersionUID = 6357950198553382989L;

	private I18NAwareFieldSupport i18NAwareFieldSupport = new I18NAwareFieldSupport(this);

	/**
	 * Constructs an empty i18n <code>TextField</code> with no caption.
	 */
	public I18NTextField() {
		super();
	}

	/**
	 * Constructs an empty i18n <code>TextField</code> with given caption
	 * message key.
	 * 
	 * @param captionKey
	 *            the caption message key for the editor.
	 */
	public I18NTextField(String captionKey) {
		super(captionKey);
		i18NAwareFieldSupport.setCaptionKey(captionKey);
	}

	public I18NTextField(String captionKey, String value) {
		super(captionKey, value);
		i18NAwareFieldSupport.setCaptionKey(captionKey);
	}

	/**
	 * Constructs a new i18n <code>TextField</code> that's bound to the
	 * specified <code>Property</code> and has no caption.
	 * 
	 * @param dataSource
	 *            the Property to be edited with this editor.
	 */
	public I18NTextField(Property dataSource) {
		super(dataSource);
	}

	/**
	 * Constructs a new i18n <code>TextField</code> that's bound to the
	 * specified <code>Property</code> and has the given caption
	 * <code>String</code>.
	 * 
	 * @param captionKey
	 *            the caption message key for the editor.
	 * @param dataSource
	 *            the Property to be edited with this editor.
	 */
	public I18NTextField(String captionKey, Property dataSource) {
		super(captionKey, dataSource);
		i18NAwareFieldSupport.setCaptionKey(captionKey);
	}

	@Override
	public void setCaptionKey(String captionKey) {
		i18NAwareFieldSupport.setCaptionKey(captionKey);
	}

	@Override
	public void setCaptionParams(Object... params) {
		i18NAwareFieldSupport.setCaptionParams(params);
	}

	@Override
	public void setRequiredErrorKey(String requiredErrorKey) {
		i18NAwareFieldSupport.setRequiredErrorKey(requiredErrorKey);
	}

	@Override
	public void setRequiredErrorParams(Object[] requiredErrorParams) {
		i18NAwareFieldSupport.setRequiredErrorParams(requiredErrorParams);
	}

	@Override
	public void i18NUpdate(I18NService i18N) {
		i18NAwareFieldSupport.i18NUpdate(i18N);
	}
}
