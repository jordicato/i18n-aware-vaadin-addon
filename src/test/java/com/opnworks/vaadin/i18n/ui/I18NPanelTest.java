package com.opnworks.vaadin.i18n.ui;

import org.junit.Test;

import com.opnworks.vaadin.i18n.AbstractI18NTest;
import com.opnworks.vaadin.i18n.ui.I18NButton;
import com.opnworks.vaadin.i18n.ui.I18NVerticalLayout;

/**
 * The I18NPanel Unit Tests
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NPanelTest extends AbstractI18NTest {

	@Test
	public void testConstructorCaptionKey() {

		final I18NPanel i18NPanel = new I18NPanel(TEST_KEY_1);

		performTest(i18NPanel, new I18NAwareTest() {

			public String getActualValue() {
				return i18NPanel.getCaption();
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

		final I18NPanel i18NPanel = new I18NPanel(TEST_KEY_1);

		i18NPanel.setCaptionKey(TEST_KEY_2);

		performTest(i18NPanel, new I18NAwareTest() {

			public String getActualValue() {
				return i18NPanel.getCaption();
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
	public void testSetContent() {

		final I18NPanel i18NPanel = new I18NPanel();

		I18NVerticalLayout component = new I18NVerticalLayout();

		final I18NButton button = new I18NButton(TEST_KEY_1);
		
		component.addComponent(button);

		i18NPanel.setContent(component);

		performTest(i18NPanel, new I18NAwareTest() {

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

		final I18NPanel i18NPanel = new I18NPanel();

		I18NVerticalLayout component = new I18NVerticalLayout();

		final I18NButton button = new I18NButton(TEST_KEY_1);
		
		component.addComponent(button);

		i18NPanel.addComponent(component);

		performTest(i18NPanel, new I18NAwareTest() {

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