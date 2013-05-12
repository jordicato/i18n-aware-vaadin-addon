package com.opnworks.vaadin.i18n.ui;

import org.junit.Test;

import com.opnworks.vaadin.i18n.AbstractI18NTest;

/**
 * The I18NRichTextArea Unit Tests
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NRichTextAreaTest extends AbstractI18NTest {

	@Test
	public void testConstructorCaptionKey() {

		final I18NRichTextArea i18NRichTextArea = new I18NRichTextArea(TEST_KEY_1);

		performTest(i18NRichTextArea, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return i18NRichTextArea.getCaption();
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

		final I18NRichTextArea i18NRichTextArea = new I18NRichTextArea(TEST_KEY_1);

		i18NRichTextArea.setCaptionMessage(TEST_KEY_2);

		performTest(i18NRichTextArea, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return i18NRichTextArea.getCaption();
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

		final I18NRichTextArea i18NRichTextArea = new I18NRichTextArea();

		final Object[] params = new Object[] { 1, 2, 3 };

		i18NRichTextArea.setCaptionMessage(TEST_KEY_3, params);

		performTest(i18NRichTextArea, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return i18NRichTextArea.getCaption();
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
	public void testSetValueKey() {

		final I18NRichTextArea i18NRichTextArea = new I18NRichTextArea();

		i18NRichTextArea.setValue(TEST_KEY_1);

		i18NRichTextArea.setValueMessage(TEST_KEY_2);

		performTest(i18NRichTextArea, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return i18NRichTextArea.getValue();
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
	public void testSetValueParams() {

		final I18NRichTextArea i18NRichTextArea = new I18NRichTextArea();

		final Object[] params = new Object[] { 1, 2, 3 };

		i18NRichTextArea.setValueMessage(TEST_KEY_3, params);

		performTest(i18NRichTextArea, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return i18NRichTextArea.getValue();
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

		final I18NRichTextArea i18NRichTextArea = new I18NRichTextArea(TEST_KEY_1);

		i18NRichTextArea.setRequiredErrorMessage(TEST_KEY_2);

		performTest(i18NRichTextArea, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return i18NRichTextArea.getRequiredError();
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