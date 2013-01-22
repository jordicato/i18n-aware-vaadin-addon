package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareField;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.processor.GenerateInstantiateSubclassAspect;
import com.opnworks.vaadin.i18n.support.I18NAwareFieldSupport;
import com.vaadin.data.Property;
import com.vaadin.ui.CheckBox;

/**
 * The I18NCheckBox
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
@SuppressWarnings("unchecked")
@GenerateInstantiateSubclassAspect
public class I18NCheckBox extends CheckBox implements I18NAwareField {

	private static final long serialVersionUID = 6357950198553382989L;

	private I18NAwareFieldSupport i18NAwareFieldSupport;

	/**
	 * Creates a new i18n switch button.
	 */
	public I18NCheckBox() {
		super();
	}

	/**
	 * Creates a new i18n switch button with a caption message key.
	 * 
	 * The value of the switch button is always false and they are immediate by default.
	 * 
	 * @param captionKey
	 *            the Button caption message key.
	 */
	public I18NCheckBox(@I18NAwareMessage String captionKey) {
		super(captionKey);
		getI18NAwareFieldSupport().setCaptionMessage(captionKey);
	}

	/**
	 * Creates a new switch button with a caption and a set initial state.
	 * 
	 * @param captionKey
	 *            the caption message key of the switch button
	 * @param initialState
	 *            the initial state of the switch button
	 */
	public I18NCheckBox(@I18NAwareMessage String captionKey, boolean initialState) {
		super(captionKey, initialState);
		getI18NAwareFieldSupport().setCaptionMessage(captionKey);
	}

	/**
	 * Creates a new i18n switch button with a caption message key and a click listener.
	 * 
	 * @param captionKey
	 *            the caption message key of the switch button
	 * @param listener
	 *            the click listener
	 */
	public I18NCheckBox(@I18NAwareMessage String captionKey, ClickListener listener) {
		super(captionKey, listener);
		getI18NAwareFieldSupport().setCaptionMessage(captionKey);
	}

	/**
	 * Convenience method for creating a new switch button with a method listening button clicks. Using this method is discouraged because it cannot
	 * be checked during compilation. Use {@link #addListener(Class, Object, _Method)} or {@link #addListener(com.vaadin.ui.Component.Listener)}
	 * instead. The method must have either no parameters, or only one parameter of Button.ClickEvent type.
	 * 
	 * @param captionKey
	 *            the caption message key of the switch button
	 * @param target
	 *            the Object having the method for listening button clicks.
	 * @param methodName
	 *            the name of the method in target object, that receives button click events.
	 */
	public I18NCheckBox(@I18NAwareMessage String captionKey, Object target, String methodName) {
		super(captionKey, target, methodName);
		getI18NAwareFieldSupport().setCaptionMessage(captionKey);
	}

	/**
	 * Creates a new switch button that is connected to a boolean property.
	 * 
	 * @param captionKey
	 *            the caption message key of the switch button
	 * @param state
	 *            the Initial state of the switch-button.
	 * @param dataSource
	 */
	public I18NCheckBox(@I18NAwareMessage String captionKey, Property dataSource) {
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
