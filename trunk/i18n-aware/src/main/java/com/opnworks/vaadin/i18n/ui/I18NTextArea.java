package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareField;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.support.I18NAwareFieldSupport;
import com.vaadin.data.Property;
import com.vaadin.ui.TextArea;

/**
 * The I18NTextArea
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
@SuppressWarnings("unchecked")
public class I18NTextArea extends TextArea implements I18NAwareField {

	private static final long serialVersionUID = 6357950198553382989L;

	private I18NAwareFieldSupport i18NAwareFieldSupport = new I18NAwareFieldSupport(this);

	/**
	 * Constructs an empty i18n TextArea.
	 */
	public I18NTextArea() {
		super();
	}

	/**
	 * Constructs an empty i18n TextArea with given caption message key.
	 * 
	 * @param captionKey
	 *            the caption message key for the field.
	 */
	public I18NTextArea(String captionKey) {
		super(captionKey);
		i18NAwareFieldSupport.setCaptionKey(captionKey);
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
	 * Constructs a i18n TextArea with given caption and property data source.
	 * 
	 * @param captionKey
	 *            the caption message key for the field.
	 * @param dataSource
	 *            the data source for the field
	 */
	public I18NTextArea(String captionKey, Property dataSource) {
		super(captionKey, dataSource);
		i18NAwareFieldSupport.setCaptionKey(captionKey);
	}

	/**
	 * Constructs a i18n TextArea with given caption and value.
	 * 
	 * @param captionKey
	 *            the caption message key for the field.
	 * @param value
	 *            the value for the field
	 */
	public I18NTextArea(String captionKey, String value) {
		super(captionKey, value);
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
