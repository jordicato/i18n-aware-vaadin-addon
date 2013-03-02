package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAware;
import com.opnworks.vaadin.i18n.I18NAwareCaption;
import com.opnworks.vaadin.i18n.I18NAwareComponent;
import com.opnworks.vaadin.i18n.I18NAwareField;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.processor.GenerateInstantiateSubclassAspect;
import com.opnworks.vaadin.i18n.service_impl.I18NServiceImpl;
import com.opnworks.vaadin.i18n.support.I18NAwareComponentCaptionSupport;
import com.opnworks.vaadin.i18n.support.I18NAwareFieldSupport;
import com.opnworks.vaadin.i18n.support.I18NAwareSupport;
import com.vaadin.data.Container;
import com.vaadin.event.Action;
import com.vaadin.ui.Table;

/**
 * The I18NTable
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
@GenerateInstantiateSubclassAspect
@SuppressWarnings("serial")
public class I18NTable extends Table implements I18NAwareComponent, I18NAwareCaption, I18NAwareField<Object> {

	private I18NAwareSupport i18NAwareSupport = new I18NAwareSupport();

	private I18NAwareComponentCaptionSupport i18NAwareComponentCaptionSupport;
	
	private I18NAwareFieldSupport<Object> i18NAwareFieldSupport;

	private String[] columnHeadersKeys;

	/**
	 * Creates a new empty i18n table.
	 */
	public I18NTable() {
		super();
	}

	/**
	 * Creates a new empty i18n table with caption.
	 * 
	 * @param caption
	 */
	public I18NTable(@I18NAwareMessage String captionKey) {
		super(captionKey);
		setCaptionMessage(captionKey);
	}

	/**
	 * Creates a new i18n table with caption and connect it to a Container.
	 * 
	 * @param caption
	 * @param dataSource
	 */
	public I18NTable(@I18NAwareMessage String captionKey, Container dataSource) {
		super(captionKey, dataSource);
		setCaptionMessage(captionKey);
	}

	@Override
	public void addActionHandler(Action.Handler actionHandler) {

		super.addActionHandler(actionHandler);

		for (Object itemId : getItemIds() ) {
			registerI18NActions(actionHandler, itemId);
		}

		registerI18NActions(actionHandler, null);
	}
	
	public I18NAwareComponentCaptionSupport getI18NAwareComponentCaptionSupport() {

		if (i18NAwareComponentCaptionSupport == null) {
			i18NAwareComponentCaptionSupport = new I18NAwareComponentCaptionSupport(this);
		}
		return i18NAwareComponentCaptionSupport;
	}

	@Override
	public void i18NUpdate(I18NService i18N) {

		getI18NAwareComponentCaptionSupport().i18NUpdate(i18N);

		updateColumnHeaders(i18N);

		Container items = getContainerDataSource();

		if (items instanceof I18NAware) {
			((I18NAware) items).i18NUpdate(i18N);
		}

		// Actions
		i18NAwareSupport.i18NUpdate(i18N);
	}

	@Override
	public void setCaption(@I18NAwareMessage String captionKey) {
		setCaptionMessage(captionKey);
	}

	@Override
	public void setCaptionMessage(@I18NAwareMessage String captionKey, Object... params) {
		getI18NAwareComponentCaptionSupport().setCaptionMessage(captionKey, params);
	}

	/**
	 * Set Column headers message keys
	 * 
	 * @param columnHeadersKeys
	 */
	public void setColumnHeadersKeys(@I18NAwareMessage String[] columnHeadersKeys) {
		this.columnHeadersKeys = columnHeadersKeys;
		if (I18NServiceImpl.getInstance() != null) {
			updateColumnHeaders(I18NServiceImpl.getInstance());
		}
	}

	@Override
	public void setDescription(@I18NAwareMessage String descriptionKey) {
		setDescriptionMessage(descriptionKey);
	}

	@Override
	public void setDescriptionMessage(@I18NAwareMessage String descriptionKey, Object... descriptionParams) {
		getI18NAwareComponentCaptionSupport().setDescriptionMessage(descriptionKey, descriptionParams);
	}

	@Override
	public void setRequiredError(@I18NAwareMessage String requiredErrorKey) {
		setRequiredErrorMessage(requiredErrorKey);
	}

	@Override
	public void setRequiredErrorMessage(@I18NAwareMessage String requiredErrorKey, Object... requiredErrorParams) {
		getI18NAwareFieldSupport().setRequiredErrorMessage(requiredErrorKey, requiredErrorParams);
	}

	@Override
	public void setRealRequiredError(String requiredMessage) {
		super.setRequiredError(requiredMessage);
	}
	
	@Override
	public void setRealCaption(String caption) {
		super.setCaption(caption);
	}

	@Override
	public void setRealDescription(String description) {
		super.setDescription(description);
	}

	private void registerI18NActions(Action.Handler actionHandler, Object itemId) {

		Action[] actions = actionHandler.getActions(itemId, this);
		if (actions != null) {
			for (Action action : actions ) {
				i18NAwareSupport.add(action);
			}
		}
	}

	private void updateColumnHeaders(I18NService i18N) {
		if (columnHeadersKeys != null) {
			String[] columnHeaders = new String[columnHeadersKeys.length];

			for (int i = 0; i < columnHeadersKeys.length; i++ ) {
				columnHeaders[i] = i18N.getMessage(columnHeadersKeys[i]);
			}

			setColumnHeaders(columnHeaders);
		}
	}
	
	private I18NAwareFieldSupport<Object> getI18NAwareFieldSupport() {

		if (i18NAwareFieldSupport == null) {
			i18NAwareFieldSupport = new I18NAwareFieldSupport<Object>((I18NAwareField<Object>) this);
		}

		return i18NAwareFieldSupport;
	}	
}
