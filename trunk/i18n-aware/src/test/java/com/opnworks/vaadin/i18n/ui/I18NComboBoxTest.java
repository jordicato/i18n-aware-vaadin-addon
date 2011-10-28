package com.opnworks.vaadin.i18n.ui;

import org.junit.Test;

import com.opnworks.vaadin.i18n.AbstractI18NTest;
import com.opnworks.vaadin.i18n.ui.I18NComboBox;

/**
 * The I18NComboBox Unit Tests
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NComboBoxTest extends AbstractI18NTest {

	@Test
	public void testConstructorCaptionKey() {

		final I18NComboBox i18NComboBox = new I18NComboBox(TEST_KEY_1);

		performTest(i18NComboBox, new I18NAwareTest() {

			public String getActualValue() {
				return i18NComboBox.getCaption();
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

		final I18NComboBox i18NComboBox = new I18NComboBox(TEST_KEY_1);

		i18NComboBox.setCaptionKey(TEST_KEY_2);

		performTest(i18NComboBox, new I18NAwareTest() {

			public String getActualValue() {
				return i18NComboBox.getCaption();
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

		final I18NComboBox i18NComboBox = new I18NComboBox(TEST_KEY_3);

		final Object[] params = new Object[] { 1, 2, 3 };

		i18NComboBox.setCaptionParams(params);

		performTest(i18NComboBox, new I18NAwareTest() {

			public String getActualValue() {
				return i18NComboBox.getCaption();
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

		final I18NComboBox i18NComboBox = new I18NComboBox(TEST_KEY_1);

		i18NComboBox.setRequiredErrorKey(TEST_KEY_2);

		performTest(i18NComboBox, new I18NAwareTest() {

			public String getActualValue() {
				return i18NComboBox.getRequiredError();
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