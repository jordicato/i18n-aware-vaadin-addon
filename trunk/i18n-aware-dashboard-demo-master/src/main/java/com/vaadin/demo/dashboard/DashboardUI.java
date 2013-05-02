package com.vaadin.demo.dashboard;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import com.opnworks.vaadin.i18n.I18NStaticService;
import com.opnworks.vaadin.i18n.data.util.I18NCountLiterals;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.demo.dashboard.data.DataProvider;
import com.vaadin.demo.dashboard.data.Generator;
import com.vaadin.demo.dashboard.data.MyConverterFactory;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.event.ShortcutListener;
import com.vaadin.event.Transferable;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.AbstractSelect.AcceptItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.DragAndDropWrapper;
import com.vaadin.ui.DragAndDropWrapper.DragStartMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("dashboard")
@Title("QuickTickets Dashboard")
public class DashboardUI extends UI {

    I18NStaticService i18NAware = new I18NStaticService("messages", Locale.ENGLISH);

    AuthenticateProxy auth = new AuthenticateProxy();

    DataProvider dataProvider = new DataProvider();

    private static final long serialVersionUID = 1L;

    CssLayout root = new CssLayout();

    VerticalLayout loginLayout;

    CssLayout menu = new CssLayout();

    CssLayout content = new CssLayout();

    HashMap<String, Class<? extends View>> routes = new HashMap<String, Class<? extends View>>() {

        {
            put("/dashboard", DashboardView.class);
            put("/sales", SalesView.class);
            put("/transactions", TransactionsView.class);
            put("/reports", ReportsView.class);
            put("/schedule", ScheduleView.class);
        }
    };

    HashMap<String, Button> viewNameToMenuButton = new HashMap<String, Button>();

    private Navigator nav;

    private HelpManager helpManager;

    @Override
    protected void init(VaadinRequest request) {
        getSession().setConverterFactory(new MyConverterFactory());
        helpManager = new HelpManager(this);
        setLocale(Locale.ENGLISH);
        setContent(root);
        root.addStyleName("root");
        root.setSizeFull();
        Label bg = new Label();
        bg.setSizeUndefined();
        bg.addStyleName("login-bg");
        root.addComponent(bg);
        buildLoginView(false);
    }

    private void buildLoginView(boolean exit) {
        if (exit) {
            root.removeAllComponents();
        }
        helpManager.closeAll();
        HelpOverlay w = helpManager.addOverlay("Welcome to the Dashboard Demo Application", "<p>This application is not real, it only demonstrates an application built with the <a href=\"http://vaadin.com\">Vaadin framework</a>.</p><p>No username or password is required, just click the ˜Sign In button to continue. You can try out a random username and password, though.</p>", "login");
        w.center();
        addWindow(w);
        addStyleName("login");
        loginLayout = new VerticalLayout();
        loginLayout.setSizeFull();
        loginLayout.addStyleName("login-layout");
        root.addComponent(loginLayout);
        final CssLayout loginPanel = new CssLayout();
        loginPanel.addStyleName("login-panel");
        HorizontalLayout labels = new HorizontalLayout();
        labels.setWidth("100%");
        labels.setMargin(true);
        labels.addStyleName("labels");
        loginPanel.addComponent(labels);
        String LB = I18NCountLiterals.registerLiteral("Welcome", "com.vaadin.demo.dashboard.DashboardUI.Welcome");
        Label welcome = new Label(LB);
        welcome.setSizeUndefined();
        welcome.addStyleName("h4");
        LB = I18NCountLiterals.registerLiteral("Welcome", "com.vaadin.demo.dashboard.DashboardUI.Welcome_1");
        labels.addComponent(welcome);
        labels.setComponentAlignment(welcome, Alignment.MIDDLE_LEFT);
        LB = I18NCountLiterals.registerLiteral("QuickTickets Dashboard", "com.vaadin.demo.dashboard.DashboardUI.QuickTickets_Dashboard");
        Label title = new Label(LB);
        title.setSizeUndefined();
        title.addStyleName("h2");
        title.addStyleName("light");
        labels.addComponent(title);
        labels.setComponentAlignment(title, Alignment.MIDDLE_RIGHT);
        HorizontalLayout fields = new HorizontalLayout();
        fields.setSpacing(true);
        fields.setMargin(true);
        fields.addStyleName("fields");
        LB = I18NCountLiterals.registerLiteral("Username", "com.vaadin.demo.dashboard.DashboardUI.Username");
        final TextField username = new TextField(LB);
        username.focus();
        fields.addComponent(username);
        final PasswordField password = new PasswordField("com.vaadin.demo.dashboard.DashboardUI.Password");
        fields.addComponent(password);
        final Button signin = new Button("com.vaadin.demo.dashboard.DashboardUI.Sign_In");
        signin.addStyleName("default");
        fields.addComponent(signin);
        fields.setComponentAlignment(signin, Alignment.BOTTOM_LEFT);
        final ShortcutListener enter = new ShortcutListener("Sign In", KeyCode.ENTER, null) {

            @Override
            public void handleAction(Object sender, Object target) {
                signin.click();
            }
        };
        signin.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                if (username.getValue() != null && username.getValue().equals("") && password.getValue() != null && password.getValue().equals("")) {
                    signin.removeShortcutListener(enter);
                    buildMainView();
                } else {
                    if (loginPanel.getComponentCount() > 2) {
                        loginPanel.removeComponent(loginPanel.getComponent(2));
                    }
                    Label error = new Label("Wrong username or password. <span>Hint: try empty values</span>", ContentMode.HTML);
                    error.addStyleName("error");
                    error.setSizeUndefined();
                    error.addStyleName("light");
                    error.addStyleName("v-animate-reveal");
                    loginPanel.addComponent(error);
                    username.focus();
                }
            }
        });
        signin.addShortcutListener(enter);
        loginPanel.addComponent(fields);
        loginLayout.addComponent(loginPanel);
        loginLayout.setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
    }

    private void buildMainView() {
        nav = new Navigator(this, content);
        for (String route : routes.keySet()) {
            nav.addView(route, routes.get(route));
        }
        helpManager.closeAll();
        removeStyleName("login");
        root.removeComponent(loginLayout);
        root.addComponent(new HorizontalLayout() {

            {
                setSizeFull();
                addStyleName("main-view");
                addComponent(new VerticalLayout() {

                    {
                        ComboBox languageSelector = createLanguageSelector();
                        addComponent(languageSelector);
                        addStyleName("sidebar");
                        setWidth(null);
                        setHeight("100%");
                        addComponent(new CssLayout() {

                            {
                                addStyleName("branding");
                                Label logo = new Label("<span>QuickTickets</span> Dashboard", ContentMode.HTML);
                                logo.setSizeUndefined();
                                addComponent(logo);
                            }
                        });
                        addComponent(menu);
                        setExpandRatio(menu, 1);
                        addComponent(new VerticalLayout() {

                            {
                                setSizeUndefined();
                                addStyleName("user");
                                Image profilePic = new Image(null, new ThemeResource("img/profile-pic.png"));
                                profilePic.setWidth("34px");
                                addComponent(profilePic);
                                Label userName = new Label(Generator.randomFirstName() + "" + Generator.randomLastName());
                                userName.setSizeUndefined();
                                addComponent(userName);
                                Command cmd = new Command() {

                                    @Override
                                    public void menuSelected(MenuItem selectedItem) {
                                        Notification.show("Not implemented in this demo");
                                    }
                                };
                                MenuBar settings = new MenuBar();
                                MenuItem settingsMenu = settings.addItem("", null);
                                settingsMenu.setStyleName("icon-cog");
                                settingsMenu.addItem("com.vaadin.demo.dashboard.DashboardUI.Setting", cmd);
                                settingsMenu.addItem("com.vaadin.demo.dashboard.DashboardUI.Preferences", cmd);
                                settingsMenu.addSeparator();
                                settingsMenu.addItem("com.vaadin.demo.dashboard.DashboardUI.My_Account", cmd);
                                addComponent(settings);
                                Button exit = new NativeButton("com.vaadin.demo.dashboard.DashboardUI.Exit");
                                exit.addStyleName("icon-cancel");
                                exit.setDescription("com.vaadin.demo.dashboard.DashboardUI.Sign_Out");
                                addComponent(exit);
                                exit.addClickListener(new ClickListener() {

                                    @Override
                                    public void buttonClick(ClickEvent event) {
                                        buildLoginView(true);
                                    }
                                });
                            }
                        });
                    }
                });
                addComponent(content);
                content.setSizeFull();
                content.addStyleName("view-content");
                setExpandRatio(content, 1);
            }
        });
        menu.removeAllComponents();
        for (final String view : new String[] { "dashboard", "sales", "transactions", "reports", "schedule" }) {
            Button b = new NativeButton(view.substring(0, 1).toUpperCase() + view.substring(1).replace('-', ' '));
            b.addStyleName("icon-" + view);
            b.addClickListener(new ClickListener() {

                @Override
                public void buttonClick(ClickEvent event) {
                    clearMenuSelection();
                    event.getButton().addStyleName("selected");
                    if (!nav.getState().equals("/" + view)) nav.navigateTo("/" + view);
                }
            });
            if (view.equals("reports")) {
                DragAndDropWrapper reports = new DragAndDropWrapper(b);
                reports.setDragStartMode(DragStartMode.NONE);
                reports.setDropHandler(new DropHandler() {

                    @Override
                    public void drop(DragAndDropEvent event) {
                        clearMenuSelection();
                        viewNameToMenuButton.get("/reports").addStyleName("selected");
                        autoCreateReport = true;
                        items = event.getTransferable();
                        nav.navigateTo("/reports");
                    }

                    @Override
                    public AcceptCriterion getAcceptCriterion() {
                        return AcceptItem.ALL;
                    }
                });
                menu.addComponent(reports);
                menu.addStyleName("no-vertical-drag-hints");
                menu.addStyleName("no-horizontal-drag-hints");
            } else {
                menu.addComponent(b);
            }
            viewNameToMenuButton.put("/" + view, b);
        }
        menu.addStyleName("menu");
        menu.setHeight("100%");
        viewNameToMenuButton.get("/dashboard").setHtmlContentAllowed(true);
        viewNameToMenuButton.get("/dashboard").setCaption("Dashboard<span class=\"badge\">2</span>");
        String f = Page.getCurrent().getUriFragment();
        if (f != null && f.startsWith("!")) {
            f = f.substring(1);
        }
        if (f == null || f.equals("") || f.equals("/")) {
            nav.navigateTo("/dashboard");
            menu.getComponent(0).addStyleName("selected");
            helpManager.showHelpFor(DashboardView.class);
        } else {
            nav.navigateTo(f);
            helpManager.showHelpFor(routes.get(f));
            viewNameToMenuButton.get(f).addStyleName("selected");
        }
        nav.addViewChangeListener(new ViewChangeListener() {

            @Override
            public boolean beforeViewChange(ViewChangeEvent event) {
                helpManager.closeAll();
                return true;
            }

            @Override
            public void afterViewChange(ViewChangeEvent event) {
                View newView = event.getNewView();
                helpManager.showHelpFor(newView);
                if (autoCreateReport && newView instanceof ReportsView) {
                    ((ReportsView) newView).autoCreate(2, items, transactions);
                }
                autoCreateReport = false;
            }
        });
    }

    private ComboBox createLanguageSelector() {
        ComboBox languageSelector = new ComboBox("com.vaadin.demo.dashboard.DashboardUI.Language");
        languageSelector.setImmediate(true);
        languageSelector.setNullSelectionAllowed(false);
        addLocale(Locale.ENGLISH, languageSelector);
        addLocale(Locale.FRENCH, languageSelector);
        addLocale(new Locale("es"), languageSelector);
        languageSelector.setValue(I18NStaticService.getI18NServive().getLocale());
        languageSelector.addValueChangeListener(new ValueChangeListener() {

            private static final long serialVersionUID = 1L;

            @Override
            public void valueChange(ValueChangeEvent event) {
                Locale locale = (Locale) (event.getProperty().getValue());
                I18NStaticService.getI18NServive().setLocale(locale);
                getUI().requestRepaintAll();
            }
        });
        return languageSelector;
    }

    private void addLocale(Locale locale, ComboBox languageSelector) {
        languageSelector.addItem(locale);
        languageSelector.setItemCaption(locale, locale.getDisplayLanguage(locale).substring(0, 1).toUpperCase() + locale.getDisplayLanguage(locale).substring(1));
    }

    private Transferable items;

    private void clearMenuSelection() {
        for (Iterator<Component> it = menu.getComponentIterator(); it.hasNext(); ) {
            Component next = it.next();
            if (next instanceof NativeButton) {
                next.removeStyleName("selected");
            } else if (next instanceof DragAndDropWrapper) {
                ((DragAndDropWrapper) next).iterator().next().removeStyleName("selected");
            }
        }
    }

    void updateReportsButtonBadge(String badgeCount) {
        viewNameToMenuButton.get("/reports").setHtmlContentAllowed(true);
        viewNameToMenuButton.get("/reports").setCaption("Reports<span class=\"badge\">" + badgeCount + "</span>");
    }

    void clearDashboardButtonBadge() {
        viewNameToMenuButton.get("/dashboard").setCaption("Dashboard");
    }

    boolean autoCreateReport = false;

    Table transactions;

    public void openReports(Table t) {
        transactions = t;
        autoCreateReport = true;
        nav.navigateTo("/reports");
        clearMenuSelection();
        viewNameToMenuButton.get("/reports").addStyleName("selected");
    }

    HelpManager getHelpManager() {
        return helpManager;
    }
}
