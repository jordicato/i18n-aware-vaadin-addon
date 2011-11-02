package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareCaption;
import com.opnworks.vaadin.i18n.I18NAwareComponent;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.support.I18NAwareComponentCaptionSupport;
import com.vaadin.ui.Upload;

/**
 * The I18NUpload
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NUpload extends Upload implements I18NAwareComponent,
		I18NAwareCaption {

	private static final long serialVersionUID = -6645271211086047948L;

	private I18NAwareComponentCaptionSupport i18NAwareComponentCaptionSupport = new I18NAwareComponentCaptionSupport(
			this);

	private String buttonCaptionKey;
	private Object[] buttonCaptionParams;

	/**
	 * Creates a new i18n Upload.
	 * 
	 * The receiver must be set before performing an upload.
	 */
	public I18NUpload() {
		super();
	}

	/**
	 * Creates a new i18n Upload with caption message key and Receiver.
	 * 
	 * @param captionKey
	 */
	public I18NUpload(String captionKey, Receiver receiver) {
		super(captionKey, receiver);
		i18NAwareComponentCaptionSupport.setCaptionKey(captionKey);
	}

	public void setButtonCaptionKey(String buttonCaptionKey) {
		this.buttonCaptionKey = buttonCaptionKey;
	}

	public void setButtonCaptionParams(Object[] buttonCaptionParams) {
		this.buttonCaptionParams = buttonCaptionParams;
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

		i18NAwareComponentCaptionSupport.i18NUpdate(i18N);

		if (buttonCaptionKey != null) {
			setButtonCaption(i18N.getMessage(buttonCaptionKey,
					buttonCaptionParams));
		}
	}
}
