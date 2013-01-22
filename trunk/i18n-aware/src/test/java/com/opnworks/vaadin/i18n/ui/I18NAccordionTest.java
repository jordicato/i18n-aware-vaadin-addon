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

		i18NAccordion.setCaptionMessage(TEST_KEY_1);

		final I18NButton component = new I18NButton(TEST_KEY_1);

		final I18NTab i18NTab = i18NAccordion.addI18NTab(component);

		i18NTab.setCaptionMessage(TEST_KEY_2);

		performTest(i18NAccordion, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return component.getCaption();
			}

			@Override
			public String getKey() {
				return TEST_KEY_1;
			}

			@Override
			public Object[] getParams() {
				return null;
			}
		});

		performTest(i18NAccordion, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return i18NTab.getCaption();
			}

			@Override
			public String getKey() {
				return TEST_KEY_2;
			}

			@Override
			public Object[] getParams() {
				return null;
			}
		});

		i18NTab.setCaptionMessage(TEST_KEY_1);

		performTest(i18NAccordion, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return component.getCaption();
			}

			@Override
			public String getKey() {
				return TEST_KEY_1;
			}

			@Override
			public Object[] getParams() {
				return null;
			}
		});

		performTest(i18NAccordion, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return i18NTab.getCaption();
			}

			@Override
			public String getKey() {
				return TEST_KEY_1;
			}

			@Override
			public Object[] getParams() {
				return null;
			}
		});

	}

}
