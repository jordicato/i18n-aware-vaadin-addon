package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareCaption;
import com.opnworks.vaadin.i18n.I18NAwareComponentExpression;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.processor.GenerateInstantiateSubclassAspect;
import com.opnworks.vaadin.i18n.support.I18NAwareComponentCaptionSupport;
import com.opnworks.vaadin.i18n.support.I18NAwareComponentExpressionSupport;
import com.opnworks.vaadin.i18n.support.I18NAwareExpressionSupport;
import com.opnworks.vaadin.i18n.support.I18NAwareExpressionSupport.AwareExpressionContainer;
import com.opnworks.vaadin.i18n.support.I18NCaptionSupport;
import com.opnworks.vaadin.i18n.support.I18NCaptionSupport.CaptionContainer;
import com.opnworks.vaadin.i18n.support.I18NExpression;
import com.vaadin.ui.Upload;

/**
 * The I18NUpload
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
@GenerateInstantiateSubclassAspect
@SuppressWarnings("serial")
public class I18NUpload extends Upload implements I18NAwareComponentExpression, I18NAwareCaption {

	private I18NAwareComponentCaptionSupport i18NAwareComponentCaptionSupport;
	private I18NAwareComponentExpressionSupport i18NAwareComponentExpressionSupport;

	private I18NAwareExpressionSupport buttonExpressionCaptionI18NCaptionSupport = new I18NAwareExpressionSupport(new AwareExpressionContainer() {
		@Override
		public void setValue(String value) {
			setButtonRealCaption(value);
		}
	});

	private I18NCaptionSupport buttonCaptionI18NCaptionSupport = new I18NCaptionSupport(new CaptionContainer() {
		@Override
		public void setRealCaption(String caption) {
			setButtonRealCaption(caption);
		}
	});

	/**
	 * Creates a new i18n Upload.
	 * 
	 * The receiver must be set before performing an upload.
	 */
	public I18NUpload() {
		super();
	}

	/**
	 * Creates a new i18n Upload with caption message key and Receiver.
	 * 
	 * @param captionKey
	 */
	public I18NUpload(@I18NAwareMessage String captionKey, Receiver receiver) {
		super(captionKey, receiver);
		setCaptionMessage(captionKey);
	}

	@Override
	public void i18NUpdate(I18NService i18N) {

		getI18NAwareComponentCaptionSupport().i18NUpdate(i18N);
		buttonCaptionI18NCaptionSupport.i18NUpdate(i18N);
	}

	@Override
	public void setButtonCaption(@I18NAwareMessage String buttonCaptionKey) {
		setButtonCaptionMessage(buttonCaptionKey);
	}

	public void setButtonCaptionMessage(@I18NAwareMessage String buttonCaptionKey, Object... buttonCaptionParams) {
		buttonCaptionI18NCaptionSupport.setCaptionMessage(buttonCaptionKey, buttonCaptionParams);
	}

	public void setButtonCaptionMessage(I18NExpression expressions, Object... buttonCaptionParams) {
		buttonExpressionCaptionI18NCaptionSupport.setCaptionMessage(expressions, buttonCaptionParams);
	}

	public void setButtonRealCaption(String buttonCaption) {
		super.setButtonCaption(buttonCaption);
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
