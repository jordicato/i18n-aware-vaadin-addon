package com.opnworks.vaadin.i18n.ui;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.opnworks.vaadin.i18n.I18NAwareFieldExpression;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.processor.GenerateInstantiateSubclassAspect;
import com.opnworks.vaadin.i18n.service_impl.I18NServiceImpl;
import com.opnworks.vaadin.i18n.support.I18NAwareFieldSupport;
import com.opnworks.vaadin.i18n.support.I18NExpression;
import com.vaadin.data.Container;
import com.vaadin.ui.OptionGroup;

/**
 * The I18NOptionGroup
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
@GenerateInstantiateSubclassAspect
@SuppressWarnings("serial")
public class I18NOptionGroup extends OptionGroup implements I18NAwareFieldExpression<Object> {

	private I18NAwareFieldSupport<Object> i18NAwareFieldSupport;

	private Map<Object, String> itemCaptionKeys = new HashMap<Object, String>();

	public I18NOptionGroup() {
		super();
	}

	public I18NOptionGroup(@I18NAwareMessage String captionKey) {
		super(captionKey);
		setCaptionMessage(captionKey);
	}

	public I18NOptionGroup(I18NExpression captionExpression) {
		super(captionExpression.getStringFinal());
		setCaptionMessage(captionExpression);
	}

	public I18NOptionGroup(@I18NAwareMessage String captionKey, Collection<?> options) {
		super(captionKey, options);
		setCaptionMessage(captionKey);
	}

	public I18NOptionGroup(I18NExpression captionExpression, Collection<?> options) {
		super(captionExpression.getStringFinal(), options);
		setCaptionMessage(captionExpression);
	}

	public I18NOptionGroup(@I18NAwareMessage String captionKey, Container dataSource) {
		super(captionKey, dataSource);
		setCaptionMessage(captionKey);
	}

	public I18NOptionGroup(I18NExpression captionExpression, Container dataSource) {
		super(captionExpression.getStringFinal(), dataSource);
		setCaptionMessage(captionExpression);
	}

	@Override
	public void i18NUpdate(I18NService i18N) {
		getI18NAwareFieldSupport().i18NUpdate(i18N);
		updateItemCaptionKeys(i18N);
	}

	@Override
	public void setCaption(@I18NAwareMessage String captionKey) {
		setCaptionMessage(captionKey);
	}

	public void setCaption(I18NExpression expression) {
		setCaptionMessage(expression);
	}

	@Override
	public void setCaptionMessage(@I18NAwareMessage String captionKey, Object... params) {
		getI18NAwareFieldSupport().setCaptionMessage(captionKey, params);
	}

	@Override
	public void setDescription(@I18NAwareMessage String descriptionKey) {
		setDescriptionMessage(descriptionKey);
	}

	public void setDescription(I18NExpression expression) {
		setDescriptionMessage(expression);
	}

	@Override
	public void setDescriptionMessage(@I18NAwareMessage String descriptionKey, Object... descriptionParams) {
		getI18NAwareFieldSupport().setDescriptionMessage(descriptionKey, descriptionParams);
	}

	public void setItemCaptionKey(Object itemId, @I18NAwareMessage String captionKey) {
		itemCaptionKeys.put(itemId, captionKey);
		if (I18NServiceImpl.getInstance() != null) {
			updateItemCaptionKey(itemId, I18NServiceImpl.getInstance());
		}
	}

	@Override
	public void setRealCaption(String caption) {
		super.setCaption(caption);
	}

	@Override
	public void setRealDescription(String description) {
		super.setDescription(description);
	}

	@Override
	public void setRealRequiredError(String requiredMessage) {
		super.setRequiredError(requiredMessage);
	}

	@Override
	public void setRequiredError(@I18NAwareMessage String requiredErrorKey) {
		setRequiredErrorMessage(requiredErrorKey);
	}

	@Override
	public void setRequiredErrorMessage(@I18NAwareMessage String requiredErrorKey, Object... requiredErrorParams) {
		getI18NAwareFieldSupport().setRequiredErrorMessage(requiredErrorKey, requiredErrorParams);
	}

	private I18NAwareFieldSupport<Object> getI18NAwareFieldSupport() {

		if (i18NAwareFieldSupport == null) {
			i18NAwareFieldSupport = new I18NAwareFieldSupport<Object>(this);
		}

		return i18NAwareFieldSupport;
	}

	private String updateItemCaptionKey(Object itemId, I18NService i18N) {
		String itemCaptionKey = itemCaptionKeys.get(itemId);
		if (itemCaptionKey == null) {
			itemCaptionKey = "No itemCaptionKey defined";
		}
		setItemCaption(itemId, i18N.getMessage(itemCaptionKey));
		return itemCaptionKey;
	}

	private void updateItemCaptionKeys(I18NService i18N) {
		Collection<?> itemIds = getItemIds();
		Map<Object, String> updatedItemCaptionKeys = new HashMap<Object, String>();
		for (Object itemId : itemIds ) {
			String itemCaptionKey = updateItemCaptionKey(itemId, i18N);
			updatedItemCaptionKeys.put(itemId, itemCaptionKey);
		}
		itemCaptionKeys = updatedItemCaptionKeys;
	}

	@Override
	public void setCaptionMessage(I18NExpression expression) {
		getI18NAwareFieldSupport().setCaptionMessage(expression);
	}

	@Override
	public void setDescriptionMessage(I18NExpression expression) {
		getI18NAwareFieldSupport().setDescriptionMessage(expression);
	}

	@Override
	public void setRealValue(Object value) {
		// TODO Auto-generated method stub
		super.setValue(value);
	}

	@Override
	public void setValueMessage(I18NExpression expression) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setValueMessage(String valueKey, Object... valueParams) {
		// TODO Auto-generated method stub

	}

}
