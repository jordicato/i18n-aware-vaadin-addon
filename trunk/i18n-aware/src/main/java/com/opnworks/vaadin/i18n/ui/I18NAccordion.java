package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareComponent;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.support.I18NAwareComponentCaptionSupport;
import com.opnworks.vaadin.i18n.support.I18NAwareSupport;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Component;

/**
 * The I18NAccordion
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NAccordion extends Accordion implements I18NAwareComponent {

	private static final long serialVersionUID = -891019186115514452L;
	
	private I18NAwareComponentCaptionSupport i18NAwareComponentCaptionSupport = new I18NAwareComponentCaptionSupport(this);
	private I18NAwareSupport i18NAwareSupport = new I18NAwareSupport();

	/**
	 * Constructs a new i18n Accordion.  
	 */
	public I18NAccordion() {
		super();
	}

	@Override
	public Tab addTab(Component c) {

		Tab tab = super.addTab(c);

		i18NAwareSupport.add(c);

		I18NTab result = new I18NTab(tab);

		i18NAwareSupport.add(result);

		return result;
	}

	public I18NTab addI18NTab(Component c) {
		return (I18NTab) addTab(c);
	}

	@Override
	public void setCaptionKey(String captionKey) {
		i18NAwareComponentCaptionSupport.setCaptionKey(captionKey);
	}

	@Override
	public void setCaptionParams(Object... params) {
		i18NAwareComponentCaptionSupport.setCaptionParams(params);
	}

	@Override
	public void i18NUpdate(I18NService i18N) {
		i18NAwareComponentCaptionSupport.i18NUpdate(i18N);
		i18NAwareSupport.i18NUpdate(i18N);
	}

}
