package com.opnworks.vaadin.i18n;

import com.vaadin.data.Item;
import com.vaadin.ui.Component;

/**
 * The I18NAwareFormFieldFactory interface
 * 
 * @author Pedro Rodriguez (OpnWorks)
 */
public interface I18NAwareFormFieldFactory {

	I18NAwareField createI18NAwareField(Item item, Object propertyId, Component uiContext);

}
