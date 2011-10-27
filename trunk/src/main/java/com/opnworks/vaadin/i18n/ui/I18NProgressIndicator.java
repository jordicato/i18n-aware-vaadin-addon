package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareCaption;
import com.opnworks.vaadin.i18n.I18NAwareComponent;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.support.I18NAwareComponentCaptionSupport;
import com.vaadin.ui.ProgressIndicator;

/**
 * The I18NProgressIndicator
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NProgressIndicator extends ProgressIndicator implements
		I18NAwareComponent, I18NAwareCaption {

	private static final long serialVersionUID = 1516381605599417078L;

	private I18NAwareComponentCaptionSupport i18NAwareComponentCaptionSupport = new I18NAwareComponentCaptionSupport(
			this);

    /**
     * Creates an a new i18n ProgressIndicator.
     */
	public I18NProgressIndicator() {
		super();
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
		i18NAwareComponentCaptionSupport.updateLabels(i18N);
	}

}
