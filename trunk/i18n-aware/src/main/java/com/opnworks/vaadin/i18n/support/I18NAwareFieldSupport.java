package com.opnworks.vaadin.i18n.support;

import java.io.Serializable;
import java.util.Collection;

import com.opnworks.vaadin.i18n.I18NAwareField;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NAwareValidator;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.support.I18NAwareValueSupport.AwareValueContainer;
import com.vaadin.data.Validator;

/**
 * The I18NAwareFieldSupport
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NAwareFieldSupport<T> implements Serializable {

	private static final long serialVersionUID = -1969833925067194971L;

	private I18NAwareField<T> originalField;

	private I18NAwareComponentCaptionSupport i18NAwareComponentCaptionSupport;

	private I18NAwareValueSupport i18NRequiredErrorSupport = new I18NAwareValueSupport(new AwareValueContainer() {
		@Override
		public void setValue(String value) {
			originalField.setRealRequiredError(value);
		}
	});

	public I18NAwareFieldSupport(I18NAwareField<T> originalField) {
		this.originalField = originalField;
		i18NAwareComponentCaptionSupport = new I18NAwareComponentCaptionSupport(originalField);
	}

	public void i18NUpdate(I18NService i18N) {
		i18NAwareComponentCaptionSupport.i18NUpdate(i18N);

		// Perform RequiredError internationalization
		if (i18NRequiredErrorSupport.getValueKey() != null) {

			Object[] requiredErrorParams = i18NRequiredErrorSupport.getValueParams();

			if (requiredErrorParams != null) {

				Object[] params = new Object[1 + requiredErrorParams.length];

				params[0] = originalField.getCaption();

				System.arraycopy(requiredErrorParams, 0, params, 1, requiredErrorParams.length);

				i18NRequiredErrorSupport.setValueParams(params);

			}
			else {
				i18NRequiredErrorSupport.setValueParams(originalField.getCaption());
			}
			i18NRequiredErrorSupport.i18NUpdate(i18N);
		}

		// Perform Validators internationalization
		Collection<Validator> validators = originalField.getValidators();

		if (validators != null) {
			for (Validator validator : validators ) {
				if (validator instanceof I18NAwareValidator) {
					((I18NAwareValidator) validator).i18NUpdate(i18N);
				}
			}
		}

	}

	public void setCaptionMessage(@I18NAwareMessage String captionKey, Object... params) {
		i18NAwareComponentCaptionSupport.setCaptionMessage(captionKey, params);
	}

	public void setDescriptionMessage(@I18NAwareMessage String descriptionKey, Object... descriptionParams) {
		i18NAwareComponentCaptionSupport.setDescriptionMessage(descriptionKey, descriptionParams);
	}

	public void setRequiredErrorMessage(@I18NAwareMessage String requiredErrorKey, Object[] requiredErrorParams) {
		i18NRequiredErrorSupport.setValueMessage(requiredErrorKey, requiredErrorParams);
	}
}
