package com.opnworks.vaadin.i18n.ui;

import org.junit.Test;

import com.opnworks.vaadin.i18n.AbstractI18NTest;

/**
 * The I18NSlider Unit Tests
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NSliderTest extends AbstractI18NTest {

	@Test
	public void testConstructorCaptionKey() {

		final I18NSlider i18NSlider = new I18NSlider(TEST_KEY_1);

		performTest(i18NSlider, new I18NAwareTest() {

			public String getActualValue() {
				return i18NSlider.getCaption();
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

		final I18NSlider i18NSlider = new I18NSlider(TEST_KEY_1);

		i18NSlider.setCaptionKey(TEST_KEY_2);

		performTest(i18NSlider, new I18NAwareTest() {

			public String getActualValue() {
				return i18NSlider.getCaption();
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

		final I18NSlider i18NSlider = new I18NSlider(TEST_KEY_3);

		final Object[] params = new Object[] { 1, 2, 3 };

		i18NSlider.setCaptionParams(params);

		performTest(i18NSlider, new I18NAwareTest() {

			public String getActualValue() {
				return i18NSlider.getCaption();
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

		final I18NSlider i18NSlider = new I18NSlider(TEST_KEY_1);

		i18NSlider.setRequiredErrorKey(TEST_KEY_2);

		performTest(i18NSlider, new I18NAwareTest() {

			public String getActualValue() {
				return i18NSlider.getRequiredError();
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