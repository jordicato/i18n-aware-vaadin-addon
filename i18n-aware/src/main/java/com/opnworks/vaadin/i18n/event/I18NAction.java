package com.opnworks.vaadin.i18n.event;

import com.opnworks.vaadin.i18n.I18NAwareCaption;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.support.I18NCaptionSupport;
import com.vaadin.event.Action;
import com.vaadin.terminal.Resource;
import com.opnworks.vaadin.i18n.support.I18NCaptionSupport.CaptionContainer;

/**
 * The I18NAware
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NAction extends Action implements I18NAwareCaption, CaptionContainer {

	private static final long serialVersionUID = -4241515863916987115L;

	private I18NCaptionSupport i18NCaptionSupport = new I18NCaptionSupport(this);

	/**
	 * Constructs a new i18n action with the given caption message key.
	 * 
	 * @param captionKey
	 *            the caption message key for the new action.
	 */
	public I18NAction(String captionKey) {
		super(captionKey);
		i18NCaptionSupport.setCaptionKey(captionKey);
	}

	/**
	 * Constructs a new i18n action with the given caption message key string
	 * and icon.
	 * 
	 * @param captionKey
	 *            the caption message key for the new action.
	 * @param icon
	 *            the icon for the new action.
	 */
	public I18NAction(String captionKey, Resource icon) {
		super(captionKey, icon);
		i18NCaptionSupport.setCaptionKey(captionKey);
	}

	@Override
	public void setCaptionKey(String captionKey) {
		i18NCaptionSupport.setCaptionKey(captionKey);
	}

	@Override
	public void setCaptionParams(Object... captionParams) {
		i18NCaptionSupport.setCaptionParams(captionParams);
	}

	@Override
	public void i18NUpdate(I18NService i18N) {
		i18NCaptionSupport.i18NUpdate(i18N);
	}
}
