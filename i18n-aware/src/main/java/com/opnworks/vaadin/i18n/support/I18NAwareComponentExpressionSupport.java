package com.opnworks.vaadin.i18n.support;

import java.io.Serializable;
import java.util.Locale;

import com.opnworks.vaadin.i18n.I18NAwareExpression;
import com.opnworks.vaadin.i18n.I18NAwareComponentExpression;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.support.I18NExpressionSupport;
import com.opnworks.vaadin.i18n.support.I18NExpressionSupport.ExpressionContainer;
import com.opnworks.vaadin.i18n.support.I18NAwareExpressionSupport.AwareExpressionContainer;;

/**
 * The I18NAwareComponentExpressionSupport
 * 
 * @author Sandy Rodriguez Garcia ( OpnWorks )
 */
public class I18NAwareComponentExpressionSupport implements Serializable, I18NAwareExpression{

	private static final long serialVersionUID = 6366729672586015894L;

	private I18NAwareComponentExpression originalComponent;
	
	private I18NExpressionSupport i18NExpressionSupport = new I18NExpressionSupport(new ExpressionContainer() {	
		@Override
		public void setRealCaption(String caption) {
			originalComponent.setRealCaption(caption);		
		}
	});

	private I18NAwareExpressionSupport descriptionI18NSupport = new I18NAwareExpressionSupport(new AwareExpressionContainer() {
		
		@Override
		public void setValue(String value) {
			originalComponent.setRealDescription(value);
		}
	});
	
	public I18NAwareComponentExpressionSupport(I18NAwareComponentExpression originalComponent) {
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
	}

	@Override
	public void setLocale(Locale locale) {
		originalComponent.setLocale(locale);		
	}

	@Override
	public void setCaptionMessage(I18NExpressions expressions, Object... valueParams) {
		i18NExpressionSupport.setCaptionMessage(expressions, valueParams);		
	}

	public void setDescriptionMessage(I18NExpressions expressions, Object... valueParams) {
		descriptionI18NSupport.setDescriptionMessage(expressions, valueParams);
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
	public void setStringVarMessage(String captionKey, Object... params) {
		i18NExpressionSupport.setStringVarMessage(captionKey, params);		
	}	
}
