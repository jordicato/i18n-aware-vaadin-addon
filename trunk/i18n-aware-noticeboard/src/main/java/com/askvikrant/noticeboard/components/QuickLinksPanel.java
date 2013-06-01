package com.askvikrant.noticeboard.components;

import java.util.ArrayList;

import com.askvikrant.noticeboard.model.QuickLink;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class QuickLinksPanel extends Panel {

	private static final long serialVersionUID = 1L;

	private HorizontalLayout linksHLayout = new HorizontalLayout();

	//data components

	public QuickLinksPanel(ArrayList<QuickLink> quickLinks){
		VerticalLayout vLayout = new VerticalLayout();
		setContent(vLayout);

//		linksHLayout.setHeight(20, Unit.PIXELS);
		linksHLayout.setSpacing(true);
		
		vLayout.addComponent(linksHLayout);
		vLayout.setComponentAlignment(linksHLayout, Alignment.MIDDLE_CENTER);

		for(int i=0;i<quickLinks.size();i++){
			Link link = new Link();
			link.setTargetName("_blank");
			link.setCaption(quickLinks.get(i).getName());
			link.setResource(new ExternalResource(quickLinks.get(i).getUrl()));
			linksHLayout.addComponent(link);			
		}
	}

}