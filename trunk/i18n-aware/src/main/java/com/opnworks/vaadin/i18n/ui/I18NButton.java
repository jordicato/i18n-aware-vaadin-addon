package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareField;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.processor.GenerateInstantiateSubclassAspect;
import com.opnworks.vaadin.i18n.support.I18NAwareFieldSupport;
import com.vaadin.data.Property;
import com.vaadin.ui.Button;

/**
 * The I18N Button
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
@GenerateInstantiateSubclassAspect
@SuppressWarnings({ "unchecked", "serial" })
public class I18NButton extends Button implements I18NAwareField {

	private I18NAwareFieldSupport i18NAwareFieldSupport;

	/**
	 * Creates a new i18n push button. The value of the push button is false and it is immediate by default.
	 */
	public I18NButton() {
		super();
	}

	/**
	 * Creates a new i18n push button with caption message key.
	 * 
	 * The value of the push button is false and it is immediate by default.
	 * 
	 * @param captionKey
	 *            the Button caption message key.
	 */
	public I18NButton(@I18NAwareMessage String captionKey) {
		super(captionKey);
		getI18NAwareFieldSupport().setCaptionMessage(captionKey);
	}

	/**
	 * Creates a new switch button with initial value.
	 * 
	 * @param captionKey
	 *            key for the Button caption
	 * @param state
	 *            the Initial state of the switch-button.
	 * @param initialState
	 * @deprecated use {@link _CheckBox} instead of Button in "switchmode"
	 */
	@Deprecated
	public I18NButton(@I18NAwareMessage String captionKey, boolean initialState) {
		super(captionKey, initialState);
		getI18NAwareFieldSupport().setCaptionMessage(captionKey);
	}

	/**
	 * Creates a new i18n push button with caption message key and click listener.
	 * 
	 * @param captionKey
	 *            the Button caption message key.
	 * @param listener
	 *            the Button click listener.
	 */
	public I18NButton(@I18NAwareMessage String captionKey, ClickListener listener) {
		super(captionKey, listener);
		getI18NAwareFieldSupport().setCaptionMessage(captionKey);
	}

	/**
	 * Creates a new push button with a method listening button clicks. Using this method is discouraged because it cannot be checked during
	 * compilation. Use {@link #Button(String, com.vaadin.ui.Button.ClickListener)} instead. The method must have either no parameters, or only one
	 * parameter of Button.ClickEvent type.
	 * 
	 * @param captionKey
	 *            key for the Button caption
	 * @param target
	 *            the Object having the method for listening button clicks.
	 * @param methodName
	 *            the name of the method in target object, that receives button click events.
	 */
	public I18NButton(@I18NAwareMessage String captionKey, Object target, String methodName) {
		super(captionKey, target, methodName);
		getI18NAwareFieldSupport().setCaptionMessage(captionKey);
	}

	/**
	 * Creates a new switch button that is connected to a boolean property.
	 * 
	 * @param captionKey
	 *            key for the Button caption
	 * @param state
	 *            the Initial state of the switch-button.
	 * @param dataSource
	 * @deprecated use {@link _CheckBox} instead of Button in "switchmode"
	 */
	@Deprecated
	public I18NButton(@I18NAwareMessage String captionKey, Property dataSource) {
		super(captionKey, dataSource);
		getI18NAwareFieldSupport().setCaptionMessage(captionKey);
	}

	@Override
	public void i18NUpdate(I18NService i18N) {
		getI18NAwareFieldSupport().i18NUpdate(i18N);
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

}
