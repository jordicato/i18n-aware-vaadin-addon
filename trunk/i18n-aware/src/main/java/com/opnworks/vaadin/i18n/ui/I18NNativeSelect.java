package com.opnworks.vaadin.i18n.ui;

import java.util.Collection;

import com.opnworks.vaadin.i18n.I18NAwareComponentExpression;
import com.opnworks.vaadin.i18n.I18NAwareField;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.processor.GenerateInstantiateSubclassAspect;
import com.opnworks.vaadin.i18n.support.I18NAwareComponentExpressionSupport;
import com.opnworks.vaadin.i18n.support.I18NAwareFieldSupport;
import com.opnworks.vaadin.i18n.support.I18NExpressions;
import com.opnworks.vaadin.i18n.support.I18NSupportExpression;
import com.vaadin.data.Container;
import com.vaadin.ui.NativeSelect;

/**
 * The I18NNativeSelect
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
@GenerateInstantiateSubclassAspect
@SuppressWarnings("serial")
public class I18NNativeSelect extends NativeSelect implements I18NAwareField<Object>, I18NAwareComponentExpression {

	private I18NAwareFieldSupport<Object> i18NAwareFieldSupport;
	private I18NAwareComponentExpressionSupport i18NAwareComponentExpressionSupport;

	public I18NNativeSelect() {
		super();
	}

	public I18NNativeSelect(@I18NAwareMessage String captionKey) {
		super(captionKey);
		I18NExpressions i18NExpressions = I18NSupportExpression.getInstance().getI18NExpressions();
		if (i18NExpressions != null) {			
			setCaptionMessage(i18NExpressions);
		} else if (!I18NExpressions.isKey(captionKey)) {
			setStringVarMessage(captionKey);
		} else {		
			setCaptionMessage(captionKey);
		}
	}

	public I18NNativeSelect(@I18NAwareMessage String captionKey, Collection<?> options) {
		super(captionKey, options);
		I18NExpressions i18NExpressions = I18NSupportExpression.getInstance().getI18NExpressions();
		if (i18NExpressions != null) {			
			setCaptionMessage(i18NExpressions);
		} else if (!I18NExpressions.isKey(captionKey)) {
			setStringVarMessage(captionKey);
		} else {		
			setCaptionMessage(captionKey);
		}
	}

	public I18NNativeSelect(@I18NAwareMessage String captionKey, Container dataSource) {
		super(captionKey, dataSource);
		I18NExpressions i18NExpressions = I18NSupportExpression.getInstance().getI18NExpressions();
		if (i18NExpressions != null) {			
			setCaptionMessage(i18NExpressions);
		} else if (!I18NExpressions.isKey(captionKey)) {
			setStringVarMessage(captionKey);
		} else {		
			setCaptionMessage(captionKey);
		}
	}

	@Override
	public void i18NUpdate(I18NService i18N) {
		getI18NAwareFieldSupport().i18NUpdate(i18N);
		getI18NAwareComponentExpressionSupport().i18NUpdate(i18N);
	}

	@Override
	public void setCaption(@I18NAwareMessage String captionKey) {
		I18NExpressions i18NExpressions = I18NSupportExpression.getInstance().getI18NExpressions();
		if (i18NExpressions != null) {			
			setCaptionMessage(i18NExpressions);
		} else if (!I18NExpressions.isKey(captionKey)) {
			setStringVarMessage(captionKey);
		} else {		
			setCaptionMessage(captionKey);
		}
	}

	@Override
	public void setCaptionMessage(@I18NAwareMessage String captionKey, Object... params) {
		getI18NAwareFieldSupport().setCaptionMessage(captionKey, params);
	}

	@Override
	public void setDescription(@I18NAwareMessage String descriptionKey) {
		I18NExpressions i18NExpressions = I18NSupportExpression.getInstance().getI18NExpressions();
		if (i18NExpressions != null) {			
			setDescriptionMessage(i18NExpressions);
		} else {
			setDescriptionMessage(descriptionKey);
		}
	}

	@Override
	public void setDescriptionMessage(@I18NAwareMessage String descriptionKey, Object... descriptionParams) {
		getI18NAwareFieldSupport().setDescriptionMessage(descriptionKey, descriptionParams);
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
	public void setRealRequiredError(String requiredMessage) {
		super.setRequiredError(requiredMessage);
	}

	@Override
	public void setRequiredError(@I18NAwareMessage String requiredErrorKey) {
		setRequiredErrorMessage(requiredErrorKey);
	}

	@Override
	public void setRequiredErrorMessage(@I18NAwareMessage String requiredErrorKey, Object... requiredErrorParams) {
		getI18NAwareFieldSupport().setRequiredErrorMessage(requiredErrorKey, requiredErrorParams);
	}

	private I18NAwareFieldSupport<Object> getI18NAwareFieldSupport() {

		if (i18NAwareFieldSupport == null) {
			i18NAwareFieldSupport = new I18NAwareFieldSupport<Object>(this);
		}

		return i18NAwareFieldSupport;
	}

	@Override
	public void setCaptionMessage(I18NExpressions expressions, Object... valueParams) {
		getI18NAwareComponentExpressionSupport().setCaptionMessage(expressions, valueParams);		
	}

	@Override
	public void setDescriptionMessage(I18NExpressions expressions, Object... valueParams) {
		getI18NAwareComponentExpressionSupport().setDescriptionMessage(expressions, valueParams);		
	}
	
	private I18NAwareComponentExpressionSupport getI18NAwareComponentExpressionSupport() {

		if (i18NAwareComponentExpressionSupport == null) {
			i18NAwareComponentExpressionSupport = new I18NAwareComponentExpressionSupport(this);
		}

		return i18NAwareComponentExpressionSupport;
	}

	@Override
	public void setStringVarMessage(String captionKey, Object... params) {
		getI18NAwareComponentExpressionSupport().setStringVarMessage(captionKey, params);		
	}
}
