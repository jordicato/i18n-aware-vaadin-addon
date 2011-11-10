package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareField;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.support.I18NAwareFieldSupport;
import com.vaadin.data.Property;
import com.vaadin.ui.RichTextArea;

/**
 * The I18N RichTextArea
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
@SuppressWarnings("unchecked")
public class I18NRichTextArea extends RichTextArea implements I18NAwareField {

	private static final long serialVersionUID = 8084707186486319041L;

	private I18NAwareFieldSupport i18NAwareFieldSupport = new I18NAwareFieldSupport(
			this);

	/**
	 * Constructs an empty <code>RichTextArea</code> with no caption.
	 */
	public I18NRichTextArea() {
		super();
	}

	/**
	 * 
	 * Constructs an empty <code>RichTextArea</code> with the given caption.
	 * 
	 * @param captionKey
	 *            the caption key for the editor.
	 */
	public I18NRichTextArea(@I18NAwareMessage String captionKey) {
		super(captionKey);
		i18NAwareFieldSupport.setCaptionMessage(captionKey);
	}

	/**
	 * Constructs a new <code>RichTextArea</code> that's bound to the specified
	 * <code>Property</code> and has no caption.
	 * 
	 * @param dataSource
	 *            the data source for the editor value
	 */
	public I18NRichTextArea(Property dataSource) {
		super(dataSource);
	}

	/**
	 * Constructs a new <code>RichTextArea</code> that's bound to the specified
	 * <code>Property</code> and has the given caption.
	 * 
	 * @param captionKey
	 *            the caption key for the editor.
	 * @param dataSource
	 *            the data source for the editor value
	 */
	public I18NRichTextArea(@I18NAwareMessage String captionKey, Property dataSource) {
		super(captionKey, dataSource);
		i18NAwareFieldSupport.setCaptionMessage(captionKey);
	}

	/**
	 * Constructs a new <code>RichTextArea</code> with the given caption and
	 * initial text contents.
	 * 
	 * @param captionKey
	 *            the caption key for the editor.
	 * @param value
	 *            the initial text content of the editor.
	 */
	public I18NRichTextArea(@I18NAwareMessage String captionKey, String value) {
		super(captionKey, value);
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
