package com.opnworks.vaadin.i18n;

import java.util.Locale;

import com.opnworks.vaadin.i18n.service_impl.I18NAwareFactory;
import com.opnworks.vaadin.i18n.service_impl.I18NServiceImpl;
import com.opnworks.vaadin.i18n.service_impl.ResourceBundleI18NMessageProvider;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class I18NAwareDemo {

	public static void main(String[] args) {

		Window mainWindow = I18NAwareFactory.newWindow("main.window.title");

		TabSheet tabSheet = I18NAwareFactory.newTabSheet();

		VerticalLayout tab1Content = I18NAwareFactory.newVerticalLayout();
		tab1Content.addComponent(I18NAwareFactory.newTextField("tab1.text"));
		tab1Content.addComponent(I18NAwareFactory.newButton("tab1.button"));

		Tab tab1 = tabSheet.addTab(tab1Content);
		I18NAwareFactory.setCaptionMessage(tab1, "tab1.name");

		VerticalLayout tab2Content = I18NAwareFactory.newVerticalLayout();
		tab2Content.addComponent(I18NAwareFactory.newTextField("tab2.text"));
		tab2Content.addComponent(I18NAwareFactory.newButton("tab2.button"));

		Tab tab2 = tabSheet.addTab(tab2Content);
		I18NAwareFactory.setCaptionMessage(tab2, "tab2.name");

		mainWindow.addComponent(tabSheet);

		I18NService i18NService = new I18NServiceImpl(new ResourceBundleI18NMessageProvider("messages"));

		i18NService.registerI18NAware(mainWindow);

		i18NService.setLocale(Locale.FRENCH);

	}
}
