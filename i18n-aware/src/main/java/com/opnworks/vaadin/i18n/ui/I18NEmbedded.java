package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareCaption;
import com.opnworks.vaadin.i18n.I18NAwareComponent;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.support.I18NAwareComponentCaptionSupport;
import com.vaadin.terminal.Resource;
import com.vaadin.ui.Embedded;

/**
 * The I18NEmbedded
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NEmbedded extends Embedded implements I18NAwareComponent,
		I18NAwareCaption {

	private static final long serialVersionUID = 2480062310104748180L;

	private I18NAwareComponentCaptionSupport i18NAwareComponentCaptionSupport = new I18NAwareComponentCaptionSupport(
			this);

	/**
	 * Creates a new empty i18n Embedded object.
	 */
	public I18NEmbedded() {
		super();
	}

	/**
	 * Creates a new empty i18n Embedded object with captionKey.
	 * 
	 * @param captionKey
	 */
	public I18NEmbedded(@I18NAwareMessage String captionKey) {
		super(captionKey);
		i18NAwareComponentCaptionSupport.setCaptionMessage(captionKey);
	}

	/**
	 * Creates a new i18n Embedded object with a caption message key whose
	 * contents is loaded from given resource. The dimensions are assumed if
	 * possible. The type is guessed from resource.
	 * 
	 * @param captionKey
	 * @param source
	 *            the Source of the embedded object.
	 */
	public I18NEmbedded(@I18NAwareMessage String captionKey, Resource resource) {
		super(captionKey, resource);
		i18NAwareComponentCaptionSupport.setCaptionMessage(captionKey);
	}

	@Override
	public void setCaptionMessage(@I18NAwareMessage String captionKey, Object... params) {
		i18NAwareComponentCaptionSupport.setCaptionMessage(captionKey, params);
	}

	@Override
	public void setDescriptionMessage(@I18NAwareMessage String descriptionKey,
			Object... descriptionParams) {
		i18NAwareComponentCaptionSupport.setDescriptionMessage(descriptionKey, descriptionParams);
	}
	
	@Override
	public void i18NUpdate(I18NService i18N) {
		i18NAwareComponentCaptionSupport.i18NUpdate(i18N);
	}

}
