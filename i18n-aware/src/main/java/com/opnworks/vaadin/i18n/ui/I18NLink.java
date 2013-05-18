package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareCaption;
import com.opnworks.vaadin.i18n.I18NAwareComponentExpression;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.processor.GenerateInstantiateSubclassAspect;
import com.opnworks.vaadin.i18n.support.I18NAwareComponentExpressionSupport;
import com.opnworks.vaadin.i18n.support.I18NExpression;
import com.vaadin.server.Resource;
import com.vaadin.shared.ui.BorderStyle;
import com.vaadin.ui.Link;

/**
 * The I18NLink
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
@GenerateInstantiateSubclassAspect
@SuppressWarnings("serial")
public class I18NLink extends Link implements I18NAwareComponentExpression, I18NAwareCaption {

	private I18NAwareComponentExpressionSupport i18NAwareComponentExpressionSupport;

	/**
	 * Creates a new i18n link.
	 */
	public I18NLink() {
		super();
	}

	/**
	 * Creates a new i18n Link with caption message key.
	 * 
	 * @param captionKey
	 * @param resource
	 */
	public I18NLink(@I18NAwareMessage String captionKey, Resource resource) {
		super(captionKey, resource);
		setCaptionMessage(captionKey);
	}

	public I18NLink(I18NExpression captionExpression, Resource resource) {
		super(captionExpression.getStringFinal(), resource);
		setCaptionMessage(captionExpression);
	}

	/**
	 * Creates a new instance of Link that opens a new window.
	 * 
	 * 
	 * @param caption
	 *            the Link text.
	 * @param targetName
	 *            the name of the target window where the link opens to. Empty name of null implies that the target is opened to the window containing
	 *            the link.
	 * @param width
	 *            the Width of the target window.
	 * @param height
	 *            the Height of the target window.
	 * @param border
	 *            the Border style of the target window.
	 * 
	 */
	public I18NLink(@I18NAwareMessage String captionKey, Resource resource, String targetName, int width, int height, BorderStyle border) {
		super(captionKey, resource, targetName, width, height, border);
		setCaptionMessage(captionKey);
	}

	public I18NLink(I18NExpression captionExpression, Resource resource, String targetName, int width, int height, BorderStyle border) {
		super(captionExpression.getStringFinal(), resource, targetName, width, height, border);
		setCaptionMessage(captionExpression);
	}

	@Override
	public void i18NUpdate(I18NService i18N) {
		getI18NAwareComponentExpressionSupport().i18NUpdate(i18N);
	}

	@Override
	public void setCaption(@I18NAwareMessage String captionKey) {
		setCaptionMessage(captionKey);
	}

	public void setCaption(I18NExpression expression) {
		setCaptionMessage(expression);
	}

	public void setDescription(I18NExpression expression) {
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
	public void setCaptionMessage(I18NExpression expression) {
		getI18NAwareComponentExpressionSupport().setCaptionMessage(expression);
	}

	@Override
	public void setDescriptionMessage(I18NExpression expression) {
		getI18NAwareComponentExpressionSupport().setDescriptionMessage(expression);
	}

	private I18NAwareComponentExpressionSupport getI18NAwareComponentExpressionSupport() {

		if (i18NAwareComponentExpressionSupport == null) {
			i18NAwareComponentExpressionSupport = new I18NAwareComponentExpressionSupport(this);
		}

		return i18NAwareComponentExpressionSupport;
	}

}
