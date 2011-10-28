package com.opnworks.vaadin.i18n.service_impl;

import com.opnworks.vaadin.i18n.I18NAwareCaption;
import com.opnworks.vaadin.i18n.I18NAwareField;
import com.opnworks.vaadin.i18n.I18NAwareValue;
import com.opnworks.vaadin.i18n.data.util.I18NIndexedContainer;
import com.opnworks.vaadin.i18n.event.I18NAction;
import com.opnworks.vaadin.i18n.ui.I18NButton;
import com.opnworks.vaadin.i18n.ui.I18NCheckBox;
import com.opnworks.vaadin.i18n.ui.I18NComboBox;
import com.opnworks.vaadin.i18n.ui.I18NDateField;
import com.opnworks.vaadin.i18n.ui.I18NDefaultFieldFactory;
import com.opnworks.vaadin.i18n.ui.I18NEmbedded;
import com.opnworks.vaadin.i18n.ui.I18NForm;
import com.opnworks.vaadin.i18n.ui.I18NFormLayout;
import com.opnworks.vaadin.i18n.ui.I18NGridLayout;
import com.opnworks.vaadin.i18n.ui.I18NHorizontalLayout;
import com.opnworks.vaadin.i18n.ui.I18NLabel;
import com.opnworks.vaadin.i18n.ui.I18NLink;
import com.opnworks.vaadin.i18n.ui.I18NNativeSelect;
import com.opnworks.vaadin.i18n.ui.I18NOptionGroup;
import com.opnworks.vaadin.i18n.ui.I18NPanel;
import com.opnworks.vaadin.i18n.ui.I18NProgressIndicator;
import com.opnworks.vaadin.i18n.ui.I18NTabSheet;
import com.opnworks.vaadin.i18n.ui.I18NTable;
import com.opnworks.vaadin.i18n.ui.I18NTextArea;
import com.opnworks.vaadin.i18n.ui.I18NTextField;
import com.opnworks.vaadin.i18n.ui.I18NUpload;
import com.opnworks.vaadin.i18n.ui.I18NVerticalLayout;
import com.opnworks.vaadin.i18n.ui.I18NWindow;
import com.vaadin.data.Container;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.Action;
import com.vaadin.terminal.Resource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * I18NAware Factory main purpose is to minimize the coupling between the
 * I18NAware addon and your code.
 * 
 * @author Pedro Rodriguez
 */
public class I18NAwareFactory {

	// Components

	public static Button newButton() {
		return new I18NButton();
	}

	public static Button newButton(String captionKey) {
		return new I18NButton(captionKey);
	}

	public Button newButton(String captionKey, ClickListener listener) {
		return new I18NButton(captionKey, listener);
	}

	public static Embedded newEmbedded() {
		return new I18NEmbedded();
	}

	public static Embedded newEmbedded(String captionKey) {
		return new I18NEmbedded(captionKey);
	}

	public Embedded newEmbedded(String captionKey, Resource resource) {
		return new I18NEmbedded(captionKey, resource);
	}

	public static Label newLabel() {
		return new I18NLabel();
	}

	public static Label newLabel(String valueKey) {
		return new I18NLabel(valueKey);
	}

	public static Link newLink() {
		return new I18NLink();
	}

	public static Link newLink(String captionKey, Resource resource) {
		return new I18NLink(captionKey, resource);
	}

	public static Link newLink(String captionKey, Resource resource,
			String targetName, int width, int height, int border) {
		return new I18NLink(captionKey, resource, targetName, width, height,
				border);
	}

	public static ProgressIndicator newProgressIndicator() {
		return new I18NProgressIndicator();
	}

	public static TabSheet newTabSheet() {
		return new I18NTabSheet();
	}

	public static Upload newUpload() {
		return new I18NUpload();
	}

	public static Upload newUpload(String captionKey, Receiver receiver) {
		return new I18NUpload(captionKey, receiver);
	}

	public static void setButtonCaptionKey(Upload upload,
			String buttonCaptionKey) {
		if (!(upload instanceof I18NUpload)) {
			throw new IllegalArgumentException("Expecting a I18NUpload");
		}
		((I18NUpload) upload).setButtonCaptionKey(buttonCaptionKey);
	}

	public static void setButtonCaptionParams(Upload upload,
			Object[] buttonCaptionParams) {
		if (!(upload instanceof I18NUpload)) {
			throw new IllegalArgumentException("Expecting a I18NUpload");
		}
		((I18NUpload) upload).setButtonCaptionParams(buttonCaptionParams);
	}

	// Containers

	public static GridLayout newGridLayout(int columns, int rows) {
		return new I18NGridLayout(columns, rows);
	}

	public static HorizontalLayout newHorizontalLayout() {
		return new I18NHorizontalLayout();
	}

	public static VerticalLayout newVerticalLayout() {
		return new I18NVerticalLayout();
	}

	public static FormLayout newFormLayout() {
		return new I18NFormLayout();
	}

	public static Panel newPanel() {
		return new I18NPanel();
	}

	public static Panel newPanel(String captionKey) {
		return new I18NPanel(captionKey);
	}

	public static Window newWindow() {
		return new I18NWindow();
	}

	public static Window newWindow(String captionKey) {
		return new I18NWindow(captionKey);
	}

	// Events

	public static Action newAction(String captionKey) {
		return new I18NAction(captionKey);
	}

	public static Action newAction(String captionKey, Resource icon) {
		return new I18NAction(captionKey, icon);
	}

	// Fields

	public static CheckBox newCheckBox() {
		return new I18NCheckBox();
	}

	public static CheckBox newCheckBox(String captionKey) {
		return new I18NCheckBox(captionKey);
	}

	public static CheckBox newCheckBox(String captionKey, ClickListener listener) {
		return new I18NCheckBox(captionKey, listener);
	}

	public static ComboBox newComboBox() {
		return new I18NComboBox();
	}

	public static ComboBox newComboBox(String captionKey) {
		return new I18NComboBox(captionKey);
	}

	public static DateField newDateField() {
		return new I18NDateField();
	}

	public static DateField newDateField(String captionKey) {
		return new I18NDateField(captionKey);
	}

	public static NativeSelect newNativeSelect() {
		return new I18NNativeSelect();
	}

	public static NativeSelect newNativeSelect(String captionKey) {
		return new I18NNativeSelect(captionKey);
	}

	public static OptionGroup newOptionGroup() {
		return new I18NOptionGroup();
	}

	public static OptionGroup newOptionGroup(String captionKey) {
		return new I18NOptionGroup(captionKey);
	}

	public static TextArea newTextArea() {
		return new I18NTextArea();
	}

	public static TextArea newTextArea(String captionKey) {
		return new I18NTextArea(captionKey);
	}

	public static TextField newTextField() {
		return new I18NTextField();
	}

	public static TextField newTextField(String captionKey) {
		return new I18NTextField(captionKey);
	}

	public static TextField newTextField(String captionKey, String value) {
		return new I18NTextField(captionKey, value);
	}

	public static void setItemCaptionKey(OptionGroup optionGroup,
			Object itemId, String caption) {
		if (!(optionGroup instanceof I18NOptionGroup)) {
			throw new IllegalArgumentException("Expecting a OptionGroup");
		}

		((I18NOptionGroup) optionGroup).setItemCaptionKey(itemId, caption);
	}

	// Form

	public static I18NDefaultFieldFactory newDefaultFieldFactory() {
		return new I18NDefaultFieldFactory();
	}

	public static Form newForm() {
		return new I18NForm();
	}

	// Table

	public static IndexedContainer newIndexedContainer() {
		return new I18NIndexedContainer();
	}

	public static Table newTable() {
		return new I18NTable();
	}

	public static Table newTable(String captionKey) {
		return new I18NTable(captionKey);
	}

	public static Table newTable(String captionKey, Container dataSource) {
		return new I18NTable(captionKey, dataSource);
	}

	public static void setColumnHeadersKeys(Table table,
			String[] columnHeadersKeys) {

		if (!(table instanceof I18NTable)) {
			throw new IllegalArgumentException("Expecting a I18NTable");
		}

		((I18NTable) table).setColumnHeadersKeys(columnHeadersKeys);
	}

	// Validators

	// Helpers

	public static <T> void setCaptionKey(T item, String captionKey) {

		if (!(item instanceof I18NAwareCaption)) {
			throw new IllegalArgumentException("Expecting a I18NAwareCaption");
		}

		((I18NAwareCaption) item).setCaptionKey(captionKey);
	}

	public static <T> void setCaptionParams(T item, Object... params) {

		if (!(item instanceof I18NAwareCaption)) {
			throw new IllegalArgumentException("Expecting a I18NAwareCaption");
		}

		((I18NAwareCaption) item).setCaptionParams(params);
	}

	public static <T> void setValueKey(T item, String valueKey) {

		if (!(item instanceof I18NAwareValue)) {
			throw new IllegalArgumentException("Expecting a I18NAwareValue");
		}

		((I18NAwareValue) item).setValueKey(valueKey);
	}

	public static <T> void setValueParams(T item, Object... valueParams) {

		if (!(item instanceof I18NAwareValue)) {
			throw new IllegalArgumentException("Expecting a I18NAwareValue");
		}

		((I18NAwareValue) item).setValueParams(valueParams);
	}

	public static <T> void setRequiredErrorKey(T item, String requiredErrorKey) {

		if (!(item instanceof I18NAwareField)) {
			throw new IllegalArgumentException("Expecting a I18NAwareValue");
		}

		((I18NAwareField) item).setRequiredErrorKey(requiredErrorKey);
	}

	public static <T> void setRequiredErrorParams(T item,
			Object... requiredErrorParams) {

		if (!(item instanceof I18NAwareField)) {
			throw new IllegalArgumentException("Expecting a I18NAwareField");
		}

		((I18NAwareField) item).setRequiredErrorParams(requiredErrorParams);
	}

}
