package com.opnworks.vaadin.i18n.converter;

import java.io.File;

/**
 * The Conversion interface
 */
public interface Converter {

	/**
	 * Perform project conversion
	 * 
	 * @param sourceDir
	 *            The source directory
	 * @param rollback
	 *            True to rollback conversion
	 */
	void performI18NAwareProjectConversion(File sourceDir, boolean rollback);
}
