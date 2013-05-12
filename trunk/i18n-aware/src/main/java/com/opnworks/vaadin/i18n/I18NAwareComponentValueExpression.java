package com.opnworks.vaadin.i18n;

public interface I18NAwareComponentValueExpression extends I18NAwareComponentExpression, I18NAwareValueExpression {

	void setValue(String value);

	void setRealValue(String value);

}
