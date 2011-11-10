package com.opnworks.vaadin.i18n.support;

import com.opnworks.vaadin.i18n.I18NAwareCaption;
import com.opnworks.vaadin.i18n.I18NAwareMessage;

/**
 * The I18NAwareCaption Support
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NCaptionSupport extends I18NAwareValueSupport implements
		I18NAwareCaption {

	public I18NCaptionSupport(final CaptionContainer captionContainer) {
		super(new ValueContainer() {
			@Override
			public void setValue(String value) {
				captionContainer.setCaption(value);
			}
		});
	}

	public void setCaptionMessage(@I18NAwareMessage String captionKey, Object... params) {
		setValueMessage(captionKey, params);
	}

	public interface CaptionContainer {
		void setCaption(String caption);
	}
}
