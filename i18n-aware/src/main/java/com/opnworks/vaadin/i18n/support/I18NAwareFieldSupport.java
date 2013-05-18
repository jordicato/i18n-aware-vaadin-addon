package com.opnworks.vaadin.i18n.support;

import java.io.Serializable;
import java.util.Collection;

import com.opnworks.vaadin.i18n.I18NAwareFieldExpression;
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

	private I18NAwareFieldExpression<T> originalField;

	private I18NAwareComponentValueExpressionSupport i18NAwareComponentValueExpressionSupport;

	private String captionKey;
	
	private I18NAwareValueSupport i18NRequiredErrorSupport = new I18NAwareValueSupport(new AwareValueContainer() {
		@Override
		public void setValue(String value) {
			originalField.setRealRequiredError(value);
		}
	});

	public I18NAwareFieldSupport(I18NAwareFieldExpression<T> originalField) {
		this.originalField = originalField;
		i18NAwareComponentValueExpressionSupport = new I18NAwareComponentValueExpressionSupport(originalField);
	}

	public void i18NUpdate(I18NService i18N) {
		i18NAwareComponentValueExpressionSupport.i18NUpdate(i18N);

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
		this.captionKey = captionKey;
		i18NAwareComponentValueExpressionSupport.setCaptionMessage(captionKey, params);
	}

	public void setDescriptionMessage(@I18NAwareMessage String descriptionKey, Object... descriptionParams) {
		i18NAwareComponentValueExpressionSupport.setDescriptionMessage(descriptionKey, descriptionParams);
	}

	public void setValueMessage(@I18NAwareMessage String valueKey, Object... valueParams) {
		i18NAwareComponentValueExpressionSupport.setValueMessage(valueKey, valueParams);
	}

	public void setCaptionMessage(I18NExpression expression) {
		i18NAwareComponentValueExpressionSupport.setCaptionMessage(expression);
	}

	public void setDescriptionMessage(I18NExpression expression) {
		i18NAwareComponentValueExpressionSupport.setDescriptionMessage(expression);
	}

	public void setValueMessage(I18NExpression expression) {
		i18NAwareComponentValueExpressionSupport.setValueMessage(expression);
	}

	public void setRequiredErrorMessage(@I18NAwareMessage String requiredErrorKey, Object[] requiredErrorParams) {
		i18NRequiredErrorSupport.setValueMessage(requiredErrorKey, requiredErrorParams);
	}
	
	public String getCaptionKey() {
		return captionKey;
	}
}
