package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareField;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.support.I18NAwareFieldSupport;
import com.vaadin.data.Property;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;

/**
 * The I18N Button
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
@SuppressWarnings("unchecked")
public class I18NButton extends Button implements I18NAwareField {

	private static final long serialVersionUID = 6357950198553382989L;

	private I18NAwareFieldSupport i18NAwareFieldSupport = new I18NAwareFieldSupport(
			this);

	/**
	 * Creates a new i18n push button. The value of the push button is false and
	 * it is immediate by default.
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
		i18NAwareFieldSupport.setCaptionMessage(captionKey);
	}

	/**
	 * Creates a new i18n push button with caption message key and click
	 * listener.
	 * 
	 * @param captionKey
	 *            the Button caption message key.
	 * @param listener
	 *            the Button click listener.
	 */
	public I18NButton( @I18NAwareMessage String captionKey, ClickListener listener) {
		super(captionKey, listener);
		i18NAwareFieldSupport.setCaptionMessage(captionKey);
	}

	/**
	 * Creates a new push button with a method listening button clicks. Using
	 * this method is discouraged because it cannot be checked during
	 * compilation. Use
	 * {@link #Button(String, com.vaadin.ui.Button.ClickListener)} instead. The
	 * method must have either no parameters, or only one parameter of
	 * Button.ClickEvent type.
	 * 
	 * @param captionKey
	 *            key for the Button caption
	 * @param target
	 *            the Object having the method for listening button clicks.
	 * @param methodName
	 *            the name of the method in target object, that receives button
	 *            click events.
	 */
	public I18NButton(@I18NAwareMessage String captionKey, Object target, String methodName) {
		super(captionKey, target, methodName);
		i18NAwareFieldSupport.setCaptionMessage(captionKey);
	}

	/**
	 * Creates a new switch button with initial value.
	 * 
	 * @param captionKey
	 *            key for the Button caption
	 * @param state
	 *            the Initial state of the switch-button.
	 * @param initialState
	 * @deprecated use {@link CheckBox} instead of Button in "switchmode"
	 */
	@Deprecated
	public I18NButton(@I18NAwareMessage String captionKey, boolean initialState) {
		super(captionKey, initialState);
		i18NAwareFieldSupport.setCaptionMessage(captionKey);
	}

	/**
	 * Creates a new switch button that is connected to a boolean property.
	 * 
	 * @param captionKey
	 *            key for the Button caption
	 * @param state
	 *            the Initial state of the switch-button.
	 * @param dataSource
	 * @deprecated use {@link CheckBox} instead of Button in "switchmode"
	 */
	@Deprecated
	public I18NButton(@I18NAwareMessage String captionKey, Property dataSource) {
		super(captionKey, dataSource);
		i18NAwareFieldSupport.setCaptionMessage(captionKey);
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
		i18NAwareFieldSupport.i18NUpdate(i18N);
	}

}
