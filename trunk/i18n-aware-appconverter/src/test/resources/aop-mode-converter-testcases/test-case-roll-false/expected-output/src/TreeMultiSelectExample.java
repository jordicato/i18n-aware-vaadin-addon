package com.vaadin.demo.sampler.features.trees;

import java.util.Iterator;
import java.util.Set;
import com.opnworks.vaadin.i18n.ui.I18NVerticalLayout;
import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.demo.sampler.ExampleUtil;
import com.vaadin.event.Action;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Tree;

@SuppressWarnings("serial")
public class TreeMultiSelectExample extends I18NVerticalLayout implements Action.Handler {

    private static final Action ACTION_ADD = new Action("Add child item");

    private static final Action ACTION_DELETE = new Action("Delete");

    private static final Action[] ACTIONS = new Action[] { ACTION_ADD, ACTION_DELETE };

    private Tree tree;

    private Button deleteButton;

    public TreeMultiSelectExample() {
        setSpacing(true);
        tree = new Tree("com.vaadin.demo.sampler.features.trees.TreeMultiSelectExample.Hardware_Inventory", ExampleUtil.getHardwareContainer());
        tree.setMultiSelect(true);
        tree.setImmediate(true);
        tree.addListener(new ValueChangeListener() {

            public void valueChange(ValueChangeEvent event) {
                Tree t = (Tree) event.getProperty();
                deleteButton.setEnabled(t.getValue() != null && ((Set<?>) t.getValue()).size() > 0);
            }
        });
        tree.addActionHandler(this);
        tree.setItemCaptionPropertyId(ExampleUtil.hw_PROPERTY_NAME);
        tree.setItemCaptionMode(AbstractSelect.ITEM_CAPTION_MODE_PROPERTY);
        for (Iterator<?> it = tree.rootItemIds().iterator(); it.hasNext(); ) {
            tree.expandItemsRecursively(it.next());
        }
        deleteButton = new Button("com.vaadin.demo.sampler.features.trees.TreeMultiSelectExample.Delete", new Button.ClickListener() {

            @SuppressWarnings("unchecked")
            public void buttonClick(ClickEvent event) {
                Object[] toDelete = ((Set<Object>) tree.getValue()).toArray();
                for (int i = 0; i < toDelete.length; i++) {
                    handleAction(ACTION_DELETE, tree, toDelete[i]);
                }
            }
        });
        deleteButton.setEnabled(false);
        addComponent(deleteButton);
        addComponent(tree);
    }

    public Action[] getActions(Object target, Object sender) {
        return ACTIONS;
    }

    public void handleAction(Action action, Object sender, Object target) {
        if (action == ACTION_ADD) {
            tree.setChildrenAllowed(target, true);
            Object itemId = tree.addItem();
            tree.setChildrenAllowed(itemId, false);
            String newItemName = "New Item # " + itemId;
            Item item = tree.getItem(itemId);
            item.getItemProperty(ExampleUtil.hw_PROPERTY_NAME).setValue(newItemName);
            tree.setParent(itemId, target);
            tree.expandItem(target);
        } else if (action == ACTION_DELETE) {
            Object parent = tree.getParent(target);
            tree.removeItem(target);
            if (parent != null && tree.getChildren(parent).size() == 0) {
                tree.setChildrenAllowed(parent, false);
            }
        }
    }
}
