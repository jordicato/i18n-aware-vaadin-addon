package com.opnworks.vaadin.i18n.ui;

import org.junit.Test;

import com.opnworks.vaadin.i18n.AbstractI18NTest;

/**
 * The PopupDateField Unit Tests
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NPopupDateFieldTest extends AbstractI18NTest {

	@Test
	public void testConstructorCaptionKey() {

		final I18NPopupDateField i18NPopupDateField = new I18NPopupDateField(TEST_KEY_1);

		performTest(i18NPopupDateField, new I18NAwareTest() {

			public String getActualValue() {
				return i18NPopupDateField.getCaption();
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

		final I18NPopupDateField i18NPopupDateField = new I18NPopupDateField(TEST_KEY_1);

		i18NPopupDateField.setCaptionMessage(TEST_KEY_2);

		performTest(i18NPopupDateField, new I18NAwareTest() {

			public String getActualValue() {
				return i18NPopupDateField.getCaption();
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

		final I18NPopupDateField i18NPopupDateField = new I18NPopupDateField();

		final Object[] params = new Object[] { 1, 2, 3 };

		i18NPopupDateField.setCaptionMessage(TEST_KEY_3, params);

		performTest(i18NPopupDateField, new I18NAwareTest() {

			public String getActualValue() {
				return i18NPopupDateField.getCaption();
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

		final I18NPopupDateField i18NPopupDateField = new I18NPopupDateField(TEST_KEY_1);

		i18NPopupDateField.setRequiredErrorMessage(TEST_KEY_2);

		performTest(i18NPopupDateField, new I18NAwareTest() {

			public String getActualValue() {
				return i18NPopupDateField.getRequiredError();
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
	public void testSetInputPromptKey() {

		final I18NPopupDateField i18NPopupDateField = new I18NPopupDateField();

		i18NPopupDateField.setInputPromptKey(TEST_KEY_1);

		performTest(i18NPopupDateField, new I18NAwareTest() {

			public String getActualValue() {
				return i18NPopupDateField.getInputPrompt();
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
	public void testSetInputPromptParams() {

		final I18NPopupDateField i18NPopupDateField = new I18NPopupDateField();

		final Object[] params = new Object[] { 1, 2, 3 };

		i18NPopupDateField.setInputPromptKey(TEST_KEY_2, params);

		performTest(i18NPopupDateField, new I18NAwareTest() {

			public String getActualValue() {
				return i18NPopupDateField.getInputPrompt();
			}

			public String getKey() {
				return TEST_KEY_2;
			}

			public Object[] getParams() {
				return params;
			}
		});
	}
	
}
