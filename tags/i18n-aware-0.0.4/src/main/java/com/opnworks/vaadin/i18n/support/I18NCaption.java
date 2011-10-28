package com.opnworks.vaadin.i18n.support;

import com.opnworks.vaadin.i18n.I18NAwareCaption;
import com.opnworks.vaadin.i18n.I18NService;

/**
 * The I18NCaption
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NCaption extends I18NCaptionSupport implements I18NAwareCaption {

	private String caption;

	public I18NCaption() {
	}

	public I18NCaption(String captionKey) {
		super(captionKey);
	}

	public I18NCaption(String captionKey, Object[] captionParams) {
		super(captionKey, captionParams);
	}

	public void i18NUpdate(I18NService i18N) {
		caption = getCaption(i18N);
	}

	@Override
	public String toString() {
		return caption;
	}

}
