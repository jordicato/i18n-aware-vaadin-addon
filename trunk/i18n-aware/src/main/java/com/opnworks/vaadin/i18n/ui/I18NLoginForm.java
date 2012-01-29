package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareCaption;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.processor.GenerateInstantiateSubclassAspect;
import com.opnworks.vaadin.i18n.support.I18NCaptionSupport;
import com.opnworks.vaadin.i18n.support.I18NCaptionSupport.CaptionContainer;
import com.vaadin.ui.LoginForm;

/**
 * The I18NLoginForm
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
@GenerateInstantiateSubclassAspect
public class I18NLoginForm extends LoginForm implements I18NAwareCaption {

	private static final long serialVersionUID = -959986070759266148L;

	private I18NCaptionSupport captionSupport = new I18NCaptionSupport(
			new CaptionContainer() {
				@Override
				public void setRealCaption(String caption) {
					I18NLoginForm.this.setRealCaption(caption);
				}
			});

	private I18NCaptionSupport usernameCaptionSupport = new I18NCaptionSupport(
			new CaptionContainer() {
				@Override
				public void setRealCaption(String caption) {
					I18NLoginForm.this.setRealUsernameCaption(caption);
				}
			});

	private I18NCaptionSupport passwordCaptionSupport = new I18NCaptionSupport(
			new CaptionContainer() {
				@Override
				public void setRealCaption(String caption) {
					I18NLoginForm.this.setRealPasswordCaption(caption);
				}
			});

	private I18NCaptionSupport loginButtonCaptionSupport = new I18NCaptionSupport(
			new CaptionContainer() {
				@Override
				public void setRealCaption(String caption) {
					I18NLoginForm.this.setRealLoginButtonCaption(caption);
				}
			});

	@Override
	public void setRealCaption(String caption) {
		super.setCaption(caption);
	}

	public void setRealUsernameCaption(String usernameCaption) {
		super.setUsernameCaption(usernameCaption);
	}

	public void setRealPasswordCaption(String passwordCaption) {
		super.setPasswordCaption(passwordCaption);
	}

	public void setRealLoginButtonCaption(String loginButtonCaption) {
		super.setLoginButtonCaption(loginButtonCaption);
	}
	
	@Override
	public void setCaption(String captionKey) {
		setCaptionMessage(captionKey);
	}
	
	public void setCaptionMessage(@I18NAwareMessage String captionKey, Object... params) {
		captionSupport.setCaptionMessage(captionKey, params);
	}

	public void setUsernameCaptionKey(String usernameCaptionKey) {
		usernameCaptionSupport.setCaptionMessage(usernameCaptionKey);
	}

	public void setPasswordCaptionKey(String passwordCaptionKey) {
		passwordCaptionSupport.setCaptionMessage(passwordCaptionKey);
	}

	public void setLoginButtonCaptionKey(String loginButtonCaptionKey) {
		loginButtonCaptionSupport.setCaptionMessage(loginButtonCaptionKey);
	}

	@Override
	public void i18NUpdate(I18NService i18nService) {
		captionSupport.i18NUpdate(i18nService);
		usernameCaptionSupport.i18NUpdate(i18nService);
		passwordCaptionSupport.i18NUpdate(i18nService);
		loginButtonCaptionSupport.i18NUpdate(i18nService);
	}
}