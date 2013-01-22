package com.opnworks.vaadin.i18n.ui;

import java.util.Date;

import com.opnworks.vaadin.i18n.I18NAwareField;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.processor.GenerateInstantiateSubclassAspect;
import com.opnworks.vaadin.i18n.support.I18NAwareFieldSupport;
import com.opnworks.vaadin.i18n.support.I18NAwareValueSupport;
import com.opnworks.vaadin.i18n.support.I18NAwareValueSupport.ValueContainer;
import com.vaadin.data.Property;
import com.vaadin.ui.PopupDateField;

/**
 * The I18NPopupDateField
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
@GenerateInstantiateSubclassAspect
@SuppressWarnings("serial")
public class I18NPopupDateField extends PopupDateField implements I18NAwareField {

	private I18NAwareFieldSupport i18NAwareFieldSupport;

	private I18NAwareValueSupport i18NInputPromptSupport;

	/**
	 * Constructs an empty i18n <code>PopupDateField</code> with no caption.
	 */
	public I18NPopupDateField() {
		super();
	}

	/**
	 * Constructs an i18n <code>PopupDateField</code> with a dataSource.
	 * 
	 * @param dataSource
	 */
	public I18NPopupDateField(Property dataSource) {
		super(dataSource);
	}

	/**
	 * Constructs an empty i18n <code>PopupDateField</code> with caption message key.
	 * 
	 * @param captionKey
	 *            the caption message key of the PopupDateField.
	 */
	public I18NPopupDateField(@I18NAwareMessage String captionKey) {
		super(captionKey);
		getI18NAwareFieldSupport().setCaptionMessage(captionKey);
	}

	/**
	 * Constructs an i18n <code>PopupDateField</code> with caption message key and initial value.
	 * 
	 * @param captionKey
	 *            the caption message key of the datefield.
	 * @param value
	 */
	public I18NPopupDateField(@I18NAwareMessage String captionKey, Date value) {
		super(captionKey, value);
		getI18NAwareFieldSupport().setCaptionMessage(captionKey);
	}

	/**
	 * Constructs an i18n <code>PopupDateField</code> with caption message key and dataSource.
	 * 
	 * @param captionKey
	 *            the caption message key of the PopupDateField.
	 * @param dataSource
	 */
	public I18NPopupDateField(@I18NAwareMessage String captionKey, Property dataSource) {
		super(captionKey, dataSource);
		getI18NAwareFieldSupport().setCaptionMessage(captionKey);
	}

	@Override
	public void i18NUpdate(I18NService i18N) {

		setLocale(i18N.getLocale());

		getI18NAwareFieldSupport().i18NUpdate(i18N);
		getI18NInputPromptSupport().i18NUpdate(i18N);
	}

	@Override
	public void setCaption(String captionKey) {
		setCaptionMessage(captionKey);
	}

	@Override
	public void setCaptionMessage(@I18NAwareMessage String captionKey, Object... params) {
		getI18NAwareFieldSupport().setCaptionMessage(captionKey, params);
	}

	@Override
	public void setDescription(String descriptionKey) {
		setDescriptionMessage(descriptionKey);
	}

	@Override
	public void setDescriptionMessage(@I18NAwareMessage String descriptionKey, Object... descriptionParams) {
		getI18NAwareFieldSupport().setDescriptionMessage(descriptionKey, descriptionParams);
	}

	public void setInputPromptKey(String inputPromptKey, Object... inputPromptParams) {
		getI18NInputPromptSupport().setValueMessage(inputPromptKey, inputPromptParams);
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
	public void setRequiredError(String requiredErrorKey) {
		setRequiredErrorMessage(requiredErrorKey);
	}

	@Override
	public void setRequiredErrorMessage(@I18NAwareMessage String requiredErrorKey, Object... requiredErrorParams) {
		getI18NAwareFieldSupport().setRequiredErrorMessage(requiredErrorKey, requiredErrorParams);
	}

	private I18NAwareFieldSupport getI18NAwareFieldSupport() {

		if (i18NAwareFieldSupport == null) {
			i18NAwareFieldSupport = new I18NAwareFieldSupport(this);
		}

		return i18NAwareFieldSupport;
	}

	private I18NAwareValueSupport getI18NInputPromptSupport() {

		if (i18NInputPromptSupport == null) {
			i18NInputPromptSupport = new I18NAwareValueSupport(new ValueContainer() {
				@Override
				public void setValue(String value) {
					setInputPrompt(value);
				}
			});
		}

		return i18NInputPromptSupport;
	}
}
