package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareCaption;
import com.opnworks.vaadin.i18n.I18NAwareContainer;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.support.I18NAwareComponentCaptionSupport;
import com.opnworks.vaadin.i18n.support.I18NAwareSupport;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Panel;

/**
 * The I18NPanel
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NPanel extends Panel implements I18NAwareContainer,
		I18NAwareCaption {

	private static final long serialVersionUID = 6357950198553382989L;

	private I18NAwareComponentCaptionSupport captionSupport = new I18NAwareComponentCaptionSupport(
			this);

	private I18NAwareSupport i18nAwareSupport;

    /**
     * Creates a new empty i18n panel. A I18NVerticalLayout is used as content.
     */
	public I18NPanel() {
		super();
		setContent(new I18NVerticalLayout());
	}

    /**
     * Creates a new empty i18n panel with caption message key. Default layout is used.
     * 
     * @param captionKey
     *            the caption message key used in the panel.
     */
	public I18NPanel(String captionKey) {
		super(captionKey);
		setCaptionKey(captionKey);
		setContent(new I18NVerticalLayout());
	}

	@Override
	public void setContent(ComponentContainer newContent) {
		super.setContent(newContent);
		getI18nAwareSupport().add(newContent);
	}

	@Override
	public void addComponent(Component c) {
		super.addComponent(c);
		getI18nAwareSupport().add(c);
	}

	@Override
	public void setCaptionKey(String captionKey) {
		captionSupport.setCaptionKey(captionKey);
	}

	@Override
	public void setCaptionParams(Object... params) {
		captionSupport.setCaptionParams(params);
	}

	@Override
	public void i18NUpdate(I18NService i18N) {
		captionSupport.updateLabels(i18N);
		getI18nAwareSupport().updateLabels(i18N);
	}

	private I18NAwareSupport getI18nAwareSupport() {
		if (i18nAwareSupport == null) {
			i18nAwareSupport = new I18NAwareSupport();
		}
		return i18nAwareSupport;
	}
}