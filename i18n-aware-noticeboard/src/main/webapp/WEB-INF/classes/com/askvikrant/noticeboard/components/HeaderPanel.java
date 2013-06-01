package com.askvikrant.noticeboard.components;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import com.askvikrant.digitalclock.DigitalClock;
import com.askvikrant.noticeboard.model.QuickLink;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class HeaderPanel extends Panel {

	private static final long serialVersionUID = 1L;

	//ui components
	private HorizontalLayout logoHLayout = new HorizontalLayout();
	private HorizontalLayout linksHLayout = new HorizontalLayout();
	private final Window window = new Window();
	private final Button btnCalendar = new Button("Calendar");
	private final InlineDateField dateField = new InlineDateField();
	//data components

	public HeaderPanel(){
		VerticalLayout vLayout = new VerticalLayout();
		setContent(vLayout);

		logoHLayout.setHeight(60, Unit.PIXELS);
		linksHLayout.setHeight(20, Unit.PIXELS);
		linksHLayout.setSpacing(true);
		
		Label nbLabel = new Label("<div align=\"center\" style=\"font-size:14pt;\">Notice Board</div>");
		nbLabel.setContentMode(ContentMode.HTML);
		nbLabel.setHeight(25, Unit.PIXELS);

		Label emptyLabel = new Label("");
//		nbLabel.setContentMode(ContentMode.HTML);
		emptyLabel.setHeight(5, Unit.PIXELS);

		DigitalClock clock = new DigitalClock();
		clock.setWidth(100, Unit.PERCENTAGE);

		window.setModal(true);
		window.setCaption("Calendar");
		window.setWidth("270px");
		window.setHeight("230px");
		window.setClosable(true);
		window.setCloseShortcut(KeyCode.ESCAPE, null);
		VerticalLayout dateVLayout = new VerticalLayout();
		
		dateVLayout.setSpacing(true);
		dateVLayout.addComponent(new Label(" "));
		dateVLayout.addComponent(dateField);
		dateVLayout.setComponentAlignment(dateField, Alignment.MIDDLE_CENTER);
		
		
		window.setContent(dateVLayout);
		
		btnCalendar.addStyleName("small");
		btnCalendar.addClickListener(new ClickListener(){

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				if(window.getParent()==null){
					dateField.setValue(new Date());
					getUI().addWindow(window);
					window.focus();
				}
			}});
		vLayout.addComponent(logoHLayout);
		vLayout.addComponent(clock);
		vLayout.addComponent(emptyLabel);
		vLayout.addComponent(btnCalendar);
		vLayout.addComponent(linksHLayout);
		vLayout.addComponent(nbLabel);
		
		vLayout.setComponentAlignment(logoHLayout, Alignment.MIDDLE_CENTER);
		vLayout.setComponentAlignment(linksHLayout, Alignment.MIDDLE_CENTER);
		vLayout.setComponentAlignment(btnCalendar, Alignment.MIDDLE_CENTER);
		
		refreshLogo();

	}

	public void refreshLogo(){
		// Find the application directory
		String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
		// Image as a file resource
		FileResource resource = new FileResource(new File(basepath + "/WEB-INF/images/logo.png"));
		// Show the image in the application
		Image image = new Image(null, resource);

		logoHLayout.removeAllComponents();
		logoHLayout.addComponent(image);
	}

	public void setQuickLinks(ArrayList<QuickLink> quickLinks){
		linksHLayout.removeAllComponents();
		for(int i=0;i<quickLinks.size();i++){
			Link link = new Link();
			link.setCaption(quickLinks.get(i).getName());
			link.setResource(new ExternalResource(quickLinks.get(i).getUrl()));
			linksHLayout.addComponent(link);			
		}
	}
}