package com.opnworks.vaadin.i18n.ui;

import java.util.Date;

import com.opnworks.vaadin.i18n.I18NAwareField;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.support.I18NAwareFieldSupport;
import com.opnworks.vaadin.i18n.support.I18NAwareValueSupport;
import com.opnworks.vaadin.i18n.support.I18NAwareValueSupport.ValueContainer;
import com.vaadin.data.Property;
import com.vaadin.ui.PopupDateField;

/**
 * The I18NPopupDateField
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NPopupDateField extends PopupDateField implements
		I18NAwareField {

	private static final long serialVersionUID = 8283053228212397120L;

	private I18NAwareFieldSupport i18NAwareFieldSupport = new I18NAwareFieldSupport(
			this);

	private I18NAwareValueSupport i18NInputPromptSupport = new I18NAwareValueSupport(
			new ValueContainer() {
				@Override
				public void setValue(String value) {
					setInputPrompt(value);
				}
			});

	/**
	 * Constructs an empty i18n <code>PopupDateField</code> with no caption.
	 */
	public I18NPopupDateField() {
		super();
	}

	/**
	 * Constructs an i18n <code>PopupDateField</code> with a dataSource.
	 * 
	 * @param dataSource
	 */
	public I18NPopupDateField(Property dataSource)
			throws IllegalArgumentException {
		super(dataSource);
	}

	/**
	 * Constructs an i18n <code>PopupDateField</code> with caption message key
	 * and initial value.
	 * 
	 * @param captionKey
	 *            the caption message key of the datefield.
	 * @param value
	 */
	public I18NPopupDateField(String captionKey, Date value) {
		super(captionKey, value);
		i18NAwareFieldSupport.setCaptionKey(captionKey);
	}

	/**
	 * Constructs an i18n <code>PopupDateField</code> with caption message key
	 * and dataSource.
	 * 
	 * @param captionKey
	 *            the caption message key of the PopupDateField.
	 * @param dataSource
	 */
	public I18NPopupDateField(String captionKey, Property dataSource) {
		super(captionKey, dataSource);
		i18NAwareFieldSupport.setCaptionKey(captionKey);
	}

	/**
	 * Constructs an empty i18n <code>PopupDateField</code> with caption message
	 * key.
	 * 
	 * @param captionKey
	 *            the caption message key of the PopupDateField.
	 */
	public I18NPopupDateField(String captionKey) {
		super(captionKey);
		i18NAwareFieldSupport.setCaptionKey(captionKey);
	}

	public void setInputPromptKey(String inputPromptKey) {
		i18NInputPromptSupport.setValueKey(inputPromptKey);
	}

	public void setInputPromptKeyParams(Object... inputPromptParams) {
		i18NInputPromptSupport.setValueParams(inputPromptParams);
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

		setLocale(i18N.getLocale());

		i18NAwareFieldSupport.i18NUpdate(i18N);
		i18NInputPromptSupport.i18NUpdate(i18N);
	}

}
