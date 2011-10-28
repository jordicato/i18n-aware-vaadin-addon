package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareCaption;
import com.opnworks.vaadin.i18n.I18NAwareContainer;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.support.I18NAwareComponentCaptionSupport;
import com.opnworks.vaadin.i18n.support.I18NAwareSupport;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Window;

/**
 * The I18NWindow
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NWindow extends Window implements I18NAwareContainer,
		I18NAwareCaption {

	private static final long serialVersionUID = 6357950198553382989L;

	private I18NAwareComponentCaptionSupport captionSupport = new I18NAwareComponentCaptionSupport(
			this);

	private I18NAwareSupport i18nAwareSupport;

	/**
	 * Creates a new unnamed i18n window with a I18NVerticalLayout layout.
	 */
	public I18NWindow() {
		super();
		setContent(new I18NVerticalLayout());
	}

	/**
	 * Creates a new unnamed i18n window with a I18NVerticalLayout layout and
	 * given title message key.
	 * 
	 * @param captionKey
	 *            the title message key of the window.
	 */
	public I18NWindow(String captionKey) {
		super(captionKey);
		captionSupport.setCaptionKey(captionKey);
		setContent(new I18NVerticalLayout());
	}

	@Override
	public void setCaptionKey(String captionKey) {
		captionSupport.setCaptionKey(captionKey);
	}

	@Override
	public void setCaptionParams(Object... captionParams) {
		captionSupport.setCaptionParams(captionParams);
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
	public void i18NUpdate(I18NService i18N) {
		captionSupport.updateLabels(i18N);
		ComponentContainer container = getContent();
		if (container instanceof I18NAwareContainer) {
			((I18NAwareContainer) container).i18NUpdate(i18N);
		}
	}

	private I18NAwareSupport getI18nAwareSupport() {
		if (i18nAwareSupport == null) {
			i18nAwareSupport = new I18NAwareSupport();
		}
		return i18nAwareSupport;
	}
}
