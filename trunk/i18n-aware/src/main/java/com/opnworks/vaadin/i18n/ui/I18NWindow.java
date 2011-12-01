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
import com.vaadin.ui.Window;

/**
 * The I18NWindow
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
@GenerateInstantiateSubclassAspect
public class I18NWindow extends Window implements I18NAwareContainer,
		I18NAwareCaption {

	private static final long serialVersionUID = 6357950198553382989L;

	private I18NAwareComponentCaptionSupport captionSupport;

	private I18NAwareValueSupport i18NDescriptionSupport = new I18NAwareValueSupport(
			new ValueContainer() {
				@Override
				public void setValue(String value) {
					setDescription(value);

				}
			});

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
	public I18NWindow(@I18NAwareMessage String captionKey) {
		super(captionKey);
		getCaptionSupport().setCaptionMessage(captionKey);
		setContent(new I18NVerticalLayout());
	}

	/**
	 * Creates a new unnamed i18n window with the given content and title.
	 * 
	 * @param captionKey
	 *            the title message key of the window.
	 * @param content
	 *            the contents of the window
	 */
	public I18NWindow(@I18NAwareMessage String captionKey,
			ComponentContainer content) {
		super(captionKey, content);
		getCaptionSupport().setCaptionMessage(captionKey);
	}

	@Override
	public void setRealCaption(String caption) {
		super.setCaption(caption);
	}

	@Override
	public void setCaption(String captionKey) {
		setCaptionMessage(captionKey);
	}

	@Override
	public void setCaptionMessage(@I18NAwareMessage String captionKey,
			Object... params) {
		getCaptionSupport().setCaptionMessage(captionKey, params);
	}

	@Override
	public void setRealDescription(String description) {
		super.setDescription(description);
	}

	@Override
	public void setDescription(String descriptionKey) {
		setDescriptionMessage(descriptionKey);
	}

	@Override
	public void setDescriptionMessage(@I18NAwareMessage String descriptionKey,
			Object... descriptionParams) {
		i18NDescriptionSupport.setValueMessage(descriptionKey,
				descriptionParams);
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
		getCaptionSupport().i18NUpdate(i18N);
		i18NDescriptionSupport.i18NUpdate(i18N);
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

	private I18NAwareComponentCaptionSupport getCaptionSupport() {

		if (captionSupport == null) {
			captionSupport = new I18NAwareComponentCaptionSupport(this);
		}

		return captionSupport;
	}
}
