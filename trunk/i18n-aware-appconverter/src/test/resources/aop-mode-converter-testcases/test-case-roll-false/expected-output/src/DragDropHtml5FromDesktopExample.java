package com.vaadin.demo.sampler.features.dragndrop;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import com.opnworks.vaadin.i18n.ui.I18NVerticalLayout;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptAll;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.service.ApplicationContext;
import com.vaadin.terminal.StreamResource;
import com.vaadin.terminal.StreamResource.StreamSource;
import com.vaadin.terminal.StreamVariable;
import com.vaadin.terminal.gwt.server.AbstractWebApplicationContext;
import com.vaadin.terminal.gwt.server.WebBrowser;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.DragAndDropWrapper;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Html5File;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.Notification;

public class DragDropHtml5FromDesktopExample extends I18NVerticalLayout {

    private ProgressIndicator progress;

    public DragDropHtml5FromDesktopExample() {
        addComponent(new Label("com.vaadin.demo.sampler.features.dragndrop.DragDropHtml5FromDesktopExample.Drag_text_from_desktop_applica" + "com.vaadin.demo.sampler.features.dragndrop.DragDropHtml5FromDesktopExample.file_system_to_the_drop_box_be"));
        CssLayout dropPane = new CssLayout();
        dropPane.setWidth("200px");
        dropPane.setHeight("200px");
        dropPane.addStyleName("image-drop-pane");
        ImageDropBox dropBox = new ImageDropBox(dropPane);
        dropBox.setSizeUndefined();
        Panel panel = new Panel(dropBox);
        panel.setSizeUndefined();
        panel.addStyleName("no-vertical-drag-hints");
        panel.addStyleName("no-horizontal-drag-hints");
        addComponent(panel);
        progress = new ProgressIndicator();
        progress.setIndeterminate(true);
        progress.setVisible(false);
        addComponent(progress);
    }

    @Override
    public void attach() {
        super.attach();
        ApplicationContext context = getApplication().getContext();
        if (context instanceof AbstractWebApplicationContext) {
            WebBrowser webBrowser = ((AbstractWebApplicationContext) context).getBrowser();
            boolean supportsHtml5FileDrop = webBrowser.isFirefox() && (webBrowser.getBrowserMajorVersion() >= 4 || (webBrowser.getBrowserMajorVersion() == 3 && webBrowser.getBrowserMinorVersion() >= 6));
            if (!supportsHtml5FileDrop) {
                supportsHtml5FileDrop = webBrowser.isChrome() || webBrowser.isSafari() && webBrowser.getBrowserMajorVersion() > 4;
            }
            if (!supportsHtml5FileDrop) {
                getWindow().showNotification("com.vaadin.demo.sampler.features.dragndrop.DragDropHtml5FromDesktopExample.Image_file_drop_is_only_suppor" + "com.vaadin.demo.sampler.features.dragndrop.DragDropHtml5FromDesktopExample.Text_can_be_dropped_into_the_b", Notification.TYPE_WARNING_MESSAGE);
            }
        }
    }

    private class ImageDropBox extends DragAndDropWrapper implements DropHandler {

        private static final long FILE_SIZE_LIMIT = 2 * 1024 * 1024;

        public ImageDropBox(Component root) {
            super(root);
            setDropHandler(this);
        }

        public void drop(DragAndDropEvent dropEvent) {
            WrapperTransferable tr = (WrapperTransferable) dropEvent.getTransferable();
            Html5File[] files = tr.getFiles();
            if (files != null) {
                for (final Html5File html5File : files) {
                    final String fileName = html5File.getFileName();
                    if (html5File.getFileSize() > FILE_SIZE_LIMIT) {
                        getWindow().showNotification("com.vaadin.demo.sampler.features.dragndrop.DragDropHtml5FromDesktopExample.File_rejected_Max_2Mb_files_a", Notification.TYPE_WARNING_MESSAGE);
                    } else {
                        final ByteArrayOutputStream bas = new ByteArrayOutputStream();
                        StreamVariable streamVariable = new StreamVariable() {

                            public OutputStream getOutputStream() {
                                return bas;
                            }

                            public boolean listenProgress() {
                                return false;
                            }

                            public void onProgress(StreamingProgressEvent event) {
                            }

                            public void streamingStarted(StreamingStartEvent event) {
                            }

                            public void streamingFinished(StreamingEndEvent event) {
                                progress.setVisible(false);
                                showFile(fileName, html5File.getType(), bas);
                            }

                            public void streamingFailed(StreamingErrorEvent event) {
                                progress.setVisible(false);
                            }

                            public boolean isInterrupted() {
                                return false;
                            }
                        };
                        html5File.setStreamVariable(streamVariable);
                        progress.setVisible(true);
                    }
                }
            } else {
                String text = tr.getText();
                if (text != null) {
                    showText(text);
                }
            }
        }

        private void showText(String text) {
            showComponent(new Label(text), "com.vaadin.demo.sampler.features.dragndrop.DragDropHtml5FromDesktopExample.Wrapped_text_content");
        }

        private void showFile(String name, String type, final ByteArrayOutputStream bas) {
            StreamSource streamSource = new StreamSource() {

                public InputStream getStream() {
                    if (bas != null) {
                        byte[] byteArray = bas.toByteArray();
                        return new ByteArrayInputStream(byteArray);
                    }
                    return null;
                }
            };
            StreamResource resource = new StreamResource(streamSource, name, getApplication());
            Embedded embedded = new Embedded(name, resource);
            showComponent(embedded, name);
        }

        private void showComponent(Component c, String name) {
            I18NVerticalLayout layout = new I18NVerticalLayout();
            layout.setSizeUndefined();
            layout.setMargin(true);
            Window w = new Window(name, layout);
            w.setSizeUndefined();
            c.setSizeUndefined();
            w.addComponent(c);
            getWindow().addWindow(w);
        }

        public AcceptCriterion getAcceptCriterion() {
            return AcceptAll.get();
        }
    }
}
