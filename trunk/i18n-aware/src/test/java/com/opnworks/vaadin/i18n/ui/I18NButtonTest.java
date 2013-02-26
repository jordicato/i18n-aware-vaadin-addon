package com.opnworks.vaadin.i18n.ui;

import org.junit.Test;

import com.opnworks.vaadin.i18n.AbstractI18NTest;
import com.vaadin.data.Property;
import com.vaadin.ui.Button.ClickListener;

/**
 * The I18NButton Unit Tests
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NButtonTest extends AbstractI18NTest {

	@Test
	public void testConstructorCaptionKey() {

		final I18NButton i18NButton = new I18NButton(TEST_KEY_1);

		performTest(i18NButton, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return i18NButton.getCaption();
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
	public void testI18NAwareMessagePresent() throws Exception {

		// Constructors
		assertI18NAwareMessagePresent(I18NButton.class, 0, String.class);
		assertI18NAwareMessagePresent(I18NButton.class, 0, String.class, ClickListener.class);

		// Methods
		assertI18NAwareMessagePresent(I18NButton.class, "setCaptionMessage", 0, String.class, Object[].class);
		assertI18NAwareMessagePresent(I18NButton.class, "setDescriptionMessage", 0, String.class, Object[].class);
	}

	@Test
	public void testSetCaptionKey() {

		final I18NButton i18NButton = new I18NButton(TEST_KEY_1);

		i18NButton.setCaptionMessage(TEST_KEY_2);

		performTest(i18NButton, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return i18NButton.getCaption();
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

		final I18NButton i18NButton = new I18NButton();

		final Object[] params = new Object[] { 1, 2, 3 };

		i18NButton.setCaptionMessage(TEST_KEY_3, params);

		performTest(i18NButton, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return i18NButton.getCaption();
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

}
