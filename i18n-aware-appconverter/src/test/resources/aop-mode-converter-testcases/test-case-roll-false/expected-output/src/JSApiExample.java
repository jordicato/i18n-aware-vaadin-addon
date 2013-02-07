package com.vaadin.demo.sampler.features.commons;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.opnworks.vaadin.i18n.ui.I18NVerticalLayout;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextArea;

@SuppressWarnings("serial")
public class JSApiExample extends I18NVerticalLayout {

    private Label toBeUpdatedFromThread;

    private Button startThread;

    private Label running = new Label("");

    public JSApiExample() {
        setSpacing(true);
        Label javascript = new Label("com.vaadin.demo.sampler.features.commons.JSApiExample.h3_Run_Native_JavaScript_h3", Label.CONTENT_XHTML);
        addComponent(javascript);
        final TextArea script = new TextArea();
        script.setWidth("100%");
        script.setRows(3);
        script.setValue("alert(\"Hello Vaadin\");");
        addComponent(script);
        addComponent(new Button("com.vaadin.demo.sampler.features.commons.JSApiExample.Run_script", new Button.ClickListener() {

            public void buttonClick(ClickEvent event) {
                getWindow().executeJavaScript(script.getValue().toString());
            }
        }));
        Label sync = new Label("com.vaadin.demo.sampler.features.commons.JSApiExample.h3_Force_Server_Syncronizatio", Label.CONTENT_XHTML);
        addComponent(sync);
        addComponent(new Label("com.vaadin.demo.sampler.features.commons.JSApiExample.For_advanced_client_side_progr" + "com.vaadin.demo.sampler.features.commons.JSApiExample.method_which_can_be_used_to_fo" + "com.vaadin.demo.sampler.features.commons.JSApiExample.server_This_may_be_needed_for" + "com.vaadin.demo.sampler.features.commons.JSApiExample.changes_things_on_server"));
        toBeUpdatedFromThread = new Label("com.vaadin.demo.sampler.features.commons.JSApiExample.This_Label_component_will_be_u" + "com.vaadin.demo.sampler.features.commons.JSApiExample.background_thread_button_and" + "com.vaadin.demo.sampler.features.commons.JSApiExample.synchronization.", Label.CONTENT_XHTML);
        addComponent(toBeUpdatedFromThread);
        running.setCaption("com.vaadin.demo.sampler.features.commons.JSApiExample.Background_process_is_running");
        running.setIcon(new ThemeResource("../base/common/img/ajax-loader-medium.gif"));
        startThread = new Button("com.vaadin.demo.sampler.features.commons.JSApiExample.Start_background_thread", new Button.ClickListener() {

            public void buttonClick(ClickEvent event) {
                ((Layout) startThread.getParent()).replaceComponent(startThread, running);
                new BackgroundProcess().start();
            }
        });
        addComponent(startThread);
        addComponent(new Label("<a href=\"javascript:vaadin.forceSync();\">javascript: vaadin.forceSync();</a>", Label.CONTENT_XHTML));
    }

    private class BackgroundProcess extends Thread {

        private SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss");

        @Override
        public void run() {
            try {
                int i = 0;
                while (i++ < 10) {
                    Thread.sleep(1000);
                    toBeUpdatedFromThread.setValue("com.vaadin.demo.sampler.features.commons.JSApiExample.strong_Server_time_is" + f.format(new Date()) + "com.vaadin.demo.sampler.features.commons.JSApiExample.strong");
                }
                toBeUpdatedFromThread.setValue("com.vaadin.demo.sampler.features.commons.JSApiExample.Background_process_finished");
                ((Layout) running.getParent()).replaceComponent(running, startThread);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
