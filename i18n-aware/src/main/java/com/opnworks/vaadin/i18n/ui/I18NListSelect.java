package com.opnworks.vaadin.i18n.ui;

import java.util.Collection;

import com.opnworks.vaadin.i18n.I18NAwareField;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.support.I18NAwareFieldSupport;
import com.vaadin.data.Container;
import com.vaadin.ui.ListSelect;

/**
 * The I18NListSelect
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NListSelect extends ListSelect implements I18NAwareField {

	private static final long serialVersionUID = -1928197703885890292L;
	
	private I18NAwareFieldSupport i18NAwareFieldSupport = new I18NAwareFieldSupport(
			this);

	public I18NListSelect(String captionKey, Collection<?> options) {
		super(captionKey, options);
		i18NAwareFieldSupport.setCaptionMessage(captionKey);
	}

	public I18NListSelect(String captionKey, Container dataSource) {
		super(captionKey, dataSource);
		i18NAwareFieldSupport.setCaptionMessage(captionKey);
	}

	public I18NListSelect(String captionKey) {
		super(captionKey);
		i18NAwareFieldSupport.setCaptionMessage(captionKey);
	}

	@Override
	public void setRequiredErrorMessage(String requiredErrorKey, Object... requiredErrorParams) {
		i18NAwareFieldSupport.setRequiredErrorMessage(requiredErrorKey, requiredErrorParams);
	}

	@Override
	public void setCaptionMessage(String captionKey, Object... params) {
		i18NAwareFieldSupport.setCaptionMessage(captionKey, params);
	}

	@Override
	public void setDescriptionMessage(String descriptionKey,
			Object... descriptionParams) {
		i18NAwareFieldSupport.setDescriptionMessage(descriptionKey, descriptionParams);
	}
	
	@Override
	public void i18NUpdate(I18NService i18N) {
		i18NAwareFieldSupport.i18NUpdate(i18N);
	}
}
