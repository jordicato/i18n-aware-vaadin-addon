package com.opnworks.vaadin.i18n.ui;

import org.junit.Test;

import com.opnworks.vaadin.i18n.AbstractI18NTest;

/**
 * The I18NEmbedded Unit Tests
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NEmbeddedTest  extends AbstractI18NTest {

	@Test
	public void testConstructorCaptionKey() {

		final I18NEmbedded i18NEmbedded = new I18NEmbedded(TEST_KEY_1, null);

		performTest(i18NEmbedded, new I18NAwareTest() {

			public String getActualValue() {
				return i18NEmbedded.getCaption();
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

		final I18NEmbedded i18NEmbedded = new I18NEmbedded();

		i18NEmbedded.setCaptionMessage(TEST_KEY_2);

		performTest(i18NEmbedded, new I18NAwareTest() {

			public String getActualValue() {
				return i18NEmbedded.getCaption();
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

		final I18NEmbedded i18NEmbedded = new I18NEmbedded();

		final Object[] params = new Object[] { 1, 2, 3 };

		i18NEmbedded.setCaptionMessage(TEST_KEY_3, params);

		performTest(i18NEmbedded, new I18NAwareTest() {

			public String getActualValue() {
				return i18NEmbedded.getCaption();
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