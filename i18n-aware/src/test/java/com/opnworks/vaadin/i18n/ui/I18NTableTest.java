package com.opnworks.vaadin.i18n.ui;

import org.junit.Test;

import com.opnworks.vaadin.i18n.AbstractI18NTest;
import com.opnworks.vaadin.i18n.data.util.I18NIndexedContainer;
import com.opnworks.vaadin.i18n.event.I18NAction;
import com.vaadin.data.Item;
import com.vaadin.event.Action;

public class I18NTableTest extends AbstractI18NTest {

	@Test
	public void testConstructorCaptionKey() {

		final I18NTable i18NTable = new I18NTable(TEST_KEY_1);

		performTest(i18NTable, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return i18NTable.getCaption();
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
	public void testI18NAwareAction() {

		final I18NTable i18NTable = new I18NTable();

		final I18NAction testAction = new I18NAction(TEST_KEY_1);

		Action.Handler actionHandler = new Action.Handler() {
			private static final long serialVersionUID = 2901525218606074985L;

			@Override
			public Action[] getActions(Object target, Object sender) {
				return new Action[] { testAction };
			}

			@Override
			public void handleAction(Action action, Object sender, Object target) {
			}
		};

		i18NTable.addActionHandler(actionHandler);

		performTest(i18NTable, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return testAction.getCaption();
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
	public void testI18NAwareItem() {

		final I18NTable i18NTable = new I18NTable();

		final I18NTable component = new I18NTable(TEST_KEY_2);

		I18NIndexedContainer container = new I18NIndexedContainer();

		container.addContainerProperty("A", I18NTable.class, null);

		Item item = container.addItem(1);

		item.getItemProperty("A").setValue(component);

		i18NTable.setContainerDataSource(container);

		i18NTable.setColumnHeadersKeys(new String[] { TEST_KEY_1 });

		performTest(i18NTable, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return component.getCaption();
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

		final I18NTable i18NTable = new I18NTable(TEST_KEY_1);

		i18NTable.setCaptionMessage(TEST_KEY_2);

		performTest(i18NTable, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return i18NTable.getCaption();
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

		final I18NTable i18NTable = new I18NTable();

		final Object[] params = new Object[] { 1, 2, 3 };

		i18NTable.setCaptionMessage(TEST_KEY_3, params);

		performTest(i18NTable, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return i18NTable.getCaption();
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
	public void testSetColumnHeadersKeys() {

		final I18NTable i18NTable = new I18NTable();

		I18NIndexedContainer container = new I18NIndexedContainer();

		container.addContainerProperty("A", String.class, null);

		Item item = container.addItem(1);

		item.getItemProperty("A").setValue("A value");

		i18NTable.setContainerDataSource(container);

		i18NTable.setColumnHeadersKeys(new String[] { TEST_KEY_1 });

		performTest(i18NTable, new I18NAwareTest() {

			@Override
			public String getActualValue() {
				return i18NTable.getColumnHeaders()[0];
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
