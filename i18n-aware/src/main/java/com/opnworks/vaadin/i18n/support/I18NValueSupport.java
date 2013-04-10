package com.opnworks.vaadin.i18n.support;

import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NAwareValue;

/**
 * The I18NAwareCaption Support
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NValueSupport extends I18NAwareValueSupport implements I18NAwareValue {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4837533183063176258L;

	public interface ValueContainer {
		void setRealValue(String value);
	}

	public I18NValueSupport(final ValueContainer valueContainer) {
		super(new AwareValueContainer() {
			@Override
			public void setValue(String value) {
				valueContainer.setRealValue(value);
			}
		});
	}

	@Override
	public void setValueMessage(@I18NAwareMessage String valueKey, Object... params) {
	 	super.setValueMessage(valueKey, params);
	}

	@Override
	public void setRealValue(String caption) {
		valueContainer.setValue(caption);
	}
}
