package com.opnworks.vaadin.i18n.ui;

import org.junit.Test;

import com.opnworks.vaadin.i18n.AbstractI18NTest;

/**
 * The I18NUpload Unit Tests
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NUploadTest extends AbstractI18NTest {

	@Test
	public void testConstructorCaptionKey() {

		final I18NUpload i18NUpload = new I18NUpload(TEST_KEY_1, null);

		performTest(i18NUpload, new I18NAwareTest() {

			public String getActualValue() {
				return i18NUpload.getCaption();
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

		final I18NUpload i18NUpload = new I18NUpload(TEST_KEY_1, null);

		i18NUpload.setCaptionMessage(TEST_KEY_2);

		performTest(i18NUpload, new I18NAwareTest() {

			public String getActualValue() {
				return i18NUpload.getCaption();
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

		final I18NUpload i18NUpload = new I18NUpload(TEST_KEY_1, null);

		final Object[] params = new Object[] { 1, 2, 3 };

		i18NUpload.setCaptionMessage(TEST_KEY_3, params);

		performTest(i18NUpload, new I18NAwareTest() {

			public String getActualValue() {
				return i18NUpload.getCaption();
			}

			public String getKey() {
				return TEST_KEY_3;
			}

			public Object[] getParams() {
				return params;
			}
		});
	}
}