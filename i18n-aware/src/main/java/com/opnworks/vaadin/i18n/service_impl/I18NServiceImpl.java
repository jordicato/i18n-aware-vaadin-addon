package com.opnworks.vaadin.i18n.service_impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.opnworks.vaadin.i18n.I18NAware;
import com.opnworks.vaadin.i18n.I18NMessageProvider;
import com.opnworks.vaadin.i18n.I18NService;

/**
 * The I18NService implementation
 * 
 * @author Pedro Rodriguez (OpnWorks)
 */
public class I18NServiceImpl implements I18NService {

	private static ThreadLocal<I18NService> threadLocal = new ThreadLocal<I18NService>();

	/**
	 * Retrieve the current application I18NService instance
	 * 
	 * @return the current application I18NService instance
	 */
	public static I18NService getInstance() {
		return threadLocal.get();
	}

	/**
	 * Set the current application I18NService instance
	 * 
	 * Your application must invoke I18NServiceImpl.setInstance on HttpServletRequestListener.onRequestStart & HttpServletRequestListener.onRequestEnd
	 * 
	 * <code> 
	 * public class MyApplication extends Application implements HttpServletRequestListener {
	 * 
	 * 		private I18NService i18NService;
	 * 
	 * 		...
	 * 
	 * 		public void onRequestStart(HttpServletRequest request, HttpServletResponse response) { 
	 *   		I18NServiceImpl.setInstance(i18NService);
	 * 		}
	 * 
	 * 		public void onRequestEnd(HttpServletRequest request, HttpServletResponse response) { 
	 * 			I18NServiceImpl.setInstance(null); 
	 * 		}
	 * }
	 * </code>
	 * 
	 * @param i18NService
	 */
	public static void setInstance(I18NService i18NService) {

		if (i18NService == null) {
			threadLocal.remove();
			return;
		}

		threadLocal.set(i18NService);
	}

	private I18NMessageProvider i18NMessageProvider;

	private List<I18NAware> i18NAwares = new ArrayList<I18NAware>();

	private Locale locale;

	public I18NServiceImpl(I18NMessageProvider i18NMessageProvider) {
		this.i18NMessageProvider = i18NMessageProvider;
		locale = Locale.getDefault();
	}

	@Override
	public Locale getLocale() {
		return locale;
	}

	@Override
	public String getMessage(Locale locale, String key, Object... args) {
		try {
			return i18NMessageProvider.getMessage(locale, key, args);
		}
		catch (Exception e) {
			return locale.toString() + ":" + key;
		}
	}

	@Override
	public String getMessage(String key, Object... args) {
		try {
			return getMessage(locale, key, args);
		}
		catch (Exception e) {
			return locale.toString() + ":" + key;
		}
	}

	@Override
	public <T> void registerI18NAware(T i18NAware) {

		if (!(i18NAware instanceof I18NAware)) {
			throw new IllegalArgumentException("Expecting a I18NAware");
		}
		if (!i18NAwares.contains((I18NAware) i18NAware)) {
			i18NAwares.add((I18NAware) i18NAware);
		}
	}

	@Override
	public void setLocale(Locale locale) {
		this.locale = locale;
		i18NMessageProvider.setLocale(locale);
		i18nUpdate();
	}

	private void i18nUpdate() {
		for (I18NAware i18NAware : i18NAwares ) {
			i18NAware.i18NUpdate(this);
		}
	}
}
