package com.opnworks.vaadin.i18n;

import com.vaadin.ui.Component;

/**
 * The I18NAware Component
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public interface I18NAwareComponent extends Component, I18NAwareCaption {

	void setDescription(String description);

	void setDescriptionMessage(@I18NAwareMessage String descriptionKey,
			Object... descriptionParams);
}
