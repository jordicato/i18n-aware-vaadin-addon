package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareCaption;
import com.opnworks.vaadin.i18n.I18NAwareComponent;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.support.I18NAwareComponentCaptionSupport;
import com.vaadin.terminal.Resource;
import com.vaadin.ui.Link;

/**
 * The I18NLink
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NLink extends Link implements I18NAwareComponent, I18NAwareCaption {

	private static final long serialVersionUID = 3731071248238555705L;

	private I18NAwareComponentCaptionSupport i18NAwareComponentCaptionSupport = new I18NAwareComponentCaptionSupport(
			this);

    /**
     * Creates a new i18n link.
     */
	public I18NLink() {
		super();
	}

    /**
     * Creates a new i18n Link with caption message key.
     * 
     * @param captionKey
     * @param resource
     */
	public I18NLink(String captionKey, Resource resource) {
		super(captionKey, resource);
		i18NAwareComponentCaptionSupport.setCaptionKey(captionKey);
	}

    /**
     * Creates a new i18n Link with text message key that opens a new window.
     * 
     * 
     * @param captionKey
     *            the Link text message key.
     * @param targetName
     *            the name of the target window where the link opens to. Empty
     *            name of null implies that the target is opened to the window
     *            containing the link.
     * @param width
     *            the Width of the target window.
     * @param height
     *            the Height of the target window.
     * @param border
     *            the Border style of the target window.
     * 
     */
	public I18NLink(String captionKey, Resource resource, String targetName,
			int width, int height, int border) {
		super(captionKey, resource, targetName, width, height, border);
		i18NAwareComponentCaptionSupport.setCaptionKey(captionKey);
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
		i18NAwareComponentCaptionSupport.i18NUpdate(i18N);
	}

}
