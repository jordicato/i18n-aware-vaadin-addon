package com.opnworks.vaadin.i18n.ui;

import org.junit.Test;

import com.opnworks.vaadin.i18n.AbstractI18NTest;

/**
 * The I18NPasswordField Unit Tests
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NPasswordFieldTest extends AbstractI18NTest {

	@Test
	public void testConstructorCaptionKey() {

		final I18NPasswordField i18NPasswordField = new I18NPasswordField(
				TEST_KEY_1);

		performTest(i18NPasswordField, new I18NAwareTest() {

			public String getActualValue() {
				return i18NPasswordField.getCaption();
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

		final I18NPasswordField i18NPasswordField = new I18NPasswordField(
				TEST_KEY_1);

		i18NPasswordField.setCaptionMessage(TEST_KEY_2);

		performTest(i18NPasswordField, new I18NAwareTest() {

			public String getActualValue() {
				return i18NPasswordField.getCaption();
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

		final I18NPasswordField i18NPasswordField = new I18NPasswordField();

		final Object[] params = new Object[] { 1, 2, 3 };

		i18NPasswordField.setCaptionMessage(TEST_KEY_3, params);

		performTest(i18NPasswordField, new I18NAwareTest() {

			public String getActualValue() {
				return i18NPasswordField.getCaption();
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

		final I18NPasswordField i18NPasswordField = new I18NPasswordField(
				TEST_KEY_1);

		i18NPasswordField.setRequiredErrorMessage(TEST_KEY_2);

		performTest(i18NPasswordField, new I18NAwareTest() {

			public String getActualValue() {
				return i18NPasswordField.getRequiredError();
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