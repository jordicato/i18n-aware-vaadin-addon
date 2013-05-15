package com.opnworks.vaadin.i18n.ui;

import java.util.Collection;

import com.opnworks.vaadin.i18n.I18NAwareFieldExpression;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.I18NServiceSingleton;
import com.opnworks.vaadin.i18n.processor.GenerateInstantiateSubclassAspect;
import com.opnworks.vaadin.i18n.support.I18NAwareFieldSupport;
import com.opnworks.vaadin.i18n.support.I18NExpression;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.ui.ComboBox;

/**
 * The I18NComboBox
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
@GenerateInstantiateSubclassAspect
@SuppressWarnings("serial")
public class I18NComboBox extends ComboBox implements I18NAwareFieldExpression<Object>{

	private I18NAwareFieldSupport<Object> i18NAwareFieldSupport;

	/**
	 * Creates a new i18n ComboBox.
	 */
	public I18NComboBox() {
		super();
	}

	/**
	 * Creates a new i18n ComboBox with a caption message key.
	 */
	public I18NComboBox(@I18NAwareMessage String captionKey) {
		super(captionKey);
		setCaptionMessage(captionKey);
	}

	public I18NComboBox(@I18NAwareMessage String captionKey, Collection<?> options) {
		super(captionKey, options);
		setCaptionMessage(captionKey);
	}

	public I18NComboBox(@I18NAwareMessage String captionKey, Container dataSource) {
		super(captionKey, dataSource);
		setCaptionMessage(captionKey);
	}

	public I18NComboBox(I18NExpression captionExpression) {
		super(captionExpression.getStringFinal());		
		setCaptionMessage(captionExpression.getObjectlist());
	}

	public I18NComboBox(I18NExpression captionExpression, Collection<?> options) {
		super(captionExpression.getStringFinal(), options);		
		setCaptionMessage(captionExpression.getObjectlist());
	}

	public I18NComboBox(I18NExpression captionExpression, Container dataSource) {
		super(captionExpression.getStringFinal(), dataSource);		
		setCaptionMessage(captionExpression.getObjectlist());
	}


	@Override
	public void i18NUpdate(I18NService i18N) {
		getI18NAwareFieldSupport().i18NUpdate(i18N);
	}

	@Override
	public void setCaption(@I18NAwareMessage String captionKey) {
		setCaptionMessage(captionKey);
	}

	public void setCaption(Object... expression) {
		setCaptionMessage(expression);
	}

	@Override
	public void setCaptionMessage(@I18NAwareMessage String captionKey, Object... params) {
		getI18NAwareFieldSupport().setCaptionMessage(captionKey, params);
	}

	@Override
	public void setDescription(@I18NAwareMessage String descriptionKey) {
		setDescriptionMessage(descriptionKey);
	}

	public void setDescription(Object... expression) {
		setDescriptionMessage(expression);
	}

	@Override
	public void setDescriptionMessage(@I18NAwareMessage String descriptionKey, Object... descriptionParams) {
		getI18NAwareFieldSupport().setDescriptionMessage(descriptionKey, descriptionParams);
	}

	@Override
	public Item addItem(Object itemId) {
		return super.addItem(itemId);
	}

	@Override
	public void setItemCaption(Object itemId, @I18NAwareMessage String itemKey) {
		super.setItemCaption(
				itemId,
				I18NServiceSingleton.getInstance().getI18NServive()
						.getMessage(I18NServiceSingleton.getInstance().getI18NServive().getLocale(), itemKey));
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
	public void setCaptionMessage(Object... expression) {
		getI18NAwareFieldSupport().setCaptionMessage(expression);
	}

	@Override
	public void setDescriptionMessage(Object... expression) {
		getI18NAwareFieldSupport().setDescriptionMessage(expression);
	}
	
	@Override
	public void setRealValue(Object value) {
		super.setValue(value);
	}

	@Override
	public void setValueMessage(Object... expression) {
		getI18NAwareFieldSupport().setValueMessage(expression);
	}

	@Override
	public void setValueMessage(String valueKey, Object... valueParams) {
		getI18NAwareFieldSupport().setValueMessage(valueKey, valueParams);		
	}

}
