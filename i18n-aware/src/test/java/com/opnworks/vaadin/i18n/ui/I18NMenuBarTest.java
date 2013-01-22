package com.opnworks.vaadin.i18n.ui;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.opnworks.vaadin.i18n.AbstractI18NTest;
import com.opnworks.vaadin.i18n.ui.I18NMenuBar.I18NMenuItem;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;

/**
 * The I18NMenuBar Unit Tests
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NMenuBarTest extends AbstractI18NTest {

	@SuppressWarnings("serial")
	private MenuBar.Command testMenuBarCommand = new MenuBar.Command() {
		@Override
		public void menuSelected(MenuItem selectedItem) {
		}
	};

	@Test
	public void testAddItem() {

		final I18NMenuBar i18NMenuBar = new I18NMenuBar();

		final MenuBar.MenuItem menuItem = i18NMenuBar.addItem(TEST_KEY_1, testMenuBarCommand);

		assertThat(menuItem, notNullValue());

		assertThat(menuItem, instanceOf(I18NMenuItem.class));

		performTest(i18NMenuBar, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return menuItem.getText();
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
	public void testAddItemBefore() {

		final I18NMenuBar i18NMenuBar = new I18NMenuBar();

		MenuBar.MenuItem itemToAddBefore = i18NMenuBar.addItem(TEST_KEY_1, testMenuBarCommand);

		final MenuBar.MenuItem menuItem = i18NMenuBar.addItemBefore(TEST_KEY_2, null, testMenuBarCommand, itemToAddBefore);

		assertThat(menuItem, notNullValue());

		assertThat(menuItem, instanceOf(I18NMenuItem.class));

		performTest(i18NMenuBar, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return menuItem.getText();
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
	public void testSetCaptionKey() {

		final I18NMenuBar i18NMenuBar = new I18NMenuBar();

		i18NMenuBar.setCaptionMessage(TEST_KEY_2);

		performTest(i18NMenuBar, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return i18NMenuBar.getCaption();
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

		final I18NMenuBar i18NMenuBar = new I18NMenuBar();

		final Object[] params = new Object[] { 1, 2, 3 };

		i18NMenuBar.setCaptionMessage(TEST_KEY_3, params);

		performTest(i18NMenuBar, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return i18NMenuBar.getCaption();
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
	public void testSubAddItem() {

		final I18NMenuBar i18NMenuBar = new I18NMenuBar();

		final MenuBar.MenuItem menuItem = i18NMenuBar.addItem(TEST_KEY_1, testMenuBarCommand);

		final MenuBar.MenuItem subMenuItem = menuItem.addItem(TEST_KEY_2, testMenuBarCommand);

		assertThat(subMenuItem, notNullValue());

		assertThat(subMenuItem, instanceOf(I18NMenuItem.class));

		performTest(i18NMenuBar, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return subMenuItem.getText();
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
	public void testSubAddItemBefore() {

		final I18NMenuBar i18NMenuBar = new I18NMenuBar();

		final MenuBar.MenuItem menuItem = i18NMenuBar.addItem(TEST_KEY_1, testMenuBarCommand);

		MenuBar.MenuItem itemToAddBefore = i18NMenuBar.addItem(TEST_KEY_2, testMenuBarCommand);

		final MenuBar.MenuItem subMenuItem = menuItem.addItemBefore(TEST_KEY_3, null, testMenuBarCommand, itemToAddBefore);

		assertThat(subMenuItem, notNullValue());

		assertThat(subMenuItem, instanceOf(I18NMenuItem.class));

		performTest(i18NMenuBar, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return subMenuItem.getText();
			}

			@Override
			public String getKey() {
				return TEST_KEY_3;
			}

			@Override
			public Object[] getParams() {
				return null;
			}
		});

	}

	@Test
	public void testSubSubSubAddItem() {

		final I18NMenuBar i18NMenuBar = new I18NMenuBar();

		final MenuBar.MenuItem menuItem = i18NMenuBar.addItem(TEST_KEY_1, testMenuBarCommand);

		final MenuBar.MenuItem subMenuItem = menuItem.addItem(TEST_KEY_1, testMenuBarCommand);
		final MenuBar.MenuItem subSubMenuItem = subMenuItem.addItem(TEST_KEY_1, testMenuBarCommand);
		final MenuBar.MenuItem subSubSubMenuItem = subSubMenuItem.addItem(TEST_KEY_2, testMenuBarCommand);

		assertThat(subSubSubMenuItem, notNullValue());

		assertThat(subSubSubMenuItem, instanceOf(I18NMenuItem.class));

		performTest(i18NMenuBar, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return subSubSubMenuItem.getText();
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
