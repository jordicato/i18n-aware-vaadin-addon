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

		final I18NRichTextArea i18NRichTextArea = new I18NRichTextArea(
				TEST_KEY_1);

		performTest(i18NRichTextArea, new I18NAwareTest() {

			public String getActualValue() {
				return i18NRichTextArea.getCaption();
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

		final I18NRichTextArea i18NRichTextArea = new I18NRichTextArea(
				TEST_KEY_1);

		i18NRichTextArea.setCaptionMessage(TEST_KEY_2);

		performTest(i18NRichTextArea, new I18NAwareTest() {

			public String getActualValue() {
				return i18NRichTextArea.getCaption();
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

		final I18NRichTextArea i18NRichTextArea = new I18NRichTextArea();

		final Object[] params = new Object[] { 1, 2, 3 };

		i18NRichTextArea.setCaptionMessage(TEST_KEY_3, params);

		performTest(i18NRichTextArea, new I18NAwareTest() {

			public String getActualValue() {
				return i18NRichTextArea.getCaption();
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

		final I18NRichTextArea i18NRichTextArea = new I18NRichTextArea(
				TEST_KEY_1);

		i18NRichTextArea.setRequiredErrorMessage(TEST_KEY_2);

		performTest(i18NRichTextArea, new I18NAwareTest() {

			public String getActualValue() {
				return i18NRichTextArea.getRequiredError();
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