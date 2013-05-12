package com.opnworks.vaadin.i18n.support;

import java.util.Locale;

import com.opnworks.vaadin.i18n.I18NAwareExpression;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.I18NServiceSingleton;

@SuppressWarnings("serial")
public class I18NAwareExpressionSupport implements I18NAwareExpression {

	public interface AwareExpressionContainer {
		void setValue(String value);
	}
	
	protected AwareExpressionContainer valueContainer;
	
	private I18NExpressions expressions;
	
	private String valueKey;

	private Object[] valueParams;

	private Locale locale;
	
	public I18NAwareExpressionSupport(AwareExpressionContainer awareExpressionContainer) {
		this.valueContainer = awareExpressionContainer;
	}

	public I18NExpressions getValueExpressions() {
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
		} else if(valueKey != null) {
			valueContainer.setValue(i18N.getMessage(valueKey));
		}
	}

	@Override
	public void setLocale(Locale locale) {
		this.locale = locale;		
	}

	@Override
	public void setCaptionMessage(I18NExpressions expressions, Object... valueParams) {		
		this.expressions = expressions;
		
		if (I18NServiceSingleton.getInstance().getI18NServive() != null) {
		 	this.i18NUpdate(I18NServiceSingleton.getInstance().getI18NServive());
		}	
	}

	@Override
	public void setRealCaption(String caption) {
		valueContainer.setValue(caption);
	}

	@Override
	public void setDescriptionMessage(I18NExpressions expressions, Object... valueParams) {
		this.expressions = expressions;
		
		if (I18NServiceSingleton.getInstance().getI18NServive() != null) {
		 	this.i18NUpdate(I18NServiceSingleton.getInstance().getI18NServive());
		}		
	}

	@Override
	public void setRealDescription(String description) {
		valueContainer.setValue(description);		
	}

	@Override
	public void setCaptionMessage(String captionKey, Object... params) {
	}

	@Override
	public void setStringVarMessage(String captionKey, Object... params) {
		if (!I18NExpressions.isKey(captionKey) && (I18NSupportExpression.getInstance().getStringLiteral().getValue().equals(captionKey))) {
			this.valueKey = I18NSupportExpression.getInstance().getStringLiteral().getkey();
		} else {
			valueKey = captionKey;
		}
				
		if (I18NServiceSingleton.getInstance().getI18NServive() != null) {
		 	this.i18NUpdate(I18NServiceSingleton.getInstance().getI18NServive());
		}		
	}
}