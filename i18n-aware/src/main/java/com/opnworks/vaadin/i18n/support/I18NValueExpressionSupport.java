package com.opnworks.vaadin.i18n.support;

import com.opnworks.vaadin.i18n.I18NAwareCaption;
import com.opnworks.vaadin.i18n.I18NAwareValueExpression;

/**
 * The I18NExpressionSupport Support
 * 
 * @author Sandy Rodriguez Garcia ( OpnWorks )
 */
public class I18NValueExpressionSupport extends I18NAwareExpressionSupport implements I18NAwareCaption, I18NAwareValueExpression {

	private static final long serialVersionUID = 834474279827581783L;

	public interface ValueExpressionContainer {
		void setRealValue(String value);
	}

	public I18NValueExpressionSupport(final ValueExpressionContainer valueCaptionContainer) {
		super(new AwareExpressionContainer() {
			@Override
			public void setValue(String value) {
				valueCaptionContainer.setRealValue(value);
			}
		});
	}

	@Override
	public void setCaptionMessage(Object... expression) {
		super.setCaptionMessage(expression);
	}

	@Override
	public void setCaptionMessage(String captionKey, Object... params) {
		setCaptionMessage(captionKey, params);
	}

	@Override
	public void setRealCaption(String caption) {
		valueContainer.setValue(caption);
	}

	@Override
	public void setDescriptionMessage(Object... expression) {
		super.setDescriptionMessage(expression);
	}

	@Override
	public void setValueMessage(Object... expression) {
		super.setValueMessage(expression);
	}
}
