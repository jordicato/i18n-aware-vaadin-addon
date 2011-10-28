package com.opnworks.vaadin.i18n.data.validator;

import com.opnworks.vaadin.i18n.I18NAwareValidator;
import com.opnworks.vaadin.i18n.I18NService;
import com.vaadin.data.validator.EmailValidator;

/**
 * The I18NEmailValidator
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NEmailValidator extends EmailValidator implements I18NAwareValidator {

	private static final long serialVersionUID = 1024490253138585045L;

	private String errorMessageKey;
	private String fieldNameKey;

	public I18NEmailValidator(String errorMessageKey, String fieldNameKey) {

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
