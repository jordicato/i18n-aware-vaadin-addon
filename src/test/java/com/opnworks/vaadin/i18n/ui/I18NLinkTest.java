package com.opnworks.vaadin.i18n.ui;

import org.junit.Test;

import com.opnworks.vaadin.i18n.AbstractI18NTest;
import com.opnworks.vaadin.i18n.ui.I18NLink;

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

			public String getActualValue() {
				return i18NLink.getCaption();
			}

			public String getKey() {
				return TEST_KEY_1;
			}

			public Object[] getParams() {
				return null;
			}
		});

	}

	@Test
	public void testSetCaptionKey() {

		final I18NLink i18NLink = new I18NLink(TEST_KEY_1, null);

		i18NLink.setCaptionKey(TEST_KEY_2);

		performTest(i18NLink, new I18NAwareTest() {

			public String getActualValue() {
				return i18NLink.getCaption();
			}

			public String getKey() {
				return TEST_KEY_2;
			}

			public Object[] getParams() {
				return null;
			}
		});

	}

	@Test
	public void testSetCaptionParams() {

		final I18NLink i18NLink = new I18NLink(TEST_KEY_3, null);

		final Object[] params = new Object[] { 1, 2, 3 };

		i18NLink.setCaptionParams(params);

		performTest(i18NLink, new I18NAwareTest() {

			public String getActualValue() {
				return i18NLink.getCaption();
			}

			public String getKey() {
				return TEST_KEY_3;
			}

			public Object[] getParams() {
				return params;
			}
		});
	}

}
