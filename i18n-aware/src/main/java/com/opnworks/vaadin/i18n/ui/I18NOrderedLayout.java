package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareContainer;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.support.I18NAwareSupport;
import com.vaadin.ui.Component;
import com.vaadin.ui.OrderedLayout;

/**
 * The I18N OrderedLayout
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
@Deprecated
public class I18NOrderedLayout extends OrderedLayout implements I18NAwareContainer {

	private static final long serialVersionUID = -4162410642504114947L;

	private I18NAwareSupport i18nAwareSupport = new I18NAwareSupport();

	/**
	 * Creates a new i18n ordered layout. The order of the layout is
	 * <code>ORIENTATION_VERTICAL</code>.
	 * 
	 * @deprecated Use I18NVerticalLayout instead.
	 */
	public I18NOrderedLayout() {
		super();
	}

	/**
	 * Create a new ordered layout. The orientation of the layout is given as
	 * parameters.
	 * 
	 * @param orientation
	 *            the Orientation of the layout.
	 * 
	 * @deprecated Use VerticalLayout/HorizontalLayout instead.
	 */
	@Deprecated
	public I18NOrderedLayout(int orientation) {
		super(orientation);
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
		i18nAwareSupport.i18NUpdate(i18N);
	}
}
