package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareContainer;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.support.I18NAwareSupport;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;

/**
 * The I18NFormLayout
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NFormLayout extends FormLayout implements I18NAwareContainer {

	private static final long serialVersionUID = -6103979352836315594L;

	private I18NAwareSupport i18nAwareSupport = new I18NAwareSupport();

	@Override
	public void addComponent(Component c) {
		super.addComponent(c);
		i18nAwareSupport.add(c);

	}

	@Override
	public void addComponentAsFirst(Component c) {
		super.addComponentAsFirst(c);
		i18nAwareSupport.add(c);
	}

	@Override
	public void addComponent(Component c, int index) {
		super.addComponent(c, index);
		i18nAwareSupport.add(c);
	}

	@Override
	public void removeComponent(Component c) {
		super.removeComponent(c);
		i18nAwareSupport.remove(c);
	}

	@Override
	public void removeAllComponents() {
		super.removeAllComponents();
		i18nAwareSupport.clear();
	}

	@Override
	public void i18NUpdate(I18NService i18N) {
		i18nAwareSupport.updateLabels(i18N);
	}
}
