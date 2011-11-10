package com.opnworks.vaadin.i18n.ui;

import java.util.Date;

import com.opnworks.vaadin.i18n.I18NAwareField;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NService;
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
public class I18NPopupDateField extends PopupDateField implements
		I18NAwareField {

	private static final long serialVersionUID = 8283053228212397120L;

	private I18NAwareFieldSupport i18NAwareFieldSupport = new I18NAwareFieldSupport(
			this);

	private I18NAwareValueSupport i18NInputPromptSupport = new I18NAwareValueSupport(
			new ValueContainer() {
				@Override
				public void setValue(String value) {
					setInputPrompt(value);
				}
			});

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
	public I18NPopupDateField(Property dataSource)
			throws IllegalArgumentException {
		super(dataSource);
	}

	/**
	 * Constructs an i18n <code>PopupDateField</code> with caption message key
	 * and initial value.
	 * 
	 * @param captionKey
	 *            the caption message key of the datefield.
	 * @param value
	 */
	public I18NPopupDateField(@I18NAwareMessage String captionKey, Date value) {
		super(captionKey, value);
		i18NAwareFieldSupport.setCaptionMessage(captionKey);
	}

	/**
	 * Constructs an i18n <code>PopupDateField</code> with caption message key
	 * and dataSource.
	 * 
	 * @param captionKey
	 *            the caption message key of the PopupDateField.
	 * @param dataSource
	 */
	public I18NPopupDateField(@I18NAwareMessage String captionKey, Property dataSource) {
		super(captionKey, dataSource);
		i18NAwareFieldSupport.setCaptionMessage(captionKey);
	}

	/**
	 * Constructs an empty i18n <code>PopupDateField</code> with caption message
	 * key.
	 * 
	 * @param captionKey
	 *            the caption message key of the PopupDateField.
	 */
	public I18NPopupDateField(@I18NAwareMessage String captionKey) {
		super(captionKey);
		i18NAwareFieldSupport.setCaptionMessage(captionKey);
	}

	public void setInputPromptKey(String inputPromptKey, Object... inputPromptParams) {
		i18NInputPromptSupport.setValueMessage(inputPromptKey, inputPromptParams);
	}

	@Override
	public void setRequiredErrorMessage(@I18NAwareMessage String requiredErrorKey, Object... requiredErrorParams) {
		i18NAwareFieldSupport.setRequiredErrorMessage(requiredErrorKey, requiredErrorParams);
	}

	@Override
	public void setCaptionMessage(@I18NAwareMessage String captionKey, Object... params) {
		i18NAwareFieldSupport.setCaptionMessage(captionKey, params);
	}

	@Override
	public void setDescriptionMessage(@I18NAwareMessage String descriptionKey,
			Object... descriptionParams) {
		i18NAwareFieldSupport.setDescriptionMessage(descriptionKey, descriptionParams);
	}

	@Override
	public void i18NUpdate(I18NService i18N) {

		setLocale(i18N.getLocale());

		i18NAwareFieldSupport.i18NUpdate(i18N);
		i18NInputPromptSupport.i18NUpdate(i18N);
	}

}
