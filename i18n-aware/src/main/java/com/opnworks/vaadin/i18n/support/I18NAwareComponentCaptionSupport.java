package com.opnworks.vaadin.i18n.support;

import java.io.Serializable;
import java.util.Locale;

import com.opnworks.vaadin.i18n.I18NAwareCaption;
import com.opnworks.vaadin.i18n.I18NAwareComponent;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.support.I18NAwareValueSupport.ValueContainer;
import com.opnworks.vaadin.i18n.support.I18NCaptionSupport.CaptionContainer;

/**
 * The I18NAwareComponentCaption
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NAwareComponentCaptionSupport implements Serializable,
		I18NAwareCaption {

	private static final long serialVersionUID = 108049770254545236L;

	private I18NAwareComponent originalComponent;

	private I18NCaptionSupport i18NCaptionSupport = new I18NCaptionSupport(
			new CaptionContainer() {
				@Override
				public void setRealCaption(String caption) {
					originalComponent.setRealCaption(caption);
				}
			});

	private I18NAwareValueSupport descriptionI18NSupport = new I18NAwareValueSupport(
			new ValueContainer() {
				@Override
				public void setValue(String value) {
					originalComponent.setRealDescription(value);
				}
			});

	public I18NAwareComponentCaptionSupport(I18NAwareComponent originalComponent) {
		this.originalComponent = originalComponent;
	}
	
	@Override
	public void setRealCaption(String caption) {
		originalComponent.setRealCaption(caption);
	}

	public void setCaptionMessage(@I18NAwareMessage String captionKey, Object... params) {
		i18NCaptionSupport.setCaptionMessage(captionKey, params);
	}

	public void setDescriptionMessage(@I18NAwareMessage String descriptionKey,
			Object... descriptionParams) {
		descriptionI18NSupport.setValueMessage(descriptionKey,
				descriptionParams);
	}

	@Override
	public Locale getLocale() {
		return originalComponent.getLocale();
	}

	@Override
	public void setLocale(Locale locale) {
		originalComponent.setLocale(locale);
	}

	public void i18NUpdate(I18NService i18N) {

		originalComponent.setLocale(i18N.getLocale());

		i18NCaptionSupport.i18NUpdate(i18N);
		descriptionI18NSupport.i18NUpdate(i18N);
	}

}
