package com.opnworks.vaadin.i18n.ui;

import org.junit.Test;

import com.opnworks.vaadin.i18n.AbstractI18NTest;

/**
 * The I18NAbsoluteLayout Unit Tests
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NAbsoluteLayoutTest extends AbstractI18NTest {

	@Test
	public void testAddComponent() {

		final I18NAbsoluteLayout i18NAbsoluteLayout = new I18NAbsoluteLayout();

		final I18NButton component = new I18NButton(TEST_KEY_1);

		i18NAbsoluteLayout.addComponent(component);

		performTest(i18NAbsoluteLayout, new I18NAwareTest() {

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
