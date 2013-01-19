package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareContainer;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.support.I18NAwareSupport;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;

/**
 * The I18NGridLayout
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NGridLayout extends GridLayout implements I18NAwareContainer {

	private static final long serialVersionUID = 7259048281272984884L;

	private I18NAwareSupport i18nAwareSupport = new I18NAwareSupport();

    /**
     * Constructor for i18n grid of given size (number of cells). Note that grid's
     * final size depends on the items that are added into the grid. Grid grows
     * if you add components outside the grid's area.
     * 
     * @param columns
     *            Number of columns in the grid.
     * @param rows
     *            Number of rows in the grid.
     */
	public I18NGridLayout(int columns, int rows) {
		super(columns, rows);
	}

	@Override
	public void addComponent(Component component) {
		super.addComponent(component);
		i18nAwareSupport.add(component);
	}

	@Override
	public void addComponent(Component component, int column, int row) {
		super.addComponent(component, column, row);
		i18nAwareSupport.add(component);
	}

	@Override
	public void addComponent(Component component, int column1, int row1,
			int column2, int row2) {
		super.addComponent(component, column1, row1, column2, row2);
		i18nAwareSupport.add(component);
	}

	@Override
	public void i18NUpdate(I18NService i18N) {
		i18nAwareSupport.updateLabels(i18N);
	}
}