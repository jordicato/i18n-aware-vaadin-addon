package com.opnworks.vaadin.i18n.support;

import com.opnworks.vaadin.i18n.I18NAwareValue;
import com.opnworks.vaadin.i18n.I18NService;

/**
 * The I18NAwareValue Support class
 * 
 * @author Pedro Rodriguez
 */
public class I18NAwareValueSupport implements I18NAwareValue {

	private ValueContainer valueContainer;

	private String valueKey;
	private Object[] valueParams;

	public I18NAwareValueSupport(ValueContainer valueContainer) {
		this.valueContainer = valueContainer;
	}

	@Override
	public void setValueKey(String valueKey) {
		this.valueKey = valueKey;
	}

	@Override
	public void setValueParams(Object... valueParams) {
		this.valueParams = valueParams;
	}
	
	public String getValueKey() {
		return valueKey;
	}

	public Object[] getValueParams() {
		return valueParams;
	}

	@Override
	public void i18NUpdate(I18NService i18N) {
		
		if( valueKey != null ) {
			valueContainer.setValue(i18N.getMessage(valueKey, valueParams));
		}
	}

	public interface ValueContainer {
		void setValue(String value);
	}
}
