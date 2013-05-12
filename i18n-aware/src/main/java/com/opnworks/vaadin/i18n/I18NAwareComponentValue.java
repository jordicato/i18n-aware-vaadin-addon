package com.opnworks.vaadin.i18n;

/**
 * The I18NAware Component
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public interface I18NAwareComponentValue extends I18NAwareComponent, I18NAwareValue {

	void setValue(String value);

	void setRealValue(String value);

}
