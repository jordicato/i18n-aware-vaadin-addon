package com.opnworks.vaadin.i18n;

import com.opnworks.vaadin.i18n.support.I18NExpressions;

/**
 * The I18NAwareExpression
 * 
 * @author Sandy Rodriguez Garcia ( OpnWorks )
 */
public interface I18NAwareExpression extends I18NAwareCaption, I18NAware {

	void setCaptionMessage(I18NExpressions expressions, Object... valueParams);
	
	void setStringVarMessage(String captionKey, Object... params);
	
	void setDescriptionMessage(I18NExpressions expressions, Object... valueParams);
	
	void setRealDescription(String description);
}
