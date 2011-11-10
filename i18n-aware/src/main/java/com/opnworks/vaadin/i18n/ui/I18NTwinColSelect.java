package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareField;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.support.I18NAwareFieldSupport;
import com.opnworks.vaadin.i18n.support.I18NCaptionSupport;
import com.opnworks.vaadin.i18n.support.I18NCaptionSupport.CaptionContainer;
import com.vaadin.data.Container;
import com.vaadin.ui.TwinColSelect;

/**
 * The I18NTwinColSelect
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NTwinColSelect extends TwinColSelect implements I18NAwareField {

	private static final long serialVersionUID = -4627014612484874917L;

	private I18NAwareFieldSupport i18NAwareFieldSupport = new I18NAwareFieldSupport(
			this);

	private I18NCaptionSupport leftColumnCaptionSupport = new I18NCaptionSupport(
			new CaptionContainer() {
				@Override
				public void setCaption(String caption) {
					setLeftColumnCaption(caption);
				}
			});

	private I18NCaptionSupport rightColumnCaptionSupport = new I18NCaptionSupport(
			new CaptionContainer() {
				@Override
				public void setCaption(String caption) {
					setRightColumnCaption(caption);
				}
			});

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
		i18NAwareFieldSupport.setCaptionMessage(captionKey);
	}

	/**
	 * Construct a I18NTwinColSelect
	 * 
	 * @param captionKey
	 * @param dataSource
	 */
	public I18NTwinColSelect(@I18NAwareMessage String captionKey, Container dataSource) {
		super(captionKey, dataSource);
		i18NAwareFieldSupport.setCaptionMessage(captionKey);
	}

	public void setLeftColumnCaptionKey(String leftColumnCaptionKey, Object... leftColumnCaptionParams) {
		leftColumnCaptionSupport.setCaptionMessage(leftColumnCaptionKey, leftColumnCaptionParams);
	}

	public void setRightColumnCaptionKey(String rightColumnCaptionKey, Object... rightColumnCaptionParams) {
		rightColumnCaptionSupport.setCaptionMessage(rightColumnCaptionKey, rightColumnCaptionParams);
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
		leftColumnCaptionSupport.i18NUpdate(i18N);
		rightColumnCaptionSupport.i18NUpdate(i18N);
	}
}
