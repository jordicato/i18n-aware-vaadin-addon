package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareCaption;
import com.opnworks.vaadin.i18n.I18NAwareComponent;
import com.opnworks.vaadin.i18n.I18NAwareValue;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.support.I18NAwareComponentCaptionSupport;
import com.vaadin.ui.Label;

/**
 * The I18NLabel
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
@SuppressWarnings("unchecked")
public class I18NLabel extends Label implements I18NAwareComponent,
		I18NAwareCaption, I18NAwareValue {

	private static final long serialVersionUID = 2379556692292586769L;

	private I18NAwareComponentCaptionSupport captionSupport = new I18NAwareComponentCaptionSupport(
			this);

	private String valueKey;
	private Object[] valueParams;

    /**
     * Creates an empty i18n Label.
     */
	public I18NLabel() {
		super();
	}

    /**
     * Creates an i18n Label with text-contents.
     * 
     * @param content
     */
	public I18NLabel(String content) {
		super(content);
	}

	@Override
	public void setCaptionKey(String captionKey) {
		captionSupport.setCaptionKey(captionKey);
	}

	@Override
	public void setCaptionParams(Object... params) {
		captionSupport.setCaptionParams(params);
	}

	@Override
	public void setValueKey(String textKey) {
		this.valueKey = textKey;
		setValue(formatMessage(textKey));
	}

	@Override
	public void setValueParams(Object... params) {
		this.valueParams = params;
	}

	@Override
	public void i18NUpdate(I18NService i18N) {

		captionSupport.updateLabels(i18N);

		if (valueKey != null) {
			String message = i18N.getMessage(valueKey, valueParams);
			setValue(formatMessage(message));
		}
	}

	protected String formatMessage(String text) {
		return text;
	}
}
