package com.askvikrant.noticeboard;

import com.askvikrant.noticeboard.business.BusinessLogic;
import com.askvikrant.noticeboard.components.FooterPanel;
import com.askvikrant.noticeboard.components.HeaderPanel;
import com.askvikrant.noticeboard.components.NoticePanel;
import com.askvikrant.noticeboard.components.UserPanel;
import com.askvikrant.noticeboard.events.DeleteNotice_Event;
import com.askvikrant.noticeboard.events.DeleteNotice_Listener;
import com.askvikrant.noticeboard.events.EditNotice_Event;
import com.askvikrant.noticeboard.events.EditNotice_Listener;
import com.askvikrant.noticeboard.events.LoginSuccess_Event;
import com.askvikrant.noticeboard.events.LoginSuccess_Listener;
import com.askvikrant.noticeboard.events.Logout_Event;
import com.askvikrant.noticeboard.events.Logout_Listener;
import com.askvikrant.noticeboard.events.NewNotice_Event;
import com.askvikrant.noticeboard.events.NewNotice_Listener;
import com.askvikrant.noticeboard.events.RefreshQuickLinks_Event;
import com.askvikrant.noticeboard.events.RefreshQuickLinks_Listener;
import com.askvikrant.noticeboard.events.SwitchView_Event;
import com.askvikrant.noticeboard.events.SwitchView_Listener;
import com.askvikrant.noticeboard.events.ViewNotice_Event;
import com.askvikrant.noticeboard.events.ViewNotice_Listener;
import com.askvikrant.noticeboard.model.Notice;
import com.askvikrant.noticeboard.model.User;
import com.askvikrant.noticeboard.views.DepartmentsView;
import com.askvikrant.noticeboard.views.NoticeEditorView;
import com.askvikrant.noticeboard.views.NoticesView;
import com.askvikrant.noticeboard.views.PasswordEditorView;
import com.askvikrant.noticeboard.views.QuickLinksView;
import com.askvikrant.noticeboard.views.UsersView;
import com.github.wolfie.blackboard.Blackboard;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

@PreserveOnRefresh
public class NoticeboardUI extends UI implements SwitchView_Listener, RefreshQuickLinks_Listener, NewNotice_Listener, ViewNotice_Listener,
		EditNotice_Listener, DeleteNotice_Listener, LoginSuccess_Listener, Logout_Listener {

	private static final long serialVersionUID = 1L;

	private transient Blackboard blackboard = null;

	private transient BusinessLogic businessLogic = new BusinessLogic();
	private User user = null;
	private Navigator navigator;
	private final GridLayout appGridLayout = new GridLayout(1, 4);

	private final HeaderPanel headerPanel = new HeaderPanel();
	private final UserPanel userPanel = new UserPanel();
	private final Panel contentPanel = new Panel();
	private final FooterPanel footerPanel = new FooterPanel();

	// various views
	private NoticesView noticesView = null;
	private NoticeEditorView noticeEditorView = null;
	private DepartmentsView departmentsView = null;
	private QuickLinksView quickLinksView = null;
	private UsersView usersView = null;
	private PasswordEditorView passwordEditorView = null;

	// varios windows and associated panels
	private final Window noticeWindow = new Window();
	private NoticePanel noticePanel = new NoticePanel();

	@Override
	protected void init(VaadinRequest request) {
		// TODO Auto-generated method stub

		GridLayout rootGridLayout = new GridLayout(1, 1);
		rootGridLayout.setWidth(100, Unit.PERCENTAGE);
		rootGridLayout.setHeight(98, Unit.PERCENTAGE);
		setContent(rootGridLayout);

		appGridLayout.setWidth(250, Unit.PIXELS);
		appGridLayout.setHeight(100, Unit.PERCENTAGE);

		rootGridLayout.addComponent(appGridLayout, 0, 0);
		rootGridLayout.setComponentAlignment(appGridLayout, Alignment.TOP_CENTER);

		headerPanel.setHeight("170px");
		userPanel.setHeight("50px");
		contentPanel.setHeight("100%");
		footerPanel.setHeight("22px");

		appGridLayout.addComponent(headerPanel, 0, 0);
		appGridLayout.setComponentAlignment(headerPanel, Alignment.TOP_CENTER);

		appGridLayout.addComponent(contentPanel, 0, 1);
		appGridLayout.setComponentAlignment(contentPanel, Alignment.TOP_CENTER);

		appGridLayout.addComponent(userPanel, 0, 2);
		appGridLayout.setComponentAlignment(userPanel, Alignment.TOP_CENTER);

		appGridLayout.addComponent(footerPanel, 0, 3);
		appGridLayout.setComponentAlignment(footerPanel, Alignment.TOP_CENTER);

		appGridLayout.setRowExpandRatio(1, 1);

		// set page title
		getPage().setTitle("NoticeBoard by askVikrant.com");

		noticeWindow.setModal(true);
		noticeWindow.setWidth("240px");
		noticeWindow.setHeight("440px");
		noticeWindow.setCaption("Notice");
		noticeWindow.setClosable(true);
		noticeWindow.setCloseShortcut(KeyCode.ESCAPE, null);

		blackboard = getBlackboard();
		navigator = new Navigator(this, contentPanel);
		initGuestUser();
	}

	private void initGuestUser() {
		businessLogic = getBusinessLogic();
		user = businessLogic.getGuestUser();
		headerPanel.setQuickLinks(businessLogic.getQuickLinks());
		setupUserViews(user);
	}

	private void registerEvents() {
		// login
		blackboard.register(ViewNotice_Listener.class, ViewNotice_Event.class);
		blackboard.register(RefreshQuickLinks_Listener.class, RefreshQuickLinks_Event.class);
		blackboard.register(EditNotice_Listener.class, EditNotice_Event.class);
		blackboard.register(DeleteNotice_Listener.class, DeleteNotice_Event.class);
		blackboard.register(NewNotice_Listener.class, NewNotice_Event.class);
		blackboard.register(SwitchView_Listener.class, SwitchView_Event.class);
		blackboard.register(LoginSuccess_Listener.class, LoginSuccess_Event.class);
		blackboard.register(Logout_Listener.class, Logout_Event.class);
	}

	private void addListeners() {
		blackboard.addListener(this);
	}

	public Blackboard getBlackboard() {
		if (blackboard == null) {
			blackboard = new Blackboard();
			registerEvents();
			addListeners();
		}
		return blackboard;
	}

	public BusinessLogic getBusinessLogic() {
		if (businessLogic == null) {
			businessLogic = new BusinessLogic();
		}
		return businessLogic;
	}

	@Override
	public void onViewNotice(ViewNotice_Event event) {
		// TODO Auto-generated method stub
		// show the NoticePanel window
		businessLogic = getBusinessLogic();
		noticePanel.setNotice(businessLogic.getNotice(event.getNoticeId()), businessLogic.getAttachments(event.getNoticeId()));
		noticeWindow.setContent(noticePanel);
		if (noticeWindow.getParent() != null) {
			// window is already showing
			Notification.show("Window already open");
		}
		else {
			getUI().addWindow(noticeWindow);
			noticeWindow.focus();
		}
	}

	@Override
	public void onLogout(Logout_Event event) {
		// TODO Auto-generated method stub
		getSession().close();
		initGuestUser();
		getUI().getPage().setLocation("");
	}

	@Override
	public void onLoginSuccess(LoginSuccess_Event event) {
		// TODO Auto-generated method stub
		user = event.getUser();
		setupUserViews(user);
	}

	private void setupUserViews(User user) {
		userPanel.setUser(user);

		if (user.getRole().equals(User.ROLE_GUEST)) {
			String defaultDays = businessLogic.getDefaultDays();
			noticesView = new NoticesView(user, defaultDays);
			getBusinessLogic();

			navigator.setErrorView(noticesView);
			navigator.addView("", noticesView);
			navigator.navigateTo("");
		}
		else if (user.getRole().equals(User.ROLE_EXECUTIVE)) {
			String defaultDays = businessLogic.getDefaultDays();
			noticesView = new NoticesView(user, defaultDays);
			noticeEditorView = new NoticeEditorView(user);
			passwordEditorView = new PasswordEditorView(user);

			navigator.setErrorView(noticesView);
			navigator.addView("", noticesView);
			navigator.addView(NoticeEditorView.NAME, noticeEditorView);
			navigator.addView(PasswordEditorView.NAME, passwordEditorView);
			navigator.navigateTo("");
		}
		else if (user.getRole().equals(User.ROLE_ROOT)) {
			String defaultDays = businessLogic.getDefaultDays();
			noticesView = new NoticesView(user, defaultDays);
			noticeEditorView = new NoticeEditorView(user);
			departmentsView = new DepartmentsView(user);
			quickLinksView = new QuickLinksView(user);
			usersView = new UsersView(user);
			passwordEditorView = new PasswordEditorView(user);

			navigator.setErrorView(noticesView);
			navigator.addView("", noticesView);
			navigator.addView(NoticeEditorView.NAME, noticeEditorView);
			navigator.addView(DepartmentsView.NAME, departmentsView);
			navigator.addView(QuickLinksView.NAME, quickLinksView);
			navigator.addView(UsersView.NAME, usersView);
			navigator.addView(PasswordEditorView.NAME, passwordEditorView);
			navigator.navigateTo("");
		}
	}

	@Override
	public void onSwitchView(SwitchView_Event event) {
		// TODO Auto-generated method stub
		String viewName = event.getViewName();
		if (viewName.equals(NoticesView.NAME)) {
			navigator.navigateTo("");
		}
		else if (viewName.equals(DepartmentsView.NAME)) {
			navigator.navigateTo(DepartmentsView.NAME);
		}
		else if (viewName.equals(UsersView.NAME)) {
			navigator.navigateTo(UsersView.NAME);
		}
		else if (viewName.equals(PasswordEditorView.NAME)) {
			navigator.navigateTo(PasswordEditorView.NAME);
		}
		else if (viewName.equals(QuickLinksView.NAME)) {
			navigator.navigateTo(QuickLinksView.NAME);
		}
	}

	@Override
	public void onEditNotice(EditNotice_Event event) {
		// TODO Auto-generated method stub
		navigator.navigateTo(NoticeEditorView.NAME);
		businessLogic = getBusinessLogic();
		Notice notice = businessLogic.getNotice(event.getNoticeId());
		noticeEditorView.setNoticeToEdit(notice);
	}

	@Override
	public void onNewNotice(NewNotice_Event event) {
		// TODO Auto-generated method stub
		navigator.navigateTo(NoticeEditorView.NAME);
		noticeEditorView.setNewNotice();
	}

	@Override
	public void onDeleteNotice(DeleteNotice_Event event) {
		// TODO Auto-generated method stub
		businessLogic = getBusinessLogic();
		boolean success = businessLogic.deleteNotice(event.getNoticeId());
		if (success) {
			Notification.show("Success", "Notice deleted", Type.TRAY_NOTIFICATION);
			navigator.navigateTo(NoticesView.NAME);
		}
		else {
			Notification.show("Error", "Notice could not be deleted", Type.ERROR_MESSAGE);
		}
	}

	@Override
	public void onRefreshQuickLinks(RefreshQuickLinks_Event event) {
		// TODO Auto-generated method stub
		businessLogic = getBusinessLogic();
		headerPanel.setQuickLinks(businessLogic.getQuickLinks());
	}
}