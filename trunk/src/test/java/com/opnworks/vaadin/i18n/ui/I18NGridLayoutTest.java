package com.opnworks.vaadin.i18n.ui;

import org.junit.Test;

import com.opnworks.vaadin.i18n.AbstractI18NTest;
import com.opnworks.vaadin.i18n.ui.I18NButton;

/**
 * The I18NGridLayout Unit Tests
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NGridLayoutTest extends AbstractI18NTest {

	@Test
	public void testAddComponent1() {

		final I18NGridLayout i18NGridLayout = new I18NGridLayout(3, 3);

		final I18NButton component = new I18NButton(TEST_KEY_1);

		i18NGridLayout.addComponent(component);

		performTest(i18NGridLayout, new I18NAwareTest() {

			public String getActualValue() {
				return component.getCaption();
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
	public void testAddComponent2() {

		final I18NGridLayout i18NGridLayout = new I18NGridLayout(3, 3);

		final I18NButton component = new I18NButton(TEST_KEY_1);

		i18NGridLayout.addComponent(component, 1, 1);

		performTest(i18NGridLayout, new I18NAwareTest() {

			public String getActualValue() {
				return component.getCaption();
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
	public void testAddComponent3() {

		final I18NGridLayout i18NGridLayout = new I18NGridLayout(3, 3);

		final I18NButton component = new I18NButton(TEST_KEY_1);

		i18NGridLayout.addComponent(component, 1, 1, 2, 2);

		performTest(i18NGridLayout, new I18NAwareTest() {

			public String getActualValue() {
				return component.getCaption();
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
