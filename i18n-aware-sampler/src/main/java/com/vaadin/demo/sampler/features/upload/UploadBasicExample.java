package com.vaadin.demo.sampler.features.upload;

import java.io.IOException;
import java.io.OutputStream;

import com.opnworks.vaadin.i18n.ui.I18NVerticalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.FinishedEvent;
import com.vaadin.ui.Upload.Receiver;

@SuppressWarnings("serial")
public class UploadBasicExample extends I18NVerticalLayout {

    private Label result = new Label();

    private LineBreakCounter counter = new LineBreakCounter();

    private Upload upload = new Upload("Upload a file", counter);

    public UploadBasicExample() {
        addComponent(upload);
        addComponent(result);
        upload.addListener(new Upload.FinishedListener() {
            public void uploadFinished(FinishedEvent event) {
                result.setValue("Uploaded file contained "
                        + counter.getLineBreakCount() + " linebreaks");
            }
        });

    }

    public static class LineBreakCounter implements Receiver {

        private String fileName;
        private String mtype;
        private int counter;

        /**
         * return an OutputStream that simply counts lineends
         */
        public OutputStream receiveUpload(String filename, String MIMEType) {
            counter = 0;
            fileName = filename;
            mtype = MIMEType;
            return new OutputStream() {
                private static final int searchedByte = '\n';

                @Override
                public void write(int b) throws IOException {
                    if (b == searchedByte) {
                        counter++;
                    }
                }
            };
        }

        public String getFileName() {
            return fileName;
        }

        public String getMimeType() {
            return mtype;
        }

        public int getLineBreakCount() {
            return counter;
        }

    }

}
