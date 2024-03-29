package com.opnworks.vaadin.i18n.ui;

import org.junit.Test;

import com.opnworks.vaadin.i18n.AbstractI18NTest;

/**
 * The I18NLink Unit Tests
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NLinkTest extends AbstractI18NTest {

	@Test
	public void testConstructorCaptionKey() {

		final I18NLink i18NLink = new I18NLink(TEST_KEY_1, null);

		performTest(i18NLink, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return i18NLink.getCaption();
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

	@Test
	public void testSetCaptionKey() {

		final I18NLink i18NLink = new I18NLink(TEST_KEY_1, null);

		i18NLink.setCaptionMessage(TEST_KEY_2);

		performTest(i18NLink, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return i18NLink.getCaption();
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

	}

	@Test
	public void testSetCaptionParams() {

		final I18NLink i18NLink = new I18NLink();

		final Object[] params = new Object[] { 1, 2, 3 };

		i18NLink.setCaptionMessage(TEST_KEY_3, params);

		performTest(i18NLink, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return i18NLink.getCaption();
			}

			@Override
			public String getKey() {
				return TEST_KEY_3;
			}

			@Override
			public Object[] getParams() {
				return params;
			}
		});
	}

}
