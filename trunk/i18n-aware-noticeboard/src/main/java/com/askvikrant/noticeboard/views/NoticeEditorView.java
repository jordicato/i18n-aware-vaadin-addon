package com.askvikrant.noticeboard.views;

import com.askvikrant.noticeboard.NoticeboardUI;
import com.askvikrant.noticeboard.business.BusinessLogic;
import com.askvikrant.noticeboard.components.FilesPanel;
import com.askvikrant.noticeboard.events.SwitchView_Event;
import com.askvikrant.noticeboard.model.Notice;
import com.askvikrant.noticeboard.model.User;
import com.github.wolfie.blackboard.Blackboard;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class NoticeEditorView extends VerticalLayout implements View {

    private static final long serialVersionUID = 1L;

    public static final String NAME = "Notice_Editor";

    private Notice noticeToEdit = null;

    private final TextField tfTitle = new TextField();

    private final TextArea taBody = new TextArea();

    private final FilesPanel filesPanel = new FilesPanel(false);

    private final Button btnPost = new Button("com.askvikrant.noticeboard.views.NoticeEditorView.Post");

    private final Button btnUpdate = new Button("com.askvikrant.noticeboard.views.NoticeEditorView.Update");

    private final Button btnCancel = new Button("com.askvikrant.noticeboard.views.NoticeEditorView.Cancel");

    private final HorizontalLayout buttonsHLayout = new HorizontalLayout();

    private User user = null;

    public NoticeEditorView(final User u) {
        user = u;
        setSpacing(true);
        tfTitle.setWidth(100, Unit.PERCENTAGE);
        tfTitle.setInputPrompt("Title");
        taBody.setWidth(100, Unit.PERCENTAGE);
        taBody.setHeight(120, Unit.PIXELS);
        taBody.setInputPrompt("Message body");
        buttonsHLayout.setSpacing(true);
        buttonsHLayout.addComponent(btnPost);
        buttonsHLayout.addComponent(btnUpdate);
        buttonsHLayout.addComponent(btnCancel);
        setNewMode(true);
        addComponent(tfTitle);
        addComponent(taBody);
        addComponent(filesPanel);
        addComponent(buttonsHLayout);
        btnPost.addClickListener(new ClickListener() {

            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event) {
                if (tfTitle.getValue().length() != 0 && taBody.getValue().length() != 0) {
                    BusinessLogic bl = ((NoticeboardUI) getUI()).getBusinessLogic();
                    boolean success = bl.saveNewNotice(tfTitle.getValue(), taBody.getValue(), filesPanel.getAttachments(), user.getUserId(), user.getDepartment());
                    String appMessage = bl.getAppMessage();
                    if (success) {
                        Notification.show("Notice posted");
                        tfTitle.setValue("");
                        taBody.setValue("");
                        filesPanel.removeAllFiles();
                        Blackboard bb = ((NoticeboardUI) getUI()).getBlackboard();
                        bb.fire(new SwitchView_Event(NoticesView.NAME));
                    } else {
                        Notification.show("Error", "Notice could not be posted. " + appMessage, Type.ERROR_MESSAGE);
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
                if (tfTitle.getValue().length() != 0 && taBody.getValue().length() != 0) {
                    BusinessLogic bl = ((NoticeboardUI) getUI()).getBusinessLogic();
                    boolean success = bl.updateNotice(noticeToEdit.getId(), tfTitle.getValue(), taBody.getValue(), filesPanel.getAttachments());
                    String appMessage = bl.getAppMessage();
                    if (success) {
                        Notification.show("Notice updated");
                        tfTitle.setValue("");
                        taBody.setValue("");
                        filesPanel.removeAllFiles();
                        Blackboard bb = ((NoticeboardUI) getUI()).getBlackboard();
                        bb.fire(new SwitchView_Event(NoticesView.NAME));
                    } else {
                        Notification.show("Error", "Notice could not be updated. " + appMessage, Type.ERROR_MESSAGE);
                    }
                } else {
                    Notification.show("Error", "Please provide all values", Type.ERROR_MESSAGE);
                }
            }
        });
        btnCancel.addClickListener(new ClickListener() {

            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event) {
                Blackboard bb = ((NoticeboardUI) getUI()).getBlackboard();
                bb.fire(new SwitchView_Event(NoticesView.NAME));
            }
        });
    }

    public void setNoticeToEdit(final Notice notice) {
        noticeToEdit = notice;
        tfTitle.setValue(notice.getTitle());
        taBody.setValue(notice.getBody());
        BusinessLogic bl = ((NoticeboardUI) getUI()).getBusinessLogic();
        filesPanel.setAttachments(bl.getAttachments(noticeToEdit.getId()));
        setNewMode(false);
    }

    public void setNewNotice() {
        tfTitle.setValue("");
        taBody.setValue("");
        filesPanel.removeAllFiles();
        setNewMode(true);
    }

    private void setNewMode(boolean newMode) {
        if (newMode) {
            btnPost.setVisible(true);
            btnUpdate.setVisible(false);
        } else {
            btnPost.setVisible(false);
            btnUpdate.setVisible(true);
        }
    }

    @Override
    public void enter(ViewChangeEvent event) {
    }
}
