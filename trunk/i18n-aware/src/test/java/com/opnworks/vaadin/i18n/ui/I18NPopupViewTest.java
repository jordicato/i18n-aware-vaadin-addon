package com.opnworks.vaadin.i18n.ui;

import org.junit.Test;

import com.opnworks.vaadin.i18n.AbstractI18NTest;
import com.opnworks.vaadin.i18n.I18NAwareComponent;
import com.opnworks.vaadin.i18n.I18NService;
import com.vaadin.ui.Component;
import com.vaadin.ui.PopupView;

/**
 * The PopupView Unit Tests
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NPopupViewTest extends AbstractI18NTest {

	@Test
	public void testConstructorWithI18NPopupViewContent() {

		final I18NButton component = new I18NButton(TEST_KEY_1);

		@SuppressWarnings("serial")
		final I18NPopupView.I18NContent content = new I18NPopupView.I18NContent() {
			@Override
			public String getI18NMinimizedValueAsHTML(I18NService i18nService) {
				return i18nService.getMessage(TEST_KEY_2);
			}

			@Override
			public I18NAwareComponent getI18nPopupComponent() {
				return component;
			}
		};

		final I18NPopupView i18NPopupView = new I18NPopupView(content);

		performTest(i18NPopupView, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return content.getMinimizedValueAsHTML();
			}

			@Override
			public String getKey() {
				return TEST_KEY_2;
			}

			@Override
			public Object[] getParams() {
				return null;
			}
		}, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return content.getPopupComponent().getCaption();
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
	public void testConstructorWithPopupViewContent() {

		final I18NButton component = new I18NButton(TEST_KEY_1);

		@SuppressWarnings("serial")
		PopupView.Content content = new PopupView.Content() {
			@Override
			public String getMinimizedValueAsHTML() {
				return "ABC";
			}

			@Override
			public Component getPopupComponent() {
				return component;
			}
		};

		final I18NPopupView i18NPopupView = new I18NPopupView(content);

		performTest(i18NPopupView, new I18NAwareTest() {

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
	public void testConstructorWithSmallLarge() {

		final I18NButton component = new I18NButton(TEST_KEY_1);

		final I18NPopupView i18NPopupView = new I18NPopupView("ABC", component);

		performTest(i18NPopupView, new I18NAwareTest() {

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
	public void testSetCaptionKey() {

		final I18NPopupView i18NPopupView = new I18NPopupView(null, null);

		i18NPopupView.setCaptionMessage(TEST_KEY_2);

		performTest(i18NPopupView, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return i18NPopupView.getCaption();
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

		final I18NPopupView i18NPopupView = new I18NPopupView(null, null);

		final Object[] params = new Object[] { 1, 2, 3 };

		i18NPopupView.setCaptionMessage(TEST_KEY_3, params);

		performTest(i18NPopupView, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return i18NPopupView.getCaption();
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