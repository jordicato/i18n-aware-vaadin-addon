package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareCaption;
import com.opnworks.vaadin.i18n.I18NAwareComponent;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.processor.GenerateInstantiateSubclassAspect;
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
@GenerateInstantiateSubclassAspect
@SuppressWarnings("serial")
public class I18NPanel extends Panel implements I18NAwareComponent, I18NAwareCaption {

	private I18NAwareComponentCaptionSupport i18NAwareComponentCaptionSupport;

	private I18NAwareSupport i18nAwareSupport;
	
	/**
	 * Creates a new empty i18n panel. A I18NVerticalLayout is used as content.
	 */
	public I18NPanel() {
		super();
		setContent(new I18NVerticalLayout());
	}

	/**
	 * Creates a new empty panel which contains the given content. The content cannot be null.
	 * 
	 * @param content
	 *            the content for the panel.
	 */
	public I18NPanel(ComponentContainer content) {
		super(content);
		setContent(new I18NVerticalLayout());
	}

	/**
	 * Creates a new empty i18n panel with caption message key. Default layout is used.
	 * 
	 * @param captionKey
	 *            the caption message key used in the panel.
	 */
	public I18NPanel(@I18NAwareMessage String captionKey) {
		super(captionKey);
		setCaptionMessage(captionKey);
		setContent(new I18NVerticalLayout());
	}

	/**
	 * Creates a new empty panel with the given caption and content.
	 * 
	 * @param captionKey
	 *            the caption message key used in the panel.
	 * @param content
	 *            the content used in the panel.
	 */
	public I18NPanel(@I18NAwareMessage String captionKey, ComponentContainer content) {
		super(captionKey, content);
		setCaptionMessage(captionKey);
		setContent(new I18NVerticalLayout());
	}

	@Override
	public void i18NUpdate(I18NService i18N) {
		getI18NAwareComponentCaptionSupport().i18NUpdate(i18N);
		//i18NDescriptionSupport.i18NUpdate(i18N);
		getI18nAwareSupport().i18NUpdate(i18N);
	}

	@Override
	public void setCaption(@I18NAwareMessage String captionKey) {
		setCaptionMessage(captionKey);
	}

	@Override
	public void setCaptionMessage(@I18NAwareMessage String captionKey, Object... params) {
		getI18NAwareComponentCaptionSupport().setCaptionMessage(captionKey, params);
	}

	@Override
	public void setDescription(@I18NAwareMessage String descriptionKey) {
		setDescriptionMessage(descriptionKey);
	}

	@Override
	public void setDescriptionMessage(@I18NAwareMessage String descriptionKey, Object... descriptionParams) {
		getI18NAwareComponentCaptionSupport().setDescriptionMessage(descriptionKey, descriptionParams);
	}

	@Override
	public void setRealCaption(String caption) {
		super.setCaption(caption);
	}

	@Override
	public void setRealDescription(String description) {
		super.setDescription(description);
	}

	private I18NAwareComponentCaptionSupport getI18NAwareComponentCaptionSupport() {

		if (i18NAwareComponentCaptionSupport == null) {
			i18NAwareComponentCaptionSupport = new I18NAwareComponentCaptionSupport(this);
		}

		return i18NAwareComponentCaptionSupport;
	}
	
	@Override
    public void setContent(Component content) {
		super.setContent(content);
		getI18nAwareSupport().add(content);
	}
	
	private I18NAwareSupport getI18nAwareSupport() {
		if (i18nAwareSupport == null) {
			i18nAwareSupport = new I18NAwareSupport();
		}
		return i18nAwareSupport;
	}
}
