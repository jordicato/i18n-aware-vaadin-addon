package com.opnworks.vaadin.i18n.converter;

import java.io.File;

/**
 * The Conversion interface
 */
public interface Converter {

	/**
	 * @param sourceDir
	 *            The source directory. 
	 * @param resourcesDir
	 *            The resources directory
	 * @param resourceBaseName
	 *            The resource base name
	 * @param defaultLanguage
	 *            The default language
	 * @param rollback
	 *            True to rollback conversion
	 */
	void performI18NAwareProjectConversion(File sourceDir, File resourcesDir, String resourceBaseName, String defaultLanguage, boolean rollback);
}
