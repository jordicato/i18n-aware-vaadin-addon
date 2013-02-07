package com.vaadin.demo.sampler.features.shortcuts;

import java.util.Iterator;
import com.opnworks.vaadin.i18n.ui.I18NVerticalLayout;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.event.ShortcutAction.ModifierKey;
import com.vaadin.event.ShortcutListener;
import com.vaadin.ui.AbstractField.FocusShortcut;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickShortcut;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class ShortcutScopeExample extends I18NVerticalLayout {

    public ShortcutScopeExample() {
        setSpacing(true);
        HorizontalLayout hz = new HorizontalLayout();
        hz.setSpacing(true);
        addComponent(hz);
        hz.addComponent(createPanel(1));
        hz.addComponent(createPanel(2));
    }

    private Panel createPanel(final int number) {
        final Panel p = new Panel("com.vaadin.demo.sampler.features.shortcuts.ShortcutScopeExample.Panel" + number);
        ((I18NVerticalLayout) p.getContent()).setSpacing(true);
        p.addAction(new ShortcutListener("com.vaadin.demo.sampler.features.shortcuts.ShortcutScopeExample.Next_field", KeyCode.ARROW_DOWN, null) {

            @Override
            public void handleAction(Object sender, Object target) {
                for (Iterator<Component> it = ((ComponentContainer) sender).getComponentIterator(); it.hasNext(); ) {
                    if (it.next() == target && it.hasNext()) {
                        Object next = it.next();
                        if (next instanceof Focusable) {
                            ((Focusable) next).focus();
                        }
                    }
                }
            }
        });
        final TextField firstname = new TextField("com.vaadin.demo.sampler.features.shortcuts.ShortcutScopeExample.Firstname");
        firstname.setInputPrompt("com.vaadin.demo.sampler.features.shortcuts.ShortcutScopeExample.ALT_SHIFT_F_to_focus");
        p.addComponent(firstname);
        p.addAction(new FocusShortcut(firstname, KeyCode.F, ModifierKey.ALT, ModifierKey.SHIFT));
        firstname.addShortcutListener(new FocusShortcut(firstname, "Focus panel &_" + number));
        p.setDescription("com.vaadin.demo.sampler.features.shortcuts.ShortcutScopeExample.CTRL" + number + "com.vaadin.demo.sampler.features.shortcuts.ShortcutScopeExample.to_focus");
        final TextField lastname = new TextField("com.vaadin.demo.sampler.features.shortcuts.ShortcutScopeExample.Lastname");
        lastname.setInputPrompt("com.vaadin.demo.sampler.features.shortcuts.ShortcutScopeExample.ALT_SHIFT_L_to_focus");
        p.addComponent(lastname);
        p.addAction(new FocusShortcut(lastname, KeyCode.L, ModifierKey.ALT, ModifierKey.SHIFT));
        final Button save = new Button("com.vaadin.demo.sampler.features.shortcuts.ShortcutScopeExample.Save", new Button.ClickListener() {

            public void buttonClick(ClickEvent event) {
                getWindow().showNotification(p.getCaption() + " save clicked");
            }
        });
        p.addComponent(save);
        p.addAction(new ClickShortcut(save, KeyCode.S, ModifierKey.ALT, ModifierKey.SHIFT));
        return p;
    }
}
