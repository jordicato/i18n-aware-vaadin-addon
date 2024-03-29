package com.opnworks.vaadin.i18n.ui;

import org.junit.Test;

import com.opnworks.vaadin.i18n.AbstractI18NTest;

/**
 * The I18NProgressIndicator Unit Tests
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NProgressIndicatorTest extends AbstractI18NTest {

	@Test
	public void testSetCaptionKey() {

		final I18NProgressIndicator i18NProgressIndicator = new I18NProgressIndicator();

		i18NProgressIndicator.setCaptionMessage(TEST_KEY_2);

		performTest(i18NProgressIndicator, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return i18NProgressIndicator.getCaption();
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

		final I18NProgressIndicator i18NProgressIndicator = new I18NProgressIndicator();

		final Object[] params = new Object[] { 1, 2, 3 };

		i18NProgressIndicator.setCaptionMessage(TEST_KEY_3, params);

		performTest(i18NProgressIndicator, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return i18NProgressIndicator.getCaption();
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
