package com.opnworks.vaadin.i18n.ui;

import org.junit.Test;

import com.opnworks.vaadin.i18n.AbstractI18NTest;
import com.opnworks.vaadin.i18n.ui.I18NForm;

/**
 * The I18NForm Unit Tests
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NFormTest extends AbstractI18NTest {

	@Test
	public void testSetCaptionKey() {

		final I18NForm i18NForm = new I18NForm();

		i18NForm.setCaptionKey(TEST_KEY_2);

		performTest(i18NForm, new I18NAwareTest() {

			public String getActualValue() {
				return i18NForm.getCaption();
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

		final I18NForm i18NForm = new I18NForm();

		i18NForm.setCaptionKey(TEST_KEY_3);

		final Object[] params = new Object[] { 1, 2, 3 };

		i18NForm.setCaptionParams(params);

		performTest(i18NForm, new I18NAwareTest() {

			public String getActualValue() {
				return i18NForm.getCaption();
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
	public void testSetRequiredErrorKey() {

		final I18NForm i18NForm = new I18NForm();

		i18NForm.setCaptionKey(TEST_KEY_1);

		i18NForm.setRequiredErrorKey(TEST_KEY_2);

		performTest(i18NForm, new I18NAwareTest() {

			public String getActualValue() {
				return i18NForm.getRequiredError();
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
