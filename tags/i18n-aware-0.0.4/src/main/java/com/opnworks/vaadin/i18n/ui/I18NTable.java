package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAware;
import com.opnworks.vaadin.i18n.I18NAwareCaption;
import com.opnworks.vaadin.i18n.I18NAwareComponent;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.support.I18NAwareComponentCaptionSupport;
import com.opnworks.vaadin.i18n.support.I18NAwareSupport;
import com.vaadin.data.Container;
import com.vaadin.event.Action;
import com.vaadin.ui.Table;

/**
 * The I18NTable
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NTable extends Table implements I18NAwareComponent,
		I18NAwareCaption {

	private static final long serialVersionUID = -8122523562074823009L;

	private I18NAwareSupport i18NAwareSupport = new I18NAwareSupport();

	private I18NAwareComponentCaptionSupport i18NAwareComponentCaptionSupport = new I18NAwareComponentCaptionSupport(
			this);

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
	public I18NTable(String captionKey) {
		super(captionKey);
		setCaptionKey(captionKey);
	}

	/**
	 * Creates a new i18n table with caption and connect it to a Container.
	 * 
	 * @param caption
	 * @param dataSource
	 */
	public I18NTable(String captionKey, Container dataSource) {
		super(captionKey, dataSource);
		setCaptionKey(captionKey);
	}

	/**
	 * Set Column headers message keys
	 * 
	 * @param columnHeadersKeys
	 */
	public void setColumnHeadersKeys(String[] columnHeadersKeys) {
		this.columnHeadersKeys = columnHeadersKeys;
	}

	@Override
	public void setCaptionKey(String captionKey) {
		i18NAwareComponentCaptionSupport.setCaptionKey(captionKey);
	}

	@Override
	public void setCaptionParams(Object... params) {
		i18NAwareComponentCaptionSupport.setCaptionParams(params);
	}

	@Override
	public void addActionHandler(Action.Handler actionHandler) {

		super.addActionHandler(actionHandler);

		for (Object itemId : getItemIds()) {
			registerI18NActions(actionHandler, itemId);
		}

		registerI18NActions(actionHandler, null);
	}

	@Override
	public void i18NUpdate(I18NService i18N) {

		i18NAwareComponentCaptionSupport.updateLabels(i18N);

		if (columnHeadersKeys != null) {
			String[] columnHeaders = new String[columnHeadersKeys.length];

			for (int i = 0; i < columnHeadersKeys.length; i++) {
				columnHeaders[i] = i18N.getMessage(columnHeadersKeys[i]);
			}

			setColumnHeaders(columnHeaders);
		}

		Container items = getContainerDataSource();

		if (items instanceof I18NAware) {
			((I18NAware) items).i18NUpdate(i18N);
		}

		// Actions
		i18NAwareSupport.updateLabels(i18N);
	}

	private void registerI18NActions(Action.Handler actionHandler, Object itemId) {

		Action[] actions = actionHandler.getActions(itemId, this);
		if (actions != null) {
			for (int i = 0; i < actions.length; i++) {
				i18NAwareSupport.add(actions[i]);
			}
		}
	}
}
