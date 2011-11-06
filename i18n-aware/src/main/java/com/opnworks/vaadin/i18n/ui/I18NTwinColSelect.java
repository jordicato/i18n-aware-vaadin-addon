package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareField;
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
	public I18NTwinColSelect(String captionKey) {
		super(captionKey);
		i18NAwareFieldSupport.setCaptionKey(captionKey);
	}

	/**
	 * Construct a I18NTwinColSelect
	 * 
	 * @param captionKey
	 * @param dataSource
	 */
	public I18NTwinColSelect(String captionKey, Container dataSource) {
		super(captionKey, dataSource);
		i18NAwareFieldSupport.setCaptionKey(captionKey);
	}

	public void setLeftColumnCaptionKey(String leftColumnCaptionKey) {
		leftColumnCaptionSupport.setCaptionKey(leftColumnCaptionKey);
	}

	public void setLeftColumnCaptionParams(Object... leftColumnCaptionParams) {
		leftColumnCaptionSupport.setCaptionParams(leftColumnCaptionParams);
	}

	public void setRightColumnCaptionKey(String rightColumnCaptionKey) {
		rightColumnCaptionSupport.setCaptionKey(rightColumnCaptionKey);
	}

	public void setRightColumnCaptionParams(Object... rightColumnCaptionParams) {
		rightColumnCaptionSupport.setCaptionParams(rightColumnCaptionParams);
	}

	@Override
	public void setCaptionKey(String captionKey) {
		i18NAwareFieldSupport.setCaptionKey(captionKey);
	}

	@Override
	public void setCaptionParams(Object... params) {
		i18NAwareFieldSupport.setCaptionParams(params);
	}

	@Override
	public void setRequiredErrorKey(String requiredErrorKey) {
		i18NAwareFieldSupport.setRequiredErrorKey(requiredErrorKey);
	}

	@Override
	public void setRequiredErrorParams(Object[] requiredErrorParams) {
		i18NAwareFieldSupport.setRequiredErrorParams(requiredErrorParams);
	}

	@Override
	public void i18NUpdate(I18NService i18N) {
		i18NAwareFieldSupport.i18NUpdate(i18N);
		leftColumnCaptionSupport.i18NUpdate(i18N);
		rightColumnCaptionSupport.i18NUpdate(i18N);
	}
}
