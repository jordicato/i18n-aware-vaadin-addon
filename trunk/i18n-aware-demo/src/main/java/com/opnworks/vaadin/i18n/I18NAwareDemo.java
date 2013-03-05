package com.opnworks.vaadin.i18n;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opnworks.vaadin.i18n.service_impl.I18NServiceImpl;
import com.opnworks.vaadin.i18n.service_impl.ResourceBundleI18NMessageProvider;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;

/**
 * The I18NAware Demo webapp
 * 
 * @author Pedro Rodriguez
 */
public class I18NAwareDemo extends UI {

	private static final long serialVersionUID = 2326412816728518688L;

	private I18NService i18NService;

	@Override
	public void init(VaadinRequest request) {

		i18NService = new I18NServiceImpl(new ResourceBundleI18NMessageProvider("messages"));

		I18NServiceImpl.setInstance(i18NService);

		// Set initial language
		i18NService.setLocale(Locale.ENGLISH);

		// Component mainWindow = new I18NAwareDemoWindow(i18NService);

		Component mainWindow = new I18NAwareVaadinComponentsExample(i18NService);

		setContent(mainWindow);

		i18NService.registerI18NAware(mainWindow);
	}

	public void onRequestStart(HttpServletRequest request, HttpServletResponse response) {

		I18NServiceImpl.setInstance(i18NService);
	}

	public void onRequestEnd(HttpServletRequest request, HttpServletResponse response) {

		I18NServiceImpl.setInstance(null);
	}

}
