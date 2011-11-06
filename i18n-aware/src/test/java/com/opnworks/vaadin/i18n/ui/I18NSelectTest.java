package com.opnworks.vaadin.i18n.ui;

import org.junit.Test;

import com.opnworks.vaadin.i18n.AbstractI18NTest;

/**
 * The I18NSelect Unit Tests
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NSelectTest extends AbstractI18NTest {

	@Test
	public void testConstructorCaptionKey() {

		final I18NSelect i18NSelect = new I18NSelect(TEST_KEY_1);

		performTest(i18NSelect, new I18NAwareTest() {

			public String getActualValue() {
				return i18NSelect.getCaption();
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

		final I18NSelect i18NSelect = new I18NSelect(TEST_KEY_1);

		i18NSelect.setCaptionKey(TEST_KEY_2);

		performTest(i18NSelect, new I18NAwareTest() {

			public String getActualValue() {
				return i18NSelect.getCaption();
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

		final I18NSelect i18NSelect = new I18NSelect(TEST_KEY_3);

		final Object[] params = new Object[] { 1, 2, 3 };

		i18NSelect.setCaptionParams(params);

		performTest(i18NSelect, new I18NAwareTest() {

			public String getActualValue() {
				return i18NSelect.getCaption();
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

		final I18NSelect i18NSelect = new I18NSelect(TEST_KEY_1);

		i18NSelect.setRequiredErrorKey(TEST_KEY_2);

		performTest(i18NSelect, new I18NAwareTest() {

			public String getActualValue() {
				return i18NSelect.getRequiredError();
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