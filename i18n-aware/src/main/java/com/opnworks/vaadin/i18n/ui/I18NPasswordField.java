package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareField;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.support.I18NAwareFieldSupport;
import com.vaadin.data.Property;
import com.vaadin.ui.PasswordField;

/**
 * The I18NPasswordField
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
@SuppressWarnings("unchecked")
public class I18NPasswordField extends PasswordField implements I18NAwareField {

	private static final long serialVersionUID = 1398328850698530271L;
	
	private I18NAwareFieldSupport i18NAwareFieldSupport = new I18NAwareFieldSupport(this);
	
    /**
     * Constructs an empty I18NPasswordField.
     */
    public I18NPasswordField() {
    	super();
    }

    /**
     * Constructs a I18NPasswordField with given property data source.
     * 
     * @param dataSource
     *            the property data source for the field
     */
    public I18NPasswordField(Property dataSource) {
    	super(dataSource);
    }

    /**
     * Constructs a I18NPasswordField with given caption and property data source.
     * 
     * @param caption
     *            the caption for the field
     * @param dataSource
     *            the property data source for the field
     */
    public I18NPasswordField(@I18NAwareMessage String captionKey, Property dataSource) {
    	super(captionKey, dataSource);
		i18NAwareFieldSupport.setCaptionMessage(captionKey);
    }

    /**
     * Constructs a I18NPasswordField with given value and caption.
     * 
     * @param caption
     *            the caption for the field
     * @param value
     *            the value for the field
     */
    public I18NPasswordField(@I18NAwareMessage String captionKey, String value) {
    	super(captionKey, value);
		i18NAwareFieldSupport.setCaptionMessage(captionKey);
    }

    /**
     * Constructs a I18NPasswordField with given caption.
     * 
     * @param caption
     *            the caption for the field
     */
    public I18NPasswordField(@I18NAwareMessage String captionKey) {
    	super(captionKey);
		i18NAwareFieldSupport.setCaptionMessage(captionKey);
    }
    
	@Override
	public void setRequiredErrorMessage(@I18NAwareMessage String requiredErrorKey, Object... requiredErrorParams) {
		i18NAwareFieldSupport.setRequiredErrorMessage(requiredErrorKey, requiredErrorParams);
	}

	@Override
	public void setCaptionMessage(@I18NAwareMessage String captionKey, Object... params) {
		i18NAwareFieldSupport.setCaptionMessage(captionKey, params);
	}

	@Override
	public void setDescriptionMessage(@I18NAwareMessage String descriptionKey,
			Object... descriptionParams) {
		i18NAwareFieldSupport.setDescriptionMessage(descriptionKey, descriptionParams);
	}

	@Override
	public void i18NUpdate(I18NService i18N) {
		i18NAwareFieldSupport.i18NUpdate(i18N);
	}
}
