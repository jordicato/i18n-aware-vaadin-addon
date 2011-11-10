package com.opnworks.vaadin.i18n.ui;

import org.junit.Test;

import com.opnworks.vaadin.i18n.AbstractI18NTest;

/**
 * The I18NWindow Unit Tests
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NWindowTest extends AbstractI18NTest {

	@Test
	public void testConstructorCaptionKey() {

		final I18NWindow i18NWindow = new I18NWindow(TEST_KEY_1);

		performTest(i18NWindow, new I18NAwareTest() {

			public String getActualValue() {
				return i18NWindow.getCaption();
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

		final I18NWindow i18NWindow = new I18NWindow(TEST_KEY_1);

		i18NWindow.setCaptionMessage(TEST_KEY_2);

		performTest(i18NWindow, new I18NAwareTest() {

			public String getActualValue() {
				return i18NWindow.getCaption();
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

		final I18NWindow i18NWindow = new I18NWindow();

		final Object[] params = new Object[] { 1, 2, 3 };

		i18NWindow.setCaptionMessage(TEST_KEY_3, params);

		performTest(i18NWindow, new I18NAwareTest() {

			public String getActualValue() {
				return i18NWindow.getCaption();
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
	public void testSetContent() {

		final I18NWindow i18NWindow = new I18NWindow(TEST_KEY_2);

		I18NVerticalLayout component = new I18NVerticalLayout();

		final I18NButton button = new I18NButton(TEST_KEY_1);
		
		component.addComponent(button);

		i18NWindow.setContent(component);

		performTest(i18NWindow, new I18NAwareTest() {

			public String getActualValue() {
				return button.getCaption();
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
	public void testAddComponent() {

		final I18NWindow i18NWindow = new I18NWindow(TEST_KEY_2);

		I18NVerticalLayout component = new I18NVerticalLayout();

		final I18NButton button = new I18NButton(TEST_KEY_1);
		
		component.addComponent(button);

		i18NWindow.addComponent(component);

		performTest(i18NWindow, new I18NAwareTest() {

			public String getActualValue() {
				return button.getCaption();
			}

			public String getKey() {
				return TEST_KEY_1;
			}

			public Object[] getParams() {
				return null;
			}
		});
	}

}
