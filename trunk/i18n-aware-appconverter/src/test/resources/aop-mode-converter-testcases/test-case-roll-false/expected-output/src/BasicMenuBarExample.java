package com.vaadin.demo.sampler.features.menubar;

import com.opnworks.vaadin.i18n.ui.I18NVerticalLayout;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;

@SuppressWarnings("serial")
public class BasicMenuBarExample extends I18NVerticalLayout {

    private MenuBar menubar = new MenuBar();

    public BasicMenuBarExample() {
        final MenuBar.MenuItem file = menubar.addItem("com.vaadin.demo.sampler.features.menubar.BasicMenuBarExample.File", null);
        final MenuBar.MenuItem newItem = file.addItem("com.vaadin.demo.sampler.features.menubar.BasicMenuBarExample.New", null);
        file.addItem("com.vaadin.demo.sampler.features.menubar.BasicMenuBarExample.Open_file", menuCommand);
        file.addSeparator();
        newItem.addItem("com.vaadin.demo.sampler.features.menubar.BasicMenuBarExample.File_1", menuCommand);
        newItem.addItem("com.vaadin.demo.sampler.features.menubar.BasicMenuBarExample.Folder", menuCommand);
        newItem.addItem("com.vaadin.demo.sampler.features.menubar.BasicMenuBarExample.Project...", menuCommand);
        file.addItem("com.vaadin.demo.sampler.features.menubar.BasicMenuBarExample.Close", menuCommand);
        file.addItem("com.vaadin.demo.sampler.features.menubar.BasicMenuBarExample.Close_All", menuCommand);
        file.addSeparator();
        file.addItem("com.vaadin.demo.sampler.features.menubar.BasicMenuBarExample.Save", menuCommand);
        file.addItem("com.vaadin.demo.sampler.features.menubar.BasicMenuBarExample.Save_As", menuCommand);
        file.addItem("com.vaadin.demo.sampler.features.menubar.BasicMenuBarExample.Save_All", menuCommand);
        final MenuBar.MenuItem edit = menubar.addItem("com.vaadin.demo.sampler.features.menubar.BasicMenuBarExample.Edit", null);
        edit.addItem("com.vaadin.demo.sampler.features.menubar.BasicMenuBarExample.Undo", menuCommand);
        edit.addItem("Redo", menuCommand).setEnabled(false);
        edit.addSeparator();
        edit.addItem("com.vaadin.demo.sampler.features.menubar.BasicMenuBarExample.Cut", menuCommand);
        edit.addItem("com.vaadin.demo.sampler.features.menubar.BasicMenuBarExample.Copy", menuCommand);
        edit.addItem("com.vaadin.demo.sampler.features.menubar.BasicMenuBarExample.Paste", menuCommand);
        edit.addSeparator();
        final MenuBar.MenuItem find = edit.addItem("com.vaadin.demo.sampler.features.menubar.BasicMenuBarExample.Find_Replace", menuCommand);
        find.addItem("com.vaadin.demo.sampler.features.menubar.BasicMenuBarExample.Google_Search", new Command() {

            public void menuSelected(MenuItem selectedItem) {
                getWindow().open(new ExternalResource("http://www.google.com"));
            }
        });
        find.addSeparator();
        find.addItem("com.vaadin.demo.sampler.features.menubar.BasicMenuBarExample.Find_Replace_1", menuCommand);
        find.addItem("com.vaadin.demo.sampler.features.menubar.BasicMenuBarExample.Find_Next", menuCommand);
        find.addItem("com.vaadin.demo.sampler.features.menubar.BasicMenuBarExample.Find_Previous", menuCommand);
        final MenuBar.MenuItem view = menubar.addItem("com.vaadin.demo.sampler.features.menubar.BasicMenuBarExample.View", null);
        view.addItem("com.vaadin.demo.sampler.features.menubar.BasicMenuBarExample.Show_Hide_Status_Bar", menuCommand);
        view.addItem("com.vaadin.demo.sampler.features.menubar.BasicMenuBarExample.Customize_Toolbar", menuCommand);
        view.addSeparator();
        view.addItem("com.vaadin.demo.sampler.features.menubar.BasicMenuBarExample.Actual_Size", menuCommand);
        view.addItem("com.vaadin.demo.sampler.features.menubar.BasicMenuBarExample.Zoom_In", menuCommand);
        view.addItem("com.vaadin.demo.sampler.features.menubar.BasicMenuBarExample.Zoom_Out", menuCommand);
        addComponent(menubar);
    }

    private Command menuCommand = new Command() {

        public void menuSelected(MenuItem selectedItem) {
            getWindow().showNotification("Action " + selectedItem.getText());
        }
    };
}
