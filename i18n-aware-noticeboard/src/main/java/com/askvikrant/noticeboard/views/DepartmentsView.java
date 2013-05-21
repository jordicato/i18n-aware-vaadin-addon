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
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.BaseTheme;

public class DepartmentsView extends VerticalLayout implements View {

	private static final long serialVersionUID = 1L;
	public static final String NAME = "Departments";
	private Department selectedDepartment = null;

	private final Table table = new Table();
	private final BeanItemContainer<Department> container = new BeanItemContainer<Department>(Department.class);

	private static final Object[] NATURAL_COL_ORDER = new Object[] { "name" };
	private static final String[] COL_HEADERS_ENGLISH = new String[] { "Department" };

	private final Button btnNew = new Button("Add New Department");

	private VerticalLayout vLayout = new VerticalLayout();
	private final Button btnSave = new Button("Save");
	private final Button btnUpdate = new Button("Update");
	private final Button btnCancel = new Button("Cancel");

	private final TextField tfName = new TextField("Department Name");

	public DepartmentsView(final User u) {

		setSpacing(true);

		table.setSizeFull();
		table.setSelectable(true);
		table.setColumnCollapsingAllowed(true);
		// table.setRowHeaderMode(Table.ROW_HEADER_MODE_INDEX);
		table.setPageLength(5);
		table.setContainerDataSource(container);
		table.setVisibleColumns(NATURAL_COL_ORDER);
		table.setColumnHeaders(COL_HEADERS_ENGLISH);
		table.addGeneratedColumn("Edit", new EditColumnGenerator());
		table.addGeneratedColumn("Delete", new DeleteColumnGenerator());

		table.setColumnWidth("name", 200);
		table.setColumnWidth("Edit", 50);
		table.setColumnWidth("Delete", 50);

		HorizontalLayout hLayout = new HorizontalLayout();
		hLayout.setSpacing(true);
		hLayout.addComponent(btnSave);
		hLayout.addComponent(btnUpdate);
		hLayout.addComponent(btnCancel);

		tfName.setWidth(100, Unit.PERCENTAGE);
		vLayout.setSpacing(true);
		vLayout.addComponent(tfName);
		vLayout.addComponent(hLayout);

		btnNew.addClickListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				vLayout.setVisible(true);
				setModeNew(true);
			}
		});

		btnCancel.addClickListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				tfName.setValue("");
				vLayout.setVisible(false);
			}
		});

		btnSave.addClickListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				if (tfName.getValue().length() != 0) {
					BusinessLogic bl = ((NoticeboardUI) getUI()).getBusinessLogic();
					boolean success = bl.saveNewDepartment(tfName.getValue());
					if (success) {
						Notification.show("Success", "Department saved", Type.TRAY_NOTIFICATION);
						tfName.setValue("");
						vLayout.setVisible(false);
						load();
					}
					else {
						Notification.show("Error", "Department could not be saved", Type.ERROR_MESSAGE);
					}
				}
				else {
					Notification.show("Error", "Please provide a name", Type.ERROR_MESSAGE);
				}

			}
		});

		btnUpdate.addClickListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				if (tfName.getValue().length() != 0) {
					BusinessLogic bl = ((NoticeboardUI) getUI()).getBusinessLogic();
					boolean success = bl.updateDepartment(selectedDepartment.getName(), tfName.getValue());
					String appMessage = bl.getAppMessage();
					if (success) {
						Notification.show("Success", "Department updated", Type.TRAY_NOTIFICATION);
						tfName.setValue("");
						vLayout.setVisible(false);
						load();
					}
					else {
						Notification.show("Error", "Department could not be updated. " + appMessage, Type.ERROR_MESSAGE);
					}
				}
				else {
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
		}
		else {
			btnSave.setVisible(false);
			btnUpdate.setVisible(true);
		}
	}

	private void load() {
		// invoke business logic method to fetch notices
		BusinessLogic bl = ((NoticeboardUI) getUI()).getBusinessLogic();
		ArrayList<Department> departments = bl.getDepartments();
		container.removeAllItems();
		container.addAll(departments);
	}

	class EditColumnGenerator implements Table.ColumnGenerator {

		private static final long serialVersionUID = 1L;

		public Component generateCell(Table source, Object itemId, Object columnId) {
			// Get the object stored in the cell as a property
			final Department department = (Department) itemId;
			Button button = new Button("Edit");
			button.setStyleName(BaseTheme.BUTTON_LINK);
			button.addClickListener(new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					// TODO Auto-generated method stub
					// show window
					selectedDepartment = department;
					vLayout.setVisible(true);
					tfName.setValue(department.getName());
					setModeNew(false);
				}
			});
			return button;
		}
	}

	class DeleteColumnGenerator implements Table.ColumnGenerator {

		private static final long serialVersionUID = 1L;

		public Component generateCell(Table source, Object itemId, Object columnId) {
			// Get the object stored in the cell as a property
			final Department department = (Department) itemId;
			Button button = new Button("Delete");
			button.setStyleName(BaseTheme.BUTTON_LINK);
			button.addClickListener(new ClickListener() {

				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					// TODO Auto-generated method stub
					BusinessLogic bl = ((NoticeboardUI) getUI()).getBusinessLogic();
					boolean success = bl.deleteDepartment(department.getName());
					if (success) {
						Notification.show("Success", "Department deleted.", Type.TRAY_NOTIFICATION);
						load();
					}
					else {
						String appMessage = bl.getAppMessage();
						Notification.show("Error", "Department could not be deleted. " + appMessage, Type.ERROR_MESSAGE);
					}
				}
			});
			return button;
		}
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		load();
	}
}