package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareContainer;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.processor.GenerateInstantiateSubclassAspect;
import com.opnworks.vaadin.i18n.support.I18NAwareComponentCaptionSupport;
import com.opnworks.vaadin.i18n.support.I18NAwareSupport;
import com.vaadin.ui.Component;
import com.vaadin.ui.SplitPanel;

/**
 * The I18N SplitPanel
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 * 
 * @deprecated in 6.5. Use {@link I18NHorizontalSplitPanel} or {@link I18NVerticalSplitPanel} instead.
 */
@Deprecated
@GenerateInstantiateSubclassAspect
@SuppressWarnings("serial")
public class I18NSplitPanel extends SplitPanel implements I18NAwareContainer {

	private I18NAwareComponentCaptionSupport i18NAwareComponentCaptionSupport = new I18NAwareComponentCaptionSupport(this);
	private I18NAwareSupport i18nAwareSupport = new I18NAwareSupport();

	/**
	 * Creates a new i18n split panel. The orientation of the panels is <code>ORIENTATION_VERTICAL</code>.
	 */
	public I18NSplitPanel() {
		super();
	}

	/**
	 * Create a new split panels. The orientation of the panel is given as parameters.
	 * 
	 * @param orientation
	 *            the Orientation of the layout.
	 */
	public I18NSplitPanel(int orientation) {
		super(orientation);
	}

	@Override
	public void addComponent(Component c) {
		super.addComponent(c);
		i18nAwareSupport.add(c);
	}

	@Override
	public void i18NUpdate(I18NService i18N) {
		i18NAwareComponentCaptionSupport.i18NUpdate(i18N);
		i18nAwareSupport.i18NUpdate(i18N);
	}

	@Override
	public void removeComponent(Component c) {
		super.removeComponent(c);
		i18nAwareSupport.remove(c);
	}

	@Override
	public void setCaption(String captionKey) {
		setCaptionMessage(captionKey);
	}

	@Override
	public void setCaptionMessage(@I18NAwareMessage String captionKey, Object... params) {
		i18NAwareComponentCaptionSupport.setCaptionMessage(captionKey, params);
	}

	@Override
	public void setDescription(String descriptionKey) {
		setDescriptionMessage(descriptionKey);
	}

	@Override
	public void setDescriptionMessage(@I18NAwareMessage String descriptionKey, Object... descriptionParams) {
		i18NAwareComponentCaptionSupport.setDescriptionMessage(descriptionKey, descriptionParams);
	}

	@Override
	public void setRealCaption(String caption) {
		super.setCaption(caption);
	}

	@Override
	public void setRealDescription(String description) {
		super.setDescription(description);
	}
}
