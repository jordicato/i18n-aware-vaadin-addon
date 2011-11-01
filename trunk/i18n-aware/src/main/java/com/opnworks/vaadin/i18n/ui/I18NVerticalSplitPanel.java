package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareContainer;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.support.I18NAwareSupport;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalSplitPanel;

/**
 * The I18N VerticalSplitPanel
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NVerticalSplitPanel extends VerticalSplitPanel
		implements I18NAwareContainer {

	private static final long serialVersionUID = -8899641796596962022L;

	private I18NAwareSupport i18nAwareSupport = new I18NAwareSupport();

	/**
	 * Creates a new i18n VerticalSplitPanel.
	 */
	public I18NVerticalSplitPanel() {
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
