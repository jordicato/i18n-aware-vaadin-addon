package com.opnworks.vaadin.i18n.support;

import com.opnworks.vaadin.i18n.I18NAwareAltText;
import com.opnworks.vaadin.i18n.I18NAwareMessage;

/**
 * The I18NAwareAltText Support
 * 
 * @author Sandy Rodriguez Garcia ( OpnWorks )
 */
public class I18NAltTextSupport extends I18NAwareValueSupport implements I18NAwareAltText {

	private static final long serialVersionUID = 7258724316612228119L;

	public interface AltTextContainer {
		void setRealAltText(String altText);
	}

	public I18NAltTextSupport(final AltTextContainer altTextContainer) {
		super(new AwareValueContainer() {
			@Override
			public void setValue(String value) {
				altTextContainer.setRealAltText(value);
			}
		});
	}

	@Override
	public void setAltTextMessage(@I18NAwareMessage String AltTextKey, Object... params) {
		setValueMessage(AltTextKey, params);
	}

	@Override
	public void setRealAltText(String altText) {
		valueContainer.setValue(altText);
	}
}
