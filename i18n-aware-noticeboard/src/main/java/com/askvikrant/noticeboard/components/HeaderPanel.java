package com.askvikrant.noticeboard.components;

import java.io.File;
import java.util.ArrayList;

import com.askvikrant.digitalclock.DigitalClock;
import com.askvikrant.noticeboard.model.QuickLink;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class HeaderPanel extends Panel {

	private static final long serialVersionUID = 1L;

	// ui components
	private HorizontalLayout logoHLayout = new HorizontalLayout();
	private HorizontalLayout linksHLayout = new HorizontalLayout();

	// data components

	public HeaderPanel() {
		VerticalLayout vLayout = new VerticalLayout();
		setContent(vLayout);

		logoHLayout.setHeight(60, Unit.PIXELS);
		linksHLayout.setHeight(20, Unit.PIXELS);
		linksHLayout.setSpacing(true);

		Label nbLabel = new Label("<div align=\"center\" style=\"font-size:14pt;\">Notice Board</div>");
		nbLabel.setContentMode(ContentMode.HTML);
		nbLabel.setHeight(25, Unit.PIXELS);

		Label emptyLabel = new Label("");
		// nbLabel.setContentMode(ContentMode.HTML);
		emptyLabel.setHeight(5, Unit.PIXELS);

		DigitalClock clock = new DigitalClock();
		clock.setWidth(100, Unit.PERCENTAGE);

		vLayout.addComponent(logoHLayout);
		vLayout.addComponent(clock);
		vLayout.addComponent(emptyLabel);
		vLayout.addComponent(linksHLayout);
		vLayout.addComponent(nbLabel);

		vLayout.setComponentAlignment(logoHLayout, Alignment.MIDDLE_CENTER);
		vLayout.setComponentAlignment(linksHLayout, Alignment.MIDDLE_CENTER);

		refreshLogo();

	}

	public void refreshLogo() {
		// Find the application directory
		String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
		// Image as a file resource
		FileResource resource = new FileResource(new File(basepath + "/WEB-INF/images/logo.png"));
		// Show the image in the application
		Image image = new Image(null, resource);

		logoHLayout.removeAllComponents();
		logoHLayout.addComponent(image);
	}

	public void setQuickLinks(ArrayList<QuickLink> quickLinks) {
		linksHLayout.removeAllComponents();
		for (int i = 0; i < quickLinks.size(); i++ ) {
			Link link = new Link();
			link.setCaption(quickLinks.get(i).getName());
			link.setResource(new ExternalResource(quickLinks.get(i).getUrl()));
			linksHLayout.addComponent(link);
		}
	}
}