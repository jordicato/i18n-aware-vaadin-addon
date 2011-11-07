package com.opnworks.vaadin.i18n.ui;

import org.junit.Test;

import com.opnworks.vaadin.i18n.AbstractI18NTest;

/**
 * The I18NTwinColSelect Unit Tests
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NTwinColSelectTest extends AbstractI18NTest {

	@Test
	public void testSetLeftColumnCaptionKeyAndParams() {

		final I18NTwinColSelect i18NTwinColSelect = new I18NTwinColSelect();

		final Object[] params = new Object[] { 1, 2, 3 };

		i18NTwinColSelect.setLeftColumnCaptionKey(TEST_KEY_3, params);

		performTest(i18NTwinColSelect, new I18NAwareTest() {

			public String getActualValue() {
				return i18NTwinColSelect.getLeftColumnCaption();
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
	public void testSetRightColumnCaptionKeyAndParams() {

		final I18NTwinColSelect i18NTwinColSelect = new I18NTwinColSelect();

		final Object[] params = new Object[] { 1, 2, 3 };

		i18NTwinColSelect.setRightColumnCaptionKey(TEST_KEY_3, params);

		performTest(i18NTwinColSelect, new I18NAwareTest() {

			public String getActualValue() {
				return i18NTwinColSelect.getRightColumnCaption();
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
	public void testConstructorCaptionKey() {

		final I18NTwinColSelect i18NTwinColSelect = new I18NTwinColSelect(
				TEST_KEY_1);

		performTest(i18NTwinColSelect, new I18NAwareTest() {

			public String getActualValue() {
				return i18NTwinColSelect.getCaption();
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

		final I18NTwinColSelect i18NTwinColSelect = new I18NTwinColSelect(
				TEST_KEY_1);

		i18NTwinColSelect.setCaptionMessage(TEST_KEY_2);

		performTest(i18NTwinColSelect, new I18NAwareTest() {

			public String getActualValue() {
				return i18NTwinColSelect.getCaption();
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

		final I18NTwinColSelect i18NTwinColSelect = new I18NTwinColSelect();

		final Object[] params = new Object[] { 1, 2, 3 };

		i18NTwinColSelect.setCaptionMessage(TEST_KEY_3, params);

		performTest(i18NTwinColSelect, new I18NAwareTest() {

			public String getActualValue() {
				return i18NTwinColSelect.getCaption();
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

		final I18NTwinColSelect i18NTwinColSelect = new I18NTwinColSelect(
				TEST_KEY_1);

		i18NTwinColSelect.setRequiredErrorMessage(TEST_KEY_2);

		performTest(i18NTwinColSelect, new I18NAwareTest() {

			public String getActualValue() {
				return i18NTwinColSelect.getRequiredError();
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