package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareCaption;
import com.opnworks.vaadin.i18n.I18NAwareComponent;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.processor.GenerateInstantiateSubclassAspect;
import com.opnworks.vaadin.i18n.support.I18NAwareComponentCaptionSupport;
import com.vaadin.ui.NativeButton;

/**
 * The I18N NativeButton
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
@SuppressWarnings("unchecked")
@GenerateInstantiateSubclassAspect
public class I18NNativeButton extends NativeButton implements I18NAwareComponent, I18NAwareCaption {

	private static final long serialVersionUID = -4271192367394219614L;

	private I18NAwareComponentCaptionSupport i18NAwareComponentCaptionSupport;

	public I18NNativeButton() {
		super();
	}

	public I18NNativeButton(@I18NAwareMessage String captionKey) {
		super(captionKey);
		getI18NAwareComponentCaptionSupport().setCaptionMessage(captionKey);
	}

	public I18NNativeButton(@I18NAwareMessage String captionKey, ClickListener listener) {
		super(captionKey, listener);
		getI18NAwareComponentCaptionSupport().setCaptionMessage(captionKey);
	}

	public I18NNativeButton(@I18NAwareMessage String captionKey, Object target, String methodName) {
		super(captionKey, target, methodName);
		getI18NAwareComponentCaptionSupport().setCaptionMessage(captionKey);
	}

	@Override
	public void i18NUpdate(I18NService i18N) {
		getI18NAwareComponentCaptionSupport().i18NUpdate(i18N);
	}

	@Override
	public void setCaption(String captionKey) {
		setCaptionMessage(captionKey);
	}

	@Override
	public void setCaptionMessage(@I18NAwareMessage String captionKey, Object... params) {
		getI18NAwareComponentCaptionSupport().setCaptionMessage(captionKey, params);
	}

	@Override
	public void setDescription(String descriptionKey) {
		setDescriptionMessage(descriptionKey);
	}

	@Override
	public void setDescriptionMessage(@I18NAwareMessage String descriptionKey, Object... descriptionParams) {
		getI18NAwareComponentCaptionSupport().setDescriptionMessage(descriptionKey, descriptionParams);
	}

	@Override
	public void setRealCaption(String caption) {
		super.setCaption(caption);
	}

	@Override
	public void setRealDescription(String description) {
		super.setDescription(description);
	}

	private I18NAwareComponentCaptionSupport getI18NAwareComponentCaptionSupport() {

		if (i18NAwareComponentCaptionSupport == null) {
			i18NAwareComponentCaptionSupport = new I18NAwareComponentCaptionSupport(this);
		}

		return i18NAwareComponentCaptionSupport;
	}
}
