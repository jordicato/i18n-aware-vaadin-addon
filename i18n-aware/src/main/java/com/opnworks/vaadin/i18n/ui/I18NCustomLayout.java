package com.opnworks.vaadin.i18n.ui;

import java.io.IOException;
import java.io.InputStream;

import com.opnworks.vaadin.i18n.I18NAwareLayout;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.processor.GenerateInstantiateSubclassAspect;
import com.opnworks.vaadin.i18n.support.I18NAwareComponentCaptionSupport;
import com.opnworks.vaadin.i18n.support.I18NAwareSupport;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomLayout;

/**
 * The I18N CustomLayout
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
@GenerateInstantiateSubclassAspect
public class I18NCustomLayout extends CustomLayout implements I18NAwareLayout {

	private static final long serialVersionUID = -6862053468403081465L;

	private I18NAwareComponentCaptionSupport i18NAwareComponentCaptionSupport = new I18NAwareComponentCaptionSupport(this);
	private I18NAwareSupport i18nAwareSupport = new I18NAwareSupport();

	/**
	 * Constructs a custom layout with the template given in the stream.
	 * 
	 * @param templateStream
	 *            Stream containing template data. Must be using UTF-8 encoding. To use a String as a template use for instance new
	 *            ByteArrayInputStream("<template>".getBytes()).
	 * @param streamLength
	 *            Length of the templateStream
	 * @throws IOException
	 */
	public I18NCustomLayout(InputStream templateStream) throws IOException {
		super(templateStream);
	}

	/**
	 * Constructor for custom layout with given template name. Template file is fetched from "<theme>/layout/<templateName>".
	 */
	public I18NCustomLayout(String template) {
		super(template);
	}

	/**
	 * Default constructor only used by subclasses. Subclasses are responsible for setting the appropriate fields. Either
	 * {@link #setTemplateName(String)}, that makes layout fetch the template from theme, or {@link #setTemplateContents(String)}.
	 */
	protected I18NCustomLayout() {
		super();
	}

	@Override
	public void addComponent(Component c) {
		super.addComponent(c);
		i18nAwareSupport.add(c);
	}

	@Override
	public void addComponent(Component c, String location) {
		super.addComponent(c, location);
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
