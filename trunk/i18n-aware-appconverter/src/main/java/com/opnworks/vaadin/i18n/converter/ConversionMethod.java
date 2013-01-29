package com.opnworks.vaadin.i18n.converter;

import com.opnworks.vaadin.i18n.converter.aop_mode.AopModeConverter;
import com.opnworks.vaadin.i18n.converter.explicit_mode.ExplicitModeConverter;

/**
 * The Conversion method. Knows the required Converter (a singleton)
 */
public enum ConversionMethod {

	aop_mode(AopModeConverter.getInstance()), explicit_mode(ExplicitModeConverter.getInstance());

	private Converter converter;

	private ConversionMethod(Converter converter) {
		this.converter = converter;
	}

	public Converter getConverter() {
		return converter;
	}
}
