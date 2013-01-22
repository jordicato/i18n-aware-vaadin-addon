package com.opnworks.vaadin.i18n.support;

import com.opnworks.vaadin.i18n.I18NAwareTableFieldFactory;
import com.vaadin.data.Container;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.TableFieldFactory;

/**
 * The I18NAwareTableFieldFactory support class
 * 
 * @author Pedro Rodriguez (OpnWorks)
 */
public abstract class I18NAwareTableFieldFactorySupport implements TableFieldFactory {

	private static final long serialVersionUID = -292185919909516292L;

	private I18NAwareTableFieldFactory delegate;

	public I18NAwareTableFieldFactorySupport(I18NAwareTableFieldFactory delegate) {
		this.delegate = delegate;
	}

	@Override
	public Field createField(Container container, Object itemId, Object propertyId, Component uiContext) {

		return delegate.createI18NAwareField(container, itemId, propertyId, uiContext);
	}

}
