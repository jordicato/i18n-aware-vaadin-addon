package com.opnworks.vaadin.i18n.support;

import java.util.Locale;

import com.opnworks.vaadin.i18n.I18NAwareValue;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.I18NStaticService;
import com.opnworks.vaadin.i18n.data.util.I18NCountLiterals;

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
	private boolean fromBinaryExpr;
	private Object[] objectList;
	public static String lastValueKey;

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
	
	public Object[] getObjectList() {
		return objectList;
	}
	
	public boolean isFromBinaryExpr() {
		return fromBinaryExpr;
	}
	
	@Override
	public void i18NUpdate(I18NService i18N) {

		setLocale(i18N.getLocale());

		boolean keep = false;
		
		if (valueKey != null) {
			
			if (isFromBinaryExpr()) {
				valueContainer.setValue(I18NCountLiterals.getStringFromBinaryExpr(objectList));
			} else {			
				if ((!I18NCountLiterals.isKey(valueKey)) && (!I18NCountLiterals.getStringLiteral().getValue().equals(valueKey)) 
						&& (i18N.getMessage(I18NAwareValueSupport.lastValueKey, valueParams).equals(valueKey))) {
					valueKey = I18NAwareValueSupport.lastValueKey;
					keep = true;
				}
				
				if (I18NCountLiterals.isKey(valueKey)) {
					valueContainer.setValue(i18N.getMessage(valueKey, valueParams));
				} else {
					if ((I18NCountLiterals.getStringLiteral().getValue().equals(valueKey)) && (I18NCountLiterals.getStringLiteral().getkey() != "")) {					
						valueContainer.setValue(i18N.getMessage(I18NCountLiterals.getStringLiteral().getkey()));
					} else {
						if (keep) {
							valueContainer.setValue(valueKey);
						} else {
							valueContainer.setValue(i18N.getMessage(valueKey, valueParams));
						}
					}
				}
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
		this.valueKey = valueKey;
		if (I18NCountLiterals.isKey(valueKey)) {
			I18NAwareValueSupport.lastValueKey = valueKey;
		}
		
		this.fromBinaryExpr = I18NCountLiterals.getIsBinaryExpr();
		
		this.objectList = I18NCountLiterals.getBinaryObjectList();
		
		this.valueParams = valueParams;
		
		if (I18NStaticService.getI18NServive() != null) {
			i18NUpdate(I18NStaticService.getI18NServive());
		}	
	}

	public void setValueParams(Object... valueParams) {
		this.valueParams = valueParams;
	}

	@Override
	public void setRealValue(String value) {
		valueContainer.setValue(value);
	}
}
