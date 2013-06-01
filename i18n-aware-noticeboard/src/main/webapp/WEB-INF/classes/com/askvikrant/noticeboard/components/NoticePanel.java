package com.askvikrant.noticeboard.components;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.askvikrant.noticeboard.model.Attachment;
import com.askvikrant.noticeboard.model.Notice;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;

public class NoticePanel extends VerticalLayout {

	private static final long serialVersionUID = 1L;
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy, hh:mm a");

	private final VerticalLayout detailsVLayout = new VerticalLayout();
	private final FilesPanel filesPanel = new FilesPanel(true);

	public NoticePanel(){
		setMargin(true);
		detailsVLayout.setWidth(100, Unit.PERCENTAGE);
		addComponent(detailsVLayout);
		addComponent(filesPanel);
	}

	public void setNotice(final Notice notice, final ArrayList<Attachment> attachments){
		detailsVLayout.removeAllComponents();
		filesPanel.removeAllFiles();
		filesPanel.setAttachments(attachments);
		//details
		Label lblTitle = new Label("<div style=\"font-size:12pt;\">"+notice.getTitle()+"</div>");

		String alert = "";
		String textColor = "";
		switch( notice.getDays()){
		case 0:{
			alert = "Today";
			textColor = "#ff0000";
			break;
		}
		case 1:{
			alert = "Yesterday";
			textColor = "#ff6600";
			break;
		}
		case 2: case 3: case 4: case 5: case 6: case 7: {
			alert = notice.getDays()+" days ago";
			textColor = "#0000c0";
			break;
		}
		default :{
			alert = notice.getDays()+" days ago";
			textColor = "#00bb00";
			break;
		}
		}
		Label lblDate = new Label("<div><span align=\"center\" style=\"background-color:#fafafa; color:#606060; font-size:9pt;\">"+dateFormat.format(notice.getDate())+"</span>"+"&nbsp;&nbsp;<span style=\"color:"+textColor+"; font-size:9pt;\">"+alert+"</span></div>");
		Label lblDepartment = new Label("<div style=\"color:#606060; font-size:9pt;font-style:italic;\">"+notice.getDepartment()+"</div>");
		Label lblUser = new Label("<div><span align=\"center\" style=\"background-color:#fafafa; color:#606060; font-size:9pt;\">Posted by: </span>"+"&nbsp;&nbsp;<span style=\"font-size:9pt;\">"+notice.getUserName()+"</span></div>");
		Label lblLine1 = new Label("<hr>");
		Label lblLine2 = new Label("<hr>");
		
		TextArea taBody = new TextArea();
		taBody.setHeight(100, Unit.PIXELS);
		taBody.setWidth(100, Unit.PERCENTAGE);
		taBody.setValue(notice.getBody());
		taBody.setReadOnly(true);
		
		lblLine1.setContentMode(ContentMode.HTML);
		lblLine2.setContentMode(ContentMode.HTML);
		lblTitle.setContentMode(ContentMode.HTML);
		lblUser.setContentMode(ContentMode.HTML);
		lblDate.setContentMode(ContentMode.HTML);
		lblDepartment.setContentMode(ContentMode.HTML);

		detailsVLayout.addComponent(lblTitle);
		detailsVLayout.addComponent(lblDepartment);
		detailsVLayout.addComponent(lblDate);
		detailsVLayout.addComponent(lblUser);
		detailsVLayout.addComponent(lblLine1);
		detailsVLayout.addComponent(taBody);
		detailsVLayout.addComponent(lblLine2);
		
	}
}