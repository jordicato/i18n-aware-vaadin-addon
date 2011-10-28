package com.opnworks.vaadin.i18n.ui;

import org.junit.Test;

import com.opnworks.vaadin.i18n.AbstractI18NTest;
import com.opnworks.vaadin.i18n.ui.I18NLabel;

/**
 * The I18NLabel Unit Tests
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NLabelTest extends AbstractI18NTest {

	@Test
	public void testConstructorValueKey() {

		final I18NLabel i18NLabel = new I18NLabel();

		i18NLabel.setValueKey(TEST_KEY_1);
		
		performTest(i18NLabel, new I18NAwareTest() {

			public String getActualValue() {
				return (String)i18NLabel.getValue();
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

		final I18NLabel i18NLabel = new I18NLabel(TEST_KEY_1);

		i18NLabel.setCaptionKey(TEST_KEY_2);

		performTest(i18NLabel, new I18NAwareTest() {

			public String getActualValue() {
				return i18NLabel.getCaption();
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

		final I18NLabel i18NLabel = new I18NLabel();

		i18NLabel.setCaptionKey(TEST_KEY_3);
		
		final Object[] params = new Object[] { 1, 2, 3 };

		i18NLabel.setCaptionParams(params);

		performTest(i18NLabel, new I18NAwareTest() {

			public String getActualValue() {
				return (String)i18NLabel.getCaption();
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
	public void testSetValueKey() {

		final I18NLabel i18NLabel = new I18NLabel(TEST_KEY_1);
		
		i18NLabel.setValueKey(TEST_KEY_2);

		performTest(i18NLabel, new I18NAwareTest() {

			public String getActualValue() {
				return (String)i18NLabel.getValue();
			}

			public String getKey() {
				return TEST_KEY_2;
			}

			public Object[] getParams() {
				return null;
			}
		});

	}
	
}
