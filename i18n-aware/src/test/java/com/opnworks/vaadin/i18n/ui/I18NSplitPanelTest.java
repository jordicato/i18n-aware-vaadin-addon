package com.opnworks.vaadin.i18n.ui;

import org.junit.Test;

import com.opnworks.vaadin.i18n.AbstractI18NTest;

/**
 * The I18NSplitPanel Unit Tests
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NSplitPanelTest extends AbstractI18NTest {

	@SuppressWarnings("deprecation")
	@Test
	public void testAddComponent() {

		final I18NSplitPanel i18NSplitPanel = new I18NSplitPanel();

		final I18NButton component = new I18NButton(TEST_KEY_1);

		i18NSplitPanel.addComponent(component);

		performTest(i18NSplitPanel, new I18NAwareTest() {

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
