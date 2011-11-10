package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareCaption;
import com.opnworks.vaadin.i18n.I18NAwareComponent;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NAwareValue;
import com.opnworks.vaadin.i18n.I18NService;
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
@SuppressWarnings("unchecked")
public class I18NLabel extends Label implements I18NAwareComponent,
		I18NAwareCaption, I18NAwareValue, ValueContainer {

	private static final long serialVersionUID = 2379556692292586769L;

	private I18NAwareComponentCaptionSupport captionSupport = new I18NAwareComponentCaptionSupport(
			this);
	
	private I18NAwareValueSupport i18NDescriptionSupport = new I18NAwareValueSupport(
			new ValueContainer() {
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
	 * Creates an i18n I18NLabel with text-contents.
	 * 
	 * @param content
	 */
	public I18NLabel(String content) {
		super(content);
	}

	/**
	 * Creates a new instance of I18NLabel with text-contents read from given
	 * datasource.
	 * 
	 * @param contentSource
	 */
	public I18NLabel(Property contentSource) {
		super(contentSource);
	}

	/**
	 * Creates a new instance of I18NLabel with text-contents.
	 * 
	 * @param content
	 * @param contentMode
	 */
	public I18NLabel(String content, int contentMode) {
		super(content, contentMode);
	}

	/**
	 * Creates a new instance of I18NLabel with text-contents read from given
	 * datasource.
	 * 
	 * @param contentSource
	 * @param contentMode
	 */
	public I18NLabel(Property contentSource, int contentMode) {
		super(contentSource, contentMode);
	}

	@Override
	public void setCaptionMessage(@I18NAwareMessage String captionKey, Object... params) {
		captionSupport.setCaptionMessage(captionKey, params);
	}

	@Override
	public void setDescriptionMessage(@I18NAwareMessage String descriptionKey,
			Object... descriptionParams) {
		i18NDescriptionSupport.setValueMessage(descriptionKey,
				descriptionParams);
	}

	@Override
	public void setValueMessage(@I18NAwareMessage String textKey, Object... params) {

		createValueSupport();

		i18NAwareValueSupport.setValueMessage(textKey, params);

		setValue(textKey);
	}

	@Override
	public void i18NUpdate(I18NService i18N) {

		captionSupport.i18NUpdate(i18N);
		
		i18NDescriptionSupport.i18NUpdate(i18N);

		if (i18NAwareValueSupport != null) {
			i18NAwareValueSupport.i18NUpdate(i18N);
		}
	}

	private void createValueSupport() {

		if (i18NAwareValueSupport == null) {
			i18NAwareValueSupport = new I18NAwareValueSupport(this);
		}
	}

	@Override
	public void setValue(String value) {
		super.setValue(value);
	}
}
