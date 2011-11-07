package com.vaadin.demo.sampler;

import com.opnworks.vaadin.i18n.ui.I18NLabel;
import com.vaadin.demo.sampler.gwt.client.ui.VCodeLabel;
import com.vaadin.ui.ClientWidget;

@ClientWidget(VCodeLabel.class)
@SuppressWarnings("serial")
public class CodeLabel extends I18NLabel {

    public CodeLabel() {
        setContentMode(CONTENT_PREFORMATTED);
    }

    public CodeLabel(String content) {
        super(content, CONTENT_PREFORMATTED);
    }

    @Override
    public void setContentMode(int contentMode) {
        if (contentMode != CONTENT_PREFORMATTED) {
            throw new UnsupportedOperationException(
                    "Only preformatted content supported");
        }
        super.setContentMode(CONTENT_PREFORMATTED);
    }

}
