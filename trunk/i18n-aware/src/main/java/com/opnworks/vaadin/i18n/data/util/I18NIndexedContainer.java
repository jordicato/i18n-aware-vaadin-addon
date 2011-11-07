package com.opnworks.vaadin.i18n.data.util;

import java.util.Locale;

import com.opnworks.vaadin.i18n.I18NAware;
import com.opnworks.vaadin.i18n.I18NService;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.IndexedContainer;

/**
 * The I18NIndexedContainer
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NIndexedContainer extends IndexedContainer implements I18NAware {

	private static final long serialVersionUID = 4796387361379052615L;

	private Locale locale;
	
	@Override
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	
	@Override
	public Locale getLocale() {
		return locale;
	}
	
	@Override
	public void i18NUpdate(I18NService i18N) {
		
		setLocale(i18N.getLocale());

		for (Object itemId : getItemIds()) {

			Item item = getItem(itemId);

			for (Object propertyId : item.getItemPropertyIds()) {

				Property property = item.getItemProperty(propertyId);

				Object value = property.getValue();

				if (value instanceof I18NAware) {

					I18NAware i18NValue = (I18NAware) value;

					i18NValue.i18NUpdate(i18N);

					property.setValue(i18NValue);
				}
			}
		}

	}

}
