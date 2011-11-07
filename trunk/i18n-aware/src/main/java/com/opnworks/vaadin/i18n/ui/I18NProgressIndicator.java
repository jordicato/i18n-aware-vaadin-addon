package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareCaption;
import com.opnworks.vaadin.i18n.I18NAwareComponent;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.support.I18NAwareComponentCaptionSupport;
import com.vaadin.data.Property;
import com.vaadin.ui.ProgressIndicator;

/**
 * The I18NProgressIndicator
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NProgressIndicator extends ProgressIndicator implements I18NAwareComponent, I18NAwareCaption {

	private static final long serialVersionUID = 1516381605599417078L;

	private I18NAwareComponentCaptionSupport i18NAwareComponentCaptionSupport = new I18NAwareComponentCaptionSupport(this);

	/**
	 * Creates an a new i18n ProgressIndicator.
	 */
	public I18NProgressIndicator() {
		super();
	}

	/**
	 * Creates a new instance of i18n ProgressIndicator with given state.
	 * 
	 * @param value
	 */
	public I18NProgressIndicator(Float value) {
		super(value);
	}

	/**
	 * Creates a new instance of i18n ProgressIndicator with stae read from
	 * given datasource.
	 * 
	 * @param contentSource
	 */
	public I18NProgressIndicator(Property contentSource) {
		super(contentSource);
	}

	@Override
	public void setCaptionMessage(String captionKey, Object... params) {
		i18NAwareComponentCaptionSupport.setCaptionMessage(captionKey, params);
	}

	@Override
	public void setDescriptionMessage(String descriptionKey,
			Object... descriptionParams) {
		i18NAwareComponentCaptionSupport.setDescriptionMessage(descriptionKey, descriptionParams);
	}

	@Override
	public void i18NUpdate(I18NService i18N) {
		i18NAwareComponentCaptionSupport.i18NUpdate(i18N);
	}

}
