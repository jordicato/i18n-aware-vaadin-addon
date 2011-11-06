package com.opnworks.vaadin.i18n.ui;

import org.junit.Test;

import com.opnworks.vaadin.i18n.AbstractI18NTest;

/**
 * The I18NNativeButton Unit Tests
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NNativeButtonTest extends AbstractI18NTest {

	@Test
	public void testConstructorCaptionKey() {

		final I18NNativeButton i18NNativeButton = new I18NNativeButton(
				TEST_KEY_1);

		performTest(i18NNativeButton, new I18NAwareTest() {

			public String getActualValue() {
				return i18NNativeButton.getCaption();
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

		final I18NNativeButton i18NNativeButton = new I18NNativeButton(
				TEST_KEY_1);

		i18NNativeButton.setCaptionKey(TEST_KEY_2);

		performTest(i18NNativeButton, new I18NAwareTest() {

			public String getActualValue() {
				return i18NNativeButton.getCaption();
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

		final I18NNativeButton i18NNativeButton = new I18NNativeButton(
				TEST_KEY_3);

		final Object[] params = new Object[] { 1, 2, 3 };

		i18NNativeButton.setCaptionParams(params);

		performTest(i18NNativeButton, new I18NAwareTest() {

			public String getActualValue() {
				return i18NNativeButton.getCaption();
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
