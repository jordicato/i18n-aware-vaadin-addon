package com.opnworks.vaadin.i18n.ui;

import java.util.List;
import java.util.Locale;

import com.opnworks.vaadin.i18n.I18NAware;
import com.opnworks.vaadin.i18n.I18NAwareCaption;
import com.opnworks.vaadin.i18n.I18NAwareComponent;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NService;
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
public class I18NMenuBar extends MenuBar implements I18NAwareComponent,
		I18NAwareCaption {

	private static final long serialVersionUID = 2322061331694761231L;

	private I18NAwareComponentCaptionSupport i18NAwareComponentCaptionSupport = new I18NAwareComponentCaptionSupport(
			this);

	private I18NAwareSupport i18nAwareSupport = new I18NAwareSupport();

	public MenuBar.MenuItem addItem(@I18NAwareMessage String captionKey, Resource icon,
			MenuBar.Command command) {

		I18NMenuItem result = new I18NMenuItem(captionKey, super.addItem(
				captionKey, icon, command));

		i18nAwareSupport.add(result);

		return result;
	}

	public MenuBar.MenuItem addItemBefore(@I18NAwareMessage String captionKey, Resource icon,
			MenuBar.Command command, MenuBar.MenuItem itemToAddBefore) {

		I18NMenuItem result = new I18NMenuItem(captionKey, super.addItemBefore(
				captionKey, icon, command, itemToAddBefore));

		i18nAwareSupport.add(result);

		return result;
	}

	public void removeItem(MenuBar.MenuItem item) {

		super.removeItem(item);
		i18nAwareSupport.remove(item);
	}

	public void removeItems() {

		super.removeItems();
		i18nAwareSupport.clear();
	}

	@Override
	public void setCaptionMessage(@I18NAwareMessage String captionKey, Object... params) {
		i18NAwareComponentCaptionSupport.setCaptionMessage(captionKey, params);
	}

	@Override
	public void setDescriptionMessage(@I18NAwareMessage String descriptionKey,
			Object... descriptionParams) {
		i18NAwareComponentCaptionSupport.setDescriptionMessage(descriptionKey, descriptionParams);
	}

	@Override
	public void i18NUpdate(I18NService i18nService) {

		i18NAwareComponentCaptionSupport.i18NUpdate(i18nService);
		i18nAwareSupport.i18NUpdate(i18nService);
	}

	@Override
	public void setLocale(Locale locale) {
		super.setLocale(locale);
	}

	public class I18NMenuItem extends MenuItem implements I18NAware {

		private static final long serialVersionUID = 1625725562623099227L;

		private I18NAwareSupport i18nAwareSupport = new I18NAwareSupport();
		
		private Locale locale;
		
		private MenuItem delegate;

		private I18NAwareValueSupport i18NTextSupport = new I18NAwareValueSupport(
				new ValueContainer() {
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
		
		public MenuBar.MenuItem addItem(@I18NAwareMessage String captionKey, Resource icon,
				MenuBar.Command command) throws IllegalStateException {

			I18NMenuItem result = new I18NMenuItem(captionKey, delegate.addItem(captionKey, icon,
					command));
			
			i18nAwareSupport.add(result);

			return result;
		}

		public MenuBar.MenuItem addItemBefore(@I18NAwareMessage String captionKey, Resource icon,
				MenuBar.Command command, MenuBar.MenuItem itemToAddBefore)
				throws IllegalStateException {

			I18NMenuItem result = new I18NMenuItem(captionKey, delegate.addItemBefore(captionKey,
					icon, command, itemToAddBefore));
			
			i18nAwareSupport.add(result);

			return result;
		}

		public void removeChild(MenuItem item) {
			delegate.removeChild(item);
			i18nAwareSupport.remove(item);
		}

		public void removeChildren() {
			delegate.removeChildren();
			i18nAwareSupport.clear();
		}
		
		@Override
		public void i18NUpdate(I18NService i18nService) {

			i18NTextSupport.i18NUpdate(i18nService);
			i18nAwareSupport.i18NUpdate(i18nService);
		}

		@Override
		public void setLocale(Locale locale) {
			this.locale = locale;
		}
		
		@Override
		public Locale getLocale() {
			return locale;
		}

		public int hashCode() {
			return delegate.hashCode();
		}

		public boolean equals(Object obj) {
			return delegate.equals(obj);
		}

		public String toString() {
			return delegate.toString();
		}

		public boolean hasChildren() {
			return delegate.hasChildren();
		}

		public MenuItem addSeparator() {
			return delegate.addSeparator();
		}

		public MenuItem addSeparatorBefore(MenuItem itemToAddBefore) {
			return delegate.addSeparatorBefore(itemToAddBefore);
		}

		public Command getCommand() {
			return delegate.getCommand();
		}

		public Resource getIcon() {
			return delegate.getIcon();
		}

		public MenuItem getParent() {
			return delegate.getParent();
		}

		public List<MenuItem> getChildren() {
			return delegate.getChildren();
		}

		public String getText() {
			return delegate.getText();
		}

		public int getSize() {
			return delegate.getSize();
		}

		public int getId() {
			return delegate.getId();
		}

		public void setCommand(Command command) {
			delegate.setCommand(command);
		}

		public void setIcon(Resource icon) {
			delegate.setIcon(icon);
		}

		public void setText(String text) {
			delegate.setText(text);
		}

		public void setEnabled(boolean enabled) {
			delegate.setEnabled(enabled);
		}

		public boolean isEnabled() {
			return delegate.isEnabled();
		}

		public void setVisible(boolean visible) {
			delegate.setVisible(visible);
		}

		public boolean isVisible() {
			return delegate.isVisible();
		}

		public boolean isSeparator() {
			return delegate.isSeparator();
		}

		public void setStyleName(String styleName) {
			delegate.setStyleName(styleName);
		}

		public String getStyleName() {
			return delegate.getStyleName();
		}

		public void setDescription(String description) {
			delegate.setDescription(description);
		}

		public String getDescription() {
			return delegate.getDescription();
		}

		public boolean isCheckable() {
			return delegate.isCheckable();
		}

		public void setCheckable(boolean checkable)
				throws IllegalStateException {
			delegate.setCheckable(checkable);
		}

		public boolean isChecked() {
			return delegate.isChecked();
		}

		public void setChecked(boolean checked) {
			delegate.setChecked(checked);
		}
	}
}
