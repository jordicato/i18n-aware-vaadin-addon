package com.opnworks.vaadin.i18n.data.validator;

import com.opnworks.vaadin.i18n.I18NAwareValidator;
import com.opnworks.vaadin.i18n.I18NService;
import com.vaadin.data.validator.StringLengthValidator;

/**
 * The I18NStringLengthValidator
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NStringLengthValidator extends StringLengthValidator implements
		I18NAwareValidator {

	private static final long serialVersionUID = 1024490253138585045L;

	private String errorMessageKey;
	private String fieldNameKey;

	public I18NStringLengthValidator(String errorMessageKey,
			String fieldNameKey, int minLength, int maxLength, boolean allowNull) {

		super(errorMessageKey, minLength, maxLength, allowNull);

		this.errorMessageKey = errorMessageKey;
		this.fieldNameKey = fieldNameKey;
	}

	public void setErrorMessageKey(String errorMessageKey) {
		this.errorMessageKey = errorMessageKey;
	}

	@Override
	public void i18NUpdate(I18NService i18N) {

		setErrorMessage(i18N.getMessage(errorMessageKey, "{0}",
				i18N.getMessage(fieldNameKey), getMinLength(), getMaxLength()));
	}

}