package com.opnworks.vaadin.i18n.ui;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.opnworks.vaadin.i18n.I18NAwareField;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.support.I18NAwareFieldSupport;
import com.vaadin.ui.OptionGroup;

/**
 * The I18NOptionGroup
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NOptionGroup extends OptionGroup implements I18NAwareField {

	private static final long serialVersionUID = 9024460858748646949L;

	private I18NAwareFieldSupport i18NAwareFieldSupport = new I18NAwareFieldSupport(
			this);

	private Map<Object, String> itemCaptionKeys = new HashMap<Object, String>();

	public I18NOptionGroup() {
		super();
	}

	public I18NOptionGroup(String captionKey) {
		super(captionKey);
		i18NAwareFieldSupport.setCaptionKey(captionKey);
	}

	public void setItemCaptionKey(Object itemId, String caption) {
		itemCaptionKeys.put(itemId, caption);
	}

	@Override
	public void setCaptionKey(String captionKey) {
		i18NAwareFieldSupport.setCaptionKey(captionKey);
	}

	@Override
	public void setCaptionParams(Object... params) {
		i18NAwareFieldSupport.setCaptionParams(params);
	}

	@Override
	public void setRequiredErrorKey(String requiredErrorKey) {
		i18NAwareFieldSupport.setRequiredErrorKey(requiredErrorKey);
	}

	@Override
	public void setRequiredErrorParams(Object[] requiredErrorParams) {
		i18NAwareFieldSupport.setRequiredErrorParams(requiredErrorParams);
	}

	@Override
	public synchronized void i18NUpdate(I18NService i18N) {
		i18NAwareFieldSupport.updateLabels(i18N);
		Collection<?> itemIds = getItemIds();
		Map<Object, String> updatedItemCaptionKeys = new HashMap<Object, String>();
		for (Object itemId : itemIds) {
			String itemCaptionKey = itemCaptionKeys.get(itemId);
			if (itemCaptionKey == null) {
				itemCaptionKey = "No itemCaptionKey defined";
			}
			updatedItemCaptionKeys.put(itemId, itemCaptionKey);
			setItemCaption(itemId, i18N.getMessage(itemCaptionKey));
		}
		itemCaptionKeys = updatedItemCaptionKeys;
	}
}
