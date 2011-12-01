package com.opnworks.vaadin.i18n;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opnworks.vaadin.i18n.service_impl.I18NServiceImpl;
import com.opnworks.vaadin.i18n.service_impl.ResourceBundleI18NMessageProvider;
import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.HttpServletRequestListener;
import com.vaadin.ui.Window;

/**
 * The I18NAware Demo webapp
 * 
 * @author Pedro Rodriguez
 */
public class I18NAwareDemo extends Application implements
		HttpServletRequestListener {

	private static final long serialVersionUID = 2326412816728518688L;
	
	private I18NService i18NService;

	@Override
	public void init() {

		i18NService = new I18NServiceImpl(
				new ResourceBundleI18NMessageProvider("messages"));

		I18NServiceImpl.setInstance(i18NService);
		
		// Set initial language
		i18NService.setLocale(Locale.ENGLISH);

		Window mainWindow = new I18NAwareDemoWindow(i18NService);

		setMainWindow(mainWindow);

		i18NService.registerI18NAware(mainWindow);
	}

	public void onRequestStart(HttpServletRequest request,
			HttpServletResponse response) {

		I18NServiceImpl.setInstance(i18NService);
	}

	public void onRequestEnd(HttpServletRequest request,
			HttpServletResponse response) {

		I18NServiceImpl.setInstance(null);
	}

}
