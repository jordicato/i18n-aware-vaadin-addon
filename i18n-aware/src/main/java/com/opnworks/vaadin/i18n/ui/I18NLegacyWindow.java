package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareCaption;
import com.opnworks.vaadin.i18n.I18NAwareComponentExpression;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.processor.GenerateInstantiateSubclassAspect;
import com.opnworks.vaadin.i18n.support.I18NAwareComponentExpressionSupport;
import com.opnworks.vaadin.i18n.support.I18NExpression;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.LegacyWindow;

/**
 * The I18NLegazyWindow
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
@GenerateInstantiateSubclassAspect
@SuppressWarnings({ "serial", "deprecation" })
@Deprecated
public class I18NLegacyWindow extends LegacyWindow implements I18NAwareComponentExpression, I18NAwareCaption {

	private I18NAwareComponentExpressionSupport i18NAwareComponentExpressionSupport;

	public I18NLegacyWindow() {
		super();
	}

	public I18NLegacyWindow(@I18NAwareMessage String captionKey) {
		super(captionKey);
		setCaptionMessage(captionKey);
	}

	public I18NLegacyWindow(I18NExpression captionExpression) {
		super(captionExpression.getStringFinal());
		setCaptionMessage(captionExpression);
	}

	public I18NLegacyWindow(@I18NAwareMessage String captionKey, ComponentContainer componentContainer) {
		super(captionKey, componentContainer);
		setCaptionMessage(captionKey);
	}

	public I18NLegacyWindow(I18NExpression captionExpression, ComponentContainer componentContainer) {
		super(captionExpression.getStringFinal(), componentContainer);
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
