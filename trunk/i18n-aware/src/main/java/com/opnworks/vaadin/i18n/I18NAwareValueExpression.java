package com.opnworks.vaadin.i18n;

import com.opnworks.vaadin.i18n.support.I18NExpression;

public interface I18NAwareValueExpression extends I18NAwareExpression {

	void setValueMessage(I18NExpression expression);

	void setValueMessage(@I18NAwareMessage String valueKey, Object... valueParams);
}
