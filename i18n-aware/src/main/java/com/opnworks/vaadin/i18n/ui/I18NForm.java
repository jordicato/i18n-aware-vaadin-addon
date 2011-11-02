package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareContainer;
import com.opnworks.vaadin.i18n.I18NAwareField;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.support.I18NAwareFieldSupport;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormFieldFactory;

/**
 * The I18NForm
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NForm extends Form implements I18NAwareField {

	private static final long serialVersionUID = 6357950198553382989L;

	private I18NAwareFieldSupport i18NAwareFieldSupport = new I18NAwareFieldSupport(this);

	/**
	 * Constructs a new i18n form with I18NFormLayout layout and
	 * I18NVerticalLayout footer.
	 */
	public I18NForm() {
		super();
		setLayout(new I18NFormLayout());
		setFooter(new I18NVerticalLayout());
	}

	/**
	 * Constructs a new form with given {@link I18NFormLayout}.
	 * 
	 * @param formLayout
	 *            the layout of the form.
	 */
	public I18NForm(I18NFormLayout formLayout) {
		super();
		setLayout(formLayout);
		setFooter(new I18NVerticalLayout());
	}

	/**
	 * Constructs a new form with given {@link I18NFormLayout} and
	 * {@link FormFieldFactory}.
	 * 
	 * @param formLayout
	 *            the layout of the form.
	 * @param fieldFactory
	 *            the FieldFactory of the form.
	 */
	public I18NForm(I18NFormLayout formLayout, FormFieldFactory fieldFactory) {
		super();
		setLayout(formLayout);
		setFooter(new I18NVerticalLayout());
	}

	@Override
	public void setCaptionKey(String captionKey) {
		i18NAwareFieldSupport.setCaptionKey(captionKey);
	}

	@Override
	public void setCaptionParams(Object... params) {
		i18NAwareFieldSupport.setCaptionParams(params);
	}

	@Override
	public void setRequiredErrorKey(String requiredErrorKey) {
		i18NAwareFieldSupport.setRequiredErrorKey(requiredErrorKey);
	}

	@Override
	public void setRequiredErrorParams(Object[] requiredErrorParams) {
		i18NAwareFieldSupport.setRequiredErrorParams(requiredErrorParams);
	}

	@Override
	public void i18NUpdate(I18NService i18N) {
		i18NAwareFieldSupport.i18NUpdate(i18N);
		((I18NAwareContainer) getLayout()).i18NUpdate(i18N);
		((I18NVerticalLayout) getFooter()).i18NUpdate(i18N);
	}

}
