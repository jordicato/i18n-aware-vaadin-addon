package com.opnworks.vaadin.i18n.service_impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Date;

import com.opnworks.vaadin.i18n.I18NAwareCaption;
import com.opnworks.vaadin.i18n.I18NAwareField;
import com.opnworks.vaadin.i18n.I18NAwareFormFieldFactory;
import com.opnworks.vaadin.i18n.I18NAwareLayout;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NAwareValue;
import com.opnworks.vaadin.i18n.data.util.I18NIndexedContainer;
import com.opnworks.vaadin.i18n.data.validator.I18NEmailValidator;
import com.opnworks.vaadin.i18n.data.validator.I18NStringLengthValidator;
import com.opnworks.vaadin.i18n.event.I18NAction;
import com.opnworks.vaadin.i18n.ui.I18NAbsoluteLayout;
import com.opnworks.vaadin.i18n.ui.I18NAccordion;
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
import com.opnworks.vaadin.i18n.ui.I18NListSelect;
import com.opnworks.vaadin.i18n.ui.I18NLoginForm;
import com.opnworks.vaadin.i18n.ui.I18NMenuBar;
import com.opnworks.vaadin.i18n.ui.I18NNativeButton;
import com.opnworks.vaadin.i18n.ui.I18NNativeSelect;
import com.opnworks.vaadin.i18n.ui.I18NOptionGroup;
import com.opnworks.vaadin.i18n.ui.I18NOrderedLayout;
import com.opnworks.vaadin.i18n.ui.I18NPanel;
import com.opnworks.vaadin.i18n.ui.I18NPasswordField;
import com.opnworks.vaadin.i18n.ui.I18NPopupDateField;
import com.opnworks.vaadin.i18n.ui.I18NPopupView;
import com.opnworks.vaadin.i18n.ui.I18NProgressIndicator;
import com.opnworks.vaadin.i18n.ui.I18NRichTextArea;
import com.opnworks.vaadin.i18n.ui.I18NSelect;
import com.opnworks.vaadin.i18n.ui.I18NSlider;
import com.opnworks.vaadin.i18n.ui.I18NSplitPanel;
import com.opnworks.vaadin.i18n.ui.I18NTabSheet;
import com.opnworks.vaadin.i18n.ui.I18NTable;
import com.opnworks.vaadin.i18n.ui.I18NTextArea;
import com.opnworks.vaadin.i18n.ui.I18NTextField;
import com.opnworks.vaadin.i18n.ui.I18NTree;
import com.opnworks.vaadin.i18n.ui.I18NTwinColSelect;
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
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Link;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.LoginForm;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.OrderedLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.Select;
import com.vaadin.ui.Slider;
import com.vaadin.ui.SplitPanel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.TwinColSelect;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import com.vaadin.ui.Window;

/**
 * I18NAware Factory main purpose is to minimize the coupling between the I18NAware addon and your code.
 * 
 * @author Pedro Rodriguez
 */
@SuppressWarnings("all")
public class I18NAwareFactory {

	// Components

	/**
	 * Add a new item to the menu bar. Icon and command can be null, but a caption must be given.
	 * 
	 * @param caption
	 *            the text for the menu item
	 * @param icon
	 *            the icon for the menu item
	 * @param command
	 *            the command for the menu item @
	 */
	public static MenuBar.MenuItem addItem(MenuBar menuBar, @I18NAwareMessage String captionKey, Resource icon, MenuBar.Command command) {

		if (!(menuBar instanceof I18NMenuBar)) {
			throw new IllegalArgumentException("Expecting a I18NMenuBar");
		}

		return ((I18NMenuBar) menuBar).addItem(captionKey, icon, command);
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
	 * Creates a Accordion
	 * 
	 * @return new i18n-aware Accordion
	 */
	public static Accordion newAccordion() {
		return new I18NAccordion();
	}

	/**
	 * Creates an Action
	 * 
	 * @param captionKey
	 *            key for the Action caption
	 * @return new i18n-aware Action
	 */
	public static Action newAction(@I18NAwareMessage String captionKey) {
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
	public static Action newAction(@I18NAwareMessage String captionKey, Resource icon) {
		return new I18NAction(captionKey, icon);
	}

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
	public static Button newButton(@I18NAwareMessage String captionKey) {
		return new I18NButton(captionKey);
	}

	/**
	 * Creates a new switch button with initial value.
	 * 
	 * @param captionKey
	 *            key for the Button caption
	 * @param state
	 *            the Initial state of the switch-button.
	 * @param initialState
	 * @deprecated use {@link CheckBox} instead of Button in "switchmode"
	 */
	@Deprecated
	public static Button newButton(@I18NAwareMessage String captionKey, boolean initialState) {
		return new I18NButton(captionKey, initialState);
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
	public static Button newButton(@I18NAwareMessage String captionKey, ClickListener listener) {
		return new I18NButton(captionKey, listener);
	}

	/**
	 * Creates a new push button with a method listening button clicks. Using this method is discouraged because it cannot be checked during
	 * compilation. Use {@link #Button(String, com.vaadin.ui.Button.ClickListener)} instead. The method must have either no parameters, or only one
	 * parameter of Button.ClickEvent type.
	 * 
	 * @param captionKey
	 *            key for the Button caption
	 * @param target
	 *            the Object having the method for listening button clicks.
	 * @param methodName
	 *            the name of the method in target object, that receives button click events.
	 */
	public static Button newButton(@I18NAwareMessage String captionKey, Object target, String methodName) {
		return new I18NButton(captionKey, target, methodName);
	}

	/**
	 * Creates a new switch button that is connected to a boolean property.
	 * 
	 * @param captionKey
	 *            key for the Button caption
	 * @param state
	 *            the Initial state of the switch-button.
	 * @param dataSource
	 * @deprecated use {@link CheckBox} instead of Button in "switchmode"
	 */
	@Deprecated
	public static Button newButton(@I18NAwareMessage String captionKey, Property dataSource) {
		return new I18NButton(captionKey, dataSource);
	}

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
	public static CheckBox newCheckBox(@I18NAwareMessage String captionKey) {
		return new I18NCheckBox(captionKey);
	}

	/**
	 * Creates a new switch button with a caption and a set initial state.
	 * 
	 * @param captionKey
	 *            the caption message key of the switch button
	 * @param initialState
	 *            the initial state of the switch button
	 */
	public static CheckBox newCheckBox(@I18NAwareMessage String captionKey, boolean initialState) {
		return new I18NCheckBox(captionKey, initialState);
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
	public static CheckBox newCheckBox(@I18NAwareMessage String captionKey, ClickListener listener) {
		return new I18NCheckBox(captionKey, listener);
	}

	/**
	 * Convenience method for creating a new switch button with a method listening button clicks. Using this method is discouraged because it cannot
	 * be checked during compilation. Use {@link #addListener(Class, Object, _Method)} or {@link #addListener(com.vaadin.ui.Component.Listener)}
	 * instead. The method must have either no parameters, or only one parameter of Button.ClickEvent type.
	 * 
	 * @param captionKey
	 *            the caption message key of the switch button
	 * @param target
	 *            the Object having the method for listening button clicks.
	 * @param methodName
	 *            the name of the method in target object, that receives button click events.
	 */
	public static CheckBox newCheckBox(@I18NAwareMessage String captionKey, Object target, String methodName) {
		return new I18NCheckBox(captionKey, target, methodName);
	}

	/**
	 * Creates a new switch button that is connected to a boolean property.
	 * 
	 * @param captionKey
	 *            the caption message key of the switch button
	 * @param state
	 *            the Initial state of the switch-button.
	 * @param dataSource
	 */
	public static CheckBox newCheckBox(@I18NAwareMessage String captionKey, Property dataSource) {
		return new I18NCheckBox(captionKey, dataSource);
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
	public static ComboBox newComboBox(@I18NAwareMessage String captionKey) {
		return new I18NComboBox(captionKey);
	}

	/**
	 * 
	 * @param captionKey
	 *            key for the ComboBox caption
	 * @param options
	 *            options for the comboBox
	 * @return
	 */
	public static ComboBox newComboBox(@I18NAwareMessage String captionKey, Collection<?> options) {
		return new I18NComboBox(captionKey, options);
	}

	/**
	 * 
	 * @param captionKey
	 *            key for the ComboBox caption
	 * @param dataSource
	 * @return
	 */
	public static ComboBox newComboBox(@I18NAwareMessage String captionKey, Container dataSource) {
		return new I18NComboBox(captionKey, dataSource);
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
	 *            Stream containing template data. Must be using UTF-8 encoding. To use a String as a template use for instance new
	 *            ByteArrayInputStream("<template>".getBytes()).
	 * @param streamLength
	 *            Length of the templateStream
	 * @throws IOException
	 */
	public static CustomLayout newCustomLayout(InputStream templateStream) throws IOException {
		return new I18NCustomLayout(templateStream);
	}

	/**
	 * Creates an i18n custom layout with given template name. Template file is fetched from "<theme>/layout/<templateName>".
	 */
	public static CustomLayout newCustomLayout(String template) {
		return new I18NCustomLayout(template);
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
	 * Constructs a new <code>DateField</code> that's bound to the specified <code>Property</code> and has no caption.
	 * 
	 * @param dataSource
	 *            the Property to be edited with this editor.
	 */
	public static DateField newDateField(Property dataSource) {
		return new I18NDateField(dataSource);
	}

	/**
	 * Creates a DateField
	 * 
	 * @param captionKey
	 *            key for the DateField caption
	 * @return new i18n-aware DateField
	 */
	public static DateField newDateField(@I18NAwareMessage String captionKey) {
		return new I18NDateField(captionKey);
	}

	/**
	 * Constructs a new <code>DateField</code> with the given caption and initial text contents. The editor constructed this way will not be bound to
	 * a Property unless {@link com.vaadin.data.Property.Viewer#setPropertyDataSource(Property)} is called to bind it.
	 * 
	 * @param caption
	 *            the caption <code>String</code> for the editor.
	 * @param value
	 *            the Date value.
	 */
	public static DateField newDateField(@I18NAwareMessage String captionKey, Date value) {
		return new I18NDateField(captionKey, value);
	}

	/**
	 * Constructs a new <code>DateField</code> that's bound to the specified <code>Property</code> and has the given caption <code>String</code>.
	 * 
	 * @param caption
	 *            the caption <code>String</code> for the editor.
	 * @param dataSource
	 *            the Property to be edited with this editor.
	 */
	public static DateField newDateField(@I18NAwareMessage String captionKey, Property dataSource) {
		return new I18NDateField(captionKey, dataSource);
	}

	/**
	 * Creates a I18NDefaultFieldFactory
	 * 
	 * @return new i18n-aware I18NDefaultFieldFactory
	 */
	public static I18NDefaultFieldFactory newDefaultFieldFactory() {
		return new I18NDefaultFieldFactory();
	}

	/**
	 * Creates an EmailValidator
	 * 
	 * @param errorMessageKey
	 *            key for error message
	 * @param fieldNameKey
	 *            key for field name
	 * @return new i18n-aware EmailValidator
	 */
	public static EmailValidator newEmailValidator(String errorMessageKey, String fieldNameKey) {
		return new I18NEmailValidator(errorMessageKey, fieldNameKey);
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
	public static Embedded newEmbedded(@I18NAwareMessage String captionKey) {
		return new I18NEmbedded(captionKey);
	}

	// Containers

	/**
	 * Creates an Embedded
	 * 
	 * @param captionKey
	 *            key for the Embedded caption
	 * @param resource
	 *            Resource to embed
	 * @return new i18n-aware Embedded
	 */
	public static Embedded newEmbedded(@I18NAwareMessage String captionKey, Resource resource) {
		return new I18NEmbedded(captionKey, resource);
	}

	/**
	 * Creates a Form
	 * 
	 * @return new i18n-aware Form
	 */
	public static Form newForm() {
		return new I18NForm();
	}

	/**
	 * Constructs a new i18n form with given {@link I18NAwareLayout}.
	 * 
	 * @param formLayout
	 *            the layout of the form.
	 */
	public static Form newForm(Layout formLayout) {

		if (!(formLayout instanceof I18NAwareLayout)) {
			throw new IllegalArgumentException("Expecting a I18NAwareLayout");
		}

		return new I18NForm((I18NAwareLayout) formLayout);
	}

	/**
	 * Constructs a new form with given {@link I18NAwareLayout} and {@link I18NAwareFormFieldFactory}.
	 * 
	 * @param formLayout
	 *            the layout of the form.
	 * @param fieldFactory
	 *            the FieldFactory of the form.
	 */
	public static Form newForm(Layout formLayout, FormFieldFactory fieldFactory) {

		if (!(formLayout instanceof I18NAwareLayout)) {
			throw new IllegalArgumentException("Expecting a I18NAwareLayout");
		}

		if (!(fieldFactory instanceof I18NAwareFormFieldFactory)) {
			throw new IllegalArgumentException("Expecting a I18NFormFieldFactory");
		}

		return new I18NForm((I18NAwareLayout) formLayout, (I18NAwareFormFieldFactory) fieldFactory);
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
	 * Constructs an empty grid layout that is extended as needed.
	 */
	public static GridLayout newGridLayout() {
		return new I18NGridLayout();
	}

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
	 * Creates a HorizontalSplitPanel
	 * 
	 * @return new i18n-aware HorizontalSplitPanel
	 */
	public static HorizontalSplitPanel newHorizontalSplitPanel() {
		return new I18NHorizontalSplitPanel();
	}

	/**
	 * Creates an IndexedContainer
	 * 
	 * @return new i18n-aware IndexedContainer
	 */
	public static IndexedContainer newIndexedContainer() {
		return new I18NIndexedContainer();
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
	public static InlineDateField newInlineDateField(Property dataSource) {
		return new I18NInlineDateField(dataSource);
	}

	/**
	 * Creates an empty i18n <code>InlineDateField</code> with caption message key.
	 * 
	 * @param captionKey
	 *            the caption message key of the InlineDateField.
	 */
	public static InlineDateField newInlineDateField(@I18NAwareMessage String captionKey) {
		return new I18NInlineDateField(captionKey);
	}

	/**
	 * Creates an i18n <code>InlineDateField</code> with caption message key and initial value.
	 * 
	 * @param captionKey
	 *            the caption message key of the datefield.
	 * @param value
	 */
	public static InlineDateField newInlineDateField(@I18NAwareMessage String captionKey, Date value) {
		return new I18NInlineDateField(captionKey, value);
	}

	/**
	 * Creates an i18n <code>InlineDateField</code> with caption message key and dataSource.
	 * 
	 * @param captionKey
	 *            the caption message key of the InlineDateField.
	 * @param dataSource
	 */
	public static InlineDateField newInlineDateField(@I18NAwareMessage String captionKey, Property dataSource) {
		return new I18NInlineDateField(captionKey, dataSource);
	}

	/**
	 * Creates a I18NLabel
	 * 
	 * @return new i18n-aware I18NLabel
	 */
	public static Label newLabel() {
		return new I18NLabel();
	}

	/**
	 * Creates a new instance of I18NLabel with text-contents read from given datasource.
	 * 
	 * @param contentSource
	 */
	public static Label newLabel(Property contentSource) {
		return new I18NLabel(contentSource);
	}

	/**
	 * Creates a new instance of I18NLabel with text-contents read from given datasource.
	 * 
	 * @param contentSource
	 * @param contentMode
	 */
	public static Label newLabel(Property contentSource, int contentMode) {
		return new I18NLabel(contentSource, contentMode);
	}

	/**
	 * Creates a I18NLabel
	 * 
	 * @param valueKey
	 *            key for the I18NLabel value
	 * @return new i18n-aware I18NLabel
	 */
	public static Label newLabel(String valueKey) {
		return new I18NLabel(valueKey);
	}

	/**
	 * Creates a new instance of I18NLabel with text-contents.
	 * 
	 * @param content
	 * @param contentMode
	 */
	public static Label newLabel(String content, int contentMode) {
		return new I18NLabel(content, contentMode);
	}

	/**
	 * Creates a Link
	 * 
	 * @return new i18n-aware Link
	 */
	public static Link newLink() {
		return new I18NLink();
	}

	// Events

	/**
	 * Creates a Link
	 * 
	 * @param captionKey
	 *            key for the Link caption
	 * @param resource
	 *            Resource for the Link
	 * @return new i18n-aware Link
	 */
	public static Link newLink(@I18NAwareMessage String captionKey, Resource resource) {
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
	public static Link newLink(@I18NAwareMessage String captionKey, Resource resource, String targetName, int width, int height, int border) {
		return new I18NLink(captionKey, resource, targetName, width, height, border);
	}

	// Fields

	/**
	 * Creates a ListSelect
	 * 
	 * @param captionKey
	 *            key for the ListSelect caption
	 * @return
	 */
	public static ListSelect newListSelect(@I18NAwareMessage String captionKey) {
		return new I18NListSelect(captionKey);
	}

	/**
	 * Creates a ListSelect
	 * 
	 * @param captionKey
	 *            key for the ListSelect caption
	 * @param options
	 * @return
	 */
	public static ListSelect newListSelect(@I18NAwareMessage String captionKey, Collection<?> options) {
		return new I18NListSelect(captionKey, options);
	}

	/**
	 * Creates a LoginForm
	 */
	public static LoginForm newLoginForm() {
		return new I18NLoginForm();
	}

	/**
	 * Creates a MenuBar
	 */
	public static MenuBar newMenuBar() {
		return new I18NMenuBar();
	}

	/**
	 * Creates a NativeButton
	 * 
	 * @return new i18n-aware NativeButton
	 */
	public static NativeButton newNativeButton() {
		return new I18NNativeButton();
	}

	/**
	 * Creates a NativeButton
	 * 
	 * @param captionKey
	 *            key for the NativeButton caption
	 * @return new i18n-aware NativeButton
	 */
	public static NativeButton newNativeButton(@I18NAwareMessage String captionKey) {
		return new I18NNativeButton(captionKey);
	}

	/**
	 * Creates a NativeButton
	 * 
	 * @param captionKey
	 *            key for the NativeButton caption
	 * @param listener
	 *            ClickListener for the NativeButton
	 * @return new i18n-aware NativeButton
	 */
	public static NativeButton newNativeButton(@I18NAwareMessage String captionKey, ClickListener listener) {
		return new I18NNativeButton(captionKey, listener);
	}

	/**
	 * Creates a NativeButton
	 * 
	 * @param captionKey
	 *            key for the NativeButton caption
	 * @param target
	 * @param methodName
	 * @return new i18n-aware NativeButton
	 */
	public static NativeButton newNativeButton(@I18NAwareMessage String captionKey, Object target, String methodName) {
		return new I18NNativeButton(captionKey, target, methodName);
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
	public static NativeSelect newNativeSelect(@I18NAwareMessage String captionKey) {
		return new I18NNativeSelect(captionKey);
	}

	/**
	 * Creates a NativeSelect
	 * 
	 * @param captionKey
	 *            key for the NativeSelect caption
	 * @param options
	 * @return
	 */
	public static NativeSelect newNativeSelect(@I18NAwareMessage String captionKey, Collection<?> options) {
		return new I18NNativeSelect(captionKey, options);
	}

	/**
	 * Creates a NativeSelect
	 * 
	 * @param captionKey
	 *            key for the NativeSelect caption
	 * @param dataSource
	 * @return
	 */
	public static NativeSelect newNativeSelect(@I18NAwareMessage String captionKey, Container dataSource) {
		return new I18NNativeSelect(captionKey, dataSource);
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
	public static OptionGroup newOptionGroup(@I18NAwareMessage String captionKey) {
		return new I18NOptionGroup(captionKey);
	}

	/**
	 * Creates an OptionGroup
	 * 
	 * @param captionKey
	 *            key for the OptionGroup caption
	 * @param options
	 * @return new i18n-aware OptionGroup
	 */
	public static OptionGroup newOptionGroup(@I18NAwareMessage String captionKey, Collection<?> options) {
		return new I18NOptionGroup(captionKey, options);
	}

	/**
	 * Creates an OptionGroup
	 * 
	 * @param captionKey
	 *            key for the OptionGroup caption
	 * @param dataSource
	 * @return new i18n-aware OptionGroup
	 */
	public static OptionGroup newOptionGroup(@I18NAwareMessage String captionKey, Container dataSource) {
		return new I18NOptionGroup(captionKey, dataSource);
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
	 * Create a new ordered layout. The orientation of the layout is given as parameters.
	 * 
	 * @param orientation
	 *            the Orientation of the layout.
	 * 
	 * @deprecated Use VerticalLayout/HorizontalLayout instead.
	 */
	@Deprecated
	public static OrderedLayout newOrderedLayout(int orientation) {
		return new I18NOrderedLayout(orientation);
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
	 * Creates a new empty panel which contains the given content. The content cannot be null.
	 * 
	 * @param content
	 *            the content for the panel.
	 */
	public static Panel newPanel(ComponentContainer content) {
		return new I18NPanel(content);
	}

	/**
	 * Sets caption key for a Panel
	 * 
	 * @param captionKey
	 *            key for the Panel caption
	 * @return new i18n-aware
	 */
	public static Panel newPanel(@I18NAwareMessage String captionKey) {
		return new I18NPanel(captionKey);
	}

	/**
	 * Creates a new empty panel with the given caption and content.
	 * 
	 * @param captionKey
	 *            the caption message key used in the panel.
	 * @param content
	 *            the content used in the panel.
	 */
	public static Panel newPanel(@I18NAwareMessage String captionKey, ComponentContainer content) {
		return new I18NPanel(captionKey, content);
	}

	/**
	 * Constructs an empty I18NPasswordField.
	 */
	public static PasswordField newPasswordField() {
		return new I18NPasswordField();
	}

	/**
	 * Constructs a I18NPasswordField with given property data source.
	 * 
	 * @param dataSource
	 *            the property data source for the field
	 */
	public static PasswordField newPasswordField(Property dataSource) {
		return new I18NPasswordField(dataSource);
	}

	/**
	 * Constructs a I18NPasswordField with given caption.
	 * 
	 * @param caption
	 *            the caption for the field
	 */
	public static PasswordField newPasswordField(@I18NAwareMessage String captionKey) {
		return new I18NPasswordField(captionKey);
	}

	/**
	 * Constructs a I18NPasswordField with given caption and property data source.
	 * 
	 * @param caption
	 *            the caption for the field
	 * @param dataSource
	 *            the property data source for the field
	 */
	public static PasswordField newPasswordField(@I18NAwareMessage String captionKey, Property dataSource) {
		return new I18NPasswordField(captionKey, dataSource);
	}

	/**
	 * Constructs a I18NPasswordField with given value and caption.
	 * 
	 * @param caption
	 *            the caption for the field
	 * @param value
	 *            the value for the field
	 */
	public static PasswordField newPasswordField(@I18NAwareMessage String captionKey, String value) {
		return new I18NPasswordField(captionKey, value);
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
	public static PopupDateField newPopupDateField(Property dataSource) {
		return new I18NPopupDateField(dataSource);
	}

	/**
	 * Creates an empty i18n <code>PopupDateField</code> with caption message key.
	 * 
	 * @param captionKey
	 *            the caption message key of the PopupDateField.
	 */
	public static PopupDateField newPopupDateField(@I18NAwareMessage String captionKey) {
		return new I18NPopupDateField(captionKey);
	}

	/**
	 * Creates an i18n <code>PopupDateField</code> with caption message key and initial value.
	 * 
	 * @param captionKey
	 *            the caption message key of the datefield.
	 * @param value
	 */
	public static PopupDateField newPopupDateField(@I18NAwareMessage String captionKey, Date value) {
		return new I18NPopupDateField(captionKey, value);
	}

	/**
	 * Creates an i18n <code>PopupDateField</code> with caption message key and dataSource.
	 * 
	 * @param captionKey
	 *            the caption message key of the PopupDateField.
	 * @param dataSource
	 */
	public static PopupDateField newPopupDateField(@I18NAwareMessage String captionKey, Property dataSource) {
		return new I18NPopupDateField(captionKey, dataSource);
	}

	/**
	 * A simple way to create a PopupPanel. Note that the minimal representation may not be dynamically updated, in order to achieve this create your
	 * own Content object and use {@link PopupView#PopupView(_Content)}.
	 * 
	 * @param small
	 *            the minimal textual representation as HTML
	 * @param large
	 *            the full, Component-type representation
	 */
	public static PopupView newPopupView(final java.lang.String small, final Component large) {
		return new I18NPopupView(small, large);
	}

	/**
	 * Creates a PopupView through the PopupView.Content interface. This allows the creator to dynamically change the contents of the PopupView.
	 * 
	 * @param content
	 *            the PopupView.Content that contains the information for this
	 */
	public static PopupView newPopupView(PopupView.Content content) {
		return new I18NPopupView(content);
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
	 * Creates a new instance of i18n-aware ProgressIndicator with given state.
	 * 
	 * @param value
	 */
	public static ProgressIndicator newProgressIndicator(Float value) {
		return new I18NProgressIndicator(value);
	}

	/**
	 * Creates a new instance of i18n-aware ProgressIndicator with stae read from given datasource.
	 * 
	 * @param contentSource
	 */
	public static ProgressIndicator newProgressIndicator(Property contentSource) {
		return new I18NProgressIndicator(contentSource);
	}

	/**
	 * Constructs an empty <code>RichTextArea</code> with no caption.
	 */
	public static RichTextArea newRichTextArea() {
		return new I18NRichTextArea();
	}

	/**
	 * Constructs a new <code>RichTextArea</code> that's bound to the specified <code>Property</code> and has no caption.
	 * 
	 * @param dataSource
	 *            the data source for the editor value
	 */
	public static RichTextArea newRichTextArea(Property dataSource) {
		return new I18NRichTextArea(dataSource);
	}

	/**
	 * 
	 * Constructs an empty <code>RichTextArea</code> with the given caption.
	 * 
	 * @param captionKey
	 *            the caption key for the editor.
	 */
	public static RichTextArea newRichTextArea(@I18NAwareMessage String captionKey) {
		return new I18NRichTextArea(captionKey);
	}

	/**
	 * Constructs a new <code>RichTextArea</code> that's bound to the specified <code>Property</code> and has the given caption.
	 * 
	 * @param captionKey
	 *            the caption key for the editor.
	 * @param dataSource
	 *            the data source for the editor value
	 */
	public static RichTextArea newRichTextArea(@I18NAwareMessage String captionKey, Property dataSource) {
		return new I18NRichTextArea(captionKey, dataSource);
	}

	/**
	 * Constructs a new <code>RichTextArea</code> with the given caption and initial text contents.
	 * 
	 * @param captionKey
	 *            the caption key for the editor.
	 * @param value
	 *            the initial text content of the editor.
	 */
	public static RichTextArea newRichTextArea(@I18NAwareMessage String captionKey, String value) {
		return new I18NRichTextArea(captionKey, value);
	}

	/**
	 * Creates a Select
	 * 
	 * @param captionKey
	 *            the caption message key of the Select.
	 * 
	 * @return new i18n-aware Select
	 */
	public static Select newSelect(@I18NAwareMessage String captionKey) {
		return new I18NSelect(captionKey);
	}

	/**
	 * Creates a Select
	 * 
	 * @param captionKey
	 *            the caption message key of the Select.
	 * @param options
	 * 
	 * @return new i18n-aware Select
	 */
	public static Select newSelect(@I18NAwareMessage String captionKey, Collection<?> options) {
		return new I18NSelect(captionKey, options);
	}

	/**
	 * Creates a Select
	 * 
	 * @param captionKey
	 *            the caption message key of the Select.
	 * @param dataSource
	 * 
	 * @return new i18n-aware Select
	 */
	public static Select newSelect(@I18NAwareMessage String captionKey, Container dataSource) {
		return new I18NSelect(captionKey, dataSource);
	}

	/**
	 * Create a new slider with given range and resolution
	 * 
	 * @param min
	 * @param max
	 * @param resolution
	 */
	public static Slider newSlider(double min, double max, int resolution) {
		return new I18NSlider(min, max, resolution);
	}

	/**
	 * Create a new slider with given range
	 * 
	 * @param min
	 * @param max
	 */
	public static Slider newSlider(int min, int max) {
		return new I18NSlider(min, max);
	}

	/**
	 * Create a new slider with the caption given as parameter. All slider values set to defaults.
	 * 
	 * @param caption
	 *            The caption for this Slider (e.g. "Volume").
	 */
	public static Slider newSlider(@I18NAwareMessage String captionKey) {
		return new I18NSlider(captionKey);
	}

	/**
	 * Create a new slider with given caption and range
	 * 
	 * @param caption
	 * @param min
	 * @param max
	 */
	public static Slider newSlider(@I18NAwareMessage String captionKey, int min, int max) {
		return new I18NSlider(captionKey, min, max);
	}

	/**
	 * Creates a new i18n split panel. The orientation of the panels is <code>ORIENTATION_VERTICAL</code>.
	 * 
	 * @return new i18n-aware SplitPanel
	 */
	public static SplitPanel newSplitPanel() {
		return new I18NSplitPanel();
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
	public static StringLengthValidator newStringLengthValidator(String errorMessageKey, String fieldNameKey, int minLength, int maxLength,
			boolean allowNull) {
		return new I18NStringLengthValidator(errorMessageKey, fieldNameKey, minLength, maxLength, allowNull);
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
	public static Table newTable(@I18NAwareMessage String captionKey) {
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
	public static Table newTable(@I18NAwareMessage String captionKey, Container dataSource) {
		return new I18NTable(captionKey, dataSource);
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
	 * Creates a TextArea
	 * 
	 * @return new i18n-aware TextArea
	 */
	public static TextArea newTextArea() {
		return new I18NTextArea();
	}

	/**
	 * Constructs a i18n TextArea with given property data source.
	 * 
	 * @param dataSource
	 *            the data source for the field
	 */
	public static TextArea newTextArea(Property dataSource) {
		return new I18NTextArea(dataSource);
	}

	/**
	 * Creates a TextArea
	 * 
	 * @param captionKey
	 *            key for the TextArea caption
	 * @return new i18n-aware TextArea
	 */
	public static TextArea newTextArea(@I18NAwareMessage String captionKey) {
		return new I18NTextArea(captionKey);
	}

	/**
	 * Constructs a i18n TextArea with given caption and property data source.
	 * 
	 * @param captionKey
	 *            the caption message key for the field.
	 * @param dataSource
	 *            the data source for the field
	 */
	public static TextArea newTextArea(@I18NAwareMessage String captionKey, Property dataSource) {
		return new I18NTextArea(captionKey, dataSource);
	}

	/**
	 * Constructs a i18n TextArea with given caption and value.
	 * 
	 * @param captionKey
	 *            the caption message key for the field.
	 * @param value
	 *            the value for the field
	 */
	public static TextArea newTextArea(@I18NAwareMessage String captionKey, String value) {
		return new I18NTextArea(captionKey, value);
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
	 * Constructs a new i18n <code>TextField</code> that's bound to the specified <code>Property</code> and has no caption.
	 * 
	 * @param dataSource
	 *            the Property to be edited with this editor.
	 */
	public static TextField newTextField(Property dataSource) {
		return new I18NTextField(dataSource);
	}

	/**
	 * Creates a TextField
	 * 
	 * @param captionKey
	 *            key for the TextField caption
	 * @return new i18n-aware TextField
	 */
	public static TextField newTextField(@I18NAwareMessage String captionKey) {
		return new I18NTextField(captionKey);
	}

	/**
	 * Constructs a new i18n <code>TextField</code> that's bound to the specified <code>Property</code> and has the given caption <code>String</code>.
	 * 
	 * @param captionKey
	 *            the caption message key for the editor.
	 * @param dataSource
	 *            the Property to be edited with this editor.
	 */
	public static TextField newTextField(@I18NAwareMessage String captionKey, Property dataSource) {
		return new I18NTextField(captionKey, dataSource);
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
	public static TextField newTextField(@I18NAwareMessage String captionKey, String value) {
		return new I18NTextField(captionKey, value);
	}

	/**
	 * Creates a new empty tree.
	 */
	public static Tree newTree() {
		return new I18NTree();
	}

	/**
	 * Creates a new empty tree with caption.
	 * 
	 * @param caption
	 */
	public static Tree newTree(@I18NAwareMessage String captionKey) {
		return new I18NTree(captionKey);
	}

	/**
	 * Creates a new tree with caption and connect it to a Container.
	 * 
	 * @param caption
	 * @param dataSource
	 */
	public static Tree newTree(@I18NAwareMessage String captionKey, Container dataSource) {
		return new I18NTree(captionKey);
	}

	/**
	 * Construct a I18NTwinColSelect
	 */
	public static TwinColSelect newTwinColSelect() {
		return new I18NTwinColSelect();
	}

	// Form

	/**
	 * Construct a I18NTwinColSelect
	 * 
	 * @param caption
	 */
	public static TwinColSelect newTwinColSelect(@I18NAwareMessage String captionKey) {
		return new I18NTwinColSelect(captionKey);
	}

	/**
	 * Construct a I18NTwinColSelect
	 * 
	 * @param captionKey
	 * @param dataSource
	 */
	public static TwinColSelect newTwinColSelect(@I18NAwareMessage String captionKey, Container dataSource) {
		return new I18NTwinColSelect(captionKey, dataSource);
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
	public static Upload newUpload(@I18NAwareMessage String captionKey, Receiver receiver) {
		return new I18NUpload(captionKey, receiver);
	}

	// Table

	/**
	 * Creates a VerticalLayout
	 * 
	 * @return new i18n-aware VerticalLayout
	 */
	public static VerticalLayout newVerticalLayout() {
		return new I18NVerticalLayout();
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
	public static Window newWindow(@I18NAwareMessage String captionKey) {
		return new I18NWindow(captionKey);
	}

	/**
	 * Creates a new unnamed i18n window with the given content and title.
	 * 
	 * @param captionKey
	 *            the title message key of the window.
	 * @param content
	 *            the contents of the window
	 */
	public static Window newWindow(@I18NAwareMessage String captionKey, ComponentContainer content) {
		return new I18NWindow(captionKey, content);
	}

	/**
	 * Sets caption key for an Upload
	 * 
	 * @param Upload
	 *            component to be set
	 * @param buttonCaptionKey
	 *            key for the Button caption
	 * @param buttonCaptionParams
	 *            params for the Button caption key
	 */
	public static void setButtonCaptionKey(Upload upload, String buttonCaptionKey, Object... buttonCaptionParams) {
		if (!(upload instanceof I18NUpload)) {
			throw new IllegalArgumentException("Expecting a I18NUpload");
		}
		((I18NUpload) upload).setButtonCaptionMessage(buttonCaptionKey, buttonCaptionParams);
	}

	/**
	 * Sets caption key
	 * 
	 * @param item
	 *            Item to be updated type I18NAwareCaption
	 * @param captionKey
	 *            new caption key
	 * @param params
	 *            parameters for the caption
	 * @see I18NAwareCaption
	 */
	public static <T> void setCaptionMessage(T item, @I18NAwareMessage String captionKey, Object... params) {

		if (!(item instanceof I18NAwareCaption)) {
			throw new IllegalArgumentException("Expecting a I18NAwareCaption");
		}

		((I18NAwareCaption) item).setCaptionMessage(captionKey, params);
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
	public static void setColumnHeadersKeys(Table table, String[] columnHeadersKeys) {

		if (!(table instanceof I18NTable)) {
			throw new IllegalArgumentException("Expecting a I18NTable");
		}

		((I18NTable) table).setColumnHeadersKeys(columnHeadersKeys);
	}

	/**
	 * Sets error message key for emailValidator
	 * 
	 * @param emailValidator
	 *            validator to update
	 * @param errorMessageKey
	 *            new error message key
	 */
	public static void setErrorMessageKey(EmailValidator emailValidator, String errorMessageKey) {
		emailValidator.setErrorMessage(errorMessageKey);
	}

	/**
	 * Sets error message key for StringValidator
	 * 
	 * @param stringLengthValidator
	 *            validator to update
	 * @param errorMessageKey
	 *            new error message key
	 */
	public static void setErrorMessageKey(StringLengthValidator stringLengthValidator, String errorMessageKey) {
		stringLengthValidator.setErrorMessage(errorMessageKey);
	}

	// Custom Components

	public static void setInputPromptKey(PopupDateField popupDateField, String inputPromptKey, Object... inputPromptParams) {

		if (!(popupDateField instanceof I18NPopupDateField)) {
			throw new IllegalArgumentException("Expecting a I18NPopupDateField");
		}
		((I18NPopupDateField) popupDateField).setInputPromptKey(inputPromptKey, inputPromptParams);
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
	public static void setItemCaptionKey(OptionGroup optionGroup, Object itemId, @I18NAwareMessage String captionKey) {
		if (!(optionGroup instanceof I18NOptionGroup)) {
			throw new IllegalArgumentException("Expecting a OptionGroup");
		}

		((I18NOptionGroup) optionGroup).setItemCaptionKey(itemId, captionKey);
	}

	public static void setLeftColumnCaptionKey(TwinColSelect twinColSelect, String leftColumnCaptionKey, Object... leftColumnCaptionParams) {

		if (!(twinColSelect instanceof I18NTwinColSelect)) {
			throw new IllegalArgumentException("Expecting a I18NTwinColSelect");
		}
		((I18NTwinColSelect) twinColSelect).setLeftColumnCaptionKey(leftColumnCaptionKey, leftColumnCaptionParams);
	}

	public static void setLoginButtonCaptionKey(LoginForm loginForm, String loginButtonCaptionKey) {
		if (!(loginForm instanceof I18NLoginForm)) {
			throw new IllegalArgumentException("Expecting a I18NLoginForm");
		}

		((I18NLoginForm) loginForm).setLoginButtonCaptionKey(loginButtonCaptionKey);
	}

	// Validators

	public static void setPasswordCaptionKey(LoginForm loginForm, String passwordCaptionKey) {
		if (!(loginForm instanceof I18NLoginForm)) {
			throw new IllegalArgumentException("Expecting a I18NLoginForm");
		}

		((I18NLoginForm) loginForm).setPasswordCaptionKey(passwordCaptionKey);
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
	public static <T> void setRequiredErrorMessage(T item, @I18NAwareMessage String requiredErrorKey, Object... requiredErrorParams) {

		if (!(item instanceof I18NAwareField)) {
			throw new IllegalArgumentException("Expecting a I18NAwareValue");
		}

		((I18NAwareField) item).setRequiredErrorMessage(requiredErrorKey, requiredErrorParams);
	}

	public static void setRightColumnCaptionKey(TwinColSelect twinColSelect, String rightColumnCaptionKey, Object... rightColumnCaptionParams) {

		if (!(twinColSelect instanceof I18NTwinColSelect)) {
			throw new IllegalArgumentException("Expecting a I18NTwinColSelect");
		}
		((I18NTwinColSelect) twinColSelect).setRightColumnCaptionKey(rightColumnCaptionKey, rightColumnCaptionParams);
	}

	public static void setUsernameCaptionKey(LoginForm loginForm, String usernameCaptionKey) {
		if (!(loginForm instanceof I18NLoginForm)) {
			throw new IllegalArgumentException("Expecting a I18NLoginForm");
		}

		((I18NLoginForm) loginForm).setUsernameCaptionKey(usernameCaptionKey);
	}

	// Helpers

	/**
	 * Set value key
	 * 
	 * @param item
	 *            Item to be updated type I18NAwareValue
	 * @param valueKey
	 *            new value
	 * @see I18NAwareValue
	 */
	public static <T> void setValueKey(T item, String valueKey, Object... valueParams) {

		if (!(item instanceof I18NAwareValue)) {
			throw new IllegalArgumentException("Expecting a I18NAwareValue");
		}

		((I18NAwareValue) item).setValueMessage(valueKey, valueParams);
	}

	/**
	 * Add an item before some item. If the given item does not exist the item is added at the end of the menu. Icon and command can be null, but a
	 * caption must be given.
	 * 
	 * @param caption
	 *            the text for the menu item
	 * @param icon
	 *            the icon for the menu item
	 * @param command
	 *            the command for the menu item
	 * @param itemToAddBefore
	 *            the item that will be after the new item @
	 */
	public MenuBar.MenuItem addItemBefore(MenuBar menuBar, @I18NAwareMessage String captionKey, Resource icon, MenuBar.Command command,
			MenuBar.MenuItem itemToAddBefore) {

		if (!(menuBar instanceof I18NMenuBar)) {
			throw new IllegalArgumentException("Expecting a I18NMenuBar");
		}

		return ((I18NMenuBar) menuBar).addItemBefore(captionKey, icon, command, itemToAddBefore);
	}

	/**
	 * Creates a ListSelect
	 * 
	 * @param captionKey
	 *            key for the ListSelect caption
	 * @param dataSource
	 * @return
	 */
	public ListSelect newListSelect(@I18NAwareMessage String captionKey, Container dataSource) {
		return new I18NListSelect(captionKey, dataSource);
	}

}
