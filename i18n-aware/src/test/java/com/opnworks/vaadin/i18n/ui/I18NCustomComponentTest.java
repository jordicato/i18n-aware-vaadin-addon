package com.opnworks.vaadin.i18n.ui;

import org.junit.Test;

import com.opnworks.vaadin.i18n.AbstractI18NTest;

/**
 * The CustomComponent Unit Tests
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NCustomComponentTest extends AbstractI18NTest {

	@Test
	public void testConstructorWithCompositionRoot() {

		final I18NButton component = new I18NButton(TEST_KEY_1);

		@SuppressWarnings("serial")
		class MyCustomComponent extends I18NCustomComponent {
			public MyCustomComponent() {
				super(component);
			}
		}

		final MyCustomComponent myCustomComponent = new MyCustomComponent();

		performTest(myCustomComponent, new I18NAwareTest() {

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

	@Test
	public void testSetCompositionRoot() {

		final I18NButton component = new I18NButton(TEST_KEY_1);

		@SuppressWarnings("serial")
		class MyCustomComponent extends I18NCustomComponent {
			public MyCustomComponent() {
				setCompositionRoot(component);
			}
		}

		final MyCustomComponent myCustomComponent = new MyCustomComponent();

		performTest(myCustomComponent, new I18NAwareTest() {

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
