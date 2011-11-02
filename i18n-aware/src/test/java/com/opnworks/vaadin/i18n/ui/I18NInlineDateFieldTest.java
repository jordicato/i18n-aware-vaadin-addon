package com.opnworks.vaadin.i18n.ui;

import org.junit.Test;

import com.opnworks.vaadin.i18n.AbstractI18NTest;

/**
 * The InlineDateField Unit Tests
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NInlineDateFieldTest extends AbstractI18NTest {

	@Test
	public void testConstructorCaptionKey() {

		final I18NInlineDateField i18NInlineDateField = new I18NInlineDateField(
				TEST_KEY_1);

		performTest(i18NInlineDateField, new I18NAwareTest() {

			public String getActualValue() {
				return i18NInlineDateField.getCaption();
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

		final I18NInlineDateField i18NInlineDateField = new I18NInlineDateField(
				TEST_KEY_1);

		i18NInlineDateField.setCaptionKey(TEST_KEY_2);

		performTest(i18NInlineDateField, new I18NAwareTest() {

			public String getActualValue() {
				return i18NInlineDateField.getCaption();
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

		final I18NInlineDateField i18NInlineDateField = new I18NInlineDateField(
				TEST_KEY_3);

		final Object[] params = new Object[] { 1, 2, 3 };

		i18NInlineDateField.setCaptionParams(params);

		performTest(i18NInlineDateField, new I18NAwareTest() {

			public String getActualValue() {
				return i18NInlineDateField.getCaption();
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

		final I18NInlineDateField i18NInlineDateField = new I18NInlineDateField(
				TEST_KEY_1);

		i18NInlineDateField.setRequiredErrorKey(TEST_KEY_2);

		performTest(i18NInlineDateField, new I18NAwareTest() {

			public String getActualValue() {
				return i18NInlineDateField.getRequiredError();
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
