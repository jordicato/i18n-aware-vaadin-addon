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
	public String getCaption() {
		return delegate.getCaption();
	}

	@Override
	public Component getComponent() {
		return delegate.getComponent();
	}

	@Override
	public ErrorMessage getComponentError() {
		return delegate.getComponentError();
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
	public Locale getLocale() {
		return locale;
	}

	@Override
	public String getStyleName() {
		return delegate.getStyleName();
	}

	@Override
	public void i18NUpdate(I18NService i18N) {
		i18NCaptionSupport.i18NUpdate(i18N);
	}

	@Override
	public boolean isClosable() {
		return delegate.isClosable();
	}

	@Override
	public boolean isEnabled() {
		return delegate.isEnabled();
	}

	@Override
	public boolean isVisible() {
		return delegate.isVisible();
	}

	@Override
	public void setCaption(String captionKey) {
		setCaptionMessage(captionKey);
	}

	@Override
	public void setCaptionMessage(@I18NAwareMessage String captionKey, Object... captionParams) {
		i18NCaptionSupport.setCaptionMessage(captionKey, captionParams);
	}

	@Override
	public void setClosable(boolean closable) {
		delegate.setClosable(closable);
	}

	@Override
	public void setComponentError(ErrorMessage componentError) {
		delegate.setComponentError(componentError);
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
	public void setRealCaption(String caption) {
		delegate.setCaption(caption);
	}

	@Override
	public void setStyleName(String styleName) {
		delegate.setStyleName(styleName);
	}

	@Override
	public void setVisible(boolean visible) {
		delegate.setVisible(visible);
	}
}