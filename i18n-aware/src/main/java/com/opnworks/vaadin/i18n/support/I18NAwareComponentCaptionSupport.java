package com.opnworks.vaadin.i18n.support;

import java.io.Serializable;

import com.opnworks.vaadin.i18n.I18NAwareComponent;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.support.I18NCaptionSupport.CaptionContainer;

/**
 * The I18NAwareComponentCaption
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NAwareComponentCaptionSupport implements Serializable {

	private static final long serialVersionUID = 108049770254545236L;

	private I18NAwareComponent originalComponent;

	private I18NCaptionSupport i18NCaptionSupport = new I18NCaptionSupport( new CaptionContainer() {
		@Override
		public void setCaption(String caption) {
			originalComponent.setCaption(caption);
		}
	});

	public I18NAwareComponentCaptionSupport(I18NAwareComponent originalComponent) {
		this.originalComponent = originalComponent;
	}

	public void setCaptionKey(String captionKey) {
		i18NCaptionSupport.setCaptionKey(captionKey);
	}

	public void setCaptionParams(Object... params) {
		i18NCaptionSupport.setCaptionParams(params);
	}

	public void i18NUpdate(I18NService i18N) {

		originalComponent.setLocale(i18N.getLocale());
		
		i18NCaptionSupport.i18NUpdate(i18N);
	}

}
