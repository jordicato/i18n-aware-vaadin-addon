package com.opnworks.vaadin.i18n.ui;

import java.util.Locale;

import com.opnworks.vaadin.i18n.I18NAwareCaption;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.support.I18NCaptionSupport;
import com.opnworks.vaadin.i18n.support.I18NCaptionSupport.CaptionContainer;
import com.vaadin.terminal.ErrorMessage;
import com.vaadin.terminal.Resource;
import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet.Tab;

/**
 * The I18NTab class is used internally by I18NTabSheet and I18NAccordion
 * 
 * @author Pedro Rodriguez (OpnWorks)
 */
public class I18NTab implements Tab, I18NAwareCaption, CaptionContainer {

	private static final long serialVersionUID = 800297056670016442L;

	private Tab delegate;

	private I18NCaptionSupport i18NCaptionSupport = new I18NCaptionSupport(this);
	
	private Locale locale;
	
	public I18NTab(Tab delegate) {
		this.delegate = delegate;
	}

	@Override
	public void setRealCaption(String caption) {
		delegate.setCaption(caption);
	}

	@Override
	public void setCaption(String captionKey) {
		setCaptionMessage(captionKey);
	}
	
	public void setCaptionMessage(@I18NAwareMessage String captionKey, Object... captionParams) {
		i18NCaptionSupport.setCaptionMessage(captionKey, captionParams);
	}

	public void i18NUpdate(I18NService i18N) {
		i18NCaptionSupport.i18NUpdate(i18N);
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
	
	public void setStyleName(String styleName) {
		delegate.setStyleName(styleName);
	}

	public String getStyleName() {
		return delegate.getStyleName();
	}

	@Override
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	
	@Override
	public Locale getLocale() {
		return locale;
	}
}