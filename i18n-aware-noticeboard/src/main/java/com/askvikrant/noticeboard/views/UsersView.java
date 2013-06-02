package com.askvikrant.noticeboard.views;

import java.util.ArrayList;
import com.askvikrant.noticeboard.NoticeboardUI;
import com.askvikrant.noticeboard.business.BusinessLogic;
import com.askvikrant.noticeboard.model.Department;
import com.askvikrant.noticeboard.model.User;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.BaseTheme;

public class UsersView extends VerticalLayout implements View {

    private static final long serialVersionUID = 1L;

    public static final String NAME = "com.askvikrant.noticeboard.views.UsersView.Users";

    private final Table table = new Table();

    private final BeanItemContainer<User> container = new BeanItemContainer<User>(User.class);

    private static final Object[] NATURAL_COL_ORDER = new Object[] { "name", "department" };

    private static final String[] COL_HEADERS_ENGLISH = new String[] { "Name", "Department" };

    private final Button btnNew = new Button("com.askvikrant.noticeboard.views.UsersView.Add_New_User");

    private VerticalLayout vLayout = new VerticalLayout();

    private final Button btnSave = new Button("com.askvikrant.noticeboard.views.UsersView.Save");

    private final Button btnUpdate = new Button("com.askvikrant.noticeboard.views.UsersView.Update");

    private final Button btnCancel = new Button("com.askvikrant.noticeboard.views.UsersView.Cancel");

    private final TextField tfUserId = new TextField("com.askvikrant.noticeboard.views.UsersView.User_Id");

    private final TextField tfUserName = new TextField("com.askvikrant.noticeboard.views.UsersView.Name");

    private final PasswordField pfPassword = new PasswordField("com.askvikrant.noticeboard.views.UsersView.Password");

    private final ComboBox combo = new ComboBox("com.askvikrant.noticeboard.views.UsersView.Department");

    public UsersView(final User u) {
        setSpacing(true);
        table.setSizeFull();
        table.setSelectable(true);
        table.setColumnCollapsingAllowed(true);
        table.setPageLength(5);
        table.setContainerDataSource(container);
        table.setVisibleColumns(NATURAL_COL_ORDER);
        table.setColumnHeaders(COL_HEADERS_ENGLISH);
        table.addGeneratedColumn("Edit", new EditColumnGenerator());
        table.addGeneratedColumn("Delete", new DeleteColumnGenerator());
        table.setColumnWidth("userId", 80);
        table.setColumnWidth("Edit", 50);
        table.setColumnWidth("Delete", 50);
        HorizontalLayout hLayout = new HorizontalLayout();
        hLayout.setSpacing(true);
        hLayout.addComponent(btnSave);
        hLayout.addComponent(btnUpdate);
        hLayout.addComponent(btnCancel);
        tfUserId.setWidth(100, Unit.PERCENTAGE);
        tfUserName.setWidth(100, Unit.PERCENTAGE);
        pfPassword.setWidth(100, Unit.PERCENTAGE);
        combo.setImmediate(true);
        combo.setWidth(100, Unit.PERCENTAGE);
        combo.setNullSelectionAllowed(false);
        combo.setTextInputAllowed(false);
        vLayout.setSpacing(true);
        vLayout.addComponent(tfUserName);
        vLayout.addComponent(tfUserId);
        vLayout.addComponent(pfPassword);
        vLayout.addComponent(combo);
        vLayout.addComponent(hLayout);
        btnNew.addClickListener(new ClickListener() {

            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event) {
                vLayout.setVisible(true);
                tfUserName.setValue("");
                tfUserId.setReadOnly(false);
                tfUserId.setValue("");
                pfPassword.setValue("");
                setModeNew(true);
            }
        });
        btnCancel.addClickListener(new ClickListener() {

            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event) {
                vLayout.setVisible(true);
                tfUserName.setValue("");
                tfUserId.setReadOnly(false);
                tfUserId.setValue("");
                pfPassword.setValue("");
                vLayout.setVisible(false);
            }
        });
        btnSave.addClickListener(new ClickListener() {

            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event) {
                if (tfUserId.getValue().length() != 0 && tfUserName.getValue().length() != 0 && pfPassword.getValue().length() != 0 && combo.getValue().toString().length() != 0) {
                    BusinessLogic bl = ((NoticeboardUI) getUI()).getBusinessLogic();
                    boolean success = bl.saveNewUser(tfUserId.getValue(), tfUserName.getValue(), pfPassword.getValue(), User.ROLE_EXECUTIVE, (String) combo.getValue());
                    String appMessage = bl.getAppMessage();
                    if (success) {
                        Notification.show("Success", "User saved", Type.TRAY_NOTIFICATION);
                        tfUserId.setValue("");
                        vLayout.setVisible(false);
                        load();
                    } else {
                        Notification.show("Error", "User could not be saved. " + appMessage, Type.ERROR_MESSAGE);
                    }
                } else {
                    Notification.show("Error", "Please provide all values", Type.ERROR_MESSAGE);
                }
            }
        });
        btnUpdate.addClickListener(new ClickListener() {

            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event) {
                if (tfUserId.getValue().length() != 0 && tfUserName.getValue().length() != 0 && pfPassword.getValue().length() != 0 && combo.getValue().toString().length() != 0) {
                    BusinessLogic bl = ((NoticeboardUI) getUI()).getBusinessLogic();
                    boolean success = bl.updateUser(tfUserId.getValue(), tfUserName.getValue(), User.ROLE_EXECUTIVE, pfPassword.getValue(), (String) combo.getValue());
                    String appMessage = bl.getAppMessage();
                    if (success) {
                        Notification.show("Success", "User updated", Type.TRAY_NOTIFICATION);
                        vLayout.setVisible(false);
                        load();
                    } else {
                        Notification.show("Error", "User could not be updated. " + appMessage, Type.ERROR_MESSAGE);
                    }
                } else {
                    Notification.show("Error", "Please provide a name", Type.ERROR_MESSAGE);
                }
            }
        });
        addComponent(table);
        addComponent(btnNew);
        addComponent(vLayout);
        vLayout.setVisible(false);
    }

    private void setModeNew(boolean modeNew) {
        if (modeNew) {
            btnSave.setVisible(true);
            btnUpdate.setVisible(false);
        } else {
            btnSave.setVisible(false);
            btnUpdate.setVisible(true);
        }
    }

    private void load() {
        BusinessLogic bl = ((NoticeboardUI) getUI()).getBusinessLogic();
        ArrayList<Department> departments = bl.getDepartments();
        combo.removeAllItems();
        for (int i = 0; i < departments.size(); i++) {
            combo.addItem(departments.get(i).getName());
        }
        ArrayList<User> users = bl.getUsers();
        container.removeAllItems();
        container.addAll(users);
    }

    class EditColumnGenerator implements Table.ColumnGenerator {

        private static final long serialVersionUID = 1L;

        public Component generateCell(Table source, Object itemId, Object columnId) {
            final User user = (User) itemId;
            Button button = new Button("com.askvikrant.noticeboard.views.UsersView.Edit");
            button.setStyleName(BaseTheme.BUTTON_LINK);
            button.addClickListener(new ClickListener() {

                private static final long serialVersionUID = 1L;

                @Override
                public void buttonClick(ClickEvent event) {
                    vLayout.setVisible(true);
                    tfUserId.setReadOnly(false);
                    tfUserId.setValue(user.getUserId());
                    tfUserId.setReadOnly(true);
                    tfUserName.setValue(user.getName());
                    pfPassword.setValue(user.getPassword());
                    combo.select(user.getDepartment());
                    setModeNew(false);
                }
            });
            return button;
        }
    }

    class DeleteColumnGenerator implements Table.ColumnGenerator {

        private static final long serialVersionUID = 1L;

        public Component generateCell(Table source, Object itemId, Object columnId) {
            final User user = (User) itemId;
            Button button = new Button("com.askvikrant.noticeboard.views.UsersView.Delete");
            button.setStyleName(BaseTheme.BUTTON_LINK);
            button.addClickListener(new ClickListener() {

                private static final long serialVersionUID = 1L;

                @Override
                public void buttonClick(ClickEvent event) {
                    BusinessLogic bl = ((NoticeboardUI) getUI()).getBusinessLogic();
                    boolean success = bl.deleteUser(user.getUserId());
                    if (success) {
                        Notification.show("Success", "User deleted.", Type.TRAY_NOTIFICATION);
                        load();
                    } else {
                        String appMessage = bl.getAppMessage();
                        Notification.show("Error", "User could not be deleted. " + appMessage, Type.ERROR_MESSAGE);
                    }
                }
            });
            return button;
        }
    }

    @Override
    public void enter(ViewChangeEvent event) {
        load();
    }
}
