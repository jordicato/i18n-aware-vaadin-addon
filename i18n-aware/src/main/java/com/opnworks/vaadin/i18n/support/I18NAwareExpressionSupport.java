package com.opnworks.vaadin.i18n.support;

import java.util.Locale;

import com.opnworks.vaadin.i18n.I18NAwareValueExpression;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.I18NServiceSingleton;

@SuppressWarnings("serial")
public class I18NAwareExpressionSupport implements I18NAwareValueExpression {

	public interface AwareExpressionContainer {
		void setValue(String value);
	}

	protected AwareExpressionContainer valueContainer;

	private I18NExpression expressions;

	private String valueKey;

	private Object[] valueParams;

	private Locale locale;
	
	static private String lastValueKey;

	public I18NAwareExpressionSupport(AwareExpressionContainer awareExpressionContainer) {
		this.valueContainer = awareExpressionContainer;
	}

	public I18NExpression getValueExpressions() {
		return expressions;
	}

	public Object[] getValueParams() {
		return valueParams;
	}

	@Override
	public Locale getLocale() {
		return locale;
	}

	@Override
	public void i18NUpdate(I18NService i18N) {		
		setLocale(i18N.getLocale());
		if (expressions != null) {
			valueContainer.setValue(expressions.getStringFinal());
		}		
		else if (valueKey != null) {
			valueContainer.setValue(i18N.getMessage(valueKey, valueParams));
		}
	}

	@Override
	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	@Override
	public void setCaptionMessage(I18NExpression expression) {
		this.expressions = expression;

		if (I18NServiceSingleton.getInstance().getI18NServive() != null) {
			this.i18NUpdate(I18NServiceSingleton.getInstance().getI18NServive());
		}
	}

	@Override
	public void setRealCaption(String caption) {
		valueContainer.setValue(caption);
	}

	@Override
	public void setDescriptionMessage(I18NExpression expression) {
		this.expressions = expression;

		if (I18NServiceSingleton.getInstance().getI18NServive() != null) {
			this.i18NUpdate(I18NServiceSingleton.getInstance().getI18NServive());
		}
	}

	@Override
	public void setRealDescription(String description) {
		valueContainer.setValue(description);
	}

	@Override
	public void setValueMessage(String valueKey, Object... valueParams) {
		this.valueKey = valueKey;		
		this.valueParams = valueParams;

		if (I18NServiceSingleton.getInstance().getI18NServive() != null) {
			this.i18NUpdate(I18NServiceSingleton.getInstance().getI18NServive());
		}
	}

	@Override
	public void setValueMessage(I18NExpression expression) {
		this.expressions = expression;
		if (I18NServiceSingleton.getInstance().getI18NServive() != null) {
			this.i18NUpdate(I18NServiceSingleton.getInstance().getI18NServive());
		}
	}

	@Override
	public void setDescriptionMessage(String descriptionKey, Object... descriptionParams) {
		this.valueKey = descriptionKey;
		this.valueParams = descriptionParams;

		if (I18NServiceSingleton.getInstance().getI18NServive() != null) {
			this.i18NUpdate(I18NServiceSingleton.getInstance().getI18NServive());
		}
	}

	@Override
	public void setCaptionMessage(String captionKey, Object... params) {
		I18NAwareExpressionSupport.lastValueKey = captionKey;
		this.valueKey = captionKey;			
		this.valueParams = params;

		if (I18NServiceSingleton.getInstance().getI18NServive() != null) {
			this.i18NUpdate(I18NServiceSingleton.getInstance().getI18NServive());
		}
	}
	
	static public String getLastValueKey() {
		return lastValueKey;
	}
}
