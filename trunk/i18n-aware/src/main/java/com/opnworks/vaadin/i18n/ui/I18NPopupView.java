package com.opnworks.vaadin.i18n.ui;

import java.util.Locale;

import com.opnworks.vaadin.i18n.I18NAware;
import com.opnworks.vaadin.i18n.I18NAwareComponent;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.support.I18NAwareComponentCaptionSupport;
import com.vaadin.ui.Component;
import com.vaadin.ui.PopupView;

/**
 * The I18NPopupView
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NPopupView extends PopupView implements I18NAwareComponent {

	private static final long serialVersionUID = 3994292451004562581L;

	private I18NAwareComponentCaptionSupport i18NAwareComponentCaptionSupport = new I18NAwareComponentCaptionSupport(
			this);

	/**
	 * A simple way to create a PopupPanel. Note that the minimal representation
	 * may not be dynamically updated, in order to achieve this create your own
	 * Content object and use {@link PopupView#PopupView(Content)}.
	 * 
	 * @param small
	 *            the minimal textual representation as HTML
	 * @param large
	 *            the full, Component-type representation
	 */
	public I18NPopupView(final java.lang.String small, final Component large) {
		super(small, large);
	}

	/**
	 * Creates a PopupView through the PopupView.Content interface. This allows
	 * the creator to dynamically change the contents of the PopupView.
	 * 
	 * @param content
	 *            the PopupView.Content that contains the information for this
	 */
	public I18NPopupView(PopupView.Content content) {
		super(content);
	}

	@Override
	public void setCaptionKey(String captionKey) {
		i18NAwareComponentCaptionSupport.setCaptionKey(captionKey);
	}

	@Override
	public void setCaptionParams(Object... params) {
		i18NAwareComponentCaptionSupport.setCaptionParams(params);
	}

	@Override
	public void i18NUpdate(I18NService i18nService) {

		i18NAwareComponentCaptionSupport.i18NUpdate(i18nService);

		PopupView.Content content = getContent();

		if (content != null && content instanceof I18NAware) {
			((I18NAware) content).i18NUpdate(i18nService);
		} else {
			Component popupComponent = content.getPopupComponent();
			if (popupComponent != null && popupComponent instanceof I18NAware) {
				((I18NAware) popupComponent).i18NUpdate(i18nService);
			}
		}
	}

	/**
	 * Used to deliver customized content-packages to the PopupView. These are
	 * dynamically loaded when they are redrawn. The user must take care that
	 * neither of these methods ever return null.
	 */
	public static abstract class I18NContent implements Content, I18NAware {

		private static final long serialVersionUID = 1472420206441355537L;

		private String minimizedValueAsHTML;

		/**
		 * This should return a small view of the full data.
		 * 
		 * @return value in HTML format
		 */
		public String getMinimizedValueAsHTML() {
			return minimizedValueAsHTML;
		}

		/**
		 * This should return the full Component representing the data
		 * 
		 * @return a Component for the value
		 */
		public Component getPopupComponent() {
			return getI18nPopupComponent();
		}

		@Override
		public void i18NUpdate(I18NService i18nService) {

			minimizedValueAsHTML = getI18NMinimizedValueAsHTML(i18nService);

			I18NAwareComponent i18NAwareComponent = getI18nPopupComponent();

			if (i18NAwareComponent != null) {
				i18NAwareComponent.i18NUpdate(i18nService);
			}
		}

		@Override
		public void setLocale(Locale locale) {
			
			I18NAwareComponent i18NAwareComponent = getI18nPopupComponent();

			if (i18NAwareComponent != null) {
				i18NAwareComponent.setLocale(locale);
			}
		}

		public abstract I18NAwareComponent getI18nPopupComponent();

		public abstract String getI18NMinimizedValueAsHTML(
				I18NService i18nService);
	}

}
