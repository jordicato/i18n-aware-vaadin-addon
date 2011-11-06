package com.opnworks.vaadin.i18n.ui;

import org.junit.Test;

import com.opnworks.vaadin.i18n.AbstractI18NTest;

/**
 * The I18NAccordion Unit Tests
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NAccordionTest extends AbstractI18NTest {

	@Test
	public void testAddI18NTab() {

		final I18NAccordion i18NAccordion = new I18NAccordion();

		final I18NButton component = new I18NButton(TEST_KEY_1);

		final I18NTab i18NTab = i18NAccordion.addI18NTab(component);

		i18NTab.setCaptionKey(TEST_KEY_2);

		performTest(i18NAccordion, new I18NAwareTest() {

			public String getActualValue() {
				return component.getCaption();
			}

			public String getKey() {
				return TEST_KEY_1;
			}

			public Object[] getParams() {
				return null;
			}
		}, new I18NAwareTest() {

			public String getActualValue() {
				return i18NTab.getCaption();
			}

			public String getKey() {
				return TEST_KEY_2;
			}

			public Object[] getParams() {
				return null;
			}
		});

	}

}
