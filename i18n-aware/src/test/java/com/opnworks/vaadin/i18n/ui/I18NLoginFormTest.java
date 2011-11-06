package com.opnworks.vaadin.i18n.ui;

import org.junit.Test;

import com.opnworks.vaadin.i18n.AbstractI18NTest;

/**
 * The I18NLoginForm Unit Tests
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NLoginFormTest extends AbstractI18NTest {

	@Test
	public void testSetCaptionKey() {

		final I18NLoginForm i18NLoginForm = new I18NLoginForm();

		i18NLoginForm.setCaptionKey(TEST_KEY_2);

		performTest(i18NLoginForm, new I18NAwareTest() {

			public String getActualValue() {
				return i18NLoginForm.getCaption();
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

		final I18NLoginForm i18NLoginForm = new I18NLoginForm();

		i18NLoginForm.setCaptionKey(TEST_KEY_3);

		final Object[] params = new Object[] { 1, 2, 3 };

		i18NLoginForm.setCaptionParams(params);

		performTest(i18NLoginForm, new I18NAwareTest() {

			public String getActualValue() {
				return (String) i18NLoginForm.getCaption();
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
	public void testSetUsernameCaptionKey() {

		final I18NLoginForm i18NLoginForm = new I18NLoginForm();

		i18NLoginForm.setUsernameCaptionKey(TEST_KEY_2);

		performTest(i18NLoginForm, new I18NAwareTest() {

			public String getActualValue() {
				return i18NLoginForm.getUsernameCaption();
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
	public void testSetPasswordCaptionKey() {

		final I18NLoginForm i18NLoginForm = new I18NLoginForm();

		i18NLoginForm.setPasswordCaptionKey(TEST_KEY_2);

		performTest(i18NLoginForm, new I18NAwareTest() {

			public String getActualValue() {
				return i18NLoginForm.getPasswordCaption();
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
	public void testSetLoginButtonCaptionKey() {

		final I18NLoginForm i18NLoginForm = new I18NLoginForm();

		i18NLoginForm.setLoginButtonCaptionKey(TEST_KEY_2);

		performTest(i18NLoginForm, new I18NAwareTest() {

			public String getActualValue() {
				return i18NLoginForm.getLoginButtonCaption();
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
