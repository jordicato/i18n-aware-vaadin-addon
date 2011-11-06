package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareField;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.support.I18NAwareFieldSupport;
import com.vaadin.ui.Slider;

/**
 * The I18NSlider
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
@SuppressWarnings("unchecked")
public class I18NSlider extends Slider implements I18NAwareField {

	private static final long serialVersionUID = 8853467560637420140L;

	private I18NAwareFieldSupport i18NAwareFieldSupport = new I18NAwareFieldSupport(
			this);

	public I18NSlider() {
		super();
	}

	/**
	 * Create a new slider with the caption given as parameter. All slider
	 * values set to defaults.
	 * 
	 * @param caption
	 *            The caption for this Slider (e.g. "Volume").
	 */
	public I18NSlider(String captionKey) {
		super(captionKey);
		i18NAwareFieldSupport.setCaptionKey(captionKey);
	}

	/**
	 * Create a new slider with given range and resolution
	 * 
	 * @param min
	 * @param max
	 * @param resolution
	 */
	public I18NSlider(double min, double max, int resolution) {
		super(min, max, resolution);
	}

	/**
	 * Create a new slider with given range
	 * 
	 * @param min
	 * @param max
	 */
	public I18NSlider(int min, int max) {
		super(min, max);
	}

	/**
	 * Create a new slider with given caption and range
	 * 
	 * @param caption
	 * @param min
	 * @param max
	 */
	public I18NSlider(String captionKey, int min, int max) {
		super(captionKey, min, max);
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
		i18NAwareFieldSupport.i18NUpdate(i18N);
	}
}
