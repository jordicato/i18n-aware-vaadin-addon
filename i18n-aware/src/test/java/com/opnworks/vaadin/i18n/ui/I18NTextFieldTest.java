package com.opnworks.vaadin.i18n.ui;

import org.junit.Test;

import com.opnworks.vaadin.i18n.AbstractI18NTest;
import com.opnworks.vaadin.i18n.ui.I18NTextField;

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

			public String getActualValue() {
				return i18NTextField.getCaption();
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

		final I18NTextField i18NTextField = new I18NTextField(TEST_KEY_1);

		i18NTextField.setCaptionKey(TEST_KEY_2);

		performTest(i18NTextField, new I18NAwareTest() {

			public String getActualValue() {
				return i18NTextField.getCaption();
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

		final I18NTextField i18NTextField = new I18NTextField(TEST_KEY_3);

		final Object[] params = new Object[] { 1, 2, 3 };

		i18NTextField.setCaptionParams(params);

		performTest(i18NTextField, new I18NAwareTest() {

			public String getActualValue() {
				return i18NTextField.getCaption();
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

		final I18NTextField i18NTextField = new I18NTextField(TEST_KEY_1);

		i18NTextField.setRequiredErrorKey(TEST_KEY_2);

		performTest(i18NTextField, new I18NAwareTest() {

			public String getActualValue() {
				return i18NTextField.getRequiredError();
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