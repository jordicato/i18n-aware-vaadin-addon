package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareCaption;
import com.opnworks.vaadin.i18n.I18NAwareContainer;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.processor.GenerateInstantiateSubclassAspect;
import com.opnworks.vaadin.i18n.support.I18NAwareComponentCaptionSupport;
import com.opnworks.vaadin.i18n.support.I18NAwareSupport;
import com.opnworks.vaadin.i18n.support.I18NAwareValueSupport;
import com.opnworks.vaadin.i18n.support.I18NAwareValueSupport.ValueContainer;
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
public class I18NPanel extends Panel implements I18NAwareContainer, I18NAwareCaption {

	private I18NAwareComponentCaptionSupport captionSupport;

	private I18NAwareValueSupport i18NDescriptionSupport = new I18NAwareValueSupport(new ValueContainer() {
		@Override
		public void setValue(String value) {
			setDescription(value);

		}
	});

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
	public void addComponent(Component c) {
		super.addComponent(c);
		getI18nAwareSupport().add(c);
	}

	@Override
	public void i18NUpdate(I18NService i18N) {
		getCaptionSupport().i18NUpdate(i18N);
		i18NDescriptionSupport.i18NUpdate(i18N);
		getI18nAwareSupport().i18NUpdate(i18N);
	}

	@Override
	public void setCaption(@I18NAwareMessage String captionKey) {
		setCaptionMessage(captionKey);
	}

	@Override
	public void setCaptionMessage(@I18NAwareMessage String captionKey, Object... params) {
		getCaptionSupport().setCaptionMessage(captionKey, params);
	}

	@Override
	public final void setContent(ComponentContainer newContent) {
		super.setContent(newContent);
		getI18nAwareSupport().add(newContent);
	}

	@Override
	public void setDescription(@I18NAwareMessage String descriptionKey) {
		setDescriptionMessage(descriptionKey);
	}

	@Override
	public void setDescriptionMessage(@I18NAwareMessage String descriptionKey, Object... descriptionParams) {
		i18NDescriptionSupport.setValueMessage(descriptionKey, descriptionParams);
	}

	@Override
	public void setRealCaption(String caption) {
		super.setCaption(caption);
	}

	@Override
	public void setRealDescription(String description) {
		super.setDescription(description);
	}

	private I18NAwareComponentCaptionSupport getCaptionSupport() {

		if (captionSupport == null) {
			captionSupport = new I18NAwareComponentCaptionSupport(this);
		}

		return captionSupport;
	}

	private I18NAwareSupport getI18nAwareSupport() {
		if (i18nAwareSupport == null) {
			i18nAwareSupport = new I18NAwareSupport();
		}
		return i18nAwareSupport;
	}
}
