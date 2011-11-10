package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareCaption;
import com.opnworks.vaadin.i18n.I18NAwareComponent;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.support.I18NAwareComponentCaptionSupport;
import com.vaadin.ui.NativeButton;

/**
 * The I18N NativeButton
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
@SuppressWarnings("unchecked")
public class I18NNativeButton extends NativeButton implements
		I18NAwareComponent, I18NAwareCaption {

	private static final long serialVersionUID = -4271192367394219614L;

	private I18NAwareComponentCaptionSupport i18NAwareComponentCaptionSupport = new I18NAwareComponentCaptionSupport(
			this);

	public I18NNativeButton() {
		super();
	}

	public I18NNativeButton(@I18NAwareMessage String captionKey) {
		super(captionKey);
		i18NAwareComponentCaptionSupport.setCaptionMessage(captionKey);
	}

	public I18NNativeButton(@I18NAwareMessage String captionKey, ClickListener listener) {
		super(captionKey, listener);
		i18NAwareComponentCaptionSupport.setCaptionMessage(captionKey);
	}

	public I18NNativeButton(@I18NAwareMessage String captionKey, Object target, String methodName) {
		super(captionKey, target, methodName);
		i18NAwareComponentCaptionSupport.setCaptionMessage(captionKey);
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
	}
}
