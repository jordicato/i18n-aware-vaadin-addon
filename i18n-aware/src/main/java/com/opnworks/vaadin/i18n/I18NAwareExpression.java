package com.opnworks.vaadin.i18n;

/**
 * The I18NAwareExpression
 * 
 * @author Sandy Rodriguez Garcia ( OpnWorks )
 */
public interface I18NAwareExpression extends I18NAwareCaption, I18NAware {

	void setCaptionMessage(Object... expression);

	void setDescriptionMessage(Object... expression);

	void setRealDescription(String description);
}
