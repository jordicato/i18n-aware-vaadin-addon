package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareComponent;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.support.I18NAwareComponentCaptionSupport;
import com.opnworks.vaadin.i18n.support.I18NAwareSupport;
import com.vaadin.terminal.Resource;
import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;

/**
 * The I18NTabSheet
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NTabSheet extends TabSheet implements I18NAwareComponent {

	private static final long serialVersionUID = -7070663953414272939L;

	private I18NAwareComponentCaptionSupport i18NAwareComponentCaptionSupport = new I18NAwareComponentCaptionSupport(this);
	private I18NAwareSupport i18NAwareSupport = new I18NAwareSupport();

	/**
	 * Constructs a new i18n Tabsheet. Tabsheet is immediate by default, and the
	 * default close handler removes the tab being closed.
	 */
	public I18NTabSheet() {
		super();
	}

	public I18NTab addI18NTab(Component c) {
		return (I18NTab) addTab(c);
	}
	
	@Override
	public Tab addTab(Component c) {
		return addI18NTabSupport(c, super.addTab(c));
	}

	@Override
	public Tab addTab(Component c, @I18NAwareMessage String captionKey) {
		return addI18NTabSupport(c, super.addTab(c, captionKey), captionKey);
	}
	
	@Override
	public Tab addTab(Component c, @I18NAwareMessage String captionKey, Resource icon) {
		return addI18NTabSupport(c, super.addTab(c, captionKey, icon), captionKey);
	}
	
	@Override
	public Tab addTab(Component c, @I18NAwareMessage String captionKey, Resource icon, int position) {
		return addI18NTabSupport(c, super.addTab(c, captionKey, icon, position), captionKey);
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
		i18NAwareSupport.i18NUpdate(i18N);
	}

	private I18NTab addI18NTabSupport(Component c, Tab tab) {
		return addI18NTabSupport(c, tab, null);
	}

	private I18NTab addI18NTabSupport(Component c, Tab tab, String captionKey, Object... captionParams) {
		
		i18NAwareSupport.add(c);

		I18NTab result = new I18NTab(tab);

		i18NAwareSupport.add(result);
		
		if( captionKey != null ) {
			result.setCaptionMessage(captionKey, captionParams);
		}

		return result;
	}
}
