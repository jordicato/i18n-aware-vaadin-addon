package com.opnworks.vaadin.i18n;

import com.vaadin.ui.Component;

/**
 * The I18NAwareContainer
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public interface I18NAwareContainer extends I18NAwareComponent {

	/**
	 * Add a Component
	 * 
	 * @param c
	 *            the Component.
	 */
	void addComponent(Component c);
}
