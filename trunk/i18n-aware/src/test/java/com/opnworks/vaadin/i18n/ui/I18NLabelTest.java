package com.opnworks.vaadin.i18n.ui;

import org.junit.Test;

import com.opnworks.vaadin.i18n.AbstractI18NTest;

/**
 * The I18NLabel Unit Tests
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NLabelTest extends AbstractI18NTest {

	@Test
	public void testConstructorValueKey() {

		final I18NLabel i18NI18NLabel = new I18NLabel();

		i18NI18NLabel.setValueMessage(TEST_KEY_1);

		performTest(i18NI18NLabel, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return (String) i18NI18NLabel.getValue();
			}

			@Override
			public String getKey() {
				return TEST_KEY_1;
			}

			@Override
			public Object[] getParams() {
				return null;
			}
		});
	}

	@Test
	public void testSetCaptionKey() {

		final I18NLabel i18NI18NLabel = new I18NLabel(TEST_KEY_1);

		i18NI18NLabel.setCaptionMessage(TEST_KEY_2);

		performTest(i18NI18NLabel, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return i18NI18NLabel.getCaption();
			}

			@Override
			public String getKey() {
				return TEST_KEY_2;
			}

			@Override
			public Object[] getParams() {
				return null;
			}
		});

	}

	@Test
	public void testSetCaptionParams() {

		final I18NLabel i18NI18NLabel = new I18NLabel();

		final Object[] params = new Object[] { 1, 2, 3 };

		i18NI18NLabel.setCaptionMessage(TEST_KEY_3, params);

		performTest(i18NI18NLabel, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return i18NI18NLabel.getCaption();
			}

			@Override
			public String getKey() {
				return TEST_KEY_3;
			}

			@Override
			public Object[] getParams() {
				return params;
			}
		});
	}

	@Test
	public void testSetValueKey() {

		final I18NLabel i18NI18NLabel = new I18NLabel(TEST_KEY_1);

		i18NI18NLabel.setValueMessage(TEST_KEY_2);

		performTest(i18NI18NLabel, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return (String) i18NI18NLabel.getValue();
			}

			@Override
			public String getKey() {
				return TEST_KEY_2;
			}

			@Override
			public Object[] getParams() {
				return null;
			}
		});

	}

}
