package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareField;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.support.I18NAwareFieldSupport;
import com.vaadin.ui.CheckBox;

/**
 * The I18NCheckBox
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
@SuppressWarnings("unchecked")
public class I18NCheckBox extends CheckBox implements I18NAwareField {

	private static final long serialVersionUID = 6357950198553382989L;

	private I18NAwareFieldSupport i18NAwareFieldSupport = new I18NAwareFieldSupport(
			this);

	/**
	 * Creates a new i18n switch button.
	 */
	public I18NCheckBox() {
		super();
	}

	/**
	 * Creates a new i18n switch button with a caption message key.
	 * 
	 * The value of the switch button is always false and they are immediate by
	 * default.
	 * 
	 * @param captionKey
	 *            the Button caption message key.
	 */
	public I18NCheckBox(String captionKey) {
		super(captionKey);
		i18NAwareFieldSupport.setCaptionKey(captionKey);
	}

	/**
	 * Creates a new i18n switch button with a caption message key and a click
	 * listener.
	 * 
	 * @param captionKey
	 *            the caption message key of the switch button
	 * @param listener
	 *            the click listener
	 */
	public I18NCheckBox(String captionKey, ClickListener listener) {
		super(captionKey, listener);
		i18NAwareFieldSupport.setCaptionKey(captionKey);
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
	public void setCaptionKey(String captionKey) {
		i18NAwareFieldSupport.setCaptionKey(captionKey);
	}

	@Override
	public void setCaptionParams(Object... params) {
		i18NAwareFieldSupport.setCaptionParams(params);
	}

	@Override
	public void i18NUpdate(I18NService i18N) {
		i18NAwareFieldSupport.updateLabels(i18N);
	}

}
