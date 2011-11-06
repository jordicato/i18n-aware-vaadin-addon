package com.opnworks.vaadin.i18n.ui;

import org.junit.Test;

import com.opnworks.vaadin.i18n.AbstractI18NTest;

/**
 * The I18NListSelect Unit Tests
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NListSelectTest extends AbstractI18NTest {

	@Test
	public void testConstructorCaptionKey() {

		final I18NListSelect i18NListSelect = new I18NListSelect(TEST_KEY_1);

		performTest(i18NListSelect, new I18NAwareTest() {

			public String getActualValue() {
				return i18NListSelect.getCaption();
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

		final I18NListSelect i18NListSelect = new I18NListSelect(TEST_KEY_1);

		i18NListSelect.setCaptionKey(TEST_KEY_2);

		performTest(i18NListSelect, new I18NAwareTest() {

			public String getActualValue() {
				return i18NListSelect.getCaption();
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

		final I18NListSelect i18NListSelect = new I18NListSelect(TEST_KEY_3);

		final Object[] params = new Object[] { 1, 2, 3 };

		i18NListSelect.setCaptionParams(params);

		performTest(i18NListSelect, new I18NAwareTest() {

			public String getActualValue() {
				return i18NListSelect.getCaption();
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

		final I18NListSelect i18NListSelect = new I18NListSelect(TEST_KEY_1);

		i18NListSelect.setRequiredErrorKey(TEST_KEY_2);

		performTest(i18NListSelect, new I18NAwareTest() {

			public String getActualValue() {
				return i18NListSelect.getRequiredError();
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