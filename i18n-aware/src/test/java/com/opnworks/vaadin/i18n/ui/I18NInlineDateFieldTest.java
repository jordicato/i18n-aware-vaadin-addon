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

		final I18NInlineDateField i18NInlineDateField = new I18NInlineDateField(TEST_KEY_1);

		performTest(i18NInlineDateField, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return i18NInlineDateField.getCaption();
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

		final I18NInlineDateField i18NInlineDateField = new I18NInlineDateField(TEST_KEY_1);

		i18NInlineDateField.setCaptionMessage(TEST_KEY_2);

		performTest(i18NInlineDateField, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return i18NInlineDateField.getCaption();
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

		final I18NInlineDateField i18NInlineDateField = new I18NInlineDateField();

		final Object[] params = new Object[] { 1, 2, 3 };

		i18NInlineDateField.setCaptionMessage(TEST_KEY_3, params);

		performTest(i18NInlineDateField, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return i18NInlineDateField.getCaption();
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

		final I18NInlineDateField i18NInlineDateField = new I18NInlineDateField(TEST_KEY_1);

		i18NInlineDateField.setRequiredErrorMessage(TEST_KEY_2);

		performTest(i18NInlineDateField, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return i18NInlineDateField.getRequiredError();
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
