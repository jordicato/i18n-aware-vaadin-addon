package com.vaadin.demo.dashboard;

import com.vaadin.demo.dashboard.data.DataProvider;
import com.vaadin.demo.dashboard.data.Generator;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.Align;
import com.vaadin.ui.Table.RowHeaderMode;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class DashboardView extends VerticalLayout implements View {

    Table t;

    public DashboardView() {
        setSizeFull();
        addStyleName("dashboard-view");
        HorizontalLayout top = new HorizontalLayout();
        top.setWidth("100%");
        top.setSpacing(true);
        top.addStyleName("toolbar");
        addComponent(top);
        final Label title = new Label("com.vaadin.demo.dashboard.DashboardView.My_Dashboard");
        title.setSizeUndefined();
        title.addStyleName("h1");
        top.addComponent(title);
        top.setComponentAlignment(title, Alignment.MIDDLE_LEFT);
        top.setExpandRatio(title, 1);
        Button notify = new Button("2");
        notify.setDescription("com.vaadin.demo.dashboard.DashboardView.Notifications_2_unread");
        notify.addStyleName("notifications");
        notify.addStyleName("unread");
        notify.addStyleName("icon-only");
        notify.addStyleName("icon-bell");
        notify.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                ((DashboardUI) getUI()).clearDashboardButtonBadge();
                event.getButton().removeStyleName("unread");
                event.getButton().setDescription("Notifications");
                if (notifications != null && notifications.getUI() != null) notifications.close(); else {
                    buildNotifications(event);
                    getUI().addWindow(notifications);
                    notifications.focus();
                    ((CssLayout) getUI().getContent()).addLayoutClickListener(new LayoutClickListener() {

                        @Override
                        public void layoutClick(LayoutClickEvent event) {
                            notifications.close();
                            ((CssLayout) getUI().getContent()).removeLayoutClickListener(this);
                        }
                    });
                }
            }
        });
        top.addComponent(notify);
        top.setComponentAlignment(notify, Alignment.MIDDLE_LEFT);
        Button edit = new Button();
        edit.addStyleName("icon-edit");
        edit.addStyleName("icon-only");
        top.addComponent(edit);
        edit.setDescription("com.vaadin.demo.dashboard.DashboardView.Edit_Dashboard");
        edit.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                final Window w = new Window("com.vaadin.demo.dashboard.DashboardView.Edit_Dashboard_1");
                w.setModal(true);
                w.setClosable(false);
                w.setResizable(false);
                w.addStyleName("edit-dashboard");
                getUI().addWindow(w);
                w.setContent(new VerticalLayout() {

                    TextField name = new TextField("com.vaadin.demo.dashboard.DashboardView.Dashboard_Name");

                    {
                        addComponent(new FormLayout() {

                            {
                                setSizeUndefined();
                                setMargin(true);
                                name.setValue(title.getValue());
                                addComponent(name);
                                name.focus();
                                name.selectAll();
                            }
                        });
                        addComponent(new HorizontalLayout() {

                            {
                                setMargin(true);
                                setSpacing(true);
                                addStyleName("footer");
                                setWidth("100%");
                                Button cancel = new Button("com.vaadin.demo.dashboard.DashboardView.Cancel");
                                cancel.addClickListener(new ClickListener() {

                                    @Override
                                    public void buttonClick(ClickEvent event) {
                                        w.close();
                                    }
                                });
                                cancel.setClickShortcut(KeyCode.ESCAPE, null);
                                addComponent(cancel);
                                setExpandRatio(cancel, 1);
                                setComponentAlignment(cancel, Alignment.TOP_RIGHT);
                                Button ok = new Button("com.vaadin.demo.dashboard.DashboardView.Save");
                                ok.addStyleName("wide");
                                ok.addStyleName("default");
                                ok.addClickListener(new ClickListener() {

                                    @Override
                                    public void buttonClick(ClickEvent event) {
                                        title.setValue(name.getValue());
                                        w.close();
                                    }
                                });
                                ok.setClickShortcut(KeyCode.ENTER, null);
                                addComponent(ok);
                            }
                        });
                    }
                });
            }
        });
        top.setComponentAlignment(edit, Alignment.MIDDLE_LEFT);
        HorizontalLayout row = new HorizontalLayout();
        row.setSizeFull();
        row.setMargin(new MarginInfo(true, true, false, true));
        row.setSpacing(true);
        addComponent(row);
        setExpandRatio(row, 1.5f);
        row.addComponent(createPanel(new TopGrossingMoviesChart()));
        TextArea notes = new TextArea("com.vaadin.demo.dashboard.DashboardView.Notes");
        notes.setValue("com.vaadin.demo.dashboard.DashboardView.Remember_to_n_Zoom_in_and_o");
        notes.setSizeFull();
        CssLayout panel = createPanel(notes);
        panel.addStyleName("notes");
        row.addComponent(panel);
        row = new HorizontalLayout();
        row.setMargin(true);
        row.setSizeFull();
        row.setSpacing(true);
        addComponent(row);
        setExpandRatio(row, 2);
        t = new Table();
        t.setWidth("100%");
        t.setPageLength(0);
        t.addStyleName("plain");
        t.addStyleName("borderless");
        t.setSortEnabled(false);
        t.setColumnAlignment("Revenue", Align.RIGHT);
        t.setRowHeaderMode(RowHeaderMode.INDEX);
        t.setCaption("com.vaadin.demo.dashboard.DashboardView.Top_10_Titles_by_Revenue");
        row.addComponent(createPanel(t));
        row.addComponent(createPanel(new TopSixTheatersChart()));
    }

    private CssLayout createPanel(Component content) {
        CssLayout panel = new CssLayout();
        panel.addStyleName("layout-panel");
        panel.setSizeFull();
        Button configure = new Button();
        configure.addStyleName("configure");
        configure.addStyleName("icon-cog");
        configure.addStyleName("icon-only");
        configure.addStyleName("borderless");
        configure.setDescription("com.vaadin.demo.dashboard.DashboardView.Configure");
        configure.addStyleName("small");
        configure.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                Notification.show("Not implemented in this demo");
            }
        });
        panel.addComponent(configure);
        panel.addComponent(content);
        return panel;
    }

    @Override
    public void enter(ViewChangeEvent event) {
        DataProvider dataProvider = ((DashboardUI) getUI()).dataProvider;
        t.setContainerDataSource(dataProvider.getRevenueByTitle());
    }

    Window notifications;

    private void buildNotifications(ClickEvent event) {
        notifications = new Window("com.vaadin.demo.dashboard.DashboardView.Notifications");
        VerticalLayout l = new VerticalLayout();
        l.setMargin(true);
        l.setSpacing(true);
        notifications.setContent(l);
        notifications.setWidth("300px");
        notifications.addStyleName("notifications");
        notifications.setClosable(false);
        notifications.setResizable(false);
        notifications.setDraggable(false);
        notifications.setPositionX(event.getClientX() - event.getRelativeX());
        notifications.setPositionY(event.getClientY() - event.getRelativeY());
        notifications.setCloseShortcut(KeyCode.ESCAPE, null);
        Label label = new Label("<hr><b>" + Generator.randomFirstName() + " " + Generator.randomLastName() + " created a new report</b><br><span>25 minutes ago</span><br>" + Generator.randomText(18), ContentMode.HTML);
        l.addComponent(label);
        label = new Label("<hr><b>" + Generator.randomFirstName() + " " + Generator.randomLastName() + " changed the schedule</b><br><span>2 days ago</span><br>" + Generator.randomText(10), ContentMode.HTML);
        l.addComponent(label);
    }
}
