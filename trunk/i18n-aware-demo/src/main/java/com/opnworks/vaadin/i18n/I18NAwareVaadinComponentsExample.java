package com.opnworks.vaadin.i18n;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Locale;
import com.opnworks.vaadin.i18n.service_impl.I18NAwareFactory;
import com.opnworks.vaadin.i18n.ui.I18NHorizontalLayout;
import com.vaadin.ui.*;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

public class I18NAwareVaadinComponentsExample extends I18NHorizontalLayout {

    private AbsoluteLayout absoluteLayout;

    private VerticalLayout verticalLayout;

    private HorizontalLayout horizontalLayout;

    private Accordion accordion;

    private Button button;

    private CheckBox checkBox;

    private ComboBox comboBox;

    private CssLayout cssLayout;

    private CustomComponent customComponent;

    private CustomLayout customLayout;

    private DateField dateField;

    private DefaultFieldFactory defaultFieldFactory;

    private Form form;

    private FormLayout formLayout;

    private GridLayout gridLayout;

    private HorizontalSplitPanel horizontalSplitPanel;

    private InlineDateField inlineDateField;

    private Label label;

    private Link link;

    private ListSelect listSelect;

    private LoginForm loginForm;

    private MenuBar menuBar;

    private NativeButton nativeButton;

    private NativeSelect nativeSelect;

    private OptionGroup optionGroup;

    private PasswordField passwordField;

    private PopupDateField poputDateField;

    private PopupView poputView;

    private ProgressIndicator progressIndicator;

    private RichTextArea richTextArea;

    private Select select;

    private Slider slider;

    private Table table;

    private TabSheet tabSheet;

    private TextArea textArea;

    private TextField textField;

    private Audio audio;

    private BrowserFrame browserFrame;

    private ColorPicker colorPicker;

    private ColorPickerArea colorPickerArea;

    private Embedded embedded;

    private Flash flash;

    private Image image;

    private Panel panel;

    private Video video;

    private Tree tree;

    private TreeTable treeTable;

    private TwinColSelect twinColSelect;

    private Upload upload;

    private VerticalSplitPanel verticalSplitPanel;

    private Window window;

    private LegacyWindow legacyWindow;

    private static String HEIGHT = "400px";

    private I18NService i18NService;

    public I18NAwareVaadinComponentsExample(I18NService i18NService) {
        this.i18NService = i18NService;
        initUI();
    }

    public void initUI() {

    	/*final String authUser = "Sandy";
    	final String authPassword = "Qwerty5";
    	
    	Authenticator.setDefault(new Authenticator() {
    		public PasswordAuthentication getPasswordAuthentication() {
    			return new PasswordAuthentication(authUser, authPassword.toCharArray());
    		}
    	});
    	
    	System.setProperty("proxySet", "true");
    	System.setProperty("http.proxyUser", authUser);
    	System.setProperty("http.proxyPassword", authPassword);
    	System.setProperty("http.proxyHost", "200.55.147.68");
    	System.setProperty("http.proxyPort", "443");            	

    	
    	Translate trans = null;
    	trans.setKey("491CDC3769B93528E0388F1C1A8DB54349BBD132");
    	String Traduccion = "NADA";
		try {
			Traduccion = trans.execute("procesar prueba", Language.ENGLISH);
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Traduccion:  " + Traduccion);*/
		
        tabSheet = new TabSheet();
        tabSheet.setCaption("vaadincomponents.name");
        tabSheet.setDescription("vaadincomponents.name");
        absoluteLayout = new AbsoluteLayout();
        absoluteLayout.setCaption("tab1.name");
        absoluteLayout.setDescription("tab1.name");
        verticalLayout = new VerticalLayout();
        verticalLayout.setCaption("tab1.name");
        verticalLayout.setDescription("tab1.name");
        horizontalLayout = new HorizontalLayout();
        horizontalLayout.setCaption("tab1.name");
        horizontalLayout.setDescription("tab1.name");
        accordion = new Accordion();
        accordion.setCaption("tab1.name");
        accordion.setDescription("tab1.name");
        button = new Button("buttons.button");
        button.setCaption("buttons.button");
        button.setDescription("buttons.button");
        checkBox = new CheckBox("buttons.checkBox");
        checkBox.setCaption("buttons.checkBox");
        checkBox.setDescription("buttons.checkBox");
        checkBox.setRequiredError("buttons.checkBox");
        comboBox = new ComboBox("selects.combobox");
        comboBox.addItem(Locale.ENGLISH);        
        comboBox.addItem(Locale.FRENCH);
        
        comboBox.setItemCaption(Locale.FRENCH, "buttons.checkBox");
        comboBox.setItemCaption(Locale.ENGLISH, i18NService.getMessage(Locale.ENGLISH, "ENGLISH"));
        
        comboBox.setCaption("selects.combobox");
        comboBox.setDescription("selects.combobox");
        comboBox.setRequiredError("selects.combobox");
        comboBox.setItemCaption(new Object(), "selects.combobox");
        cssLayout = new CssLayout();
        cssLayout.setCaption("tab1.name");
        cssLayout.setDescription("tab1.name");
        customComponent = new CustomComponent();
        customComponent.setCaption("tab1.name");
        customComponent.setDescription("tab1.name");
        customLayout = new CustomLayout("tab1.name");
        customLayout.setCaption("tab1.name");
        customLayout.setDescription("tab1.name");
        dateField = new DateField("dates.datefield");
        dateField.setCaption("dates.datefield");
        dateField.setDescription("dates.datefield");
        form = new Form(formLayout);
        form.setCaption("forms.form");
        form.setDescription("forms.form");
        form.setRequiredError("forms.form");
        formLayout = new FormLayout();
        formLayout.setCaption("tab1.name");
        formLayout.setDescription("tab1.name");
        gridLayout = new GridLayout(200, 200);
        gridLayout.setCaption("tab1.name");
        gridLayout.setDescription("tab1.name");
        horizontalSplitPanel = new HorizontalSplitPanel();
        horizontalSplitPanel.setCaption("tab1.name");
        horizontalSplitPanel.setDescription("tab1.name");
        inlineDateField = new InlineDateField("dates.inlinedatefield");
        inlineDateField.setCaption("dates.inlinedatefield");
        inlineDateField.setDescription("dates.inlinedatefield");
        inlineDateField.setRequiredError("dates.inlinedatefield");
        //label = new Label("texts.label");
        //label.setCaption("texts.label");
        label = new Label("<span>QuickTickets</span> Dashboard", ContentMode.HTML);
        label.setDescription("texts.label");
        link = new Link("links.link", new ExternalResource("http://vaadin.com/"));
        link.setCaption("links.link");
        link.setDescription("links.link");
        listSelect = new ListSelect("selects.listselect");
        listSelect.setCaption("selects.listselect");
        listSelect.setDescription("selects.listselect");
        listSelect.setRequiredError("selects.listselect");
        listSelect.addItem("Option1");
        loginForm = new LoginForm();
        loginForm.setCaption("forms.loginform");
        loginForm.setDescription("forms.loginform");
        menuBar = new MenuBar();
        menuBar.setCaption("menubars.menubar");
        menuBar.setDescription("menubars.menubar");
        menuBar.addItem("menubars.menubar", null);
        menuBar.addItem("menubars.menubar", null);
        nativeButton = new NativeButton("buttons.nativebutton");
        nativeButton.setCaption("buttons.nativebutton");
        nativeButton.setDescription("buttons.nativebutton");
        nativeSelect = new NativeSelect("selects.nativeselect");
        nativeSelect.setCaption("selects.nativeselect");
        nativeSelect.setDescription("selects.nativeselect");
        nativeSelect.setRequiredError("selects.nativeselect");
        optionGroup = new OptionGroup("selects.optiongroup");
        optionGroup.setCaption("selects.optiongroup");
        optionGroup.setDescription("selects.optiongroup");
        optionGroup.setRequiredError("selects.optiongroup");
        optionGroup.addItem("Option1");
        optionGroup.addItem("Option2");
        passwordField = new PasswordField("passwordsfields.passwordsfield");
        passwordField.setCaption("passwordsfields.passwordsfield");
        passwordField.setDescription("passwordsfields.passwordsfield");
        passwordField.setRequiredError("passwordsfields.passwordsfield");
        poputDateField = new PopupDateField("dates.poputdatefield");
        poputView = new PopupView("poputviews.poputview", label);
        progressIndicator = new ProgressIndicator();
        progressIndicator.setCaption("progressindicators.progressindicator");
        progressIndicator.setDescription("progressindicators.progressindicator");
        progressIndicator.setRequiredError("progressindicators.progressindicator");
        richTextArea = new RichTextArea("texts.richTextArea");
        richTextArea.setCaption("texts.richTextArea");
        richTextArea.setDescription("texts.richTextArea");
        richTextArea.setRequiredError("texts.richTextArea");
        select = new Select("selects.select");
        select.setCaption("selects.select");
        select.setDescription("selects.select");
        select.setRequiredError("selects.select");
        slider = new Slider("sliders.slider");
        slider.setCaption("sliders.slider");
        slider.setDescription("sliders.slider");
        slider.setRequiredError("sliders.slider");
        table = new Table("tables.table");
        table.setCaption("tables.table");
        table.setDescription("tables.table");
        table.setRequiredError("tables.table");
        textArea = new TextArea("texts.textArea");
        textArea.setCaption("texts.textArea");
        textArea.setDescription("texts.textArea");
        textArea.setRequiredError("texts.textArea");
        textField = new TextField("texts.textfield");
        textField.setCaption("texts.textfield");
        textField.setDescription("texts.textfield");
        textField.setRequiredError("texts.textfield");
        tree = new Tree("trees.tree");
        tree.setCaption("trees.tree");
        tree.setDescription("trees.tree");
        tree.setRequiredError("trees.tree");
        treeTable = new TreeTable("trees.treetable");
        treeTable.setCaption("trees.treetable");
        treeTable.setDescription("trees.treetable");
        treeTable.setRequiredError("trees.treetable");
        twinColSelect = new TwinColSelect("tab1.name");
        twinColSelect.setCaption("tab1.name");
        twinColSelect.setDescription("tab1.name");
        twinColSelect.setRequiredError("tab1.name");
        upload = new Upload();
        upload.setCaption("uploads.upload");
        upload.setDescription("uploads.upload");
        verticalSplitPanel = new VerticalSplitPanel();
        verticalSplitPanel.setCaption("tab1.name");
        verticalSplitPanel.setDescription("tab1.name");
        audio = new Audio("embeddes.audio");
        audio.setCaption("embeddes.audio");
        audio.setAltText("embeddes.audio");
        audio.setDescription("embeddes.audio");
        audio.setSource(new ExternalResource("http://jonatan.virtuallypreinstalled.com/media/audio.mp3"));
        browserFrame = new BrowserFrame("embeddes.browserFrame");
        browserFrame.setCaption("embeddes.browserFrame");
        browserFrame.setAlternateText("embeddes.browserFrame");
        browserFrame.setDescription("embeddes.browserFrame");
        browserFrame.setSource(new ExternalResource("http://demo.vaadin.com/sampler/"));
        colorPicker = new ColorPicker("colorpickers.colorpicker");
        colorPicker.setCaption("colorpickers.colorpicker");
        colorPicker.setDescription("colorpickers.colorpicker");
        colorPickerArea = new ColorPickerArea("colorpickers.colorpickerarea");
        colorPickerArea.setCaption("colorpickers.colorpickerarea");
        colorPickerArea.setDescription("colorpickers.colorpickerarea");
        embedded = new Embedded("embeddes.embedde");
        embedded.setCaption("embeddes.embedde");
        embedded.setAlternateText("embeddes.embedde");
        embedded.setDescription("embeddes.embedde");
        flash = new Flash("embeddes.flash");
        flash.setCaption("embeddes.flash");
        flash.setAlternateText("embeddes.flash");
        flash.setDescription("embeddes.flash");
        flash.setSource(new ThemeResource("img/vaadin_spin.swf"));
        image = new Image("embeddes.image");
        image.setCaption("embeddes.image");
        image.setAlternateText("embeddes.image");
        image.setDescription("embeddes.image");
        image.setSource(new ExternalResource("http://opnworks.com/mirasol-theme/images/custom/head_brun.gif"));
        panel = new Panel("panels.panel");
        panel.setCaption("panels.panel");
        panel.setDescription("panels.panel");
        panel.setHeight("100px");
        panel.setWidth("100px");
        video = new Video("embeddes.video");
        video.setCaption("embeddes.video");
        video.setAltText("embeddes.video");
        video.setDescription("embeddes.video");
        video.setSource(new ExternalResource("http://jonatan.virtuallypreinstalled.com/media/big_buck_bunny.mp4"));
        window = new Window("tab1.name");
        legacyWindow = new LegacyWindow("tab1.name");
        HorizontalLayout buttons = new HorizontalLayout();
        buttons.addComponent(button);
        buttons.addComponent(nativeButton);
        buttons.addComponent(checkBox);
        buttons.setHeight(HEIGHT);
        HorizontalLayout selects = new HorizontalLayout();
        selects.addComponent(select);
        selects.addComponent(nativeSelect);
        selects.addComponent(listSelect);
        selects.addComponent(optionGroup);
        selects.addComponent(comboBox);
        selects.setHeight(HEIGHT);
        HorizontalLayout dates = new HorizontalLayout();
        dates.addComponent(dateField);
        dates.addComponent(inlineDateField);
        dates.addComponent(poputDateField);
        dates.setHeight(HEIGHT);
        HorizontalLayout embeddeds = new HorizontalLayout();
        embeddeds.addComponent(flash);
        embeddeds.addComponent(image);
        embeddeds.addComponent(browserFrame);
        embeddeds.addComponent(video);
        embeddeds.addComponent(audio);
        embeddeds.setHeight(HEIGHT);
        HorizontalLayout forms = new HorizontalLayout();
        forms.addComponent(form);
        forms.addComponent(formLayout);
        forms.addComponent(loginForm);
        forms.setHeight(HEIGHT);
        HorizontalLayout links = new HorizontalLayout();
        links.addComponent(link);
        links.setHeight(HEIGHT);
        HorizontalLayout menubars = new HorizontalLayout();
        menubars.addComponent(menuBar);
        menubars.setHeight(HEIGHT);
        HorizontalLayout panels = new HorizontalLayout();
        panels.addComponent(panel);
        panels.setHeight(HEIGHT);
        HorizontalLayout poputviews = new HorizontalLayout();
        poputviews.addComponent(poputView);
        poputviews.setHeight(HEIGHT);
        HorizontalLayout progressindicators = new HorizontalLayout();
        progressindicators.addComponent(progressIndicator);
        progressindicators.setHeight(HEIGHT);
        HorizontalLayout sliders = new HorizontalLayout();
        sliders.addComponent(slider);
        sliders.setHeight(HEIGHT);
        HorizontalLayout tables = new HorizontalLayout();
        tables.addComponent(table);
        tables.setHeight(HEIGHT);
        HorizontalLayout texts = new HorizontalLayout();
        texts.addComponent(label);
        texts.addComponent(richTextArea);
        texts.addComponent(textArea);
        texts.addComponent(textField);
        texts.setHeight(HEIGHT);
        HorizontalLayout colorpickers = new HorizontalLayout();
        colorpickers.addComponent(colorPicker);
        colorpickers.addComponent(colorPickerArea);
        colorpickers.setHeight(HEIGHT);
        HorizontalLayout trees = new HorizontalLayout();
        trees.addComponent(tree);
        trees.addComponent(treeTable);
        trees.setHeight(HEIGHT);
        HorizontalLayout uploads = new HorizontalLayout();
        uploads.addComponent(upload);
        uploads.setHeight(HEIGHT);
        HorizontalLayout passwordsfields = new HorizontalLayout();
        passwordsfields.addComponent(passwordField);
        passwordsfields.setHeight(HEIGHT);
        Tab example1 = tabSheet.addTab(buttons);
        Tab example2 = tabSheet.addTab(selects);
        Tab example3 = tabSheet.addTab(dates);
        Tab example4 = tabSheet.addTab(embeddeds);
        Tab example5 = tabSheet.addTab(forms);
        Tab example6 = tabSheet.addTab(links);
        Tab example7 = tabSheet.addTab(menubars);
        Tab example8 = tabSheet.addTab(panels);
        Tab example9 = tabSheet.addTab(poputviews);
        Tab example10 = tabSheet.addTab(progressindicators);
        Tab example11 = tabSheet.addTab(sliders);
        Tab example12 = tabSheet.addTab(tables);
        Tab example13 = tabSheet.addTab(texts);
        Tab example14 = tabSheet.addTab(trees);
        Tab example15 = tabSheet.addTab(uploads);
        Tab example16 = tabSheet.addTab(passwordsfields);
        Tab example17 = tabSheet.addTab(colorpickers);
        I18NAwareFactory.setCaptionMessage(example1, "buttons.name");
        I18NAwareFactory.setCaptionMessage(example2, "selects.name");
        I18NAwareFactory.setCaptionMessage(example3, "dates.name");
        I18NAwareFactory.setCaptionMessage(example4, "embeddes.name");
        I18NAwareFactory.setCaptionMessage(example5, "forms.name");
        I18NAwareFactory.setCaptionMessage(example6, "links.name");
        I18NAwareFactory.setCaptionMessage(example7, "menubars.name");
        I18NAwareFactory.setCaptionMessage(example8, "panels.name");
        I18NAwareFactory.setCaptionMessage(example9, "poputviews.name");
        I18NAwareFactory.setCaptionMessage(example10, "progressindicators.name");
        I18NAwareFactory.setCaptionMessage(example11, "sliders.name");
        I18NAwareFactory.setCaptionMessage(example12, "tables.name");
        I18NAwareFactory.setCaptionMessage(example13, "texts.name");
        I18NAwareFactory.setCaptionMessage(example14, "trees.name");
        I18NAwareFactory.setCaptionMessage(example15, "uploads.name");
        I18NAwareFactory.setCaptionMessage(example16, "passwordsfields.name");
        I18NAwareFactory.setCaptionMessage(example17, "colorpickers.name");
        addComponent(tabSheet);
        addComponent(createLanguageSelector(i18NService));
    }

    @SuppressWarnings("serial")
    private NativeSelect createLanguageSelector(final I18NService i18NService) {
        NativeSelect languageSelector = new NativeSelect("language.selector");
        languageSelector.setImmediate(true);
        languageSelector.setNullSelectionAllowed(false);
        languageSelector.addItem(Locale.ENGLISH);
        languageSelector.setItemCaption(Locale.ENGLISH, "English");
        languageSelector.addItem(Locale.FRENCH);
        languageSelector.setItemCaption(Locale.FRENCH, "Francais");
        languageSelector.setValue(i18NService.getLocale());
        languageSelector.addListener(new Property.ValueChangeListener() {

            public void valueChange(ValueChangeEvent event) {
                Locale locale = (Locale) (event.getProperty().getValue());
                i18NService.setLocale(locale);
            }
        });
        return languageSelector;
    }
}
