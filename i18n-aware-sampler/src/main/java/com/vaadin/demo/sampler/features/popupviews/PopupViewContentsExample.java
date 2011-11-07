package com.vaadin.demo.sampler.features.popupviews;

import com.opnworks.vaadin.i18n.ui.I18NLabel;
import com.opnworks.vaadin.i18n.ui.I18NVerticalLayout;
import com.vaadin.ui.Component;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class PopupViewContentsExample extends I18NVerticalLayout {

    public PopupViewContentsExample() {

        setSpacing(true);

        // ------
        // Static content for the minimized view
        // ------

        // Create the content for the popup
        I18NLabel content = new I18NLabel(
                "This is a simple I18NLabel component inside the popup. You can place any Vaadin components here.");
        // The PopupView popup will be as large as needed by the content
        content.setWidth("300px");

        // Construct the PopupView with simple HTML text representing the
        // minimized view
        PopupView popup = new PopupView("Static HTML content", content);
        addComponent(popup);

        // ------
        // Dynamic content for the minimized view
        // ------

        // In this sample we update the minimized view value with the content of
        // the TextField inside the popup.
        popup = new PopupView(new PopupTextField());
        popup.setDescription("Click to edit");
        popup.setHideOnMouseOut(false);
        addComponent(popup);
    }

    // Create a dynamically updating content for the popup
    public class PopupTextField implements PopupView.Content {

        private TextField tf = new TextField("Edit me");
        private I18NVerticalLayout root = new I18NVerticalLayout();

        public PopupTextField() {
            root.setSizeUndefined();
            root.setSpacing(true);
            root.setMargin(true);
            root.addComponent(new I18NLabel(
                    "The changes made to any components inside the popup are reflected automatically when the popup is closed, but you might want to provide explicit action buttons for the user, like \"Save\" or \"Close\"."));

            root.addComponent(tf);
            tf.setValue("Initial dynamic content");
            tf.setWidth("300px");
        }

        public String getMinimizedValueAsHTML() {
            return tf.getValue().toString();
        }

        public Component getPopupComponent() {
            return root;
        }
    };
}
