package com.opnworks.vaadin.i18n.service_impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opnworks.vaadin.i18n.I18NMessageProvider;

/**
 * The Java ResourceBundle I18NMessageProvider implementation
 * 
 * @author Pedro Rodriguez
 */
public class ResourceBundleI18NMessageProvider implements I18NMessageProvider {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceBundleI18NMessageProvider.class);
	
	private String baseName;
	private ClassLoader classLoader;

	private Map<Locale, ResourceBundle> bundles = new HashMap<Locale, ResourceBundle>();
	
	private Locale locale;
	
	public ResourceBundleI18NMessageProvider(String baseName) {
		this(baseName, null);
	}
	
	public ResourceBundleI18NMessageProvider(String baseName,
			ClassLoader classLoader) {

		this.baseName = baseName;
		this.classLoader = classLoader;
	}

	public void setLocale(Locale locale) {

		this.locale = locale;
		
		// Preload bundle
		getBundle(locale);
	}

	@Override
	public String getMessage(String key, Object... args) {
		return getMessage( locale, key, args);
	}

	@Override
	public String getMessage(Locale locale, String key, Object... args) {
		return getMessage( getBundle(locale), key, args);
	}
	
	private String getMessage(ResourceBundle localeBundle, String key, Object... args) {
		
        String normalizedKey = normalizeKey(key);
		
		try {
			
			MessageFormat messageFormat = new MessageFormat(
					localeBundle.getString(normalizedKey), locale);
			
			return messageFormat.format(args, new StringBuffer(), null)
					.toString();
			
		} catch (MissingResourceException e) {
			
			try {
				appendNewMessageKey( normalizedKey, key );
			} catch (IOException e1) {
				LOGGER.error("Error saving new message key: " + normalizedKey, e);
			}
			
			return key;
		}
	}

	public void appendNewMessageKey( String normalizedKey, String text  ) throws IOException {
		
		String newMessageKeysFileName = System.getProperty("I18N_AWARE_NEW_MESSAGE_KEYS");
		
		if( newMessageKeysFileName == null) {
			return;
		}

        Properties properties = new Properties();
		
		File newMessageKeysFile = new File(newMessageKeysFileName);
		
		if(newMessageKeysFile.exists()) {
			FileInputStream fis = new FileInputStream( newMessageKeysFile );
	        properties.load( fis );
	        fis.close();
		}
		
		String currentValue = properties.getProperty(normalizedKey);
        
		properties.put(normalizedKey, text);
		
        if( !text.equals(currentValue)  ) {
    		FileOutputStream fos = new FileOutputStream(newMessageKeysFile); 
            properties.store(fos, "New keys generated on " + new Date().toString() );
            fos.close();
        }
        
	}

	private ResourceBundle getBundle(Locale locale) {
		
		ResourceBundle result = bundles.get(locale);
		
		if( result == null) {
			if (classLoader == null) {
				result = ResourceBundle.getBundle(baseName, locale);
			}
			else {
				result = ResourceBundle.getBundle(baseName, locale, classLoader);
			}
			
			bundles.put(locale, result);
		}
		
		return result;
	}
	
	private String normalizeKey( String key ) {
		
		if( !StringUtils.containsWhitespace(key) ) {
			return key;
		}
		
		StringBuilder result = new StringBuilder(); 
        
        int strLen = key.length();
        for (int i = 0; i < strLen; i++) {
            char charAt = key.charAt(i);
			if (Character.isWhitespace(charAt)) {
	        	result.append('\\');
            }
        	result.append(charAt);
        }
        
        return result.toString();
	}
	
}
