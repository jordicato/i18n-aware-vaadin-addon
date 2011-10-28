package com.opnworks.vaadin.i18n.ui;

import org.junit.Test;

import com.opnworks.vaadin.i18n.AbstractI18NTest;
import com.opnworks.vaadin.i18n.ui.I18NCheckBox;

/**
 * The I18NCheckBox Unit Tests
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NCheckBoxTest extends AbstractI18NTest {

	@Test
	public void testConstructorCaptionKey() {

		final I18NCheckBox i18NCheckBox = new I18NCheckBox(TEST_KEY_1);

		performTest(i18NCheckBox, new I18NAwareTest() {

			public String getActualValue() {
				return i18NCheckBox.getCaption();
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

		final I18NCheckBox i18NCheckBox = new I18NCheckBox(TEST_KEY_1);

		i18NCheckBox.setCaptionKey(TEST_KEY_2);

		performTest(i18NCheckBox, new I18NAwareTest() {

			public String getActualValue() {
				return i18NCheckBox.getCaption();
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

		final I18NCheckBox i18NCheckBox = new I18NCheckBox(TEST_KEY_3);

		final Object[] params = new Object[] { 1, 2, 3 };

		i18NCheckBox.setCaptionParams(params);

		performTest(i18NCheckBox, new I18NAwareTest() {

			public String getActualValue() {
				return i18NCheckBox.getCaption();
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

		final I18NCheckBox i18NCheckBox = new I18NCheckBox(TEST_KEY_1);

		i18NCheckBox.setRequiredErrorKey(TEST_KEY_2);

		performTest(i18NCheckBox, new I18NAwareTest() {

			public String getActualValue() {
				return i18NCheckBox.getRequiredError();
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