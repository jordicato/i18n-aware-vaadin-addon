package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAware;
import com.opnworks.vaadin.i18n.I18NAwareCaption;
import com.opnworks.vaadin.i18n.I18NAwareComponent;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.processor.GenerateInstantiateSubclassAspect;
import com.opnworks.vaadin.i18n.service_impl.I18NServiceImpl;
import com.opnworks.vaadin.i18n.support.I18NAwareComponentCaptionSupport;
import com.opnworks.vaadin.i18n.support.I18NAwareSupport;

import com.vaadin.data.Container;
import com.vaadin.event.Action;
import com.vaadin.ui.TreeTable;

/**
 * The I18NTreeable
 * 
 * @author Aniceto Perez ( Ipse )
 */
@GenerateInstantiateSubclassAspect
public class I18NTreeTable extends TreeTable implements I18NAwareComponent,
		I18NAwareCaption {

	private static final long serialVersionUID = -812252356204823009L;

	private I18NAwareSupport i18NAwareSupport = new I18NAwareSupport();

	private I18NAwareComponentCaptionSupport i18NAwareComponentCaptionSupport;

	private String[] columnHeadersKeys;
 
	/**
	 * Creates a new empty i18n TreeTable.
	 */
	public I18NTreeTable() {
		super();
	}

	/**
	 * Creates a new empty i18n TreeTable with caption.
	 * 
	 * @param caption
	 */
	public I18NTreeTable(@I18NAwareMessage String captionKey) {
		super(captionKey);
		setCaptionMessage(captionKey);
	}

	/**
	 * Creates a new i18n TreeTable with caption and connect it to a Container.
	 * 
	 * @param caption
	 * @param dataSource
	 */
	public I18NTreeTable(@I18NAwareMessage String captionKey, Container dataSource) {
		super(captionKey, dataSource);
		setCaptionMessage(captionKey);
	}

	/**
	 * Set Column headers message keys
	 * 
	 * @param columnHeadersKeys
	 */
	public void setColumnHeadersKeys(String[] columnHeadersKeys) {
		this.columnHeadersKeys = columnHeadersKeys;
		if (I18NServiceImpl.getInstance() != null) {
			updateColumnHeaders(I18NServiceImpl.getInstance());
		}
	}

	public void setRealCaption(String caption) {
		super.setCaption(caption);
	}

	public void setCaption(String captionKey) {
		setCaptionMessage(captionKey);
	}

	public void setCaptionMessage(@I18NAwareMessage String captionKey,
			Object... params) {
		getI18NAwareComponentCaptionSupport().setCaptionMessage(captionKey, params);
	}

	public void setRealDescription(String description) {
		super.setDescription(description);
	}

	public void setDescription(String descriptionKey) {
		setDescriptionMessage(descriptionKey);
	}

	public void setDescriptionMessage(@I18NAwareMessage String descriptionKey,
			Object... descriptionParams) {
		getI18NAwareComponentCaptionSupport().setDescriptionMessage(descriptionKey,
				descriptionParams);
	}

	public void addActionHandler(Action.Handler actionHandler) {

		super.addActionHandler(actionHandler);

		for (Object itemId : getItemIds()) {
			registerI18NActions(actionHandler, itemId);
		}

		registerI18NActions(actionHandler, null);
	}

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

	private void updateColumnHeaders(I18NService i18N) {
		if (columnHeadersKeys != null) {
			String[] columnHeaders = new String[columnHeadersKeys.length];

			for (int i = 0; i < columnHeadersKeys.length; i++) {
				columnHeaders[i] = i18N.getMessage(columnHeadersKeys[i]);
			}

			setColumnHeaders(columnHeaders);
		}
	}

	private void registerI18NActions(Action.Handler actionHandler, Object itemId) {

		Action[] actions = actionHandler.getActions(itemId, this);
		if (actions != null) {
			for (int i = 0; i < actions.length; i++) {
				i18NAwareSupport.add(actions[i]);
			}
		}
	}
	
	public I18NAwareComponentCaptionSupport getI18NAwareComponentCaptionSupport() {

		if (i18NAwareComponentCaptionSupport == null) {
			i18NAwareComponentCaptionSupport = new I18NAwareComponentCaptionSupport(
					this);
		}
		return i18NAwareComponentCaptionSupport;
	}
}