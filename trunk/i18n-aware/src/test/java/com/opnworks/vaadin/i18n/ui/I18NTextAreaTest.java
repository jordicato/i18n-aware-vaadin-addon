package com.opnworks.vaadin.i18n.ui;

import org.junit.Test;

import com.opnworks.vaadin.i18n.AbstractI18NTest;

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

			@Override
			public String getActualValue() {
				return i18NTextArea.getCaption();
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

		final I18NTextArea i18NTextArea = new I18NTextArea(TEST_KEY_1);

		i18NTextArea.setCaptionMessage(TEST_KEY_2);

		performTest(i18NTextArea, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return i18NTextArea.getCaption();
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

		final I18NTextArea i18NTextArea = new I18NTextArea();

		final Object[] params = new Object[] { 1, 2, 3 };

		i18NTextArea.setCaptionMessage(TEST_KEY_3, params);

		performTest(i18NTextArea, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return i18NTextArea.getCaption();
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

		final I18NTextArea i18NTextArea = new I18NTextArea();
		
		i18NTextArea.setValue(TEST_KEY_1);

		i18NTextArea.setValueMessage(TEST_KEY_2);

		performTest(i18NTextArea, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return i18NTextArea.getValue();
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
	public void testSetValueParams() {

		final I18NTextArea i18NTextArea = new I18NTextArea();

		final Object[] params = new Object[] { 1, 2, 3 };

		i18NTextArea.setValueMessage(TEST_KEY_3, params);

		performTest(i18NTextArea, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return i18NTextArea.getValue();
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
	public void testsetRequiredErrorMessage() {

		final I18NTextArea i18NTextArea = new I18NTextArea(TEST_KEY_1);

		i18NTextArea.setRequiredErrorMessage(TEST_KEY_2);

		performTest(i18NTextArea, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return i18NTextArea.getRequiredError();
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