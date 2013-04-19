package com.opnworks.vaadin.i18n.support;

import com.opnworks.vaadin.i18n.I18NAwareCaption;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.data.util.I18NCountLiterals;

/**
 * The I18NAwareCaption Support
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NCaptionSupport extends I18NAwareValueSupport implements I18NAwareCaption {

	private static final long serialVersionUID = 7258724316612228119L;

	public interface CaptionContainer {
		void setRealCaption(String caption);
	}

	public I18NCaptionSupport(final CaptionContainer captionContainer) {
		super(new AwareValueContainer() {
			@Override
			public void setValue(String value) {
				captionContainer.setRealCaption(value);
			}
		});
	}

	@Override
	public void setCaptionMessage(@I18NAwareMessage String captionKey, Object... params) {
		setValueMessage(captionKey, params);
	}

	@Override
	public void setRealCaption(String caption) {
			valueContainer.setValue(caption);
	}
}
