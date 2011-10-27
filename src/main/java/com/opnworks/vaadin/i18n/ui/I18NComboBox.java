package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareField;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.support.I18NAwareFieldSupport;
import com.vaadin.ui.ComboBox;

/**
 * The I18NComboBox
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NComboBox extends ComboBox implements I18NAwareField {

	private static final long serialVersionUID = -7385824210862452162L;

	private I18NAwareFieldSupport i18NAwareFieldSupport = new I18NAwareFieldSupport(
			this);

	/**
	 * Creates a new i18n ComboBox.
	 */
	public I18NComboBox() {
		super();
	}

	/**
	 * Creates a new i18n ComboBox with a caption message key.
	 */
	public I18NComboBox(String captionKey) {
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
		i18NAwareFieldSupport.updateLabels(i18N);
	}

}
