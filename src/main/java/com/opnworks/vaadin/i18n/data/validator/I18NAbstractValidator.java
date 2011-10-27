package com.opnworks.vaadin.i18n.data.validator;

import com.opnworks.vaadin.i18n.I18NAwareValidator;
import com.opnworks.vaadin.i18n.I18NService;
import com.vaadin.data.validator.AbstractValidator;

/**
 * The I18NAbstractValidator
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public abstract class I18NAbstractValidator extends AbstractValidator implements
		I18NAwareValidator {

	private static final long serialVersionUID = -633288038283553961L;

	private String errorMessageKey;
	private String fieldNameKey;

	public I18NAbstractValidator(String errorMessageKey, String fieldNameKey) {

		super(errorMessageKey);

		this.errorMessageKey = errorMessageKey;
		this.fieldNameKey = fieldNameKey;
	}

	public void setErrorMessageKey(String errorMessageKey) {
		this.errorMessageKey = errorMessageKey;
	}

	@Override
	public void i18NUpdate(I18NService i18N) {

		setErrorMessage(i18N.getMessage(errorMessageKey, "{0}",
				i18N.getMessage(fieldNameKey)));
	}

}
