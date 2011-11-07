package com.opnworks.vaadin.i18n.ui;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.opnworks.vaadin.i18n.I18NAwareField;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.service_impl.I18NServiceImpl;
import com.opnworks.vaadin.i18n.support.I18NAwareFieldSupport;
import com.vaadin.data.Container;
import com.vaadin.ui.OptionGroup;

/**
 * The I18NOptionGroup
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NOptionGroup extends OptionGroup implements I18NAwareField {

	private static final long serialVersionUID = 9024460858748646949L;

	private I18NAwareFieldSupport i18NAwareFieldSupport = new I18NAwareFieldSupport(this);

	private Map<Object, String> itemCaptionKeys = new HashMap<Object, String>();

	public I18NOptionGroup() {
		super();
	}

	public I18NOptionGroup(String captionKey) {
		super(captionKey);
		i18NAwareFieldSupport.setCaptionMessage(captionKey);
	}

	public I18NOptionGroup(String captionKey, Collection<?> options) {
		super(captionKey, options);
		i18NAwareFieldSupport.setCaptionMessage(captionKey);
	}

	public I18NOptionGroup(String captionKey, Container dataSource) {
		super(captionKey, dataSource);
		i18NAwareFieldSupport.setCaptionMessage(captionKey);
	}

	public void setItemCaptionKey(Object itemId, String captionKey) {
		itemCaptionKeys.put(itemId, captionKey);
		if( I18NServiceImpl.getInstance() != null ) {
			updateItemCaptionKey( itemId, I18NServiceImpl.getInstance() );
		}
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
		updateItemCaptionKeys(i18N);
	}

	private void updateItemCaptionKeys(I18NService i18N) {
		Collection<?> itemIds = getItemIds();
		Map<Object, String> updatedItemCaptionKeys = new HashMap<Object, String>();
		for (Object itemId : itemIds) {
			String itemCaptionKey = updateItemCaptionKey(itemId, i18N);
			updatedItemCaptionKeys.put(itemId, itemCaptionKey);
		}
		itemCaptionKeys = updatedItemCaptionKeys;
	}

	private String updateItemCaptionKey(Object itemId, I18NService i18N) {
		String itemCaptionKey = itemCaptionKeys.get(itemId);
		if (itemCaptionKey == null) {
			itemCaptionKey = "No itemCaptionKey defined";
		}
		setItemCaption(itemId, i18N.getMessage(itemCaptionKey));
		return itemCaptionKey;
	}
}
