package com.opnworks.vaadin.i18n.ui;

import org.junit.Test;

import com.opnworks.vaadin.i18n.AbstractI18NTest;

/**
 * The I18NTextField Unit Tests
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NTextFieldTest extends AbstractI18NTest {

	@Test
	public void testConstructorCaptionKey() {

		final I18NTextField i18NTextField = new I18NTextField(TEST_KEY_1);

		performTest(i18NTextField, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return i18NTextField.getCaption();
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

		final I18NTextField i18NTextField = new I18NTextField(TEST_KEY_1);

		i18NTextField.setCaptionMessage(TEST_KEY_2);

		performTest(i18NTextField, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return i18NTextField.getCaption();
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

		final I18NTextField i18NTextField = new I18NTextField();

		final Object[] params = new Object[] { 1, 2, 3 };

		i18NTextField.setCaptionMessage(TEST_KEY_3, params);

		performTest(i18NTextField, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return i18NTextField.getCaption();
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

		final I18NTextField i18NTextField = new I18NTextField(TEST_KEY_1);

		i18NTextField.setRequiredErrorMessage(TEST_KEY_2);

		performTest(i18NTextField, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return i18NTextField.getRequiredError();
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