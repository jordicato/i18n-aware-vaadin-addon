package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareFieldExpression;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.processor.GenerateInstantiateSubclassAspect;
import com.opnworks.vaadin.i18n.support.I18NAwareFieldSupport;
import com.opnworks.vaadin.i18n.support.I18NCaptionSupport;
import com.opnworks.vaadin.i18n.support.I18NCaptionSupport.CaptionContainer;
import com.opnworks.vaadin.i18n.support.I18NExpression;
import com.vaadin.data.Container;
import com.vaadin.ui.TwinColSelect;

/**
 * The I18NTwinColSelect
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
@GenerateInstantiateSubclassAspect
@SuppressWarnings("serial")
public class I18NTwinColSelect extends TwinColSelect implements I18NAwareFieldExpression<Object> {

	private I18NAwareFieldSupport<Object> i18NAwareFieldSupport;

	private I18NCaptionSupport leftColumnCaptionSupport;

	private I18NCaptionSupport rightColumnCaptionSupport;

	/**
	 * Construct a I18NTwinColSelect
	 */
	public I18NTwinColSelect() {
		super();
	}

	/**
	 * Construct a I18NTwinColSelect
	 * 
	 * @param caption
	 */
	public I18NTwinColSelect(@I18NAwareMessage String captionKey) {
		super(captionKey);
		setCaptionMessage(captionKey);
	}

	public I18NTwinColSelect(I18NExpression captionExpression) {
		super(captionExpression.getStringFinal());
		setCaptionMessage(captionExpression);
	}

	/**
	 * Construct a I18NTwinColSelect
	 * 
	 * @param captionKey
	 * @param dataSource
	 */
	public I18NTwinColSelect(@I18NAwareMessage String captionKey, Container dataSource) {
		super(captionKey, dataSource);
		setCaptionMessage(captionKey);
	}

	public I18NTwinColSelect(I18NExpression captionExpression, Container dataSource) {
		super(captionExpression.getStringFinal(), dataSource);
		setCaptionMessage(captionExpression);
	}

	@Override
	public void i18NUpdate(I18NService i18N) {
		getI18NAwareFieldSupport().i18NUpdate(i18N);
		getLeftColumnCaptionSupport().i18NUpdate(i18N);
		getRightColumnCaptionSupport().i18NUpdate(i18N);
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
		getI18NAwareFieldSupport().setCaptionMessage(captionKey, params);
	}

	@Override
	public void setDescription(@I18NAwareMessage String descriptionKey) {
		setDescriptionMessage(descriptionKey);
	}

	@Override
	public void setDescriptionMessage(@I18NAwareMessage String descriptionKey, Object... descriptionParams) {
		getI18NAwareFieldSupport().setDescriptionMessage(descriptionKey, descriptionParams);
	}

	@Override
	public void setLeftColumnCaption(@I18NAwareMessage String leftColumnCaptionKey) {
		setLeftColumnCaptionKey(leftColumnCaptionKey);
	}

	public void setLeftColumnCaptionKey(@I18NAwareMessage String leftColumnCaptionKey, Object... leftColumnCaptionParams) {
		getLeftColumnCaptionSupport().setCaptionMessage(leftColumnCaptionKey, leftColumnCaptionParams);
	}

	public void setLeftColumnRealCaption(String leftColumnCaption) {
		super.setLeftColumnCaption(leftColumnCaption);
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

	@Override
	public void setRightColumnCaption(@I18NAwareMessage String rightColumnCaption) {
		setRightColumnRealCaption(rightColumnCaption);
	}

	public void setRightColumnCaptionKey(@I18NAwareMessage String rightColumnCaptionKey, Object... rightColumnCaptionParams) {
		getRightColumnCaptionSupport().setCaptionMessage(rightColumnCaptionKey, rightColumnCaptionParams);
	}

	public void setRightColumnRealCaption(String rightColumnCaption) {
		super.setRightColumnCaption(rightColumnCaption);
	}

	private I18NAwareFieldSupport<Object> getI18NAwareFieldSupport() {

		if (i18NAwareFieldSupport == null) {
			i18NAwareFieldSupport = new I18NAwareFieldSupport<Object>(this);
		}

		return i18NAwareFieldSupport;
	}

	private I18NCaptionSupport getLeftColumnCaptionSupport() {

		if (leftColumnCaptionSupport == null) {
			leftColumnCaptionSupport = new I18NCaptionSupport(new CaptionContainer() {
				@Override
				public void setRealCaption(String caption) {
					setLeftColumnRealCaption(caption);
				}
			});
		}

		return leftColumnCaptionSupport;
	}

	private I18NCaptionSupport getRightColumnCaptionSupport() {

		if (rightColumnCaptionSupport == null) {
			rightColumnCaptionSupport = new I18NCaptionSupport(new CaptionContainer() {
				@Override
				public void setRealCaption(String caption) {
					setRightColumnRealCaption(caption);
				}
			});
		}

		return rightColumnCaptionSupport;
	}

	@Override
	public void setCaptionMessage(I18NExpression expression) {
		getI18NAwareFieldSupport().setCaptionMessage(expression);
	}

	@Override
	public void setDescriptionMessage(I18NExpression expression) {
		getI18NAwareFieldSupport().setDescriptionMessage(expression);
	}

	@Override
	public void setRealValue(Object value) {
		// TODO Auto-generated method stub
		super.setValue(value);
	}

	@Override
	public void setValueMessage(I18NExpression expression) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setValueMessage(String valueKey, Object... valueParams) {
		// TODO Auto-generated method stub

	}

}
