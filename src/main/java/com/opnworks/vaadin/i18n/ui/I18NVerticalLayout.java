package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareContainer;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.support.I18NAwareSupport;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

/**
 * The I18NVerticalLayout
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NVerticalLayout extends VerticalLayout implements
		I18NAwareContainer {

	private static final long serialVersionUID = 1060456585902319374L;

	private I18NAwareSupport i18nAwareSupport = new I18NAwareSupport();

	/**
	 * Creates a new i18n VerticalLayout.
	 */
	public I18NVerticalLayout() {
		super();
	}
	
	@Override
	public void addComponent(Component c) {
		super.addComponent(c);
		i18nAwareSupport.add(c);
	}

	@Override
	public void i18NUpdate(I18NService i18N) {
		i18nAwareSupport.updateLabels(i18N);
	}
}
