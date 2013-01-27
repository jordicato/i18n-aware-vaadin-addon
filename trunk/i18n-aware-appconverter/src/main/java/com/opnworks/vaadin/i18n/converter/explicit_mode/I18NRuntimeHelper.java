package com.opnworks.vaadin.i18n.converter.explicit_mode;

import java.util.Iterator;
import com.opnworks.vaadin.i18n.I18NAwareField;
import com.opnworks.vaadin.i18n.ui.I18NWindow;
import com.vaadin.ui.Component;

public class I18NRuntimeHelper {

    public static void FixCaptionMessages(I18NWindow window) {
        for (Iterator<Component> it = window.getComponentIterator(); it.hasNext(); ) {
            try {
                Component c = it.next();
                if (c instanceof I18NAwareField) {
                    processComponent((I18NAwareField) c);
                }
            } catch (Exception e) {
            }
        }
    }

    private static void processComponent(I18NAwareField f) {
    }
}
