package com.opnworks.vaadin.i18n.ui;

import org.junit.Test;

import com.opnworks.vaadin.i18n.AbstractI18NTest;

/**
 * The I18NDateField Unit Tests
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NDateFieldTest extends AbstractI18NTest {

	@Test
	public void testConstructorCaptionKey() {

		final I18NDateField i18NDateField = new I18NDateField(TEST_KEY_1);

		performTest(i18NDateField, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return i18NDateField.getCaption();
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

		final I18NDateField i18NDateField = new I18NDateField(TEST_KEY_1);

		i18NDateField.setCaptionMessage(TEST_KEY_2);

		performTest(i18NDateField, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return i18NDateField.getCaption();
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

		final I18NDateField i18NDateField = new I18NDateField();

		final Object[] params = new Object[] { 1, 2, 3 };

		i18NDateField.setCaptionMessage(TEST_KEY_3, params);

		performTest(i18NDateField, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return i18NDateField.getCaption();
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

	@Test
	public void testsetRequiredErrorMessage() {

		final I18NDateField i18NDateField = new I18NDateField(TEST_KEY_1);

		i18NDateField.setRequiredErrorMessage(TEST_KEY_2);

		performTest(i18NDateField, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return i18NDateField.getRequiredError();
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

}