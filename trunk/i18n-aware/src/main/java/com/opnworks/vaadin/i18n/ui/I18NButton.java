package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareCaption;
import com.opnworks.vaadin.i18n.I18NAwareComponent;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.support.I18NAwareComponentCaptionSupport;
import com.vaadin.data.Property;
import com.vaadin.ui.Button;

/**
 * The I18N Button
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
@SuppressWarnings("unchecked")
public class I18NButton extends Button implements I18NAwareComponent, I18NAwareCaption {

	private static final long serialVersionUID = 6357950198553382989L;

	private I18NAwareComponentCaptionSupport i18NAwareComponentCaptionSupport = new I18NAwareComponentCaptionSupport(this);

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
	public I18NButton(String captionKey) {
		super(captionKey);
		i18NAwareComponentCaptionSupport.setCaptionKey(captionKey);
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
	public I18NButton(String captionKey, ClickListener listener) {
		super(captionKey, listener);
		i18NAwareComponentCaptionSupport.setCaptionKey(captionKey);
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
	public I18NButton(String captionKey, Object target, String methodName) {
		super(captionKey, target, methodName);
		i18NAwareComponentCaptionSupport.setCaptionKey(captionKey);
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
	public I18NButton(String captionKey, boolean initialState) {
		super(captionKey, initialState);
		i18NAwareComponentCaptionSupport.setCaptionKey(captionKey);
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
	public I18NButton(String captionKey, Property dataSource) {
		super(captionKey, dataSource);
		i18NAwareComponentCaptionSupport.setCaptionKey(captionKey);
	}

	@Override
	public void setCaptionKey(String captionKey) {
		i18NAwareComponentCaptionSupport.setCaptionKey(captionKey);
	}

	@Override
	public void setCaptionParams(Object... params) {
		i18NAwareComponentCaptionSupport.setCaptionParams(params);
	}

	@Override
	public void i18NUpdate(I18NService i18N) {
		i18NAwareComponentCaptionSupport.i18NUpdate(i18N);
	}

}
