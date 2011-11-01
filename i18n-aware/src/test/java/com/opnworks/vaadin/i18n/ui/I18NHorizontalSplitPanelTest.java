package com.opnworks.vaadin.i18n.ui;

import org.junit.Test;

import com.opnworks.vaadin.i18n.AbstractI18NTest;

/**
 * The I18NHorizontalSplitPanel Unit Tests
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NHorizontalSplitPanelTest extends AbstractI18NTest {

	@Test
	public void testAddComponent() {

		final I18NHorizontalSplitPanel i18NHorizontalSplitPanel = new I18NHorizontalSplitPanel();

		final I18NButton component = new I18NButton(TEST_KEY_1);

		i18NHorizontalSplitPanel.addComponent(component);

		performTest(i18NHorizontalSplitPanel, new I18NAwareTest() {

			public String getActualValue() {
				return component.getCaption();
			}

			public String getKey() {
				return TEST_KEY_1;
			}

			public Object[] getParams() {
				return null;
			}
		});
	}

}
