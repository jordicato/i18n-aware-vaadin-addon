package com.opnworks.vaadin.i18n.support;

import com.opnworks.vaadin.i18n.I18NService;

/**
 * The I18NCaptionSupport
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NCaptionSupport {

	protected String captionKey;
	protected Object[] captionParams;

	public I18NCaptionSupport() {
	}

	public I18NCaptionSupport(String captionKey) {
		this(captionKey, null);
	}

	public I18NCaptionSupport(String captionKey, Object[] captionParams) {
		this.captionKey = captionKey;
		this.captionParams = captionParams;
	}

	public void setCaptionKey(String captionKey) {
		this.captionKey = captionKey;
	}

	public String getCaptionKey() {
		return captionKey;
	}

	public Object[] getCaptionParams() {
		return captionParams;
	}

	public void setCaptionParams(Object... params) {
		this.captionParams = params;
	}

	public String getCaption(I18NService i18N) {
		return i18N.getMessage(captionKey, captionParams);
	}
}
