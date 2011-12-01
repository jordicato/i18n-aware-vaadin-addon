package com.opnworks.vaadin.i18n.ui;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.opnworks.vaadin.i18n.I18NAwareField;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.processor.GenerateInstantiateSubclassAspect;
import com.opnworks.vaadin.i18n.service_impl.I18NServiceImpl;
import com.opnworks.vaadin.i18n.support.I18NAwareFieldSupport;
import com.vaadin.data.Container;
import com.vaadin.ui.OptionGroup;

/**
 * The I18NOptionGroup
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
@GenerateInstantiateSubclassAspect
public class I18NOptionGroup extends OptionGroup implements I18NAwareField {

	private static final long serialVersionUID = 9024460858748646949L;

	private I18NAwareFieldSupport i18NAwareFieldSupport;

	private Map<Object, String> itemCaptionKeys = new HashMap<Object, String>();

	public I18NOptionGroup() {
		super();
	}

	public I18NOptionGroup(@I18NAwareMessage String captionKey) {
		super(captionKey);
		getI18NAwareFieldSupport().setCaptionMessage(captionKey);
	}

	public I18NOptionGroup(@I18NAwareMessage String captionKey,
			Collection<?> options) {
		super(captionKey, options);
		getI18NAwareFieldSupport().setCaptionMessage(captionKey);
	}

	public I18NOptionGroup(@I18NAwareMessage String captionKey,
			Container dataSource) {
		super(captionKey, dataSource);
		getI18NAwareFieldSupport().setCaptionMessage(captionKey);
	}

	public void setItemCaptionKey(Object itemId,
			@I18NAwareMessage String captionKey) {
		itemCaptionKeys.put(itemId, captionKey);
		if (I18NServiceImpl.getInstance() != null) {
			updateItemCaptionKey(itemId, I18NServiceImpl.getInstance());
		}
	}

	@Override
	public void setRealRequiredError(String requiredMessage) {
		super.setRequiredError(requiredMessage);
	}

	@Override
	public void setRequiredError(String requiredErrorKey) {
		setRequiredErrorMessage(requiredErrorKey);
	}

	@Override
	public void setRequiredErrorMessage(
			@I18NAwareMessage String requiredErrorKey,
			Object... requiredErrorParams) {
		getI18NAwareFieldSupport().setRequiredErrorMessage(requiredErrorKey,
				requiredErrorParams);
	}

	@Override
	public void setRealCaption(String caption) {
		super.setCaption(caption);
	}

	@Override
	public void setCaption(String captionKey) {
		setCaptionMessage(captionKey);
	}

	@Override
	public void setCaptionMessage(@I18NAwareMessage String captionKey,
			Object... params) {
		getI18NAwareFieldSupport().setCaptionMessage(captionKey, params);
	}

	@Override
	public void setRealDescription(String description) {
		super.setDescription(description);
	}

	@Override
	public void setDescription(String descriptionKey) {
		setDescriptionMessage(descriptionKey);
	}

	@Override
	public void setDescriptionMessage(@I18NAwareMessage String descriptionKey,
			Object... descriptionParams) {
		getI18NAwareFieldSupport().setDescriptionMessage(descriptionKey,
				descriptionParams);
	}

	@Override
	public void i18NUpdate(I18NService i18N) {
		getI18NAwareFieldSupport().i18NUpdate(i18N);
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
	
	private I18NAwareFieldSupport getI18NAwareFieldSupport() {

		if (i18NAwareFieldSupport == null) {
			i18NAwareFieldSupport = new I18NAwareFieldSupport(this);
		}

		return i18NAwareFieldSupport;
	}

}
