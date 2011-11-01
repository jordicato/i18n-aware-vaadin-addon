package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareCaption;
import com.opnworks.vaadin.i18n.I18NAwareComponent;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.support.I18NAwareComponentCaptionSupport;
import com.vaadin.ui.Button;

/**
 * The I18N Button
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
@SuppressWarnings("unchecked")
public class I18NButton extends Button implements I18NAwareComponent,
		I18NAwareCaption {

	private static final long serialVersionUID = 6357950198553382989L;

	private I18NAwareComponentCaptionSupport i18NAwareComponentCaptionSupport = new I18NAwareComponentCaptionSupport(
			this);

	/**
	 * Creates a new i18n push button. The value of the push button is false and
	 * it is immediate by default.
	 */
	public I18NButton() {
		super();
	}

	/**
	 * Creates a new i18n push button with caption message key.
	 * 
	 * The value of the push button is false and it is immediate by default.
	 * 
	 * @param captionKey
	 *            the Button caption message key.
	 */
	public I18NButton(String captionKey) {
		super(captionKey);
		i18NAwareComponentCaptionSupport.setCaptionKey(captionKey);
	}

	/**
	 * Creates a new i18n push button with caption message key and click listener.
	 * 
	 * @param captionKey
	 *            the Button caption message key.
	 * @param listener
	 *            the Button click listener.
	 */
	public I18NButton(String captionKey, ClickListener listener) {
		super(captionKey, listener);
		i18NAwareComponentCaptionSupport.setCaptionKey(captionKey);
	}

	@Override
	public void setCaptionKey(String captionKey) {
		i18NAwareComponentCaptionSupport.setCaptionKey(captionKey);
	}

	@Override
	public void setCaptionParams(Object... params) {
		i18NAwareComponentCaptionSupport.setCaptionParams(params);
	}

	@Override
	public void i18NUpdate(I18NService i18N) {
		i18NAwareComponentCaptionSupport.updateLabels(i18N);
	}

}
