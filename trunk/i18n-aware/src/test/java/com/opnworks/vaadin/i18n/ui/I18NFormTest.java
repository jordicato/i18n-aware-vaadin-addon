package com.opnworks.vaadin.i18n.ui;

import org.junit.Test;

import com.opnworks.vaadin.i18n.AbstractI18NTest;

/**
 * The I18NForm Unit Tests
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NFormTest extends AbstractI18NTest {

	@Test
	public void testSetCaptionKey() {

		final I18NForm i18NForm = new I18NForm();

		i18NForm.setCaptionMessage(TEST_KEY_2);

		performTest(i18NForm, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return i18NForm.getCaption();
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

		final I18NForm i18NForm = new I18NForm();

		final Object[] params = new Object[] { 1, 2, 3 };

		i18NForm.setCaptionMessage(TEST_KEY_3, params);

		performTest(i18NForm, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return i18NForm.getCaption();
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

		final I18NForm i18NForm = new I18NForm();

		i18NForm.setCaptionMessage(TEST_KEY_1);

		i18NForm.setRequiredErrorMessage(TEST_KEY_2);

		performTest(i18NForm, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return i18NForm.getRequiredError();
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
