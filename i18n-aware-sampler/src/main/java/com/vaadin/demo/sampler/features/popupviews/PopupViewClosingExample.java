package com.vaadin.demo.sampler.features.popupviews;

import com.opnworks.vaadin.i18n.ui.I18NLabel;
import com.opnworks.vaadin.i18n.ui.I18NVerticalLayout;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.PopupView.PopupVisibilityEvent;

@SuppressWarnings("serial")
public class PopupViewClosingExample extends I18NVerticalLayout implements
        PopupView.PopupVisibilityListener {

    public PopupViewClosingExample() {

        setSpacing(true);

        // Create the content for the popup
        I18NLabel content = new I18NLabel(
                "This popup will close as soon as you move the mouse cursor outside of the popup area.");
        // The PopupView popup will be as large as needed by the content
        content.setWidth("300px");

        // Construct the PopupView with simple HTML text representing the
        // minimized view
        PopupView popup = new PopupView("Default popup", content);
        popup.setHideOnMouseOut(true);
        popup.addListener(this);
        addComponent(popup);

        content = new I18NLabel(
                "This popup will only close if you click the mouse outside the popup area.");
        // The PopupView popup will be as large as needed by the content
        content.setWidth("300px");

        popup = new PopupView("Popup that won't auto-close", content);
        popup.setHideOnMouseOut(false);
        popup.addListener(this);
        addComponent(popup);
    }

    public void popupVisibilityChange(PopupVisibilityEvent event) {
        if (!event.isPopupVisible()) {
            getWindow().showNotification("Popup closed");
        }
    }
}
