package com.askvikrant.noticeboard.components;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import com.askvikrant.noticeboard.model.Attachment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.FailedEvent;
import com.vaadin.ui.Upload.FinishedEvent;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.StartedEvent;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.BaseTheme;

public class FilesPanel extends VerticalLayout {

    private static final long serialVersionUID = 1L;

    private boolean readOnly = true;

    private final ArrayList<Attachment> attachments = new ArrayList<Attachment>();

    private final VerticalLayout topVLayout = new VerticalLayout();

    private final VerticalLayout bottomVLayout = new VerticalLayout();

    @SuppressWarnings("deprecation")
    public FilesPanel(final boolean b) {
        readOnly = b;
        topVLayout.setSpacing(true);
        topVLayout.setSizeFull();
        Panel panel = new Panel();
        panel.setHeight(80, Unit.PIXELS);
        panel.setContent(bottomVLayout);
        addComponent(topVLayout);
        addComponent(panel);
        final Label textualProgress = new Label();
        final ProgressIndicator pi = new ProgressIndicator();
        final UploadReceiver uploadReceiver = new UploadReceiver();
        final Upload upload = new Upload(null, uploadReceiver);
        upload.setImmediate(true);
        upload.setButtonCaption("com.askvikrant.noticeboard.components.FilesPanel.Attach_file");
        pi.setWidth(99, Unit.PERCENTAGE);
        textualProgress.setWidth(30, Unit.PIXELS);
        final Button cancelProcessing = new Button("com.askvikrant.noticeboard.components.FilesPanel.Cancel");
        cancelProcessing.setVisible(false);
        cancelProcessing.addStyleName(BaseTheme.BUTTON_LINK);
        cancelProcessing.addClickListener(new ClickListener() {

            private static final long serialVersionUID = 1L;

            public void buttonClick(ClickEvent event) {
                upload.interruptUpload();
            }
        });
        textualProgress.setVisible(false);
        pi.setVisible(false);
        HorizontalLayout hLayout = new HorizontalLayout();
        hLayout.setSpacing(true);
        hLayout.addComponent(textualProgress);
        hLayout.addComponent(cancelProcessing);
        if (!readOnly) {
            topVLayout.addComponent(upload);
            topVLayout.addComponent(pi);
            topVLayout.addComponent(hLayout);
        }
        upload.addListener(new Upload.StartedListener() {

            private static final long serialVersionUID = 1L;

            public void uploadStarted(StartedEvent event) {
                pi.setValue(0f);
                pi.setVisible(true);
                pi.setPollingInterval(500);
                textualProgress.setVisible(true);
                cancelProcessing.setVisible(true);
                upload.setEnabled(false);
                bottomVLayout.setEnabled(false);
            }
        });
        upload.addListener(new Upload.ProgressListener() {

            private static final long serialVersionUID = 1L;

            public void updateProgress(long readBytes, long contentLength) {
                Float rp = new Float(readBytes);
                Float tr = new Float(contentLength);
                Float value = rp / tr;
                pi.setValue(value);
                Float percent = value * 100.0f;
                textualProgress.setValue(Math.round(percent) + "%");
            }
        });
        upload.addListener(new Upload.SucceededListener() {

            private static final long serialVersionUID = 1L;

            public void uploadSucceeded(SucceededEvent event) {
                File file = uploadReceiver.getFile();
                Attachment attachment = new Attachment();
                attachment.setFile(file);
                attachment.setContentType(uploadReceiver.getMimeType());
                attachments.add(attachment);
                refresh();
                upload.setEnabled(true);
                bottomVLayout.setEnabled(true);
            }
        });
        upload.addListener(new Upload.FailedListener() {

            private static final long serialVersionUID = 1L;

            public void uploadFailed(FailedEvent event) {
                Notification.show("Error", "File could not be uploaded. ", Type.ERROR_MESSAGE);
                upload.setEnabled(true);
                bottomVLayout.setEnabled(true);
            }
        });
        upload.addListener(new Upload.FinishedListener() {

            private static final long serialVersionUID = 1L;

            public void uploadFinished(FinishedEvent event) {
                pi.setVisible(false);
                textualProgress.setVisible(false);
                cancelProcessing.setVisible(false);
                upload.setEnabled(true);
            }
        });
    }

    public ArrayList<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(ArrayList<Attachment> a) {
        attachments.clear();
        for (int i = 0; i < a.size(); i++) {
            attachments.add(a.get(i));
        }
        refresh();
    }

    public void removeAllFiles() {
        attachments.clear();
        refresh();
    }

    private void refresh() {
        FilesPanel.this.setCaption(attachments.size() + " attachments");
        bottomVLayout.removeAllComponents();
        for (int i = 0; i < attachments.size(); i++) {
            final Attachment attachment = attachments.get(i);
            final Panel panel = new Panel();
            HorizontalLayout hLayout = new HorizontalLayout();
            panel.setContent(hLayout);
            hLayout.setSpacing(true);
            bottomVLayout.addComponent(hLayout);
            Label lblName = new Label(i + 1 + ": " + attachment.getFile().getName());
            hLayout.addComponent(lblName);
            if (readOnly) {
                Button btnDownload = new Button("com.askvikrant.noticeboard.components.FilesPanel.Download");
                btnDownload.addStyleName(BaseTheme.BUTTON_LINK);
                btnDownload.addClickListener(new ClickListener() {

                    private static final long serialVersionUID = 1L;

                    @Override
                    public void buttonClick(ClickEvent event) {
                        String url = getUI().getPage().getLocation().toString();
                        String[] tokens = url.split("#");
                        url = tokens[0] + "download?attachment_id=" + attachment.getId();
                        System.out.println("URL: " + url);
                        getUI().getPage().open(url, "_blank");
                    }
                });
                hLayout.addComponent(btnDownload);
            }
            if (!readOnly) {
                Button btnDelete = new Button("com.askvikrant.noticeboard.components.FilesPanel.Remove");
                btnDelete.addStyleName(BaseTheme.BUTTON_LINK);
                hLayout.addComponent(btnDelete);
                btnDelete.addClickListener(new ClickListener() {

                    private static final long serialVersionUID = 1L;

                    @Override
                    public void buttonClick(ClickEvent event) {
                        attachments.remove(attachment);
                        refresh();
                    }
                });
            }
        }
    }

    class UploadReceiver implements Receiver {

        /**
		 * 
		 */
        private static final long serialVersionUID = 1L;

        private String fileName;

        private String mtype;

        private int counter;

        private int total;

        private File file = null;

        private transient FileOutputStream fos = null;

        /**
	 * return an OutputStream that simply counts line ends
	 */
        public OutputStream receiveUpload(String filename, String mimeType) {
            counter = 0;
            total = 0;
            fileName = filename;
            mtype = mimeType;
            try {
                final File parentDir = new File("noticeboard_temp");
                if (!parentDir.exists()) {
                    parentDir.mkdir();
                }
                file = new File(parentDir, fileName);
                file.createNewFile();
                fos = new FileOutputStream(file);
            } catch (IOException ioEx) {
                System.out.println("ERROR: " + ioEx.getMessage());
                return null;
            }
            return new OutputStream() {

                @Override
                public void write(int b) throws IOException {
                    fos.write(b);
                    total++;
                    if (total % 10000 == 0) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException ex) {
                            System.out.println("ERROR: Document Upload Panel: " + ex.getMessage());
                        }
                    }
                }
            };
        }

        public File getFile() {
            return file;
        }

        public String getFileName() {
            return fileName;
        }

        public String getMimeType() {
            return mtype;
        }

        int getLineBreakCount() {
            return counter;
        }
    }
}
