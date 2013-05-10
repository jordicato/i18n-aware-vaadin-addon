package com.opnworks.vaadin.i18n.support;

import com.opnworks.vaadin.i18n.I18NAwareCaption;
import com.opnworks.vaadin.i18n.I18NAwareExpression;

/**
 * The I18NExpressionSupport Support
 * 
 * @author Sandy Rodriguez Garcia ( OpnWorks )
 */
public class I18NExpressionSupport extends I18NAwareExpressionSupport implements I18NAwareCaption, I18NAwareExpression {

	private static final long serialVersionUID = 834474279827581783L;

	public interface ExpressionContainer {
		void setRealCaption(String caption);
	}

	public I18NExpressionSupport(final ExpressionContainer captionContainer) {
		super(new AwareExpressionContainer() {			
			@Override
			public void setValue(String value) {
				captionContainer.setRealCaption(value);			
			}
		});
	}

	@Override
	public void setCaptionMessage(I18NExpressions expressions, Object... valueParams) {
		super.setCaptionMessage(expressions, valueParams);
	}

	@Override
	public void setCaptionMessage(String captionKey, Object... params) {
		setCaptionMessage(captionKey, params);
	}

	@Override
	public void setRealCaption(String caption) {
		valueContainer.setValue(caption);
	}	
}
