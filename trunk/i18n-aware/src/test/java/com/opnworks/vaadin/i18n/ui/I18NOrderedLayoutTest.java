package com.opnworks.vaadin.i18n.ui;

import org.junit.Test;

import com.opnworks.vaadin.i18n.AbstractI18NTest;

/**
 * The I18NOrderedLayout Unit Tests
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NOrderedLayoutTest extends AbstractI18NTest {

	@SuppressWarnings("deprecation")
	@Test
	public void testAddComponent() {

		final I18NOrderedLayout i18NOrderedLayout = new I18NOrderedLayout();

		final I18NButton component = new I18NButton(TEST_KEY_1);

		i18NOrderedLayout.addComponent(component);

		performTest(i18NOrderedLayout, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return component.getCaption();
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

}
