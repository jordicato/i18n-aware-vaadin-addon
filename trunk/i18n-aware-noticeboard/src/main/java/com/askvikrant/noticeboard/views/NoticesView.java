package com.askvikrant.noticeboard.views;

import java.util.ArrayList;

import com.askvikrant.noticeboard.NoticeboardUI;
import com.askvikrant.noticeboard.business.BusinessLogic;
import com.askvikrant.noticeboard.components.NoticeSummaryPanel;
import com.askvikrant.noticeboard.model.Notice;
import com.askvikrant.noticeboard.model.NoticeSearchParams;
import com.askvikrant.noticeboard.model.User;
import com.github.wolfie.refresher.Refresher;
import com.github.wolfie.refresher.Refresher.RefreshListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.BaseTheme;

public class NoticesView extends VerticalLayout implements View {

    private static final long serialVersionUID = 1L;

    public static final String NAME = "com.askvikrant.noticeboard.views.NoticesView.Notices";

    private final VerticalLayout noticesVLayout = new VerticalLayout();

    private final Button btn07 = new Button("com.askvikrant.noticeboard.views.NoticesView.Last_7_days");

    private final Button btn15 = new Button("com.askvikrant.noticeboard.views.NoticesView.Last_15_days");

    private final Button btn30 = new Button("com.askvikrant.noticeboard.views.NoticesView.Last_30_days");

    private final TextField tfSearchText = new TextField();

    private final Button btnSearch = new Button("com.askvikrant.noticeboard.views.NoticesView.Search");

    private final Label lblSearchType = new Label("");

    private final Label lblRecords = new Label("");

    private User user = null;

    private final NoticeSearchParams searchParams = new NoticeSearchParams();

    public NoticesView(final User u, String defaultDays) {
        user = u;
        searchParams.setUser(user);
        lblSearchType.setImmediate(true);
        HorizontalLayout searchHLayout = new HorizontalLayout();
        HorizontalLayout daysHLayout = new HorizontalLayout();
        HorizontalLayout resultsHLayout = new HorizontalLayout();
        searchHLayout.setSpacing(true);
        daysHLayout.setSpacing(true);
        resultsHLayout.setSpacing(true);
        daysHLayout.addComponent(btn07);
        daysHLayout.addComponent(btn15);
        daysHLayout.addComponent(btn30);
        btn07.addStyleName(BaseTheme.BUTTON_LINK);
        btn15.addStyleName(BaseTheme.BUTTON_LINK);
        btn30.addStyleName(BaseTheme.BUTTON_LINK);
        btn07.addClickListener(new ClickListener() {

            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event) {
                tfSearchText.setValue("");
                lblSearchType.setValue("<span style=\"font-weight:bold;\">" + "Last 7 days: </span>");
                searchParams.setSearchType(NoticeSearchParams.TYPE_07_DAYS);
                load();
            }
        });
        btn15.addClickListener(new ClickListener() {

            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event) {
                tfSearchText.setValue("");
                lblSearchType.setValue("<span style=\"font-weight:bold;\">" + "Last 15 days: </span>");
                searchParams.setSearchType(NoticeSearchParams.TYPE_15_DAYS);
                load();
            }
        });
        btn30.addClickListener(new ClickListener() {

            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event) {
                tfSearchText.setValue("");
                lblSearchType.setValue("<span style=\"font-weight:bold;\">" + "Last 30 days: </span>");
                searchParams.setSearchType(NoticeSearchParams.TYPE_30_DAYS);
                load();
            }
        });
        lblSearchType.setValue("<span style=\"font-weight:bold;\">" + "Last 15 days: </span>");
        searchParams.setSearchType(NoticeSearchParams.TYPE_15_DAYS);
        if (defaultDays.equals(NoticeSearchParams.TYPE_07_DAYS)) {
            lblSearchType.setValue("<span style=\"font-weight:bold;\">" + "Last 7 days: </span>");
            searchParams.setSearchType(NoticeSearchParams.TYPE_07_DAYS);
        } else if (defaultDays.equals(NoticeSearchParams.TYPE_15_DAYS)) {
            lblSearchType.setValue("<span style=\"font-weight:bold;\">" + "Last 15 days: </span>");
            searchParams.setSearchType(NoticeSearchParams.TYPE_15_DAYS);
        } else if (defaultDays.equals(NoticeSearchParams.TYPE_30_DAYS)) {
            lblSearchType.setValue("<span style=\"font-weight:bold;\">" + "Last 30 days: </span>");
            searchParams.setSearchType(NoticeSearchParams.TYPE_30_DAYS);
        }
        tfSearchText.setInputPrompt("Text to search");
        tfSearchText.setWidth(160, Unit.PIXELS);
        btnSearch.setClickShortcut(KeyCode.ENTER);
        btnSearch.addClickListener(new ClickListener() {

            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event) {
                lblSearchType.setValue("<span style=\"font-weight:bold;\">" + "Search: </span>");
                searchParams.setSearchType(NoticeSearchParams.TYPE_TEXT_SEARCH);
                searchParams.setSearchText(tfSearchText.getValue());
                load();
            }
        });

        searchHLayout.addComponent(tfSearchText);
        searchHLayout.addComponent(btnSearch);
        lblSearchType.setContentMode(ContentMode.HTML);
        lblRecords.setContentMode(ContentMode.HTML);
        resultsHLayout.addComponent(lblSearchType);
        resultsHLayout.addComponent(lblRecords);
        Label lblLine1 = new Label("<hr>");
        Label lblLine2 = new Label("<hr>");
        lblLine1.setContentMode(ContentMode.HTML);
        lblLine2.setContentMode(ContentMode.HTML);
        addComponent(searchHLayout);
        addComponent(daysHLayout);
        addComponent(lblLine1);
        addComponent(resultsHLayout);
        addComponent(lblLine2);
        addComponent(noticesVLayout);
        final Refresher refresher = new Refresher();
        refresher.setRefreshInterval(60000);
        refresher.addListener(new NoticesRefreshListener());
        addExtension(refresher);
    }
    
    private void load() {
        BusinessLogic bl = ((NoticeboardUI) getUI()).getBusinessLogic();
        ArrayList<Notice> notices = bl.getNotices(searchParams);
        lblRecords.setValue("<span style=\"font-weight:bold;\">" + notices.size() + " record(s) found.</span>");
        noticesVLayout.removeAllComponents();
        for (int i = 0; i < notices.size(); i++) {
            Notice notice = notices.get(i);
            NoticeSummaryPanel noticeSummaryPanel = new NoticeSummaryPanel(user);
            noticesVLayout.addComponent(noticeSummaryPanel);
            noticeSummaryPanel.setNotice(notice);
        }
    }

    public class NoticesRefreshListener implements RefreshListener {

        private static final long serialVersionUID = 1L;

        public void refresh(final Refresher source) {
            load();
        }
    }

    @Override
    public void enter(ViewChangeEvent event) {
        load();
    }
}
