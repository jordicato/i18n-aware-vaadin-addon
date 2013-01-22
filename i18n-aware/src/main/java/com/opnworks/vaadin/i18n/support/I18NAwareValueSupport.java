package com.opnworks.vaadin.i18n.support;

import java.util.Locale;

import com.opnworks.vaadin.i18n.I18NAwareValue;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.service_impl.I18NServiceImpl;

/**
 * The I18NAwareValue Support class
 * 
 * @author Pedro Rodriguez
 */
public class I18NAwareValueSupport implements I18NAwareValue {

	public interface ValueContainer {
		void setValue(String value);
	}

	protected ValueContainer valueContainer;
	private String valueKey;

	private Object[] valueParams;

	private Locale locale;

	public I18NAwareValueSupport(ValueContainer valueContainer) {
		this.valueContainer = valueContainer;
	}

	@Override
	public Locale getLocale() {
		return locale;
	}

	public String getValueKey() {
		return valueKey;
	}

	public Object[] getValueParams() {
		return valueParams;
	}

	@Override
	public void i18NUpdate(I18NService i18N) {

		setLocale(i18N.getLocale());

		if (valueKey != null) {
			valueContainer.setValue(i18N.getMessage(valueKey, valueParams));
		}
	}

	@Override
	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public void setValueKey(String valueKey) {
		this.valueKey = valueKey;
	}

	@Override
	public void setValueMessage(String valueKey, Object... valueParams) {
		this.valueKey = valueKey;
		this.valueParams = valueParams;

		if (I18NServiceImpl.getInstance() != null) {
			i18NUpdate(I18NServiceImpl.getInstance());
		}
	}

	public void setValueParams(Object... valueParams) {
		this.valueParams = valueParams;
	}
}
