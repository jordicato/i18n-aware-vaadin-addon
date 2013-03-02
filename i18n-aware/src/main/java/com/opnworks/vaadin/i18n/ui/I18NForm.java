package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareContainer;
import com.opnworks.vaadin.i18n.I18NAwareField;
import com.opnworks.vaadin.i18n.I18NAwareFormFieldFactory;
import com.opnworks.vaadin.i18n.I18NAwareLayout;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.processor.GenerateInstantiateSubclassAspect;
import com.opnworks.vaadin.i18n.support.I18NAwareFieldSupport;
import com.opnworks.vaadin.i18n.support.I18NAwareFormFieldFactorySupport;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormFieldFactory;

/**
 * The I18NForm
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
@GenerateInstantiateSubclassAspect
@SuppressWarnings({ "serial", "deprecation" })
@Deprecated
public class I18NForm extends Form implements I18NAwareField<Object> {

	private I18NAwareFieldSupport<Object> i18NAwareFieldSupport = new I18NAwareFieldSupport<Object>(this);

	/**
	 * Constructs a new i18n form with I18NFormLayout layout and I18NVerticalLayout footer.
	 */
	public I18NForm() {
		super(null, I18NDefaultFieldFactory.getInstance());
		setLayout(new I18NFormLayout());
		setFooter(new I18NVerticalLayout());
	}

	/**
	 * Constructs a new i18n form with given {@link I18NAwareLayout}.
	 * 
	 * @param formLayout
	 *            the layout of the form.
	 */
	public I18NForm(I18NAwareLayout formLayout) {
		super(formLayout, I18NDefaultFieldFactory.getInstance());
		setFooter(new I18NVerticalLayout());
	}

	/**
	 * Constructs a new i18n form with given {@link I18NAwareLayout} and {@link I18NAwareFormFieldFactory}.
	 * 
	 * @param formLayout
	 *            the layout of the form.
	 * @param fieldFactory
	 *            the FieldFactory of the form.
	 */
	public I18NForm(I18NAwareLayout formLayout, I18NAwareFormFieldFactory fieldFactory) {
		super(formLayout, new I18NAwareFormFieldFactorySupport(fieldFactory));
		setFooter(new I18NVerticalLayout());
	}

	@Override
	public void i18NUpdate(I18NService i18N) {
		i18NAwareFieldSupport.i18NUpdate(i18N);
		((I18NAwareContainer) getLayout()).i18NUpdate(i18N);
		((I18NVerticalLayout) getFooter()).i18NUpdate(i18N);
	}

	@Override
	public void setCaption(@I18NAwareMessage String captionKey) {
		setCaptionMessage(captionKey);
	}

	@Override
	public void setCaptionMessage(@I18NAwareMessage String captionKey, Object... params) {
		i18NAwareFieldSupport.setCaptionMessage(captionKey, params);
	}

	@Override
	public void setDescription(@I18NAwareMessage String descriptionKey) {
		setDescriptionMessage(descriptionKey);
	}

	@Override
	public void setDescriptionMessage(@I18NAwareMessage String descriptionKey, Object... descriptionParams) {
		i18NAwareFieldSupport.setDescriptionMessage(descriptionKey, descriptionParams);
	}

	@Override
	public void setFormFieldFactory(FormFieldFactory fieldFactory) {

		if (!(fieldFactory instanceof I18NAwareFormFieldFactory)) {
			throw new IllegalArgumentException("Expecting a I18NFormFieldFactory");
		}

		super.setFormFieldFactory(new I18NAwareFormFieldFactorySupport((I18NAwareFormFieldFactory) fieldFactory));
	}

	public void setFormI18NFieldFactory(I18NAwareFormFieldFactory fieldFactory) {
		super.setFormFieldFactory(new I18NAwareFormFieldFactorySupport(fieldFactory));
	}

	@Override
	public void setRealCaption(String caption) {
		super.setCaption(caption);
	}

	@Override
	public void setRealDescription(String description) {
		super.setDescription(description);
	}

	@Override
	public void setRealRequiredError(String requiredMessage) {
		super.setRequiredError(requiredMessage);
	}

	@Override
	public void setRequiredError(@I18NAwareMessage String requiredErrorKey) {
		setRequiredErrorMessage(requiredErrorKey);
	}

	@Override
	public void setRequiredErrorMessage(@I18NAwareMessage String requiredErrorKey, Object... requiredErrorParams) {
		i18NAwareFieldSupport.setRequiredErrorMessage(requiredErrorKey, requiredErrorParams);
	}

}
