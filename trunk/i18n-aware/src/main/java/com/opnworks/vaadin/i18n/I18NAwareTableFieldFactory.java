package com.opnworks.vaadin.i18n;

import com.vaadin.data.Container;
import com.vaadin.ui.Component;

/**
 * The I18NAwareTableFieldFactory interface
 * 
 * @author Pedro Rodriguez (OpnWorks)
 */
public interface I18NAwareTableFieldFactory {

	I18NAwareFieldValue<?> createI18NAwareField(Container container, Object itemId, Object propertyId, Component uiContext);
}
