package com.opnworks.vaadin.i18n.ui;

import org.junit.Test;

import com.opnworks.vaadin.i18n.AbstractI18NTest;

/**
 * The I18NNativeSelect Unit Tests
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NNativeSelectTest extends AbstractI18NTest {

	@Test
	public void testConstructorCaptionKey() {

		final I18NNativeSelect i18NNativeSelect = new I18NNativeSelect(
				TEST_KEY_1);

		performTest(i18NNativeSelect, new I18NAwareTest() {

			public String getActualValue() {
				return i18NNativeSelect.getCaption();
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

		final I18NNativeSelect i18NNativeSelect = new I18NNativeSelect(
				TEST_KEY_1);

		i18NNativeSelect.setCaptionMessage(TEST_KEY_2);

		performTest(i18NNativeSelect, new I18NAwareTest() {

			public String getActualValue() {
				return i18NNativeSelect.getCaption();
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

		final I18NNativeSelect i18NNativeSelect = new I18NNativeSelect();

		final Object[] params = new Object[] { 1, 2, 3 };

		i18NNativeSelect.setCaptionMessage(TEST_KEY_3, params);

		performTest(i18NNativeSelect, new I18NAwareTest() {

			public String getActualValue() {
				return i18NNativeSelect.getCaption();
			}

			public String getKey() {
				return TEST_KEY_3;
			}

			public Object[] getParams() {
				return params;
			}
		});
	}

	@Test
	public void testsetRequiredErrorMessage() {

		final I18NNativeSelect i18NNativeSelect = new I18NNativeSelect(
				TEST_KEY_1);

		i18NNativeSelect.setRequiredErrorMessage(TEST_KEY_2);

		performTest(i18NNativeSelect, new I18NAwareTest() {

			public String getActualValue() {
				return i18NNativeSelect.getRequiredError();
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