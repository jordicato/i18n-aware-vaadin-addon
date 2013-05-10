package com.opnworks.vaadin.i18n.support;

import java.util.Locale;

import com.opnworks.vaadin.i18n.I18NAwareValue;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.I18NServiceSingleton;

/**
 * The I18NAwareValue Support class
 * 
 * @author Pedro Rodriguez
 */
@SuppressWarnings("serial")
public class I18NAwareValueSupport implements I18NAwareValue {
	
	public interface AwareValueContainer {
		void setValue(String value);
	}

	protected AwareValueContainer valueContainer;
	private String valueKey;

	private Object[] valueParams;

	private Locale locale;

	public I18NAwareValueSupport(AwareValueContainer awareValueContainer) {
		this.valueContainer = awareValueContainer;
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
			if ((!I18NExpressions.isKey(valueKey)) && (i18N.getMessage(I18NSupportExpression.getInstance().getLastValueKey(), valueParams).equals(valueKey))) {
				valueKey = I18NSupportExpression.getInstance().getLastValueKey();
			} else {					
				valueContainer.setValue(i18N.getMessage(valueKey, valueParams));
			}
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
		I18NSupportExpression.getInstance().setStringVarStatus(false);
		this.valueKey = valueKey;
		if (isKey(valueKey)) {
			I18NSupportExpression.getInstance().setLastValueKey(valueKey);
		}
		
		this.valueParams = valueParams;
		
		if (I18NServiceSingleton.getInstance().getI18NServive() != null) {
			i18NUpdate(I18NServiceSingleton.getInstance().getI18NServive());
		}	
	}

	public void setValueParams(Object... valueParams) {
		this.valueParams = valueParams;
	}

	@Override
	public void setRealValue(String value) {
		valueContainer.setValue(value);
	}
	
	public boolean isKey(String key) {
		if (key == null) {
			return false;
		}
		if (key.contains(" ")) {
			return false;
		}
		if ( !(key.contains("_") || key.contains(".")) ) {
			return false;
		}
		return true;
	}	

}
