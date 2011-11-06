package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareCaption;
import com.opnworks.vaadin.i18n.I18NAwareComponent;
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

	public I18NNativeButton(String captionKey) {
		super(captionKey);
		i18NAwareComponentCaptionSupport.setCaptionKey(captionKey);
	}

	public I18NNativeButton(String captionKey, ClickListener listener) {
		super(captionKey, listener);
		i18NAwareComponentCaptionSupport.setCaptionKey(captionKey);
	}

	public I18NNativeButton(String captionKey, Object target, String methodName) {
		super(captionKey, target, methodName);
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
		i18NAwareComponentCaptionSupport.i18NUpdate(i18N);
	}
}
