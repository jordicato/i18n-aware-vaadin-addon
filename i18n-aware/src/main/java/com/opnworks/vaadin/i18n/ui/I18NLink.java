package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareCaption;
import com.opnworks.vaadin.i18n.I18NAwareComponent;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.processor.GenerateInstantiateSubclassAspect;
import com.opnworks.vaadin.i18n.support.I18NAwareComponentCaptionSupport;
import com.vaadin.terminal.Resource;
import com.vaadin.ui.Link;

/**
 * The I18NLink
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
@GenerateInstantiateSubclassAspect
public class I18NLink extends Link implements I18NAwareComponent, I18NAwareCaption {

	private static final long serialVersionUID = 3731071248238555705L;

	private I18NAwareComponentCaptionSupport i18NAwareComponentCaptionSupport;

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
	public I18NLink(@I18NAwareMessage String captionKey, Resource resource) {
		super(captionKey, resource);
		getI18NAwareComponentCaptionSupport().setCaptionMessage(captionKey);
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
	public I18NLink(@I18NAwareMessage String captionKey, Resource resource, String targetName,
			int width, int height, int border) {
		super(captionKey, resource, targetName, width, height, border);
		getI18NAwareComponentCaptionSupport().setCaptionMessage(captionKey);
	}

	@Override
	public void setRealCaption(String caption) {
		super.setCaption(caption);
	}
	
	@Override
	public void setCaption(String captionKey) {
		setCaptionMessage(captionKey);
	}
	
	@Override
	public void setCaptionMessage(@I18NAwareMessage String captionKey, Object... params) {
		getI18NAwareComponentCaptionSupport().setCaptionMessage(captionKey, params);
	}

	@Override
	public void setRealDescription(String description) {
		super.setDescription(description);
	}
	
	@Override
	public void setDescription(String descriptionKey) {
		setDescriptionMessage(descriptionKey);
	}
	
	@Override
	public void setDescriptionMessage(@I18NAwareMessage String descriptionKey,
			Object... descriptionParams) {
		getI18NAwareComponentCaptionSupport().setDescriptionMessage(descriptionKey, descriptionParams);
	}
	
	@Override
	public void i18NUpdate(I18NService i18N) {
		getI18NAwareComponentCaptionSupport().i18NUpdate(i18N);
	}

    private I18NAwareComponentCaptionSupport getI18NAwareComponentCaptionSupport() {
    	
    	if(i18NAwareComponentCaptionSupport==null) {
    		i18NAwareComponentCaptionSupport = new I18NAwareComponentCaptionSupport(
    				this);
    	}
    	
		return i18NAwareComponentCaptionSupport;
	}
}
