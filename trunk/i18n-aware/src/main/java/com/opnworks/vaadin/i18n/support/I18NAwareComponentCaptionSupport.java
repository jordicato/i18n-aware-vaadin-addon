package com.opnworks.vaadin.i18n.support;

import java.io.Serializable;

import com.opnworks.vaadin.i18n.I18NAwareComponent;
import com.opnworks.vaadin.i18n.I18NService;

/**
 * The I18NAwareComponentCaption
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NAwareComponentCaptionSupport implements Serializable {

	private static final long serialVersionUID = 108049770254545236L;

	private I18NAwareComponent originalComponent;

	private String captionKey;
	private Object[] captionParams;

	public I18NAwareComponentCaptionSupport(I18NAwareComponent originalComponent) {
		this.originalComponent = originalComponent;
	}

	public void setCaptionKey(String captionKey) {
		this.captionKey = captionKey;
		originalComponent.setCaption(captionKey);
	}

	public void setCaptionParams(Object... params) {
		this.captionParams = params;
	}

	public void i18NUpdate(I18NService i18N) {

		if (captionKey != null) {
			originalComponent.setCaption(i18N.getMessage(captionKey,
					captionParams));
		}
	}

}
