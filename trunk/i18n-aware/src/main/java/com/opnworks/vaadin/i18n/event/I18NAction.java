package com.opnworks.vaadin.i18n.event;

import java.util.Locale;

import com.opnworks.vaadin.i18n.I18NAwareCaption;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.processor.GenerateInstantiateSubclassAspect;
import com.opnworks.vaadin.i18n.support.I18NCaptionSupport;
import com.opnworks.vaadin.i18n.support.I18NCaptionSupport.CaptionContainer;
import com.vaadin.event.Action;
import com.vaadin.server.Resource;

/**
 * The I18NAware
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
@GenerateInstantiateSubclassAspect
public class I18NAction extends Action implements I18NAwareCaption, CaptionContainer {

	private static final long serialVersionUID = -4241515863916987115L;

	private I18NCaptionSupport i18NCaptionSupport = new I18NCaptionSupport(this);

	private Locale locale;

	/**
	 * Constructs a new i18n action with the given caption message key.
	 * 
	 * @param captionKey
	 *            the caption message key for the new action.
	 */
	public I18NAction(@I18NAwareMessage String captionKey) {
		super(captionKey);
		i18NCaptionSupport.setCaptionMessage(captionKey);
	}

	/**
	 * Constructs a new i18n action with the given caption message key string and icon.
	 * 
	 * @param captionKey
	 *            the caption message key for the new action.
	 * @param icon
	 *            the icon for the new action.
	 */
	public I18NAction(@I18NAwareMessage String captionKey, Resource icon) {
		super(captionKey, icon);
		i18NCaptionSupport.setCaptionMessage(captionKey);
	}

	@Override
	public Locale getLocale() {
		return locale;
	}

	@Override
	public void i18NUpdate(I18NService i18N) {

		setLocale(i18N.getLocale());

		i18NCaptionSupport.i18NUpdate(i18N);
	}

	@Override
	public void setCaption(String captionKey) {
		setCaptionMessage(captionKey);
	}

	@Override
	public void setCaptionMessage(@I18NAwareMessage String captionKey, Object... captionParams) {
		i18NCaptionSupport.setCaptionMessage(captionKey, captionParams);
	}

	@Override
	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	@Override
	public void setRealCaption(String caption) {
		super.setCaption(caption);
	}
}
