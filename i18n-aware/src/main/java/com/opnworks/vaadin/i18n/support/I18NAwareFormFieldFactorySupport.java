package com.opnworks.vaadin.i18n.support;

import com.opnworks.vaadin.i18n.I18NAwareFormFieldFactory;
import com.vaadin.data.Item;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;

/**
 * The FormFieldFactory support class
 * 
 * @author Pedro Rodriguez (OpnWorks)
 */
public class I18NAwareFormFieldFactorySupport implements FormFieldFactory {

	private static final long serialVersionUID = -1923726555833929871L;

	private I18NAwareFormFieldFactory delegate;

	public I18NAwareFormFieldFactorySupport(I18NAwareFormFieldFactory delegate) {
		this.delegate = delegate;
	}

	@Override
	public Field createField(Item item, Object propertyId, Component uiContext) {
		return delegate.createI18NAwareField(item, propertyId, uiContext);
	}

}
