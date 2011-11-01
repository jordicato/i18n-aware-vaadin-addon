package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareContainer;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.support.I18NAwareSupport;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Component;

/**
 * The I18N AbsoluteLayout
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NAbsoluteLayout extends AbsoluteLayout implements
		I18NAwareContainer {

	private static final long serialVersionUID = -4162410642504114947L;

	private I18NAwareSupport i18nAwareSupport = new I18NAwareSupport();

    /**
     * Creates an i18n AbsoluteLayout with full size.
     */
    public I18NAbsoluteLayout() {
        super();
    }
	
	@Override
	public void addComponent(Component c) {
		super.addComponent(c);
		i18nAwareSupport.add(c);
	}

	@Override
	public void removeComponent(Component c) {
		super.removeComponent(c);
		i18nAwareSupport.remove(c);
	}

	@Override
	public void i18NUpdate(I18NService i18N) {
		i18nAwareSupport.updateLabels(i18N);
	}
}
