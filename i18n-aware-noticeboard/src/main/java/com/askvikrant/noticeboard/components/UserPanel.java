package com.askvikrant.noticeboard.components;

import com.askvikrant.noticeboard.NoticeboardUI;
import com.askvikrant.noticeboard.business.BusinessLogic;
import com.askvikrant.noticeboard.events.LoginSuccess_Event;
import com.askvikrant.noticeboard.events.Logout_Event;
import com.askvikrant.noticeboard.events.NewNotice_Event;
import com.askvikrant.noticeboard.events.SwitchView_Event;
import com.askvikrant.noticeboard.model.NoticeSearchParams;
import com.askvikrant.noticeboard.model.User;
import com.askvikrant.noticeboard.views.DepartmentsView;
import com.askvikrant.noticeboard.views.NoticesView;
import com.askvikrant.noticeboard.views.PasswordEditorView;
import com.askvikrant.noticeboard.views.QuickLinksView;
import com.askvikrant.noticeboard.views.UsersView;
import com.github.wolfie.blackboard.Blackboard;
import com.opnworks.vaadin.i18n.support.I18NExpression;
import com.opnworks.vaadin.i18n.ui.I18NHorizontalLayout;
import com.opnworks.vaadin.i18n.ui.I18NLabel;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.BaseTheme;

public class UserPanel extends Panel {

    private static final long serialVersionUID = 1L;

    private User user = null;

    private final I18NHorizontalLayout hLayout1 = new I18NHorizontalLayout();

    private final HorizontalLayout hLayout2 = new HorizontalLayout();

    private final MenuBar menuBar = new MenuBar();

    private MenuBar.Command executiveCommand = null;

    private MenuBar.Command rootCommand = null;

    public UserPanel() {
        VerticalLayout vLayout = new VerticalLayout();
        setContent(vLayout);
        vLayout.addComponent(hLayout1);
        vLayout.addComponent(hLayout2);
        hLayout1.setSpacing(true);
        hLayout2.setSpacing(true);
    }

    public void setUser(final User u) {
        user = u;
        if (user.getRole().equals(User.ROLE_GUEST)) {
            displayGuest();
        } else if (user.getRole().equals(User.ROLE_EXECUTIVE)) {
            displayExecutive();
        } else if (user.getRole().equals(User.ROLE_ROOT)) {
            displayRoot();
        }
    }

    private void displayGuest() {
        hLayout1.removeAllComponents();
        hLayout2.removeAllComponents();
        hLayout1.addComponent(new Label("com.askvikrant.noticeboard.components.UserPanel.Hello_Guest"));
        final TextField tfUserId = new TextField();
        final PasswordField pfPassword = new PasswordField();
        final Button btnLogin = new Button("com.askvikrant.noticeboard.components.UserPanel.Login");
        tfUserId.setWidth(75, Unit.PIXELS);
        tfUserId.setInputPrompt("User id");
        tfUserId.setRequired(true);
        pfPassword.setWidth(75, Unit.PIXELS);
        pfPassword.setInputPrompt("Password");
        pfPassword.setRequired(true);
        btnLogin.addClickListener(new ClickListener() {

            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event) {
                BusinessLogic bl = ((NoticeboardUI) getUI()).getBusinessLogic();
                User u = bl.authenticate(tfUserId.getValue(), pfPassword.getValue());
                if (u == null) {
                    Notification.show("Error", "Invalid user or password", Type.ERROR_MESSAGE);
                } else {
                    Blackboard bb = ((NoticeboardUI) getUI()).getBlackboard();
                    bb.fire(new LoginSuccess_Event(u));
                }
            }
        });
        hLayout2.addComponent(tfUserId);
        hLayout2.addComponent(pfPassword);
        hLayout2.addComponent(btnLogin);
    }

    private void displayExecutive() {
        hLayout1.removeAllComponents();
        hLayout2.removeAllComponents();
        hLayout1.addComponent(new I18NLabel(new I18NExpression("com.askvikrant.noticeboard.components.UserPanel.Hello", user.getName(), " !")));
        Button btnLogout = new Button("com.askvikrant.noticeboard.components.UserPanel.Log_out");
        btnLogout.addStyleName(BaseTheme.BUTTON_LINK);
        btnLogout.addClickListener(new ClickListener() {

            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event) {
                Blackboard bb = ((NoticeboardUI) getUI()).getBlackboard();
                bb.fire(new Logout_Event());
            }
        });
        Button btnPassword = new Button("com.askvikrant.noticeboard.components.UserPanel.Password");
        btnPassword.addStyleName(BaseTheme.BUTTON_LINK);
        btnPassword.addClickListener(new ClickListener() {

            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event) {
                Blackboard bb = ((NoticeboardUI) getUI()).getBlackboard();
                bb.fire(new SwitchView_Event(PasswordEditorView.NAME));
            }
        });
        hLayout1.addComponent(btnPassword);
        hLayout1.addComponent(btnLogout);
        executiveCommand = new MenuBar.Command() {

            private static final long serialVersionUID = 1L;

            public void menuSelected(MenuItem selectedItem) {
                String text = selectedItem.getText();
                if (text.equals("Add New Notice")) {
                    Blackboard bb = ((NoticeboardUI) getUI()).getBlackboard();
                    bb.fire(new NewNotice_Event());
                }
            }
        };
        menuBar.setWidth(100, Unit.PERCENTAGE);
        menuBar.setAutoOpen(true);
        menuBar.addItem("com.askvikrant.noticeboard.components.UserPanel.Add_New_Notice", null, executiveCommand);
        hLayout2.addComponent(menuBar);
    }

    private void displayRoot() {
        hLayout1.removeAllComponents();
        hLayout2.removeAllComponents();
        hLayout1.addComponent(new I18NLabel(new I18NExpression("com.askvikrant.noticeboard.components.UserPanel.Hello_1", user.getName(), " !")));
        Button btnLogout = new Button("com.askvikrant.noticeboard.components.UserPanel.Log_out_1");
        btnLogout.addStyleName(BaseTheme.BUTTON_LINK);
        btnLogout.addClickListener(new ClickListener() {

            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event) {
                Blackboard bb = ((NoticeboardUI) getUI()).getBlackboard();
                bb.fire(new Logout_Event());
            }
        });
        Button btnPassword = new Button("com.askvikrant.noticeboard.components.UserPanel.Password_1");
        btnPassword.addStyleName(BaseTheme.BUTTON_LINK);
        btnPassword.addClickListener(new ClickListener() {

            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event) {
                Blackboard bb = ((NoticeboardUI) getUI()).getBlackboard();
                bb.fire(new SwitchView_Event(PasswordEditorView.NAME));
            }
        });
        hLayout1.addComponent(btnPassword);
        hLayout1.addComponent(btnLogout);
        rootCommand = new MenuBar.Command() {

            private static final long serialVersionUID = 1L;

            public void menuSelected(MenuItem selectedItem) {
                String text = selectedItem.getText();
                if (text.equals("View Notices")) {
                    Blackboard bb = ((NoticeboardUI) getUI()).getBlackboard();
                    bb.fire(new SwitchView_Event(NoticesView.NAME));
                } else if (text.equals("Set default period: 07 Days")) {
                    BusinessLogic bl = ((NoticeboardUI) getUI()).getBusinessLogic();
                    boolean success = bl.updateDefaultDays(NoticeSearchParams.TYPE_07_DAYS);
                    String appMessage = bl.getAppMessage();
                    if (success) {
                        Notification.show("Success", "Please re-login", Type.TRAY_NOTIFICATION);
                        Blackboard bb = ((NoticeboardUI) getUI()).getBlackboard();
                        bb.fire(new SwitchView_Event(NoticesView.NAME));
                    } else {
                        Notification.show("Error", appMessage, Type.ERROR_MESSAGE);
                    }
                } else if (text.equals("Set default period: 15 Days")) {
                    BusinessLogic bl = ((NoticeboardUI) getUI()).getBusinessLogic();
                    boolean success = bl.updateDefaultDays(NoticeSearchParams.TYPE_15_DAYS);
                    String appMessage = bl.getAppMessage();
                    if (success) {
                        Notification.show("Success", "Please re-login", Type.TRAY_NOTIFICATION);
                        Blackboard bb = ((NoticeboardUI) getUI()).getBlackboard();
                        bb.fire(new SwitchView_Event(NoticesView.NAME));
                    } else {
                        Notification.show("Error", appMessage, Type.ERROR_MESSAGE);
                    }
                } else if (text.equals("Set default period: 30 Days")) {
                    BusinessLogic bl = ((NoticeboardUI) getUI()).getBusinessLogic();
                    boolean success = bl.updateDefaultDays(NoticeSearchParams.TYPE_30_DAYS);
                    String appMessage = bl.getAppMessage();
                    if (success) {
                        Notification.show("Success", "Please re-login", Type.TRAY_NOTIFICATION);
                        Blackboard bb = ((NoticeboardUI) getUI()).getBlackboard();
                        bb.fire(new SwitchView_Event(NoticesView.NAME));
                    } else {
                        Notification.show("Error", appMessage, Type.ERROR_MESSAGE);
                    }
                } else if (text.equals("Links")) {
                    Blackboard bb = ((NoticeboardUI) getUI()).getBlackboard();
                    bb.fire(new SwitchView_Event(QuickLinksView.NAME));
                } else if (text.equals("View Users")) {
                    Blackboard bb = ((NoticeboardUI) getUI()).getBlackboard();
                    bb.fire(new SwitchView_Event(UsersView.NAME));
                } else if (text.equals("View Departments")) {
                    Blackboard bb = ((NoticeboardUI) getUI()).getBlackboard();
                    bb.fire(new SwitchView_Event(DepartmentsView.NAME));
                }
            }
        };
        menuBar.setWidth(100, Unit.PERCENTAGE);
        menuBar.setAutoOpen(true);
        MenuItem miNotices = menuBar.addItem("com.askvikrant.noticeboard.components.UserPanel.Notices", null, null);
        miNotices.addItem("com.askvikrant.noticeboard.components.UserPanel.View_Notices", null, rootCommand);
        miNotices.addItem("com.askvikrant.noticeboard.components.UserPanel.Set_default_period_07_Days", null, rootCommand);
        miNotices.addItem("com.askvikrant.noticeboard.components.UserPanel.Set_default_period_15_Days", null, rootCommand);
        miNotices.addItem("com.askvikrant.noticeboard.components.UserPanel.Set_default_period_30_Days", null, rootCommand);
        MenuItem miUsers = menuBar.addItem("com.askvikrant.noticeboard.components.UserPanel.Users", null, null);
        miUsers.addItem("com.askvikrant.noticeboard.components.UserPanel.View_Users", null, rootCommand);
        miUsers.addItem("com.askvikrant.noticeboard.components.UserPanel.View_Departments", null, rootCommand);
        menuBar.addItem("com.askvikrant.noticeboard.components.UserPanel.Links", null, rootCommand);
        hLayout2.addComponent(menuBar);
    }
}
