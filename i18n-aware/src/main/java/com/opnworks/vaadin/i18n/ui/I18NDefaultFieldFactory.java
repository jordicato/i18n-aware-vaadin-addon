package com.opnworks.vaadin.i18n.ui;

import java.util.Date;

import com.opnworks.vaadin.i18n.I18NAwareField;
import com.opnworks.vaadin.i18n.I18NAwareFormFieldFactory;
import com.opnworks.vaadin.i18n.I18NAwareTableFieldFactory;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.TableFieldFactory;

/**
 * I18N aware DefaultFieldFactory implementation
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NDefaultFieldFactory implements I18NAwareFormFieldFactory,
		I18NAwareTableFieldFactory, FormFieldFactory, TableFieldFactory {

	private static final long serialVersionUID = 4477342039842651573L;
	
	private static final I18NDefaultFieldFactory singleton = new I18NDefaultFieldFactory();

	public static I18NDefaultFieldFactory getInstance() {
		return singleton;
	}

	@Override
	public Field createField(Container container, Object itemId,
			Object propertyId, Component uiContext) {

		return createI18NAwareField(container, itemId, propertyId, uiContext);
	}

	@Override
	public Field createField(Item item, Object propertyId, Component uiContext) {
		return createI18NAwareField(item, propertyId, uiContext);
	}

	public I18NAwareField createI18NAwareField(Item item, Object propertyId,
			Component uiContext) {
		Class<?> type = item.getItemProperty(propertyId).getType();
		I18NAwareField field = createI18NFieldByPropertyType(type);
		field.setCaptionMessage(createCaptionKeyByPropertyId(propertyId, uiContext));
		return field;
	}

	public I18NAwareField createI18NAwareField(Container container,
			Object itemId, Object propertyId, Component uiContext) {
		Property containerProperty = container.getContainerProperty(itemId,
				propertyId);
		Class<?> type = containerProperty.getType();
		I18NAwareField field = createI18NFieldByPropertyType(type);
		field.setCaptionMessage(createCaptionKeyByPropertyId(propertyId, uiContext));
		return field;
	}

	public String createCaptionKeyByPropertyId(Object propertyId,
			Component uiContext) {

		return uiContext.getClass().getSimpleName() + "." + propertyId;
	}

	public I18NAwareField createI18NFieldByPropertyType(Class<?> type) {
		// Null typed properties can not be edited
		if (type == null) {
			return null;
		}

		// Item field
		if (Item.class.isAssignableFrom(type)) {
			return new I18NForm();
		}

		// Date field
		if (Date.class.isAssignableFrom(type)) {
			final I18NDateField df = new I18NDateField();
			df.setResolution(DateField.RESOLUTION_DAY);
			return df;
		}

		// Boolean field
		if (Boolean.class.isAssignableFrom(type)) {
			return new I18NCheckBox();
		}

		return new I18NTextField();
	}

}
