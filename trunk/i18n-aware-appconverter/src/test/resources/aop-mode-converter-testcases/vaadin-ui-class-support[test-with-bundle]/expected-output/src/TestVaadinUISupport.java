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

    private OptionGroup otionGroup;

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

    private TextArea testArea;

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
        verticalLayout = new VAbsoluteLayout();
        verticalLayout.setCaption("The caption");
        horizontalLayout = new HorizontalLayout();
        horizontalLayout.setCaption("The caption");
        accordion = new Accordion();
        accordion.setCaption("The caption");
        tab = accordion.addTab(label, "The caption");
        tab.setCaption("The caption");
        button = new Button("The caption");
        checkBox = new CheckBox("The caption");
        comboBox = new ComboBox("The caption");
        cssLayout = new CssLayout();
        cssLayout.setCaption("The caption");
        customComponent = new CustomComponent();
        customComponent.setCaption("The caption");
        customLayout = new CustomLayout("The caption");
        dateField = new DateField("The caption");
        embedded = new Embedded("The caption");
        form = new Form(formLayout);
        form.setCaption("The caption");
        formLayout = new FormLayout();
        formLayout.setCaption("The caption");
        gridLayout = new GridLayout(200, 200);
        gridLayout.setCaption("The caption");
        horizontalSplitPanel = new HorizontalSplitPanel();
        horizontalSplitPanel.setCaption("The caption");
        inlineDateField = new InlineDateField("The caption");
        label = new Label("The caption");
        link = new Link("The caption", new ExternalResource("http://vaadin.com/"));
        listSelect = new ListSelect("The caption");
        loginForm = new LoginForm();
        loginForm.setCaption("The caption");
        menuBar = new MenuBar();
        menuBar.setCaption("The caption");
        nativeButton = new NativeButton("The caption");
        nativeSelect = new NativeSelect("The caption");
        otionGroup = new OptionGroup("The caption");
        passwordField = new PasswordField("The caption");
        poputDateField = new PopupDateField("The caption");
        poputView = new PopupView("The caption", label);
        progressIndicator = new ProgressIndicator();
        progressIndicator.setCaption("The caption");
        richTextArea = new RichTextArea("The caption");
        select = new Select("The caption");
        slider = new Slider("The caption");
        splitPanel = new SplitPanel();
        splitPanel.setCaption("The caption");
        table = new Table("The caption");
        tabSheet = new TabSheet();
        tabSheet.addTab(label, "The caption");
        tabSheet.setCaption("The caption");
        textArea = new TextArea("The caption");
        textField = new TextField("The caption");
        tree = new Tree("The caption");
        treeTable = new TreeTable("The caption");
        twinColSelect = new TwinColSelect("The caption");
        upload = new Upload("The caption", new LineBreakCounter());
        verticalSplitPanel = new VerticalSplitPanel();
        verticalSplitPanel.setCaption("The caption");
        windows = new Window("The caption");
    }
}
