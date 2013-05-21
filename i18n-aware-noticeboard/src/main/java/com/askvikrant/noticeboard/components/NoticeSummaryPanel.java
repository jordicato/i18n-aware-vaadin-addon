package com.askvikrant.noticeboard.components;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.askvikrant.noticeboard.NoticeboardUI;
import com.askvikrant.noticeboard.events.DeleteNotice_Event;
import com.askvikrant.noticeboard.events.EditNotice_Event;
import com.askvikrant.noticeboard.events.ViewNotice_Event;
import com.askvikrant.noticeboard.model.Notice;
import com.askvikrant.noticeboard.model.User;
import com.github.wolfie.blackboard.Blackboard;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.BaseTheme;

public class NoticeSummaryPanel extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private User user = null;
	private HorizontalLayout daysHLayout = new HorizontalLayout();
	private HorizontalLayout titleHLayout = new HorizontalLayout();
	private HorizontalLayout dateHLayout = new HorizontalLayout();
	private HorizontalLayout actionsHLayout = new HorizontalLayout();
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy, hh:mm a");

	public NoticeSummaryPanel(final User u) {
		user = u;
		setSizeFull();

		titleHLayout.setWidth(100, Unit.PERCENTAGE);
		dateHLayout.setWidth(100, Unit.PERCENTAGE);
		// actionsHLayout.setWidth(97, Unit.PERCENTAGE);
		dateHLayout.setSpacing(true);

		addComponent(daysHLayout);
		addComponent(titleHLayout);
		addComponent(dateHLayout);
		addComponent(actionsHLayout);

		actionsHLayout.setSpacing(true);
		setComponentAlignment(actionsHLayout, Alignment.MIDDLE_RIGHT);

		Label lblLine = new Label("<hr>");
		lblLine.setContentMode(ContentMode.HTML);
		addComponent(lblLine);
	}

	public void setNotice(final Notice notice) {
		daysHLayout.removeAllComponents();
		titleHLayout.removeAllComponents();
		dateHLayout.removeAllComponents();
		actionsHLayout.removeAllComponents();

		Label lblTitle = new Label("<div style=\"font-size:12pt;\">" + notice.getTitle() + "</div>");

		String alert = "";
		String textColor = "";
		switch (notice.getDays()) {
			case 0: {
				alert = "Today";
				textColor = "#ff0000";
				break;
			}
			case 1: {
				alert = "Yesterday";
				textColor = "#ff6600";
				break;
			}
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7: {
				alert = notice.getDays() + " days ago";
				textColor = "#0000c0";
				break;
			}
			default: {
				alert = notice.getDays() + " days ago";
				textColor = "#00bb00";
				break;
			}
		}
		Label lblDays = new Label("<div style=\"color:" + textColor + "; font-size:9pt;\">" + alert + "</div>");
		Label lblDate = new Label("<div align=\"center\" style=\"background-color:#fafafa; color:#606060; font-size:9pt;\">"
				+ dateFormat.format(notice.getDate()) + "</div>");
		Label lblDepartment = new Label("<div style=\"color:#606060; font-size:9pt;font-style:italic;\">&nbsp;&nbsp;" + notice.getDepartment()
				+ "</div>");

		lblTitle.setContentMode(ContentMode.HTML);
		lblDate.setContentMode(ContentMode.HTML);
		lblDays.setContentMode(ContentMode.HTML);
		lblDepartment.setContentMode(ContentMode.HTML);

		Button btnView = new Button("View");
		Button btnEdit = new Button("Edit");
		Button btnDelete = new Button("Delete");

		btnView.addStyleName(BaseTheme.BUTTON_LINK);
		btnEdit.addStyleName(BaseTheme.BUTTON_LINK);
		btnDelete.addStyleName(BaseTheme.BUTTON_LINK);

		btnView.addClickListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				Blackboard bb = ((NoticeboardUI) getUI()).getBlackboard();
				bb.fire(new ViewNotice_Event(notice.getId()));
			}
		});

		btnEdit.addClickListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				Blackboard bb = ((NoticeboardUI) getUI()).getBlackboard();
				bb.fire(new EditNotice_Event(notice.getId()));
			}
		});

		btnDelete.addClickListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				Blackboard bb = ((NoticeboardUI) getUI()).getBlackboard();
				bb.fire(new DeleteNotice_Event(notice.getId()));
			}
		});

		titleHLayout.addComponent(lblTitle);

		dateHLayout.setSpacing(true);
		daysHLayout.addComponent(lblDays);
		daysHLayout.addComponent(lblDepartment);

		dateHLayout.addComponent(lblDate);

		daysHLayout.setExpandRatio(lblDays, 1);
		daysHLayout.setExpandRatio(lblDepartment, 1);

		if (user.getRole().equals(User.ROLE_GUEST)) {
			actionsHLayout.addComponent(btnView);
			actionsHLayout.addComponent(new Label(" "));
			// actionsHLayout.setComponentAlignment(btnView, Alignment.TOP_CENTER);

		}
		else if (user.getRole().equals(User.ROLE_EXECUTIVE)) {
			actionsHLayout.addComponent(btnView);
			actionsHLayout.addComponent(btnEdit);
			actionsHLayout.addComponent(new Label(" "));
			// actionsHLayout.setComponentAlignment(btnView, Alignment.TOP_RIGHT);
			// actionsHLayout.setComponentAlignment(btnEdit, Alignment.TOP_RIGHT);
			if (user.getUserId().equals(notice.getUserId())) {
				// if the notice has been uploaded today only, allow the user to edit it
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date uploadDate = notice.getDate();
				Date today = new Date();

				String uploadDateStr = dateFormat.format(uploadDate);
				String todayStr = dateFormat.format(today);

				if (uploadDateStr.equals(todayStr)) {
					btnEdit.setEnabled(true);
				}
				else {
					btnEdit.setEnabled(false);
				}

			}
			else {
				btnEdit.setEnabled(false);
			}
		}
		else if (user.getRole().equals(User.ROLE_ROOT)) {
			actionsHLayout.addComponent(btnView);
			actionsHLayout.addComponent(btnEdit);
			actionsHLayout.addComponent(btnDelete);
			actionsHLayout.addComponent(new Label(" "));
			// actionsHLayout.setComponentAlignment(btnView, Alignment.TOP_RIGHT);
			// actionsHLayout.setComponentAlignment(btnEdit, Alignment.TOP_RIGHT);
			// actionsHLayout.setComponentAlignment(btnDelete, Alignment.TOP_RIGHT);
		}
	}
}