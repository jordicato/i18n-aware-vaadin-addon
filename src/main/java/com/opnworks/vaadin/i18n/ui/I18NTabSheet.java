package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareCaption;
import com.opnworks.vaadin.i18n.I18NAwareComponent;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.support.I18NAwareSupport;
import com.vaadin.terminal.ErrorMessage;
import com.vaadin.terminal.Resource;
import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;

/**
 * The I18NTabSheet
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NTabSheet extends TabSheet implements I18NAwareComponent {

	private static final long serialVersionUID = -7070663953414272939L;

	private I18NAwareSupport i18NAwareSupport = new I18NAwareSupport();

    /**
     * Constructs a new i18n Tabsheet. Tabsheet is immediate by default, and the
     * default close handler removes the tab being closed.
     */
	public I18NTabSheet() {
		super();
	}
	
	@Override
	public Tab addTab(Component c) {

		Tab tab = super.addTab(c);

		i18NAwareSupport.add(c);

		I18NTab result = new I18NTab(tab);

		i18NAwareSupport.add(result);

		return result;
	}
	
	public I18NTab addI18NTab(Component c) {
		return (I18NTab)addTab(c);
	}

	@Override
	public void i18NUpdate(I18NService i18N) {
		i18NAwareSupport.updateLabels(i18N);
	}

	public class I18NTab implements Tab, I18NAwareCaption {

		private static final long serialVersionUID = 800297056670016442L;

		private Tab delegate;

		private String captionKey;
		private Object[] captionParams;

		public I18NTab(Tab delegate) {
			this.delegate = delegate;
		}

		public void setCaptionKey(String captionKey) {
			this.captionKey = captionKey;
		}

		public String getCaptionKey() {
			return captionKey;
		}

		public void setCaptionParams(Object... captionParams) {
			this.captionParams = captionParams;
		}

		public void i18NUpdate(I18NService i18N) {

			if (captionKey != null) {
				delegate.setCaption(i18N.getMessage(captionKey, captionParams));
			}
		}

		public boolean isVisible() {
			return delegate.isVisible();
		}

		public void setVisible(boolean visible) {
			delegate.setVisible(visible);
		}

		public boolean isClosable() {
			return delegate.isClosable();
		}

		public void setClosable(boolean closable) {
			delegate.setClosable(closable);
		}

		public boolean isEnabled() {
			return delegate.isEnabled();
		}

		public void setEnabled(boolean enabled) {
			delegate.setEnabled(enabled);
		}

		public void setCaption(String caption) {
			delegate.setCaption(caption);
		}

		public String getCaption() {
			return delegate.getCaption();
		}

		public Resource getIcon() {
			return delegate.getIcon();
		}

		public void setIcon(Resource icon) {
			delegate.setIcon(icon);
		}

		public String getDescription() {
			return delegate.getDescription();
		}

		public void setDescription(String description) {
			delegate.setDescription(description);
		}

		public void setComponentError(ErrorMessage componentError) {
			delegate.setComponentError(componentError);
		}

		public ErrorMessage getComponentError() {
			return delegate.getComponentError();
		}

		public Component getComponent() {
			return delegate.getComponent();
		}
	}
}
