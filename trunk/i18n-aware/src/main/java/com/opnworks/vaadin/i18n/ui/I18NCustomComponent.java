package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAware;
import com.opnworks.vaadin.i18n.I18NAwareComponent;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.support.I18NAwareComponentCaptionSupport;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;

/**
 * The I18NCustomComponent
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NCustomComponent extends CustomComponent implements
		I18NAwareComponent {

	private static final long serialVersionUID = 7454615691570069153L;

	private I18NAwareComponentCaptionSupport i18NAwareComponentCaptionSupport = new I18NAwareComponentCaptionSupport(this);
	
	/**
	 * Constructs a new i18n custom component.
	 * 
	 * <p>
	 * The component is implemented by wrapping the methods of the composition
	 * root component given as parameter. The composition root must be set
	 * before the component can be used.
	 * </p>
	 */
	public I18NCustomComponent() {
		super();
	}

	/**
	 * Constructs a new i18n custom component.
	 * 
	 * <p>
	 * The component is implemented by wrapping the methods of the composition
	 * root component given as parameter. The composition root must not be null
	 * and can not be changed after the composition.
	 * </p>
	 * 
	 * @param compositionRoot
	 *            the root of the composition component tree.
	 */
	public I18NCustomComponent(Component compositionRoot) {
		super(compositionRoot);
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
	public void i18NUpdate(I18NService i18nService) {

		i18NAwareComponentCaptionSupport.i18NUpdate(i18nService);
		
		Component root = getCompositionRoot();

		if (root != null && root instanceof I18NAware) {
			((I18NAware) root).i18NUpdate(i18nService);
		}
	}

}
