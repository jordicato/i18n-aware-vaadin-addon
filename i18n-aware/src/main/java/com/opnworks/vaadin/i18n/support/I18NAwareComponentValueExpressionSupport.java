package com.opnworks.vaadin.i18n.support;

import java.io.Serializable;
import java.util.Locale;

import com.opnworks.vaadin.i18n.I18NAwareComponentValueExpression;
import com.opnworks.vaadin.i18n.I18NAwareValueExpression;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.support.I18NAwareExpressionSupport.AwareExpressionContainer;
import com.opnworks.vaadin.i18n.support.I18NExpressionSupport.ExpressionContainer;
import com.opnworks.vaadin.i18n.support.I18NValueExpressionSupport.ValueExpressionContainer;

public class I18NAwareComponentValueExpressionSupport implements Serializable, I18NAwareValueExpression {

	private static final long serialVersionUID = 7478489345554644052L;

	private I18NAwareComponentValueExpression originalComponent;

	private I18NExpressionSupport i18NExpressionSupport = new I18NExpressionSupport(new ExpressionContainer() {
		@Override
		public void setRealCaption(String caption) {
			originalComponent.setRealCaption(caption);
		}
	});

	private I18NValueExpressionSupport i18NValueExpressionSupport = new I18NValueExpressionSupport(new ValueExpressionContainer() {

		@Override
		public void setRealValue(String value) {
			originalComponent.setRealValue(value);
		}
	});

	private I18NAwareExpressionSupport descriptionI18NSupport = new I18NAwareExpressionSupport(new AwareExpressionContainer() {

		@Override
		public void setValue(String value) {
			originalComponent.setRealDescription(value);
		}
	});

	public I18NAwareComponentValueExpressionSupport(I18NAwareComponentValueExpression originalComponent) {
		this.originalComponent = originalComponent;
	}

	@Override
	public Locale getLocale() {
		return originalComponent.getLocale();
	}

	@Override
	public void i18NUpdate(I18NService i18N) {
		originalComponent.setLocale(i18N.getLocale());

		i18NExpressionSupport.i18NUpdate(i18N);
		descriptionI18NSupport.i18NUpdate(i18N);
		i18NValueExpressionSupport.i18NUpdate(i18N);
	}

	@Override
	public void setLocale(Locale locale) {
		originalComponent.setLocale(locale);
	}

	@Override
	public void setCaptionMessage(I18NExpression expression) {
		i18NExpressionSupport.setCaptionMessage(expression);
	}

	public void setDescriptionMessage(I18NExpression expression) {
		descriptionI18NSupport.setDescriptionMessage(expression);
	}

	@Override
	public void setCaptionMessage(String captionKey, Object... params) {
		i18NExpressionSupport.setCaptionMessage(captionKey, params);
	}

	@Override
	public void setRealCaption(String caption) {
		originalComponent.setRealCaption(caption);
	}

	@Override
	public void setRealDescription(String description) {
		originalComponent.setRealDescription(description);
	}

	@Override
	public void setValueMessage(I18NExpression expression) {
		i18NValueExpressionSupport.setValueMessage(expression);
	}

	@Override
	public void setDescriptionMessage(String descriptionKey, Object... descriptionParams) {
		descriptionI18NSupport.setDescriptionMessage(descriptionKey, descriptionParams);
	}

	@Override
	public void setValueMessage(String valueKey, Object... valueParams) {
		i18NValueExpressionSupport.setValueMessage(valueKey, valueParams);
	}

}
