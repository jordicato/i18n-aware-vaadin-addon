package com.opnworks.vaadin.i18n.support;

import java.io.Serializable;
import java.util.Locale;

import com.opnworks.vaadin.i18n.I18NAwareComponentValue;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NAwareValue;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.support.I18NAwareValueSupport.AwareValueContainer;
import com.opnworks.vaadin.i18n.support.I18NValueSupport.ValueContainer;

/**
 * The I18NAwareComponentCaption
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NAwareComponentValueSupport implements Serializable, I18NAwareValue {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8232080295463303716L;

	private I18NAwareComponentValue originalComponent;

	private I18NValueSupport i18NValueSupport = new I18NValueSupport(new ValueContainer() {
		@Override
		public void setRealValue(String value) {
			originalComponent.setRealValue(value);
		}
	});

	private I18NAwareValueSupport descriptionI18NSupport = new I18NAwareValueSupport(new AwareValueContainer() {

		@Override
		public void setValue(String value) {
			originalComponent.setRealDescription(value);
		}
	});

	public I18NAwareComponentValueSupport(I18NAwareComponentValue originalComponent) {
		this.originalComponent = originalComponent;
	}

	@Override
	public Locale getLocale() {
		return originalComponent.getLocale();
	}

	@Override
	public void i18NUpdate(I18NService i18N) {

		originalComponent.setLocale(i18N.getLocale());

		i18NValueSupport.i18NUpdate(i18N);
	}

	public void setDescriptionMessage(@I18NAwareMessage String descriptionKey, Object... descriptionParams) {
		descriptionI18NSupport.setValueMessage(descriptionKey, descriptionParams);
	}

	@Override
	public void setLocale(Locale locale) {
		originalComponent.setLocale(locale);
	}

	@Override
	public void setValueMessage(String valueKey, Object... valueParams) {
		i18NValueSupport.setValueMessage(valueKey, valueParams);
	}

	@Override
	public void setRealValue(String value) {
		originalComponent.setRealValue(value);
	}
}
