package com.vaadin.demo.sampler.features.commons;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.opnworks.vaadin.i18n.ui.I18NLabel;
import com.opnworks.vaadin.i18n.ui.I18NVerticalLayout;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextArea;

@SuppressWarnings("serial")
public class JSApiExample extends I18NVerticalLayout {

    private I18NLabel toBeUpdatedFromThread;
    private Button startThread;
    private I18NLabel running = new I18NLabel("");

    public JSApiExample() {
        setSpacing(true);

        I18NLabel javascript = new I18NLabel("<h3>Run Native JavaScript</h3>",
                Label.CONTENT_XHTML);
        addComponent(javascript);

        final TextArea script = new TextArea();
        script.setWidth("100%");
        script.setRows(3);
        script.setValue("alert(\"Hello Vaadin\");");
        addComponent(script);

        addComponent(new Button("Run script", new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                getWindow().executeJavaScript(script.getValue().toString());
            }
        }));

        I18NLabel sync = new I18NLabel("<h3>Force Server Syncronization</h3>",
                Label.CONTENT_XHTML);
        addComponent(sync);

        addComponent(new I18NLabel(
                "For advanced client side programmers Vaadin offers a simple "
                        + "method which can be used to force the client to synchronize with the "
                        + "server. This may be needed for example if another part of a mashup "
                        + "changes things on server."));

        toBeUpdatedFromThread = new I18NLabel(
                "This I18NLabel component will be updated by a background thread. Click \"Start "
                        + "background thread\" button and start clicking on the link below to force "
                        + "synchronization.", Label.CONTENT_XHTML);
        addComponent(toBeUpdatedFromThread);

        // This I18NLabel will be show for 10 seconds while the background process
        // is working
        running.setCaption("Background process is running for 10 seconds, click the link below");
        running.setIcon(new ThemeResource(
                "../base/common/img/ajax-loader-medium.gif"));

        // Clicking on this button will start a repeating thread that updates
        // the I18NLabel value
        startThread = new Button("Start background thread",
                new Button.ClickListener() {
                    public void buttonClick(ClickEvent event) {
                        ((Layout) startThread.getParent()).replaceComponent(
                                startThread, running);
                        new BackgroundProcess().start();
                    }
                });
        addComponent(startThread);

        // This link will make an Ajax request to the server that will respond
        // with UI changes that have happened since last request
        addComponent(new I18NLabel(
                "<a href=\"javascript:vaadin.forceSync();\">javascript: vaadin.forceSync();</a>",
                Label.CONTENT_XHTML));
    }

    private class BackgroundProcess extends Thread {
        private SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss");

        @Override
        public void run() {
            try {
                int i = 0;
                while (i++ < 10) {
                    Thread.sleep(1000);
                    toBeUpdatedFromThread.setValue("<strong>Server time is "
                            + f.format(new Date()) + "</strong>");
                }
                toBeUpdatedFromThread.setValue("Background process finished");
                ((Layout) running.getParent()).replaceComponent(running,
                        startThread);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
