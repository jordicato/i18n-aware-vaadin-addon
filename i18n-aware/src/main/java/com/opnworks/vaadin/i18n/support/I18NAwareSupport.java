package com.opnworks.vaadin.i18n.support;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.opnworks.vaadin.i18n.I18NAware;
import com.opnworks.vaadin.i18n.I18NService;

/**
 * The I18NAwareSupport
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
@SuppressWarnings("serial")
public class I18NAwareSupport implements Serializable {

	private List<I18NAware> i18nAwares = new ArrayList<I18NAware>();

	public <T> void add(T item) {

		if (item instanceof I18NAware) {
			i18nAwares.add((I18NAware) item);
		}
	}

	public void clear() {

		i18nAwares.clear();
	}

	public List<I18NAware> getI18nAwares() {
		return i18nAwares;
	}

	public void i18NUpdate(I18NService i18N) {

		for (I18NAware i18nAware : i18nAwares ) {
			i18nAware.i18NUpdate(i18N);
		}
	}

	public void remove(Object item) {

		i18nAwares.remove(item);
	}

}
