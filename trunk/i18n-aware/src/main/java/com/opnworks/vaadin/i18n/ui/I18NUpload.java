package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareCaption;
import com.opnworks.vaadin.i18n.I18NAwareComponent;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.support.I18NAwareComponentCaptionSupport;
import com.opnworks.vaadin.i18n.support.I18NCaptionSupport;
import com.opnworks.vaadin.i18n.support.I18NCaptionSupport.CaptionContainer;
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

	private I18NCaptionSupport buttonCaptionI18NCaptionSupport = new I18NCaptionSupport(
			new CaptionContainer() {
				@Override
				public void setCaption(String caption) {
					setButtonCaption(caption);
				}
			});

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
	public I18NUpload(@I18NAwareMessage String captionKey, Receiver receiver) {
		super(captionKey, receiver);
		i18NAwareComponentCaptionSupport.setCaptionMessage(captionKey);
	}

	public void setButtonCaptionMessage(String buttonCaptionKey,
			Object... buttonCaptionParams) {
		buttonCaptionI18NCaptionSupport.setCaptionMessage(buttonCaptionKey,
				buttonCaptionParams);
	}

	@Override
	public void setCaptionMessage(@I18NAwareMessage String captionKey, Object... params) {
		i18NAwareComponentCaptionSupport.setCaptionMessage(captionKey, params);
	}

	@Override
	public void setDescriptionMessage(@I18NAwareMessage String descriptionKey,
			Object... descriptionParams) {
		i18NAwareComponentCaptionSupport.setDescriptionMessage(descriptionKey, descriptionParams);
	}

	@Override
	public void i18NUpdate(I18NService i18N) {

		i18NAwareComponentCaptionSupport.i18NUpdate(i18N);
		buttonCaptionI18NCaptionSupport.i18NUpdate(i18N);
	}
}
