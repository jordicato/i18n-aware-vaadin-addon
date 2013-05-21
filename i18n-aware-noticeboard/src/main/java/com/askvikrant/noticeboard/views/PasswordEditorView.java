package com.askvikrant.noticeboard.views;

import com.askvikrant.noticeboard.NoticeboardUI;
import com.askvikrant.noticeboard.business.BusinessLogic;
import com.askvikrant.noticeboard.events.SwitchView_Event;
import com.askvikrant.noticeboard.model.User;
import com.github.wolfie.blackboard.Blackboard;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.VerticalLayout;

public class PasswordEditorView extends VerticalLayout implements View {

	private static final long serialVersionUID = 1L;
	public static final String NAME = "Password_Editor";

	private final PasswordField tfOldPassword = new PasswordField("Old Password");
	private final PasswordField tfNewPassword1 = new PasswordField("New Password");
	private final PasswordField tfNewPassword2 = new PasswordField("Repeat New Password");

	private final Button btnUpdate = new Button("Update");
	private final Button btnCancel = new Button("Cancel");

	private final HorizontalLayout buttonsHLayout = new HorizontalLayout();

	private User user = null;

	public PasswordEditorView(final User u) {
		user = u;

		setSpacing(true);

		buttonsHLayout.setSpacing(true);
		buttonsHLayout.addComponent(btnUpdate);
		buttonsHLayout.addComponent(btnCancel);

		addComponent(tfOldPassword);
		addComponent(tfNewPassword1);
		addComponent(tfNewPassword2);
		addComponent(buttonsHLayout);

		btnUpdate.setClickShortcut(KeyCode.ENTER);
		btnUpdate.addClickListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				if (tfOldPassword.getValue().length() != 0 && tfNewPassword1.getValue().length() != 0 && tfNewPassword2.getValue().length() != 0) {
					BusinessLogic bl = ((NoticeboardUI) getUI()).getBusinessLogic();
					boolean success = bl.updateUserPassword(user.getUserId(), tfOldPassword.getValue(), tfNewPassword1.getValue(),
							tfNewPassword2.getValue());
					String appMessage = bl.getAppMessage();
					if (success) {
						Notification.show("Success", "Password updated", Type.TRAY_NOTIFICATION);
						tfOldPassword.setValue("");
						tfNewPassword1.setValue("");
						tfNewPassword2.setValue("");
						Blackboard bb = ((NoticeboardUI) getUI()).getBlackboard();
						bb.fire(new SwitchView_Event(NoticesView.NAME));
					}
					else {
						Notification.show("Error", "Password could not be updated. " + appMessage, Type.ERROR_MESSAGE);
					}
				}
				else {
					Notification.show("Error", "Please provide all values", Type.ERROR_MESSAGE);
				}

			}
		});

		btnCancel.addClickListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				Blackboard bb = ((NoticeboardUI) getUI()).getBlackboard();
				bb.fire(new SwitchView_Event(NoticesView.NAME));
			}
		});

	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
	}
}