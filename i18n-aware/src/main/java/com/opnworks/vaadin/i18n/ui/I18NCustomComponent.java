package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAware;
import com.opnworks.vaadin.i18n.I18NAwareComponentExpression;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.processor.GenerateInstantiateSubclassAspect;
import com.opnworks.vaadin.i18n.support.I18NAwareComponentCaptionSupport;
import com.opnworks.vaadin.i18n.support.I18NAwareComponentExpressionSupport;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;

/**
 * The I18NCustomComponent
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
@GenerateInstantiateSubclassAspect
@SuppressWarnings("serial")
public class I18NCustomComponent extends CustomComponent implements I18NAwareComponentExpression {

	private I18NAwareComponentCaptionSupport i18NAwareComponentCaptionSupport = new I18NAwareComponentCaptionSupport(this);
	private I18NAwareComponentExpressionSupport i18NAwareComponentExpressionSupport;

	/**
	 * Constructs a new i18n custom component.
	 * 
	 * <p>
	 * The component is implemented by wrapping the methods of the composition root component given as parameter. The composition root must be set
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
	 * The component is implemented by wrapping the methods of the composition root component given as parameter. The composition root must not be
	 * null and can not be changed after the composition.
	 * </p>
	 * 
	 * @param compositionRoot
	 *            the root of the composition component tree.
	 */
	public I18NCustomComponent(Component compositionRoot) {
		super(compositionRoot);
	}

	@Override
	public void i18NUpdate(I18NService i18nService) {

		i18NAwareComponentCaptionSupport.i18NUpdate(i18nService);

		getI18NAwareComponentExpressionSupport().i18NUpdate(i18nService);

		Component root = getCompositionRoot();

		if (root instanceof I18NAware) {
			((I18NAware) root).i18NUpdate(i18nService);
		}
	}

	@Override
	public void setCaption(@I18NAwareMessage String captionKey) {
		setCaptionMessage(captionKey);
	}

	@Override
	public void setCaptionMessage(@I18NAwareMessage String captionKey, Object... params) {
		i18NAwareComponentCaptionSupport.setCaptionMessage(captionKey, params);
	}

	@Override
	public void setDescription(@I18NAwareMessage String descriptionKey) {
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
	public void setCaptionMessage(Object... expression) {
		getI18NAwareComponentExpressionSupport().setCaptionMessage(expression);
	}

	@Override
	public void setDescriptionMessage(Object... expression) {
		getI18NAwareComponentExpressionSupport().setDescriptionMessage(expression);
	}

	private I18NAwareComponentExpressionSupport getI18NAwareComponentExpressionSupport() {

		if (i18NAwareComponentExpressionSupport == null) {
			i18NAwareComponentExpressionSupport = new I18NAwareComponentExpressionSupport(this);
		}

		return i18NAwareComponentExpressionSupport;
	}

	@Override
	public void setRealDescription(String description) {
		super.setDescription(description);
	}
}
