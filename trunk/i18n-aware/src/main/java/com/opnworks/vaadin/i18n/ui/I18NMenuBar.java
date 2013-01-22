package com.opnworks.vaadin.i18n.ui;

import java.util.List;
import java.util.Locale;

import com.opnworks.vaadin.i18n.I18NAware;
import com.opnworks.vaadin.i18n.I18NAwareCaption;
import com.opnworks.vaadin.i18n.I18NAwareComponent;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.processor.GenerateInstantiateSubclassAspect;
import com.opnworks.vaadin.i18n.support.I18NAwareComponentCaptionSupport;
import com.opnworks.vaadin.i18n.support.I18NAwareSupport;
import com.opnworks.vaadin.i18n.support.I18NAwareValueSupport;
import com.opnworks.vaadin.i18n.support.I18NAwareValueSupport.ValueContainer;
import com.vaadin.terminal.Resource;
import com.vaadin.ui.MenuBar;

/**
 * The I18NMenuBar
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
@GenerateInstantiateSubclassAspect
@SuppressWarnings("serial")
public class I18NMenuBar extends MenuBar implements I18NAwareComponent, I18NAwareCaption {

	public class I18NMenuItem extends MenuItem implements I18NAware {

		private I18NAwareSupport i18nAwareSupport = new I18NAwareSupport();

		private Locale locale;

		private MenuItem delegate;

		private I18NAwareValueSupport i18NTextSupport = new I18NAwareValueSupport(new ValueContainer() {
			@Override
			public void setValue(String value) {
				I18NMenuItem.this.setText(value);
			}
		});

		public I18NMenuItem(@I18NAwareMessage String captionKey, MenuItem delegate) {
			super("dummy", null, null);
			this.delegate = delegate;
			i18NTextSupport.setValueMessage(captionKey);
		}

		@Override
		public MenuBar.MenuItem addItem(@I18NAwareMessage String captionKey, Resource icon, MenuBar.Command command) {

			I18NMenuItem result = new I18NMenuItem(captionKey, delegate.addItem(captionKey, icon, command));

			i18nAwareSupport.add(result);

			return result;
		}

		@Override
		public MenuBar.MenuItem addItemBefore(@I18NAwareMessage String captionKey, Resource icon, MenuBar.Command command,
				MenuBar.MenuItem itemToAddBefore) {

			I18NMenuItem result = new I18NMenuItem(captionKey, delegate.addItemBefore(captionKey, icon, command, itemToAddBefore));

			i18nAwareSupport.add(result);

			return result;
		}

		@Override
		public MenuItem addSeparator() {
			return delegate.addSeparator();
		}

		@Override
		public MenuItem addSeparatorBefore(MenuItem itemToAddBefore) {
			return delegate.addSeparatorBefore(itemToAddBefore);
		}

		@Override
		public boolean equals(Object obj) {
			return delegate.equals(obj);
		}

		@Override
		public List<MenuItem> getChildren() {
			return delegate.getChildren();
		}

		@Override
		public Command getCommand() {
			return delegate.getCommand();
		}

		@Override
		public String getDescription() {
			return delegate.getDescription();
		}

		@Override
		public Resource getIcon() {
			return delegate.getIcon();
		}

		@Override
		public int getId() {
			return delegate.getId();
		}

		@Override
		public Locale getLocale() {
			return locale;
		}

		@Override
		public MenuItem getParent() {
			return delegate.getParent();
		}

		@Override
		public int getSize() {
			return delegate.getSize();
		}

		@Override
		public String getStyleName() {
			return delegate.getStyleName();
		}

		@Override
		public String getText() {
			return delegate.getText();
		}

		@Override
		public boolean hasChildren() {
			return delegate.hasChildren();
		}

		@Override
		public int hashCode() {
			return delegate.hashCode();
		}

		@Override
		public void i18NUpdate(I18NService i18nService) {

			i18NTextSupport.i18NUpdate(i18nService);
			i18nAwareSupport.i18NUpdate(i18nService);
		}

		@Override
		public boolean isCheckable() {
			return delegate.isCheckable();
		}

		@Override
		public boolean isChecked() {
			return delegate.isChecked();
		}

		@Override
		public boolean isEnabled() {
			return delegate.isEnabled();
		}

		@Override
		public boolean isSeparator() {
			return delegate.isSeparator();
		}

		@Override
		public boolean isVisible() {
			return delegate.isVisible();
		}

		@Override
		public void removeChild(MenuItem item) {
			delegate.removeChild(item);
			i18nAwareSupport.remove(item);
		}

		@Override
		public void removeChildren() {
			delegate.removeChildren();
			i18nAwareSupport.clear();
		}

		@Override
		public void setCheckable(boolean checkable) {
			delegate.setCheckable(checkable);
		}

		@Override
		public void setChecked(boolean checked) {
			delegate.setChecked(checked);
		}

		@Override
		public void setCommand(Command command) {
			delegate.setCommand(command);
		}

		@Override
		public void setDescription(String description) {
			delegate.setDescription(description);
		}

		@Override
		public void setEnabled(boolean enabled) {
			delegate.setEnabled(enabled);
		}

		@Override
		public void setIcon(Resource icon) {
			delegate.setIcon(icon);
		}

		@Override
		public void setLocale(Locale locale) {
			this.locale = locale;
		}

		@Override
		public void setStyleName(String styleName) {
			delegate.setStyleName(styleName);
		}

		@Override
		public void setText(String text) {
			delegate.setText(text);
		}

		@Override
		public void setVisible(boolean visible) {
			delegate.setVisible(visible);
		}

		@Override
		public String toString() {
			return delegate.toString();
		}
	}

	private static final long serialVersionUID = 2322061331694761231L;

	private I18NAwareComponentCaptionSupport i18NAwareComponentCaptionSupport = new I18NAwareComponentCaptionSupport(this);

	private I18NAwareSupport i18nAwareSupport = new I18NAwareSupport();

	@Override
	public MenuBar.MenuItem addItem(@I18NAwareMessage String captionKey, Resource icon, MenuBar.Command command) {

		I18NMenuItem result = new I18NMenuItem(captionKey, super.addItem(captionKey, icon, command));

		i18nAwareSupport.add(result);

		return result;
	}

	@Override
	public MenuBar.MenuItem addItemBefore(@I18NAwareMessage String captionKey, Resource icon, MenuBar.Command command,
			MenuBar.MenuItem itemToAddBefore) {

		I18NMenuItem result = new I18NMenuItem(captionKey, super.addItemBefore(captionKey, icon, command, itemToAddBefore));

		i18nAwareSupport.add(result);

		return result;
	}

	@Override
	public void i18NUpdate(I18NService i18nService) {

		i18NAwareComponentCaptionSupport.i18NUpdate(i18nService);
		i18nAwareSupport.i18NUpdate(i18nService);
	}

	@Override
	public void removeItem(MenuBar.MenuItem item) {

		super.removeItem(item);
		i18nAwareSupport.remove(item);
	}

	@Override
	public void removeItems() {

		super.removeItems();
		i18nAwareSupport.clear();
	}

	@Override
	public void setCaption(String captionKey) {
		setCaptionMessage(captionKey);
	}

	@Override
	public void setCaptionMessage(@I18NAwareMessage String captionKey, Object... params) {
		i18NAwareComponentCaptionSupport.setCaptionMessage(captionKey, params);
	}

	@Override
	public void setDescription(String descriptionKey) {
		setDescriptionMessage(descriptionKey);
	}

	@Override
	public void setDescriptionMessage(@I18NAwareMessage String descriptionKey, Object... descriptionParams) {
		i18NAwareComponentCaptionSupport.setDescriptionMessage(descriptionKey, descriptionParams);
	}

	@Override
	public void setLocale(Locale locale) {
		super.setLocale(locale);
	}

	@Override
	public void setRealCaption(String caption) {
		super.setCaption(caption);
	}

	@Override
	public void setRealDescription(String description) {
		super.setDescription(description);
	}
}
