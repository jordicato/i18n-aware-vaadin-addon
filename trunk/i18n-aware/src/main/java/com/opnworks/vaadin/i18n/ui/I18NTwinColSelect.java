package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareField;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.processor.GenerateInstantiateSubclassAspect;
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
@GenerateInstantiateSubclassAspect
public class I18NTwinColSelect extends TwinColSelect implements I18NAwareField {

	private static final long serialVersionUID = -4627014612484874917L;

	private I18NAwareFieldSupport i18NAwareFieldSupport;

	private I18NCaptionSupport leftColumnCaptionSupport;

	private I18NCaptionSupport rightColumnCaptionSupport;

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
		getI18NAwareFieldSupport().setCaptionMessage(captionKey);
	}

	/**
	 * Construct a I18NTwinColSelect
	 * 
	 * @param captionKey
	 * @param dataSource
	 */
	public I18NTwinColSelect(@I18NAwareMessage String captionKey, Container dataSource) {
		super(captionKey, dataSource);
		getI18NAwareFieldSupport().setCaptionMessage(captionKey);
	}

	@Override
	public void i18NUpdate(I18NService i18N) {
		getI18NAwareFieldSupport().i18NUpdate(i18N);
		getLeftColumnCaptionSupport().i18NUpdate(i18N);
		getRightColumnCaptionSupport().i18NUpdate(i18N);
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
	public void setLeftColumnCaption(String leftColumnCaptionKey) {
		setLeftColumnCaptionKey(leftColumnCaptionKey);
	}

	public void setLeftColumnCaptionKey(String leftColumnCaptionKey, Object... leftColumnCaptionParams) {
		getLeftColumnCaptionSupport().setCaptionMessage(leftColumnCaptionKey, leftColumnCaptionParams);
	}

	public void setLeftColumnRealCaption(String leftColumnCaption) {
		super.setLeftColumnCaption(leftColumnCaption);
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

	@Override
	public void setRightColumnCaption(String rightColumnCaption) {
		setRightColumnRealCaption(rightColumnCaption);
	}

	public void setRightColumnCaptionKey(String rightColumnCaptionKey, Object... rightColumnCaptionParams) {
		getRightColumnCaptionSupport().setCaptionMessage(rightColumnCaptionKey, rightColumnCaptionParams);
	}

	public void setRightColumnRealCaption(String rightColumnCaption) {
		super.setRightColumnCaption(rightColumnCaption);
	}

	private I18NAwareFieldSupport getI18NAwareFieldSupport() {

		if (i18NAwareFieldSupport == null) {
			i18NAwareFieldSupport = new I18NAwareFieldSupport(this);
		}

		return i18NAwareFieldSupport;
	}

	private I18NCaptionSupport getLeftColumnCaptionSupport() {

		if (leftColumnCaptionSupport == null) {
			leftColumnCaptionSupport = new I18NCaptionSupport(new CaptionContainer() {
				@Override
				public void setRealCaption(String caption) {
					setLeftColumnRealCaption(caption);
				}
			});
		}

		return leftColumnCaptionSupport;
	}

	private I18NCaptionSupport getRightColumnCaptionSupport() {

		if (rightColumnCaptionSupport == null) {
			rightColumnCaptionSupport = new I18NCaptionSupport(new CaptionContainer() {
				@Override
				public void setRealCaption(String caption) {
					setRightColumnRealCaption(caption);
				}
			});
		}

		return rightColumnCaptionSupport;
	}
}
