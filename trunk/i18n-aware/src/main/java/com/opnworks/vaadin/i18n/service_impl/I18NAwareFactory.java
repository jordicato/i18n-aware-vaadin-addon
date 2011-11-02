package com.opnworks.vaadin.i18n.service_impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import com.opnworks.vaadin.i18n.I18NAwareCaption;
import com.opnworks.vaadin.i18n.I18NAwareField;
import com.opnworks.vaadin.i18n.I18NAwareValue;
import com.opnworks.vaadin.i18n.data.util.I18NIndexedContainer;
import com.opnworks.vaadin.i18n.data.validator.I18NEmailValidator;
import com.opnworks.vaadin.i18n.data.validator.I18NStringLengthValidator;
import com.opnworks.vaadin.i18n.event.I18NAction;
import com.opnworks.vaadin.i18n.ui.I18NAbsoluteLayout;
import com.opnworks.vaadin.i18n.ui.I18NButton;
import com.opnworks.vaadin.i18n.ui.I18NCheckBox;
import com.opnworks.vaadin.i18n.ui.I18NComboBox;
import com.opnworks.vaadin.i18n.ui.I18NCssLayout;
import com.opnworks.vaadin.i18n.ui.I18NCustomLayout;
import com.opnworks.vaadin.i18n.ui.I18NDateField;
import com.opnworks.vaadin.i18n.ui.I18NDefaultFieldFactory;
import com.opnworks.vaadin.i18n.ui.I18NEmbedded;
import com.opnworks.vaadin.i18n.ui.I18NForm;
import com.opnworks.vaadin.i18n.ui.I18NFormLayout;
import com.opnworks.vaadin.i18n.ui.I18NGridLayout;
import com.opnworks.vaadin.i18n.ui.I18NHorizontalLayout;
import com.opnworks.vaadin.i18n.ui.I18NHorizontalSplitPanel;
import com.opnworks.vaadin.i18n.ui.I18NInlineDateField;
import com.opnworks.vaadin.i18n.ui.I18NLabel;
import com.opnworks.vaadin.i18n.ui.I18NLink;
import com.opnworks.vaadin.i18n.ui.I18NNativeSelect;
import com.opnworks.vaadin.i18n.ui.I18NOptionGroup;
import com.opnworks.vaadin.i18n.ui.I18NOrderedLayout;
import com.opnworks.vaadin.i18n.ui.I18NPanel;
import com.opnworks.vaadin.i18n.ui.I18NPopupDateField;
import com.opnworks.vaadin.i18n.ui.I18NProgressIndicator;
import com.opnworks.vaadin.i18n.ui.I18NSplitPanel;
import com.opnworks.vaadin.i18n.ui.I18NTabSheet;
import com.opnworks.vaadin.i18n.ui.I18NTable;
import com.opnworks.vaadin.i18n.ui.I18NTextArea;
import com.opnworks.vaadin.i18n.ui.I18NTextField;
import com.opnworks.vaadin.i18n.ui.I18NUpload;
import com.opnworks.vaadin.i18n.ui.I18NVerticalLayout;
import com.opnworks.vaadin.i18n.ui.I18NVerticalSplitPanel;
import com.opnworks.vaadin.i18n.ui.I18NWindow;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.Action;
import com.vaadin.terminal.Resource;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.OrderedLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.SplitPanel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import com.vaadin.ui.Window;

/**
 * I18NAware Factory main purpose is to minimize the coupling between the
 * I18NAware addon and your code.
 * 
 * @author Pedro Rodriguez
 */
@SuppressWarnings("deprecation")
public class I18NAwareFactory {

	// Components

	/**
	 * Creates a Button
	 * 
	 * @return new i18n-aware Button
	 */
	public static Button newButton() {
		return new I18NButton();
	}

	/**
	 * Creates a Button
	 * 
	 * @param captionKey
	 *            key for the Button caption
	 * @return new i18n-aware Button
	 */
	public static Button newButton(String captionKey) {
		return new I18NButton(captionKey);
	}

	/**
	 * Creates a Button
	 * 
	 * @param captionKey
	 *            key for the Button caption
	 * @param listener
	 *            ClickListener for the Button
	 * @return new i18n-aware Button
	 */
	public Button newButton(String captionKey, ClickListener listener) {
		return new I18NButton(captionKey, listener);
	}

	/**
	 * Creates an Embedded
	 * 
	 * @return new i18n-aware Embedded
	 */
	public static Embedded newEmbedded() {
		return new I18NEmbedded();
	}

	/**
	 * Creates an Embedded
	 * 
	 * @param captionKey
	 *            key for the Embedded caption
	 * @return new i18n-aware Embedded
	 */
	public static Embedded newEmbedded(String captionKey) {
		return new I18NEmbedded(captionKey);
	}

	/**
	 * Creates an Embedded
	 * 
	 * @param captionKey
	 *            key for the Embedded caption
	 * @param resource
	 *            Resource to embed
	 * @return new i18n-aware Embedded
	 */
	public Embedded newEmbedded(String captionKey, Resource resource) {
		return new I18NEmbedded(captionKey, resource);
	}

	/**
	 * Creates a Label
	 * 
	 * @return new i18n-aware Label
	 */
	public static Label newLabel() {
		return new I18NLabel();
	}

	/**
	 * Creates a Label
	 * 
	 * @param valueKey
	 *            key for the Label value
	 * @return new i18n-aware Label
	 */
	public static Label newLabel(String valueKey) {
		return new I18NLabel(valueKey);
	}

	/**
	 * Creates a Link
	 * 
	 * @return new i18n-aware Link
	 */
	public static Link newLink() {
		return new I18NLink();
	}

	/**
	 * Creates a Link
	 * 
	 * @param captionKey
	 *            key for the Link caption
	 * @param resource
	 *            Resource for the Link
	 * @return new i18n-aware Link
	 */
	public static Link newLink(String captionKey, Resource resource) {
		return new I18NLink(captionKey, resource);
	}

	/**
	 * Creates a Link
	 * 
	 * @param captionKey
	 *            key for the Link caption
	 * @param resource
	 *            Resource for the Link
	 * @param targetName
	 *            target for the Link
	 * @param width
	 *            width of the Link
	 * @param height
	 *            height of the Link
	 * @param border
	 *            border of the Link
	 * @return new i18n-aware Link
	 */
	public static Link newLink(String captionKey, Resource resource,
			String targetName, int width, int height, int border) {
		return new I18NLink(captionKey, resource, targetName, width, height,
				border);
	}

	/**
	 * Creates a ProgressIndicator
	 * 
	 * @return new i18n-aware ProgressIndicator
	 */
	public static ProgressIndicator newProgressIndicator() {
		return new I18NProgressIndicator();
	}

	/**
	 * Creates a TabSheet
	 * 
	 * @return new i18n-aware TabSheet
	 */
	public static TabSheet newTabSheet() {
		return new I18NTabSheet();
	}

	/**
	 * Creates an Upload
	 * 
	 * @return new i18n-aware Upload
	 */
	public static Upload newUpload() {
		return new I18NUpload();
	}

	/**
	 * Creates an Upload
	 * 
	 * @param captionKey
	 *            key for the Upload caption
	 * @param receiver
	 *            for the Upload
	 * @return new i18n-aware Upload
	 */
	public static Upload newUpload(String captionKey, Receiver receiver) {
		return new I18NUpload(captionKey, receiver);
	}

	/**
	 * Sets caption key for an Upload
	 * 
	 * @param Upload
	 *            component to be set
	 * @param buttonCaptionKey
	 *            key for the Button caption
	 */
	public static void setButtonCaptionKey(Upload upload,
			String buttonCaptionKey) {
		if (!(upload instanceof I18NUpload)) {
			throw new IllegalArgumentException("Expecting a I18NUpload");
		}
		((I18NUpload) upload).setButtonCaptionKey(buttonCaptionKey);
	}

	/**
	 * Sets params for the button caption of an Upload component
	 * 
	 * @param Upload
	 *            component to be set
	 * @param buttonCaptionParams
	 *            params for the Button caption key
	 */
	public static void setButtonCaptionParams(Upload upload,
			Object[] buttonCaptionParams) {
		if (!(upload instanceof I18NUpload)) {
			throw new IllegalArgumentException("Expecting a I18NUpload");
		}
		((I18NUpload) upload).setButtonCaptionParams(buttonCaptionParams);
	}

	// Containers

	/**
	 * Creates a GridLayout
	 * 
	 * @param columns
	 *            number of columns
	 * @param rows
	 *            number of rows
	 * @return new i18n-aware GridLayout
	 */
	public static GridLayout newGridLayout(int columns, int rows) {
		return new I18NGridLayout(columns, rows);
	}

	/**
	 * Creates an HorizontalLayout
	 * 
	 * @return new i18n-aware HorizontalLayout
	 */
	public static HorizontalLayout newHorizontalLayout() {
		return new I18NHorizontalLayout();
	}

	/**
	 * Creates a VerticalLayout
	 * 
	 * @return new i18n-aware VerticalLayout
	 */
	public static VerticalLayout newVerticalLayout() {
		return new I18NVerticalLayout();
	}

	/**
	 * Creates a FormLayout
	 * 
	 * @return new i18n-aware FormLayout
	 */
	public static FormLayout newFormLayout() {
		return new I18NFormLayout();
	}

	/**
	 * Creates a HorizontalSplitPanel
	 * 
	 * @return new i18n-aware HorizontalSplitPanel
	 */
	public static HorizontalSplitPanel newHorizontalSplitPanel() {
		return new I18NHorizontalSplitPanel();
	}

	/**
	 * Creates a new i18n VerticalSplitPanel.
	 * 
	 * @return new i18n-aware VerticalSplitPanel
	 */
	public static VerticalSplitPanel newVerticalSplitPanel() {
		return new I18NVerticalSplitPanel();
	}

	/**
	 * Creates a new i18n split panel. The orientation of the panels is
	 * <code>ORIENTATION_VERTICAL</code>.
	 * 
	 * @return new i18n-aware SplitPanel
	 */
	public static SplitPanel newSplitPanel() {
		return new I18NSplitPanel();
	}

	/**
	 * Creates an i18n AbsoluteLayout with full size.
	 * 
	 * @return new i18n-aware AbsoluteLayout
	 */
	public static AbsoluteLayout newAbsoluteLayout() {
		return new I18NAbsoluteLayout();
	}

	/**
	 * Creates a CssLayout
	 * 
	 * @return new i18n-aware CssLayout
	 */
	public static CssLayout newCssLayout() {
		return new I18NCssLayout();
	}

	/**
	 * Creates an i18n custom layout with the template given in the stream.
	 * 
	 * @param templateStream
	 *            Stream containing template data. Must be using UTF-8 encoding.
	 *            To use a String as a template use for instance new
	 *            ByteArrayInputStream("<template>".getBytes()).
	 * @param streamLength
	 *            Length of the templateStream
	 * @throws IOException
	 */
	public static CustomLayout newCustomLayout(InputStream templateStream)
			throws IOException {
		return new I18NCustomLayout(templateStream);
	}

	/**
	 * Creates an i18n custom layout with given template name. Template file is
	 * fetched from "<theme>/layout/<templateName>".
	 */
	public static CustomLayout newCustomLayout(String template) {
		return new I18NCustomLayout(template);
	}

	/**
	 * Creates a OrderedLayout
	 * 
	 * @return new i18n-aware CssLayout
	 */
	public static OrderedLayout newOrderedLayout() {
		return new I18NOrderedLayout();
	}

	/**
	 * Creates a Panel
	 * 
	 * @return new i18n-aware Panel
	 */
	public static Panel newPanel() {
		return new I18NPanel();
	}

	/**
	 * Sets caption key for a Panel
	 * 
	 * @param captionKey
	 *            key for the Panel caption
	 * @return new i18n-aware
	 */
	public static Panel newPanel(String captionKey) {
		return new I18NPanel(captionKey);
	}

	/**
	 * Creates a Window
	 * 
	 * @return new i18n-aware Window
	 */
	public static Window newWindow() {
		return new I18NWindow();
	}

	/**
	 * Creates a Window
	 * 
	 * @param captionKey
	 *            key for the Window caption
	 * @return new i18n-aware Window
	 */
	public static Window newWindow(String captionKey) {
		return new I18NWindow(captionKey);
	}

	// Events

	/**
	 * Creates an Action
	 * 
	 * @param captionKey
	 *            key for the Action caption
	 * @return new i18n-aware Action
	 */
	public static Action newAction(String captionKey) {
		return new I18NAction(captionKey);
	}

	/**
	 * Creates an Action
	 * 
	 * @param captionKey
	 *            key for the Action caption
	 * @param icon
	 *            resource for the icon
	 * @return new i18n-aware Action
	 */
	public static Action newAction(String captionKey, Resource icon) {
		return new I18NAction(captionKey, icon);
	}

	// Fields

	/**
	 * Creates a CheckBox
	 * 
	 * @return new i18n-aware CheckBox
	 */
	public static CheckBox newCheckBox() {
		return new I18NCheckBox();
	}

	/**
	 * Creates a CheckBox
	 * 
	 * @param captionKey
	 *            key for the CheckBox caption
	 * @return new i18n-aware CheckBox
	 */
	public static CheckBox newCheckBox(String captionKey) {
		return new I18NCheckBox(captionKey);
	}

	/**
	 * Creates a CheckBox
	 * 
	 * @param captionKey
	 *            key for the CheckBox caption
	 * @param listener
	 *            listener for the CheckBox
	 * @return new i18n-aware CheckBox
	 */
	public static CheckBox newCheckBox(String captionKey, ClickListener listener) {
		return new I18NCheckBox(captionKey, listener);
	}

	/**
	 * Creates a ComboBox
	 * 
	 * @return new i18n-aware ComboBox
	 */
	public static ComboBox newComboBox() {
		return new I18NComboBox();
	}

	/**
	 * Creates a ComboBox
	 * 
	 * @param captionKey
	 *            key for the ComboBox caption
	 * @return new i18n-aware ComboBox
	 */
	public static ComboBox newComboBox(String captionKey) {
		return new I18NComboBox(captionKey);
	}

	/**
	 * Creates a DateField
	 * 
	 * @return new i18n-aware DateField
	 */
	public static DateField newDateField() {
		return new I18NDateField();
	}

	/**
	 * Creates a DateField
	 * 
	 * @param captionKey
	 *            key for the DateField caption
	 * @return new i18n-aware DateField
	 */
	public static DateField newDateField(String captionKey) {
		return new I18NDateField(captionKey);
	}

	/**
	 * Creates an empty i18n <code>PopupDateField</code> with no caption.
	 */
	public static PopupDateField newPopupDateField() {
		return new I18NPopupDateField();
	}

	/**
	 * Creates an i18n <code>PopupDateField</code> with a dataSource.
	 * 
	 * @param dataSource
	 */
	public static PopupDateField newPopupDateField(Property dataSource)
			throws IllegalArgumentException {
		return new I18NPopupDateField(dataSource);
	}

	/**
	 * Creates an i18n <code>PopupDateField</code> with caption message key
	 * and initial value.
	 * 
	 * @param captionKey
	 *            the caption message key of the datefield.
	 * @param value
	 */
	public static PopupDateField newPopupDateField(String captionKey, Date value) {
		return new I18NPopupDateField(captionKey, value);
	}

	/**
	 * Creates an i18n <code>PopupDateField</code> with caption message key
	 * and dataSource.
	 * 
	 * @param captionKey
	 *            the caption message key of the PopupDateField.
	 * @param dataSource
	 */
	public static PopupDateField newPopupDateField(String captionKey, Property dataSource) {
		return new I18NPopupDateField(captionKey, dataSource);
	}

	/**
	 * Creates an empty i18n <code>PopupDateField</code> with caption message
	 * key.
	 * 
	 * @param captionKey
	 *            the caption message key of the PopupDateField.
	 */
	public static PopupDateField newPopupDateField(String captionKey) {
		return new I18NPopupDateField(captionKey);
	}
	
	public static void setInputPromptKey(PopupDateField popupDateField, String inputPromptKey) {
		if (!(popupDateField instanceof I18NPopupDateField)) {
			throw new IllegalArgumentException("Expecting a I18NPopupDateField");
		}
		((I18NPopupDateField) popupDateField).setInputPromptKey(inputPromptKey);
	}

	public static void setInputPromptKeyParams(PopupDateField popupDateField, Object... inputPromptParams) {
		if (!(popupDateField instanceof I18NPopupDateField)) {
			throw new IllegalArgumentException("Expecting a I18NPopupDateField");
		}
		((I18NPopupDateField) popupDateField).setInputPromptKeyParams(inputPromptParams);
	}
	
	/**
	 * Creates an empty i18n <code>InlineDateField</code> with no caption.
	 */
	public static InlineDateField newInlineDateField() {
		return new I18NInlineDateField();
	}

	/**
	 * Creates an i18n <code>InlineDateField</code> with a dataSource.
	 * 
	 * @param dataSource
	 */
	public static InlineDateField newInlineDateField(Property dataSource)
			throws IllegalArgumentException {
		return new I18NInlineDateField(dataSource);
	}

	/**
	 * Creates an i18n <code>InlineDateField</code> with caption message key
	 * and initial value.
	 * 
	 * @param captionKey
	 *            the caption message key of the datefield.
	 * @param value
	 */
	public static InlineDateField newInlineDateField(String captionKey, Date value) {
		return new I18NInlineDateField(captionKey, value);
	}

	/**
	 * Creates an i18n <code>InlineDateField</code> with caption message key
	 * and dataSource.
	 * 
	 * @param captionKey
	 *            the caption message key of the InlineDateField.
	 * @param dataSource
	 */
	public static InlineDateField newInlineDateField(String captionKey, Property dataSource) {
		return new I18NInlineDateField(captionKey, dataSource);
	}

	/**
	 * Creates an empty i18n <code>InlineDateField</code> with caption message
	 * key.
	 * 
	 * @param captionKey
	 *            the caption message key of the InlineDateField.
	 */
	public static InlineDateField newInlineDateField(String captionKey) {
		return new I18NInlineDateField(captionKey);
	}

	
	/**
	 * Creates a NativeSelect
	 * 
	 * @return new i18n-aware NativeSelect
	 */
	public static NativeSelect newNativeSelect() {
		return new I18NNativeSelect();
	}

	/**
	 * Creates a NativeSelect
	 * 
	 * @param captionKey
	 *            key for the NativeSelect caption
	 * @return new i18n-aware NativeSelect
	 */
	public static NativeSelect newNativeSelect(String captionKey) {
		return new I18NNativeSelect(captionKey);
	}

	/**
	 * Creates an OptionGroup
	 * 
	 * @return new i18n-aware OptionGroup
	 */
	public static OptionGroup newOptionGroup() {
		return new I18NOptionGroup();
	}

	/**
	 * Creates an OptionGroup
	 * 
	 * @param captionKey
	 *            key for the OptionGroup caption
	 * @return new i18n-aware OptionGroup
	 */
	public static OptionGroup newOptionGroup(String captionKey) {
		return new I18NOptionGroup(captionKey);
	}

	/**
	 * Sets caption key for OptionGroup
	 * 
	 * @param optionGroup
	 *            OptionGroup to be set
	 * @param itemId
	 *            itemid in the form
	 * @param captionKey
	 *            key for the OptionGroup caption
	 */
	public static void setItemCaptionKey(OptionGroup optionGroup,
			Object itemId, String captionKey) {
		if (!(optionGroup instanceof I18NOptionGroup)) {
			throw new IllegalArgumentException("Expecting a OptionGroup");
		}

		((I18NOptionGroup) optionGroup).setItemCaptionKey(itemId, captionKey);
	}

	/**
	 * Creates a TextArea
	 * 
	 * @return new i18n-aware TextArea
	 */
	public static TextArea newTextArea() {
		return new I18NTextArea();
	}

	/**
	 * Creates a TextArea
	 * 
	 * @param captionKey
	 *            key for the TextArea caption
	 * @return new i18n-aware TextArea
	 */
	public static TextArea newTextArea(String captionKey) {
		return new I18NTextArea(captionKey);
	}

	/**
	 * Creates a TextField
	 * 
	 * @return new i18n-aware TextField
	 */
	public static TextField newTextField() {
		return new I18NTextField();
	}

	/**
	 * Creates a TextField
	 * 
	 * @param captionKey
	 *            key for the TextField caption
	 * @return new i18n-aware TextField
	 */
	public static TextField newTextField(String captionKey) {
		return new I18NTextField(captionKey);
	}

	/**
	 * Creates a TextField
	 * 
	 * @param captionKey
	 *            key for the TextField caption
	 * @param value
	 *            value for the TextField
	 * @return new i18n-aware TextField
	 */
	public static TextField newTextField(String captionKey, String value) {
		return new I18NTextField(captionKey, value);
	}

	// Form

	/**
	 * Creates a I18NDefaultFieldFactory
	 * 
	 * @return new i18n-aware I18NDefaultFieldFactory
	 */
	public static I18NDefaultFieldFactory newDefaultFieldFactory() {
		return new I18NDefaultFieldFactory();
	}

	/**
	 * Creates a Form
	 * 
	 * @return new i18n-aware Form
	 */
	public static Form newForm() {
		return new I18NForm();
	}

	// Table

	/**
	 * Creates an IndexedContainer
	 * 
	 * @return new i18n-aware IndexedContainer
	 */
	public static IndexedContainer newIndexedContainer() {
		return new I18NIndexedContainer();
	}

	/**
	 * Creates a Table
	 * 
	 * @return new i18n-aware Table
	 */
	public static Table newTable() {
		return new I18NTable();
	}

	/**
	 * Creates a Table
	 * 
	 * @param captionKey
	 *            key for the table caption
	 * @return new i18n-aware Table
	 */
	public static Table newTable(String captionKey) {
		return new I18NTable(captionKey);
	}

	/**
	 * Creates a Table
	 * 
	 * @param captionKey
	 *            key for the table caption
	 * @param dataSource
	 *            data source for the Table
	 * @return new i18n-aware Table
	 */
	public static Table newTable(String captionKey, Container dataSource) {
		return new I18NTable(captionKey, dataSource);
	}

	/**
	 * Creates a Table
	 * 
	 * @param table
	 *            Table type I18NTable
	 * @param columnHeadersKeys
	 *            key array for column header names
	 * @see I18NTable
	 */
	public static void setColumnHeadersKeys(Table table,
			String[] columnHeadersKeys) {

		if (!(table instanceof I18NTable)) {
			throw new IllegalArgumentException("Expecting a I18NTable");
		}

		((I18NTable) table).setColumnHeadersKeys(columnHeadersKeys);
	}

	// Validators
	
	/**
	 * Creates an EmailValidator
	 * 
	 * @param errorMessageKey
	 *            key for error message
	 * @param fieldNameKey
	 *            key for field name
	 * @return new i18n-aware EmailValidator
	 */
	public static EmailValidator newEmailValidator(String errorMessageKey,
			String fieldNameKey) {
		return new I18NEmailValidator(errorMessageKey, fieldNameKey);
	}

	/**
	 * Sets error message key for emailValidator
	 * 
	 * @param emailValidator
	 *            validator to update
	 * @param errorMessageKey
	 *            new error message key
	 */
	public static void setErrorMessageKey(EmailValidator emailValidator,
			String errorMessageKey) {
		emailValidator.setErrorMessage(errorMessageKey);
	}

	/**
	 * Creates a StringValidatoe
	 * 
	 * @param errorMessageKey
	 *            key for error message
	 * @param fieldNameKey
	 *            key for field name
	 * @param minLength
	 *            minimum string length
	 * @param maxLength
	 *            maximum string length
	 * @param allowNull
	 *            null allowed
	 * @return new i18n-aware StringLengthValidator
	 */
	public static StringLengthValidator newStringLengthValidator(
			String errorMessageKey, String fieldNameKey, int minLength,
			int maxLength, boolean allowNull) {
		return new I18NStringLengthValidator(errorMessageKey, fieldNameKey,
				minLength, maxLength, allowNull);
	}

	/**
	 * Sets error message key for StringValidator
	 * 
	 * @param stringLengthValidator
	 *            validator to update
	 * @param errorMessageKey
	 *            new error message key
	 */
	public static void setErrorMessageKey(
			StringLengthValidator stringLengthValidator, String errorMessageKey) {
		stringLengthValidator.setErrorMessage(errorMessageKey);
	}

	// Helpers

	/**
	 * Sets caption key
	 * 
	 * @param item
	 *            Item to be updated type I18NAwareCaption
	 * @param captionKey
	 *            new caption key
	 * @see I18NAwareCaption
	 */
	public static <T> void setCaptionKey(T item, String captionKey) {

		if (!(item instanceof I18NAwareCaption)) {
			throw new IllegalArgumentException("Expecting a I18NAwareCaption");
		}

		((I18NAwareCaption) item).setCaptionKey(captionKey);
	}

	/**
	 * Set caption key params
	 * 
	 * @param item
	 *            Item to be updated type I18NAwareCaption
	 * @param params
	 *            parameters for the caption
	 * @see I18NAwareCaption
	 */
	public static <T> void setCaptionParams(T item, Object... params) {

		if (!(item instanceof I18NAwareCaption)) {
			throw new IllegalArgumentException("Expecting a I18NAwareCaption");
		}

		((I18NAwareCaption) item).setCaptionParams(params);
	}

	/**
	 * Set value key
	 * 
	 * @param item
	 *            Item to be updated type I18NAwareValue
	 * @param valueKey
	 *            new value
	 * @see I18NAwareValue
	 */
	public static <T> void setValueKey(T item, String valueKey) {

		if (!(item instanceof I18NAwareValue)) {
			throw new IllegalArgumentException("Expecting a I18NAwareValue");
		}

		((I18NAwareValue) item).setValueKey(valueKey);
	}

	/**
	 * Set params for the value
	 * 
	 * @param item
	 *            Item to be updated I18NAwareValue
	 * @param valueParams
	 *            parameters for the value
	 * @see I18NAwareValue
	 */
	public static <T> void setValueParams(T item, Object... valueParams) {

		if (!(item instanceof I18NAwareValue)) {
			throw new IllegalArgumentException("Expecting a I18NAwareValue");
		}

		((I18NAwareValue) item).setValueParams(valueParams);
	}

	/**
	 * Set key for error required message
	 * 
	 * @param item
	 *            Item to be updated type I18NAwareValue
	 * @param requiredErrorKey
	 *            key for required error message
	 * @see I18NAwareValue
	 */
	public static <T> void setRequiredErrorKey(T item, String requiredErrorKey) {

		if (!(item instanceof I18NAwareField)) {
			throw new IllegalArgumentException("Expecting a I18NAwareValue");
		}

		((I18NAwareField) item).setRequiredErrorKey(requiredErrorKey);
	}

	/**
	 * Set params for error required message
	 * 
	 * @param item
	 *            Item to be updated type I18NAwareField
	 * @param requiredErrorParams
	 *            params for required error message
	 * @see I18NAwareField
	 */
	public static <T> void setRequiredErrorParams(T item,
			Object... requiredErrorParams) {

		if (!(item instanceof I18NAwareField)) {
			throw new IllegalArgumentException("Expecting a I18NAwareField");
		}

		((I18NAwareField) item).setRequiredErrorParams(requiredErrorParams);
	}

}
