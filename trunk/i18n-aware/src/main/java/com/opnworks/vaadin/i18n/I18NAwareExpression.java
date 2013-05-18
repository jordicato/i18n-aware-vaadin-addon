package com.opnworks.vaadin.i18n;

import com.opnworks.vaadin.i18n.support.I18NExpression;

/**
 * The I18NAwareExpression
 * 
 * @author Sandy Rodriguez Garcia ( OpnWorks )
 */
public interface I18NAwareExpression extends I18NAwareCaption, I18NAware {

	void setCaptionMessage(I18NExpression expression);

	void setDescriptionMessage(I18NExpression expression);

	void setDescriptionMessage(@I18NAwareMessage String descriptionKey, Object... descriptionParams);

	void setRealDescription(String description);
}
