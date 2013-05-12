package com.opnworks.vaadin.i18n.ui;

import com.opnworks.vaadin.i18n.I18NAwareCaption;
import com.opnworks.vaadin.i18n.I18NAwareComponentExpression;
import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.I18NService;
import com.opnworks.vaadin.i18n.processor.GenerateInstantiateSubclassAspect;
import com.opnworks.vaadin.i18n.support.I18NAwareComponentCaptionSupport;
import com.opnworks.vaadin.i18n.support.I18NAwareComponentExpressionSupport;
import com.opnworks.vaadin.i18n.support.I18NCaptionSupport;
import com.opnworks.vaadin.i18n.support.I18NCaptionSupport.CaptionContainer;
import com.vaadin.ui.LoginForm;

/**
 * The I18NLoginForm
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
@GenerateInstantiateSubclassAspect
@SuppressWarnings({ "serial", "deprecation" })
@Deprecated
public class I18NLoginForm extends LoginForm implements I18NAwareCaption, I18NAwareComponentExpression {

	private I18NAwareComponentCaptionSupport i18NAwareComponentCaptionSupport;
	private I18NAwareComponentExpressionSupport i18NAwareComponentExpressionSupport;

	private I18NCaptionSupport captionSupport = new I18NCaptionSupport(new CaptionContainer() {
		@Override
		public void setRealCaption(String caption) {
			I18NLoginForm.this.setRealCaption(caption);
		}
	});

	private I18NCaptionSupport usernameCaptionSupport = new I18NCaptionSupport(new CaptionContainer() {
		@Override
		public void setRealCaption(String caption) {
			I18NLoginForm.this.setRealUsernameCaption(caption);
		}
	});

	private I18NCaptionSupport passwordCaptionSupport = new I18NCaptionSupport(new CaptionContainer() {
		@Override
		public void setRealCaption(String caption) {
			I18NLoginForm.this.setRealPasswordCaption(caption);
		}
	});

	private I18NCaptionSupport loginButtonCaptionSupport = new I18NCaptionSupport(new CaptionContainer() {
		@Override
		public void setRealCaption(String caption) {
			I18NLoginForm.this.setRealLoginButtonCaption(caption);
		}
	});

	@Override
	public void i18NUpdate(I18NService i18nService) {
		captionSupport.i18NUpdate(i18nService);
		usernameCaptionSupport.i18NUpdate(i18nService);
		passwordCaptionSupport.i18NUpdate(i18nService);
		loginButtonCaptionSupport.i18NUpdate(i18nService);
		getI18NAwareComponentExpressionSupport().i18NUpdate(i18nService);
	}

	@Override
	public void setCaption(@I18NAwareMessage String captionKey) {
		setCaptionMessage(captionKey);
	}

	@Override
	public void setDescription(@I18NAwareMessage String descriptionKey) {
		setDescriptionMessage(descriptionKey);
	}

	@Override
	public void setDescriptionMessage(String descriptionKey, Object... descriptionParams) {
		getI18NAwareComponentCaptionSupport().setDescriptionMessage(descriptionKey, descriptionParams);
	}

	@Override
	public void setRealDescription(String description) {
		super.setDescription(description);
	}

	@Override
	public void setCaptionMessage(@I18NAwareMessage String captionKey, Object... params) {
		captionSupport.setCaptionMessage(captionKey, params);
	}

	public void setLoginButtonCaptionKey(@I18NAwareMessage String loginButtonCaptionKey) {
		loginButtonCaptionSupport.setCaptionMessage(loginButtonCaptionKey);
	}

	public void setPasswordCaptionKey(@I18NAwareMessage String passwordCaptionKey) {
		passwordCaptionSupport.setCaptionMessage(passwordCaptionKey);
	}

	@Override
	public void setRealCaption(String caption) {
		super.setCaption(caption);
	}

	public void setRealLoginButtonCaption(String loginButtonCaption) {
		super.setLoginButtonCaption(loginButtonCaption);
	}

	public void setRealPasswordCaption(String passwordCaption) {
		super.setPasswordCaption(passwordCaption);
	}

	public void setRealUsernameCaption(String usernameCaption) {
		super.setUsernameCaption(usernameCaption);
	}

	public void setUsernameCaptionKey(@I18NAwareMessage String usernameCaptionKey) {
		usernameCaptionSupport.setCaptionMessage(usernameCaptionKey);
	}

	private I18NAwareComponentCaptionSupport getI18NAwareComponentCaptionSupport() {

		if (i18NAwareComponentCaptionSupport == null) {
			i18NAwareComponentCaptionSupport = new I18NAwareComponentCaptionSupport(this);
		}

		return i18NAwareComponentCaptionSupport;
	}

	@Override
	public void setCaptionMessage(Object... expression) {
		getI18NAwareComponentExpressionSupport().setCaptionMessage(expression);
	}

	@Override
	public void setDescriptionMessage(Object... expression) {
		getI18NAwareComponentExpressionSupport().setDescriptionMessage(expression);
	}

	private I18NAwareComponentExpressionSupport getI18NAwareComponentExpressionSupport() {

		if (i18NAwareComponentExpressionSupport == null) {
			i18NAwareComponentExpressionSupport = new I18NAwareComponentExpressionSupport(this);
		}

		return i18NAwareComponentExpressionSupport;
	}

}
