package com.opnworks.vaadin.i18n;

import java.util.Locale;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.data.util.I18NCountLiterals;
import com.opnworks.vaadin.i18n.service_impl.I18NServiceImpl;
import com.opnworks.vaadin.i18n.service_impl.ResourceBundleI18NMessageProvider;

/**
 * The I18NCountLiterals
 * 
 * @author Sandy Rodriguez Garcia ( OpnWorks )
 */
public class I18NStaticService {
		
	public static I18NService i18NService;
		
	public I18NStaticService(String namespace, Locale locale) {
		i18NService = new I18NServiceImpl(new ResourceBundleI18NMessageProvider(namespace));
		I18NServiceImpl.setInstance(i18NService);
		i18NService.setLocale(locale);
	}
		
	public static I18NService getI18NServive() {
		return i18NService;
	}
}
