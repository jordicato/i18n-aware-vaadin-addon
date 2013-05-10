package com.opnworks.vaadin.i18n;

/**
 * The I18NAware ComponentExpression
 * 
 * @author Sandy Rodriguez Garcia ( OpnWorks )
 */
public interface I18NAwareComponentExpression extends I18NAwareComponent, I18NAwareExpression {

	void setCaption(String value);
	
	void setRealCaption(String value);

}
