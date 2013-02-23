package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareCaption;
import com.opnworks.vaadin.i18n.I18NAwareComponent;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NAwareValue;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.processor.GenerateInstantiateSubclassAspect;
import com.opnworks.vaadin.i18n.support.I18NAwareComponentCaptionSupport;
import com.opnworks.vaadin.i18n.support.I18NAwareValueSupport;
import com.opnworks.vaadin.i18n.support.I18NAwareValueSupport.ValueContainer;
import com.vaadin.data.Property;
import com.vaadin.ui.Label;

/**
 * The I18NLabel
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
@GenerateInstantiateSubclassAspect
@SuppressWarnings({ "unchecked", "serial" })
public class I18NLabel extends Label implements I18NAwareComponent, I18NAwareCaption, I18NAwareValue, ValueContainer {

	private I18NAwareComponentCaptionSupport captionSupport = new I18NAwareComponentCaptionSupport(this);

	private I18NAwareValueSupport i18NDescriptionSupport = new I18NAwareValueSupport(new ValueContainer() {
		@Override
		public void setValue(String value) {
			setDescription(value);

		}
	});

	private I18NAwareValueSupport i18NAwareValueSupport;

	/**
	 * Creates an empty i18n I18NLabel.
	 */
	public I18NLabel() {
		super();
	}

	/**
	 * Creates a new instance of I18NLabel with text-contents read from given datasource.
	 * 
	 * @param contentSource
	 */
	public I18NLabel(Property contentSource) {
		super(contentSource);
	}

	/**
	 * Creates a new instance of I18NLabel with text-contents read from given datasource.
	 * 
	 * @param contentSource
	 * @param contentMode
	 */
	public I18NLabel(Property contentSource, int contentMode) {
		super(contentSource, contentMode);
	}

	/**
	 * Creates an i18n I18NLabel with text-contents.
	 * 
	 * @param content
	 */
	public I18NLabel(@I18NAwareMessage String contentKey) {		
		super();
		setCaptionMessage(contentKey);
	}

	/**
	 * Creates a new instance of I18NLabel with text-contents.
	 * 
	 * @param content
	 * @param contentMode
	 */
	public I18NLabel(@I18NAwareMessage String contentKey, int contentMode) {
		super();
		super.setCaption(contentKey);
		super.setContentMode(contentMode);
		setCaptionMessage(contentKey);
	}

	@Override
	public void i18NUpdate(I18NService i18N) {

		captionSupport.i18NUpdate(i18N);

		i18NDescriptionSupport.i18NUpdate(i18N);

		if (i18NAwareValueSupport != null) {
			i18NAwareValueSupport.i18NUpdate(i18N);
		}
	}

	@Override
	public void setCaption(@I18NAwareMessage String captionKey) {
		setCaptionMessage(captionKey);
	}

	@Override
	public void setCaptionMessage(@I18NAwareMessage String captionKey, Object... params) {
		captionSupport.setCaptionMessage(captionKey, params);
	}

	@Override
	public void setDescription(@I18NAwareMessage String descriptionKey) {
		setDescriptionMessage(descriptionKey);
	}

	@Override
	public void setDescriptionMessage(@I18NAwareMessage String descriptionKey, Object... descriptionParams) {
		i18NDescriptionSupport.setValueMessage(descriptionKey, descriptionParams);
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
	public void setValue(String value) {
		super.setValue(value);
	}

	@Override
	public void setValueMessage(@I18NAwareMessage String textKey, Object... params) {

		createValueSupport();

		i18NAwareValueSupport.setValueMessage(textKey, params);

		setValue(textKey);
	}

	private void createValueSupport() {

		if (i18NAwareValueSupport == null) {
			i18NAwareValueSupport = new I18NAwareValueSupport(this);
		}
	}
}
