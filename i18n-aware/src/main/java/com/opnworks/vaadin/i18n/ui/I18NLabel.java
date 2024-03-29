package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareComponentExpression;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NAwareValue;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.processor.GenerateInstantiateSubclassAspect;
import com.opnworks.vaadin.i18n.support.I18NAwareComponentExpressionSupport;
import com.opnworks.vaadin.i18n.support.I18NAwareValueSupport;
import com.opnworks.vaadin.i18n.support.I18NAwareValueSupport.AwareValueContainer;
import com.opnworks.vaadin.i18n.support.I18NExpression;
import com.vaadin.data.Property;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;

/**
 * The I18NLabel
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
@GenerateInstantiateSubclassAspect
@SuppressWarnings("serial")
public class I18NLabel extends Label implements I18NAwareValue, AwareValueContainer, I18NAwareComponentExpression {

	private I18NAwareComponentExpressionSupport i18NAwareComponentExpressionSupport;

	//private I18NAwareComponentCaptionSupport captionSupport = new I18NAwareComponentCaptionSupport(this);

	private I18NAwareValueSupport i18NAwareValueSupport;

	/**
	 * Creates an empty i18n I18NLabel.
	 */
	public I18NLabel() {
		super();
	}

	/**
	 * Creates a new instance of I18NLabel with text-contents read from given datasource.
	 * 
	 * @param contentSource
	 */
	public I18NLabel(Property<?> contentSource) {
		super(contentSource);
	}

	/**
	 * Creates a new instance of I18NLabel with text-contents read from given datasource.
	 * 
	 * @param contentSource
	 * @param contentMode
	 */
	public I18NLabel(Property<?> contentSource, ContentMode contentMode) {
		super(contentSource, contentMode);
	}

	/**
	 * Creates an i18n I18NLabel with text-contents.
	 * 
	 * @param content
	 */
	public I18NLabel(@I18NAwareMessage String contentKey) {
		super();
		setValueMessage(contentKey);
	}

	public I18NLabel(I18NExpression expressions) {
		super();
		setValueMessage(expressions.getStringFinal());		
	}

	/**
	 * Creates a new instance of I18NLabel with text-contents.
	 * 
	 * @param content
	 * @param contentMode
	 */
	public I18NLabel(String contentKey, ContentMode contentMode) {
		super();
		super.setContentMode(contentMode);
		setValueMessage(contentKey);
	}

	public I18NLabel(I18NExpression captionExpression, ContentMode contentMode) {
		super();
		super.setContentMode(contentMode);
		setValueMessage(captionExpression.getStringFinal());
	}

	@Override
	public void i18NUpdate(I18NService i18N) {

		//captionSupport.i18NUpdate(i18N);
	
		getI18NAwareComponentExpressionSupport().i18NUpdate(i18N);

		if (i18NAwareValueSupport != null) {
			i18NAwareValueSupport.i18NUpdate(i18N);
		}
	}

	public void setCaption(I18NExpression expression) {
		setCaptionMessage(expression);
	}

	public void setDescription(I18NExpression expression) {
		setDescriptionMessage(expression);
	}

	@Override
	public void setCaption(@I18NAwareMessage String captionKey) {
		setCaptionMessage(captionKey);
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
	public void setValue(String value) {
		super.setValue(value);
	}

	@Override
	public void setValueMessage(@I18NAwareMessage String textKey, Object... params) {

		createValueSupport();

		i18NAwareValueSupport.setValueMessage(textKey, params);

	}

	private void createValueSupport() {

		if (i18NAwareValueSupport == null) {
			i18NAwareValueSupport = new I18NAwareValueSupport(this);
		}
	}

	private I18NAwareComponentExpressionSupport getI18NAwareComponentExpressionSupport() {

		if (i18NAwareComponentExpressionSupport == null) {
			i18NAwareComponentExpressionSupport = new I18NAwareComponentExpressionSupport(this);
		}

		return i18NAwareComponentExpressionSupport;
	}

	@Override
	public void setRealValue(String value) {
		super.setValue(value);
	}

	@Override
	public void setCaptionMessage(I18NExpression expression) {
		getI18NAwareComponentExpressionSupport().setCaptionMessage(expression);
	}

	@Override
	public void setDescriptionMessage(I18NExpression expression) {
		getI18NAwareComponentExpressionSupport().setDescriptionMessage(expression);
	}

}
