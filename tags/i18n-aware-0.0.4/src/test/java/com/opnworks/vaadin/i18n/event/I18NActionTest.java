package com.opnworks.vaadin.i18n.event;

import org.junit.Test;

import com.opnworks.vaadin.i18n.AbstractI18NTest;
import com.opnworks.vaadin.i18n.event.I18NAction;

/**
 * The I18NAction Unit Tests
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NActionTest extends AbstractI18NTest {

	@Test
	public void testConstructorCaptionKey() {

		final I18NAction i18NAction = new I18NAction(TEST_KEY_1);

		performTest(i18NAction, new I18NAwareTest() {

			public String getActualValue() {
				return i18NAction.getCaption();
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

		final I18NAction i18NAction = new I18NAction(TEST_KEY_1);

		i18NAction.setCaptionKey(TEST_KEY_2);

		performTest(i18NAction, new I18NAwareTest() {

			public String getActualValue() {
				return i18NAction.getCaption();
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

		final I18NAction i18NAction = new I18NAction(TEST_KEY_3);

		final Object[] params = new Object[] { 1, 2, 3 };

		i18NAction.setCaptionParams(params);

		performTest(i18NAction, new I18NAwareTest() {

			public String getActualValue() {
				return i18NAction.getCaption();
			}

			public String getKey() {
				return TEST_KEY_3;
			}

			public Object[] getParams() {
				return params;
			}
		});
	}

}