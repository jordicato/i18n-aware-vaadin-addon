package com.opnworks.vaadin.i18n.data.validator;

import java.util.Locale;

import com.opnworks.vaadin.i18n.I18NAwareValidator;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.processor.GenerateInstantiateSubclassAspect;
import com.opnworks.vaadin.i18n.service_impl.I18NServiceImpl;
import com.vaadin.data.validator.EmailValidator;

/**
 * The I18NEmailValidator
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
@GenerateInstantiateSubclassAspect
public class I18NEmailValidator extends EmailValidator implements I18NAwareValidator {

	private static final long serialVersionUID = 1024490253138585045L;

	private String errorMessageKey;
	private String fieldNameKey;

	private Locale locale;

	public I18NEmailValidator(String errorMessageKey, String fieldNameKey) {

		super(errorMessageKey);

		this.errorMessageKey = errorMessageKey;
		this.fieldNameKey = fieldNameKey;

		i18NUpdate();
	}

	@Override
	public Locale getLocale() {
		return locale;
	}

	@Override
	public void i18NUpdate(I18NService i18N) {

		setLocale(i18N.getLocale());

		setErrorMessage(i18N.getMessage(errorMessageKey, "{0}", i18N.getMessage(fieldNameKey)));
	}

	public void setErrorMessageKey(String errorMessageKey) {
		this.errorMessageKey = errorMessageKey;
		i18NUpdate();
	}

	@Override
	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	private void i18NUpdate() {
		if (I18NServiceImpl.getInstance() != null) {
			i18NUpdate(I18NServiceImpl.getInstance());
		}
	}

}
