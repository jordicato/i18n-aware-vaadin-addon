package com.opnworks.vaadin.i18n.support;

/**
 * The I18NSupportExpression Singleton
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NSupportExpression {
	
	private I18NExpressions i18NExpressions;
	
	boolean isStringVar;
	
	private StringLiteral stringLiteral = new StringLiteral("", "");
		
	private static String lastValueKey;
	
	static I18NSupportExpression singleton = new I18NSupportExpression();
	
	public static I18NSupportExpression getInstance(){
		return singleton;
	}
	
	public static class StringLiteral {
		private String value;
		private String key;

		public StringLiteral(String value, String key) {
			this.value = value;
			this.key = key;
		}

		public String getValue() {
			return this.value;
		}
		
		public void setValue(String value) {
			this.value = value;
		}

		public String getkey() {
			return this.key;
		}
		
		public void setkey(String key) {
			this.key = key;
		}
	}
			
	public I18NExpressions getI18NExpressions() {
		I18NExpressions i18NExpressionsAux = i18NExpressions;
		i18NExpressions = null;
		return i18NExpressionsAux;
	}
	
	public String registerBinaryExpression(Object... exp) {
		setStringVarStatus(false);
		I18NExpressions i18NExpressions = new I18NExpressions();
		i18NExpressions.setObjectlist(exp);
		this.i18NExpressions = i18NExpressions;
		return i18NExpressions.getStringFinal();
	}
	
	public String registerLiteral(String literal, String key) {
		stringLiteral.setValue(literal);
		stringLiteral.setkey(key);
		return literal;
	}
	
	public StringLiteral getStringLiteral() {
		return stringLiteral;
	}
	
	public void setStringVarStatus(boolean status) {
		this.isStringVar = status;
	}
	
	public boolean getStringVarStatus() {
		return isStringVar;
	}
	
	public void setLastValueKey(String valueKey) {
		lastValueKey = valueKey;
	}
	
	public String getLastValueKey() {
		return lastValueKey;
	}
}
