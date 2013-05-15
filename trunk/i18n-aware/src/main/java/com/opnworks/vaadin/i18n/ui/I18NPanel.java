package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareCaption;
import com.opnworks.vaadin.i18n.I18NAwareComponentExpression;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.processor.GenerateInstantiateSubclassAspect;
import com.opnworks.vaadin.i18n.support.I18NAwareComponentExpressionSupport;
import com.opnworks.vaadin.i18n.support.I18NAwareSupport;
import com.opnworks.vaadin.i18n.support.I18NExpression;
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
public class I18NPanel extends Panel implements I18NAwareComponentExpression, I18NAwareCaption {

	private I18NAwareComponentExpressionSupport i18NAwareComponentExpressionSupport;

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

	public I18NPanel(I18NExpression captionExpression) {
		super(captionExpression.getStringFinal());
		setCaptionMessage(captionExpression.getObjectlist());
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

	public I18NPanel(I18NExpression captionExpression, ComponentContainer content) {
		super(captionExpression.getStringFinal(), content);
		setCaptionMessage(captionExpression.getObjectlist());
		setContent(new I18NVerticalLayout());
	}

	@Override
	public void i18NUpdate(I18NService i18N) {
		// i18NDescriptionSupport.i18NUpdate(i18N);
		getI18nAwareSupport().i18NUpdate(i18N);
		getI18NAwareComponentExpressionSupport().i18NUpdate(i18N);
	}

	@Override
	public void setCaption(@I18NAwareMessage String captionKey) {
		setCaptionMessage(captionKey);
	}

	public void setCaption(Object... expression) {
		setCaptionMessage(expression);
	}

	public void setDescription(Object... expression) {
		setDescriptionMessage(expression);
	}

	@Override
	public void setCaptionMessage(@I18NAwareMessage String captionKey, Object... params) {
		getI18NAwareComponentExpressionSupport().setCaptionMessage(captionKey, params);
	}

	@Override
	public void setDescription(@I18NAwareMessage String descriptionKey) {
		setDescriptionMessage(descriptionKey);
	}

	@Override
	public void setDescriptionMessage(@I18NAwareMessage String descriptionKey, Object... descriptionParams) {
		getI18NAwareComponentExpressionSupport().setDescriptionMessage(descriptionKey, descriptionParams);
	}

	@Override
	public void setRealCaption(String caption) {
		super.setCaption(caption);
	}

	@Override
	public void setRealDescription(String description) {
		super.setDescription(description);
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

	@Override
	public void setCaptionMessage(Object... expression) {
		getI18NAwareComponentExpressionSupport().setCaptionMessage(expression);
	}

	@Override
	public void setDescriptionMessage(Object... expression) {
		getI18NAwareComponentExpressionSupport().setDescriptionMessage(expression);
	}

	private I18NAwareComponentExpressionSupport getI18NAwareComponentExpressionSupport() {

		if (i18NAwareComponentExpressionSupport == null) {
			i18NAwareComponentExpressionSupport = new I18NAwareComponentExpressionSupport(this);
		}

		return i18NAwareComponentExpressionSupport;
	}

}
