package com.opnworks.vaadin.i18n.support;

import java.io.Serializable;
import java.util.Locale;

import com.opnworks.vaadin.i18n.I18NAwareAltText;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.support.I18NAltTextSupport.AltTextContainer;

/**
 * The I18NAwareComponentAltText
 * 
 * @author Sandy Rodriguez Garcia ( OpnWorks )
 */
public class I18NAwareComponentAltTextSupport implements Serializable, I18NAwareAltText {

	private static final long serialVersionUID = 108049770254545236L;

	private I18NAwareAltText component;

	private I18NAltTextSupport i18NAltTextSupport = new I18NAltTextSupport(new AltTextContainer() {
		@Override
		public void setRealAltText(String altText) {
			component.setRealAltText(altText);
		}
	});

	public I18NAwareComponentAltTextSupport(I18NAwareAltText component) {
		this.component = component;
	}

	@Override
	public Locale getLocale() {
		return component.getLocale();
	}

	@Override
	public void i18NUpdate(I18NService i18N) {

		component.setLocale(i18N.getLocale());
		i18NAltTextSupport.i18NUpdate(i18N);
	}

	@Override
	public void setAltTextMessage(@I18NAwareMessage String altTextKey, Object... params) {
		i18NAltTextSupport.setAltTextMessage(altTextKey, params);
	}

	@Override
	public void setLocale(Locale locale) {
		component.setLocale(locale);
	}

	@Override
	public void setRealAltText(String altText) {
		component.setRealAltText(altText);
	}
}
