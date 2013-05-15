package com.opnworks.vaadin.i18n;

public interface I18NAwareValueExpression extends I18NAwareExpression {

	void setValueMessage(Object... expression);

	void setValueMessage(@I18NAwareMessage String valueKey, Object... valueParams);
}
