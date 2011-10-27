package com.opnworks.vaadin.i18n.ui;

import org.junit.Test;

import com.opnworks.vaadin.i18n.AbstractI18NTest;
import com.opnworks.vaadin.i18n.ui.I18NTextArea;

/**
 * The I18NTextArea Unit Tests
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NTextAreaTest extends AbstractI18NTest {

	@Test
	public void testConstructorCaptionKey() {

		final I18NTextArea i18NTextArea = new I18NTextArea(TEST_KEY_1);

		performTest(i18NTextArea, new I18NAwareTest() {

			public String getActualValue() {
				return i18NTextArea.getCaption();
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

		final I18NTextArea i18NTextArea = new I18NTextArea(TEST_KEY_1);

		i18NTextArea.setCaptionKey(TEST_KEY_2);

		performTest(i18NTextArea, new I18NAwareTest() {

			public String getActualValue() {
				return i18NTextArea.getCaption();
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

		final I18NTextArea i18NTextArea = new I18NTextArea(TEST_KEY_3);

		final Object[] params = new Object[] { 1, 2, 3 };

		i18NTextArea.setCaptionParams(params);

		performTest(i18NTextArea, new I18NAwareTest() {

			public String getActualValue() {
				return i18NTextArea.getCaption();
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

		final I18NTextArea i18NTextArea = new I18NTextArea(TEST_KEY_1);

		i18NTextArea.setRequiredErrorKey(TEST_KEY_2);

		performTest(i18NTextArea, new I18NAwareTest() {

			public String getActualValue() {
				return i18NTextArea.getRequiredError();
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