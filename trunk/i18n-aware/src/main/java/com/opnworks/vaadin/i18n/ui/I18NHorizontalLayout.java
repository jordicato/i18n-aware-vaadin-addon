package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareComponentExpression;
import com.opnworks.vaadin.i18n.I18NAwareLayout;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.processor.GenerateInstantiateSubclassAspect;
import com.opnworks.vaadin.i18n.support.I18NAwareComponentCaptionSupport;
import com.opnworks.vaadin.i18n.support.I18NAwareComponentExpressionSupport;
import com.opnworks.vaadin.i18n.support.I18NAwareSupport;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;

/**
 * The I18NHorizontalLayout
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
@GenerateInstantiateSubclassAspect
@SuppressWarnings("serial")
public class I18NHorizontalLayout extends HorizontalLayout implements I18NAwareLayout, I18NAwareComponentExpression {

	private I18NAwareComponentCaptionSupport i18NAwareComponentCaptionSupport = new I18NAwareComponentCaptionSupport(this);
	private I18NAwareComponentExpressionSupport i18NAwareComponentExpressionSupport;
	private I18NAwareSupport i18nAwareSupport = new I18NAwareSupport();

	@Override
	public void addComponent(Component c) {
		super.addComponent(c);
		i18nAwareSupport.add(c);
	}

	@Override
	public void i18NUpdate(I18NService i18N) {
		i18NAwareComponentCaptionSupport.i18NUpdate(i18N);
		i18nAwareSupport.i18NUpdate(i18N);
		getI18NAwareComponentExpressionSupport().i18NUpdate(i18N);
	}

	@Override
	public void removeComponent(Component c) {
		super.removeComponent(c);
		i18nAwareSupport.remove(c);
	}

	@Override
	public void setCaption(@I18NAwareMessage String captionKey) {
		setCaptionMessage(captionKey);
	}

	@Override
	public void setCaptionMessage(@I18NAwareMessage String captionKey, Object... params) {
		i18NAwareComponentCaptionSupport.setCaptionMessage(captionKey, params);
	}

	@Override
	public void setDescription(@I18NAwareMessage String descriptionKey) {
		setDescriptionMessage(descriptionKey);
	}

	@Override
	public void setDescriptionMessage(@I18NAwareMessage String descriptionKey, Object... descriptionParams) {
		i18NAwareComponentCaptionSupport.setDescriptionMessage(descriptionKey, descriptionParams);
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
