package com.opnworks.vaadin.i18n.data.validator;

import com.opnworks.vaadin.i18n.I18NAwareValidator;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.service_impl.I18NServiceImpl;
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
		
		i18NUpdate();
	}

	public void setErrorMessageKey(String errorMessageKey) {
		this.errorMessageKey = errorMessageKey;
		i18NUpdate();
	}
	
	public void setFieldNameKey(String fieldNameKey) {
		this.fieldNameKey = fieldNameKey;
		i18NUpdate();
	}

	private void i18NUpdate() {
		if( I18NServiceImpl.getInstance() != null ) {
			i18NUpdate( I18NServiceImpl.getInstance() );
		}
	}

	@Override
	public void i18NUpdate(I18NService i18N) {

		setErrorMessage(i18N.getMessage(errorMessageKey, "{0}",
				i18N.getMessage(fieldNameKey)));
	}

}
