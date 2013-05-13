package com.vaadin.demo.dashboard;

import java.text.SimpleDateFormat;

import com.opnworks.vaadin.i18n.support.I18NExpression;
import com.opnworks.vaadin.i18n.ui.I18NLabel;
import com.vaadin.demo.dashboard.ScheduleView.MovieEvent;
import com.vaadin.demo.dashboard.data.DataProvider.Movie;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptAll;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.DragAndDropWrapper;
import com.vaadin.ui.DragAndDropWrapper.DragStartMode;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class MovieDetailsWindow extends Window {

	I18NLabel synopsis = new I18NLabel();

    public MovieDetailsWindow(Movie movie, MovieEvent event) {
        VerticalLayout l = new VerticalLayout();
        l.setSpacing(true);
        setCaption(movie.title);
        setContent(l);
        center();
        setCloseShortcut(KeyCode.ESCAPE, null);
        setResizable(false);
        setClosable(false);
        addStyleName("no-vertical-drag-hints");
        addStyleName("no-horizontal-drag-hints");
        HorizontalLayout details = new HorizontalLayout();
        details.setSpacing(true);
        details.setMargin(true);
        l.addComponent(details);
        final Image coverImage = new Image("", new ExternalResource(movie.posterUrl));
        final Button more = new Button("com.vaadin.demo.dashboard.MovieDetailsWindow.More");
        DragAndDropWrapper cover = new DragAndDropWrapper(coverImage);
        cover.setDragStartMode(DragStartMode.NONE);
        cover.setWidth("200px");
        cover.setHeight("270px");
        cover.addStyleName("cover");
        cover.setDropHandler(new DropHandler() {

            @Override
            public void drop(DragAndDropEvent event) {
                DragAndDropWrapper d = (DragAndDropWrapper) event.getTransferable().getSourceComponent();
                if (d == event.getTargetDetails().getTarget()) return;
                Movie m = (Movie) d.getData();
                coverImage.setSource(new ExternalResource(m.posterUrl));
                coverImage.setAlternateText(m.title);
                setCaption(m.title);
                updateSynopsis(m, false);
                more.setVisible(true);
            }

            @Override
            public AcceptCriterion getAcceptCriterion() {
                return AcceptAll.get();
            }
        });
        details.addComponent(cover);
        FormLayout fields = new FormLayout();
        fields.setWidth("35em");
        fields.setSpacing(true);
        fields.setMargin(true);
        details.addComponent(fields);
        I18NLabel label;
        if (event != null) {
            SimpleDateFormat df = new SimpleDateFormat();
            df.applyPattern("dd-mm-yyyy");
            label = new I18NLabel(new I18NExpression("com.vaadin.demo.dashboard.MovieDetailsWindow.Date", ": ", df.format(event.start)));
            label.setSizeUndefined();
            fields.addComponent(label);
            df.applyPattern("hh:mm a");
            label = new I18NLabel(new I18NExpression("com.vaadin.demo.dashboard.MovieDetailsWindow.Starts", ": ", df.format(event.start)));
            label.setSizeUndefined();
            fields.addComponent(label);
            label = new I18NLabel(new I18NExpression("com.vaadin.demo.dashboard.MovieDetailsWindow.Ends", ": ", df.format(event.end)));
            label.setSizeUndefined();
            fields.addComponent(label);
        }
        label = new I18NLabel(new I18NExpression("com.vaadin.demo.dashboard.MovieDetailsWindow.Duration", ": ", movie.duration, "com.vaadin.demo.dashboard.MovieDetailsWindow.minutes"));
        label.setSizeUndefined();
        fields.addComponent(label);
        synopsis.setData(movie.synopsis);
        synopsis.setCaption("com.vaadin.demo.dashboard.MovieDetailsWindow.Synopsis");
        updateSynopsis(movie, false);
        fields.addComponent(synopsis);
        more.addStyleName("link");
        fields.addComponent(more);
        more.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                updateSynopsis(null, true);
                event.getButton().setVisible(false);
            }
        });
        HorizontalLayout footer = new HorizontalLayout();
        footer.addStyleName("footer");
        footer.setWidth("100%");
        footer.setMargin(true);
        Button ok = new Button("com.vaadin.demo.dashboard.MovieDetailsWindow.Close");
        ok.addStyleName("wide");
        ok.addStyleName("default");
        ok.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                close();
            }
        });
        footer.addComponent(ok);
        footer.setComponentAlignment(ok, Alignment.TOP_RIGHT);
        l.addComponent(footer);
    }

    public void updateSynopsis(Movie m, boolean expand) {
        String synopsisText = synopsis.getData().toString();
        if (m != null) {
            synopsisText = m.synopsis;
            synopsis.setData(m.synopsis);
        }
        if (!expand) {
            synopsisText = synopsisText.length() > 300 ? synopsisText.substring(0, 300) + " " : synopsisText;
        }
        synopsis.setValue(synopsisText);
    }
}
