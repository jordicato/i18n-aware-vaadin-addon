package com.vaadin.demo.sampler.features.windows;

import java.util.Date;

import com.opnworks.vaadin.i18n.ui.I18NLabel;
import com.opnworks.vaadin.i18n.ui.I18NVerticalLayout;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Link;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class NativeWindowExample extends I18NVerticalLayout {

    public NativeWindowExample() {
        setSpacing(true);

        // Add a button for opening the window
        Button open = new Button("Open native window",
                new Button.ClickListener() {
                    // inline click-listener
                    public void buttonClick(ClickEvent event) {
                        Window window = new NativeWindow();
                        // Add the window to the application
                        getApplication().addWindow(window);

                        // Get the URL for the window, and open that in a new
                        // browser window, in this case in a small window.
                        getWindow().open(new ExternalResource(window.getURL()), // URL
                                "_blank", // window name
                                500, // width
                                200, // weight
                                Window.BORDER_NONE // decorations
                                );
                    }
                });
        addComponent(open);

        // Add a link for opening sampler in a new window; this will cause
        // Sampler's getWindow() to create a new Window.
        Link openSampler = new Link("Open Sampler in a new window",
                new ExternalResource("#"), // URL
                "_blank", // window name
                700, // width
                500, // height
                Link.TARGET_BORDER_NONE // decorations
        );
        addComponent(openSampler);

    }

    /*
     * We'll be instantiating the same window multiple times, so we'll make an
     * inner class for separation. You could of course just create a new
     * Window() and addCompoent to that instead.
     */
    class NativeWindow extends Window {
        NativeWindow() {
            // Configure the layout
            I18NVerticalLayout layout = (I18NVerticalLayout) getContent();
            layout.setMargin(true);
            layout.setSpacing(true);

            // Add some content; a I18NLabel and a close-button
            I18NLabel message = new I18NLabel("This is a native window, created at "
                    + new Date());
            addComponent(message);

            // It's a good idea to remove the window when it's closed (also
            // when the browser window 'x' is used), unless you explicitly
            // want the window to persist (if it's not removed from the
            // application, it can still be retrieved from it's URL.
            addListener(new CloseListener() {
                public void windowClose(CloseEvent e) {
                    // remove from application
                    getApplication().removeWindow(NativeWindow.this);
                }
            });

        }
    }

}