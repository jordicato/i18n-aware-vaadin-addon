import com.vaadin.demo.sampler.features.upload.UploadBasicExample.LineBreakCounter;
import com.vaadin.terminal.gwt.client.ui.VAbsoluteLayout;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
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
import com.vaadin.ui.Layout;
import com.vaadin.ui.Link;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.LoginForm;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.Select;
import com.vaadin.ui.Slider;
import com.vaadin.ui.SplitPanel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.TreeTable;
import com.vaadin.ui.TwinColSelect;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalSplitPanel;
import com.vaadin.ui.Window;

public class TestVaadinUISupport {

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

    private Embedded embedded;

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

    private PopudDateField poputDateField;

    private PoputView poputView;

    private ProgressIndicator progressIndicator;

    private RichTextArea richTextArea;

    private Select select;

    private Slider slider;

    private SplitPanel splitPanel;

    private Tab tab;

    private Table table;

    private TabSheet tabSheet;

    private TextArea textArea;

    private TextField textField;

    private Tree tree;

    private TreeTable treeTable;

    private TwinColSelect twinColSelect;

    private Upload upload;

    private VerticalSplitPanel verticalSplitPanel;

    private Windows windows;

    public TestVaadinUISupport() {
        absoluteLayout = new AbsoluteLayout();
        absoluteLayout.setCaption("The caption");
        absoluteLayout.setDescription("The caption");
        verticalLayout = new VerticalLayout();
        verticalLayout.setCaption("The caption");
        verticalLayout.setDescription("The caption");
        horizontalLayout = new HorizontalLayout();
        horizontalLayout.setCaption("The caption");
        horizontalLayout.setDescription("The caption");
        accordion = new Accordion();
        accordion.setCaption("The caption");
        accordion.setDescription("The caption");
        tab = accordion.addTab(label, "The caption");
        tab.setCaption("The caption");
        tab.setDescription("The caption");
        button = new Button("The caption");
        button.setCaption("The caption");
        button.setDescription("The caption");
        button.setRequiredError("The caption");
        checkBox = new CheckBox("The caption");
        checkBox.setCaption("The caption");
        checkBox.setDescription("The caption");
        checkBox.setRequiredError("The caption");
        comboBox = new ComboBox("The caption");
        comboBox.setCaption("The caption");
        comboBox.setDescription("The caption");
        comboBox.setRequiredError("The caption");
        comboBox.setItemCaption(new Object(), "The caption");
        cssLayout = new CssLayout();
        cssLayout.setCaption("The caption");
        cssLayout.setDescription("The caption");
        customComponent = new CustomComponent();
        customComponent.setCaption("The caption");
        customComponent.setDescription("The caption");
        customLayout = new CustomLayout("The_Caption");
        customLayout.setCaption("The caption");
        customLayout.setDescription("The caption");
        dateField = new DateField("The caption");
        dateField.setCaption("The caption");
        dateField.setDescription("The caption");
        embedded = new Embedded("The caption");
        embedded.setCaption("The caption");
        embedded.setDescription("The caption");
        form = new Form(formLayout);
        form.setCaption("The caption");
        form.setDescription("The caption");
        form.setRequiredError("The caption");
        formLayout = new FormLayout();
        formLayout.setCaption("The caption");
        formLayout.setDescription("The caption");
        gridLayout = new GridLayout(200, 200);
        gridLayout.setCaption("The caption");
        gridLayout.setDescription("The caption");
        horizontalSplitPanel = new HorizontalSplitPanel();
        horizontalSplitPanel.setCaption("The caption");
        horizontalSplitPanel.setDescription("The caption");
        inlineDateField = new InlineDateField("The caption");
        inlineDateField.setCaption("The caption");
        inlineDateField.setDescription("The caption");
        inlineDateField.setRequiredError("The caption");
        label = new Label("The caption");
        label.setCaption("The caption");
        label.setDescription("The caption");
        link = new Link("The caption", new ExternalResource("http://vaadin.com/"));
        link.setCaption("The caption");
        link.setDescription("The caption");
        listSelect = new ListSelect("The caption");
        listSelect.setCaption("The caption");
        listSelect.setDescription("The caption");
        listSelect.setRequiredError("The caption");
        loginForm = new LoginForm();
        loginForm.setCaption("The caption");
        loginForm.setDescription("The caption");
        menuBar = new MenuBar();
        menuBar.setCaption("The caption");
        menuBar.setDescription("The caption");
        nativeButton = new NativeButton("The caption");
        nativeButton.setCaption("The caption");
        nativeButton.setDescription("The caption");
        nativeButton.setRequiredError("The caption");
        nativeSelect = new NativeSelect("The caption");
        nativeSelect.setCaption("The caption");
        nativeSelect.setDescription("The caption");
        nativeSelect.setRequiredError("The caption");
        optionGroup = new OptionGroup("The caption");
        optionGroup.setCaption("The caption");
        optionGroup.setDescription("The caption");
        optionGroup.setRequiredError("The caption");
        passwordField = new PasswordField("The caption");
        passwordField.setCaption("The caption");
        passwordField.setDescription("The caption");
        passwordField.setRequiredError("The caption");
        poputDateField = new PopupDateField("The caption");
        poputView = new PopupView("The_Caption", label);
        progressIndicator = new ProgressIndicator();
        progressIndicator.setCaption("The caption");
        progressIndicator.setDescription("The caption");
        progressIndicator.setRequiredError("The caption");
        richTextArea = new RichTextArea("The caption");
        richTextArea.setCaption("The caption");
        richTextArea.setDescription("The caption");
        richTextArea.setRequiredError("The caption");
        select = new Select("The caption");
        select.setCaption("The caption");
        select.setDescription("The caption");
        select.setRequiredError("The caption");
        slider = new Slider("The caption");
        slider.setCaption("The caption");
        slider.setDescription("The caption");
        slider.setRequiredError("The caption");
        splitPanel = new SplitPanel();
        splitPanel.setCaption("The caption");
        splitPanel.setDescription("The caption");
        table = new Table("The caption");
        table.setCaption("The caption");
        table.setDescription("The caption");
        table.setRequiredError("The caption");
        tabSheet = new TabSheet();
        tabSheet.addTab(label, "The caption");
        tabSheet.setCaption("The caption");
        tabSheet.setDescription("The caption");
        textArea = new TextArea("The caption");
        textArea.setCaption("The caption");
        textArea.setDescription("The caption");
        textArea.setRequiredError("The caption");
        textField = new TextField("The caption");
        textField.setCaption("The caption");
        textField.setDescription("The caption");
        textField.setRequiredError("The caption");
        tree = new Tree("The caption");
        tree.setCaption("The caption");
        tree.setDescription("The caption");
        tree.setRequiredError("The caption");
        treeTable = new TreeTable("The caption");
        treeTable.setCaption("The caption");
        treeTable.setDescription("The caption");
        treeTable.setRequiredError("The caption");
        twinColSelect = new TwinColSelect("The caption");
        twinColSelect.setCaption("The caption");
        twinColSelect.setDescription("The caption");
        twinColSelect.setRequiredError("The caption");
        upload = new Upload("The caption", new LineBreakCounter());
        upload.setCaption("The caption");
        upload.setDescription("The caption");
        verticalSplitPanel = new VerticalSplitPanel();
        verticalSplitPanel.setCaption("The caption");
        verticalSplitPanel.setDescription("The caption");
        windows = new Window("The caption");
    }
}
