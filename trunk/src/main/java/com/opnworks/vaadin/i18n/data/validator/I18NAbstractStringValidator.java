package com.opnworks.vaadin.i18n.data.validator;

import com.opnworks.vaadin.i18n.I18NAwareValidator;
import com.opnworks.vaadin.i18n.I18NService;
import com.vaadin.data.validator.AbstractStringValidator;

/**
 * The I18NAbstractStringValidator
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public abstract class I18NAbstractStringValidator extends
		AbstractStringValidator implements I18NAwareValidator {

	private static final long serialVersionUID = 1086008964914338409L;

	private String errorMessageKey;
	private String fieldNameKey;

	public I18NAbstractStringValidator(String errorMessageKey,
			String fieldNameKey) {

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
