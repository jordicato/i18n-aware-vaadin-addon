package com.opnworks.vaadin.i18n.ui;

import org.junit.Test;

import com.opnworks.vaadin.i18n.AbstractI18NTest;

/**
 * The I18NTree Unit Tests
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NTreeTest extends AbstractI18NTest {

	@Test
	public void testConstructorCaptionKey() {

		final I18NTree i18NTree = new I18NTree(TEST_KEY_1);

		performTest(i18NTree, new I18NAwareTest() {

			public String getActualValue() {
				return i18NTree.getCaption();
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

		final I18NTree i18NTree = new I18NTree(TEST_KEY_1);

		i18NTree.setCaptionMessage(TEST_KEY_2);

		performTest(i18NTree, new I18NAwareTest() {

			public String getActualValue() {
				return i18NTree.getCaption();
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

		final I18NTree i18NTree = new I18NTree();

		final Object[] params = new Object[] { 1, 2, 3 };

		i18NTree.setCaptionMessage(TEST_KEY_3, params);

		performTest(i18NTree, new I18NAwareTest() {

			public String getActualValue() {
				return i18NTree.getCaption();
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
	public void testsetRequiredErrorMessage() {

		final I18NTree i18NTree = new I18NTree(TEST_KEY_1);

		i18NTree.setRequiredErrorMessage(TEST_KEY_2);

		performTest(i18NTree, new I18NAwareTest() {

			public String getActualValue() {
				return i18NTree.getRequiredError();
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