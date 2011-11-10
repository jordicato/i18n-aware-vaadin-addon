package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareLayout;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.support.I18NAwareComponentCaptionSupport;
import com.opnworks.vaadin.i18n.support.I18NAwareSupport;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;

/**
 * The I18NHorizontalLayout
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NHorizontalLayout extends HorizontalLayout implements
		I18NAwareLayout {

	private static final long serialVersionUID = 1060456585902319374L;

	private I18NAwareComponentCaptionSupport i18NAwareComponentCaptionSupport = new I18NAwareComponentCaptionSupport(this);
	private I18NAwareSupport i18nAwareSupport = new I18NAwareSupport();

	/**
	 * Creates a new i18n HorizontalLayout.
	 */
	public I18NHorizontalLayout() {
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
	public void setCaptionMessage(@I18NAwareMessage String captionKey, Object... params) {
		i18NAwareComponentCaptionSupport.setCaptionMessage(captionKey, params);
	}

	@Override
	public void setDescriptionMessage(@I18NAwareMessage String descriptionKey,
			Object... descriptionParams) {
		i18NAwareComponentCaptionSupport.setDescriptionMessage(descriptionKey, descriptionParams);
	}
	
	@Override
	public void i18NUpdate(I18NService i18N) {
		i18NAwareComponentCaptionSupport.i18NUpdate(i18N);
		i18nAwareSupport.i18NUpdate(i18N);
	}
}
