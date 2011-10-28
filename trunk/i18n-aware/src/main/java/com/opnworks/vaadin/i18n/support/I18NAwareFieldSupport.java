package com.opnworks.vaadin.i18n.support;

import java.io.Serializable;
import java.util.Collection;

import com.opnworks.vaadin.i18n.I18NAwareField;
import com.opnworks.vaadin.i18n.I18NAwareValidator;
import com.opnworks.vaadin.i18n.I18NService;
import com.vaadin.data.Validator;

/**
 * The I18NAwareFieldSupport
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NAwareFieldSupport implements Serializable {

	private static final long serialVersionUID = -1969833925067194971L;

	private I18NAwareField originalField;

	private I18NAwareComponentCaptionSupport i18NAwareComponentCaptionSupport;

	private String requiredErrorKey;
	private Object[] requiredErrorParams;

	public I18NAwareFieldSupport(I18NAwareField originalField) {
		this.originalField = originalField;
		i18NAwareComponentCaptionSupport = new I18NAwareComponentCaptionSupport(
				originalField);
	}

	public void setCaptionKey(String captionKey) {
		i18NAwareComponentCaptionSupport.setCaptionKey(captionKey);
	}

	public void setCaptionParams(Object... params) {
		i18NAwareComponentCaptionSupport.setCaptionParams(params);
	}

	public void setRequiredErrorKey(String requiredErrorKey) {
		this.requiredErrorKey = requiredErrorKey;
	}

	public void setRequiredErrorParams(Object[] requiredErrorParams) {
		this.requiredErrorParams = requiredErrorParams;
	}

	public void updateLabels(I18NService i18N) {

		i18NAwareComponentCaptionSupport.updateLabels(i18N);

		if (requiredErrorKey != null) {

			Object[] params = null;

			if (requiredErrorParams != null) {
				params = new Object[1 + requiredErrorParams.length];
				System.arraycopy(requiredErrorParams, 0, params, 1,
						requiredErrorParams.length);
			} else {
				params = new Object[] { originalField.getCaption() };
			}

			originalField.setRequiredError(i18N.getMessage(requiredErrorKey,
					params));
		}

		Collection<Validator> validators = originalField.getValidators();

		if (validators != null) {
			for (Validator validator : validators) {
				if (validator instanceof I18NAwareValidator) {
					((I18NAwareValidator) validator).i18NUpdate(i18N);
				}
			}
		}

	}

}
