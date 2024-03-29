package com.opnworks.vaadin.i18n.ui;

import org.junit.Test;

import com.opnworks.vaadin.i18n.AbstractI18NTest;

/**
 * The I18NOptionGroup Unit Tests
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NOptionGroupTest extends AbstractI18NTest {

	@Test
	public void testConstructorCaptionKey() {

		final I18NOptionGroup i18NOptionGroup = new I18NOptionGroup(TEST_KEY_1);

		performTest(i18NOptionGroup, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return i18NOptionGroup.getCaption();
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

		final I18NOptionGroup i18NOptionGroup = new I18NOptionGroup(TEST_KEY_1);

		i18NOptionGroup.setCaptionMessage(TEST_KEY_2);

		performTest(i18NOptionGroup, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return i18NOptionGroup.getCaption();
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

		final I18NOptionGroup i18NOptionGroup = new I18NOptionGroup();

		final Object[] params = new Object[] { 1, 2, 3 };

		i18NOptionGroup.setCaptionMessage(TEST_KEY_3, params);

		performTest(i18NOptionGroup, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return i18NOptionGroup.getCaption();
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

	@Test
	public void testSetItemCaptionKey() {

		final I18NOptionGroup i18NOptionGroup = new I18NOptionGroup();

		i18NOptionGroup.addItem("Item1");
		i18NOptionGroup.addItem("Item2");

		i18NOptionGroup.setItemCaptionKey("Item1", TEST_KEY_1);
		i18NOptionGroup.setItemCaptionKey("Item2", TEST_KEY_2);

		performTest(i18NOptionGroup, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return i18NOptionGroup.getItemCaption("Item1");
			}

			@Override
			public String getKey() {
				return TEST_KEY_1;
			}

			@Override
			public Object[] getParams() {
				return null;
			}
		}, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return i18NOptionGroup.getItemCaption("Item2");
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
	public void testsetRequiredErrorMessage() {

		final I18NOptionGroup i18NOptionGroup = new I18NOptionGroup(TEST_KEY_1);

		i18NOptionGroup.setRequiredErrorMessage(TEST_KEY_2);

		performTest(i18NOptionGroup, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return i18NOptionGroup.getRequiredError();
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

}