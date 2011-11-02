package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareField;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.support.I18NAwareFieldSupport;
import com.vaadin.ui.DateField;

/**
 * The I18NDateField
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NDateField extends DateField implements I18NAwareField {

	private static final long serialVersionUID = 1555819731368279548L;

	private I18NAwareFieldSupport i18NAwareFieldSupport = new I18NAwareFieldSupport(
			this);

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
