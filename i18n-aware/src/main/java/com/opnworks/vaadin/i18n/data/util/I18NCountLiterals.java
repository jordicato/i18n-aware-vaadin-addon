package com.opnworks.vaadin.i18n.data.util;

import com.opnworks.vaadin.i18n.I18NStaticService;
import com.opnworks.vaadin.i18n.support.I18NAwareValueSupport;

/**
 * The I18NCountLiterals
 * 
 * @author Sandy Rodriguez Garcia ( OpnWorks )
 */
public class I18NCountLiterals {

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

	private static StringLiteral stringLiteral = new StringLiteral("", "");
	private static Object[] objectList;
	private static boolean isBinaryExpr = false;
			
	public static boolean isKey(String key) {
		if (key == null) {
			return false;
		}
		if (key.contains(" ")) {
			return false;
		}
		if ( !(key.contains("_") || key.contains(".")) ) {
			return false;
		}
		return true;
	}
	
	public static String registerLiteral(String literal, String key) {
		stringLiteral.setValue(literal);
		stringLiteral.setkey(key);
		return literal;
	}
			
	public static String getStringFromBinaryExpr(Object... exp) {
		String stringFinal = "";	
		
		for (Object obj : exp) {
			if (obj != "") {
				String tempValue = obj.toString();
				boolean keep = false;
				
				if ((!I18NCountLiterals.isKey(tempValue)) && (!I18NCountLiterals.getStringLiteral().getValue().equals(tempValue)) 
						&& (I18NStaticService.getI18NServive().getMessage(I18NAwareValueSupport.lastValueKey).equals(tempValue))) {
					tempValue = I18NAwareValueSupport.lastValueKey;
					keep = true;
				}
				
				if (I18NCountLiterals.isKey(tempValue)) {
					stringFinal = stringFinal + I18NStaticService.getI18NServive().getMessage(tempValue);
				} else {
					if (I18NCountLiterals.getStringLiteral().getkey() != "") {					
						stringFinal = stringFinal + tempValue;
					} else {
						if (keep) {
							stringFinal = stringFinal + tempValue;
						} else {
							stringFinal = stringFinal + I18NStaticService.getI18NServive().getMessage(tempValue);
						}
					}
				}				
			}
		}
		return stringFinal;			
	}
	
	public static String registerBinaryExpression(Object... exp) {
		objectList = null;
		isBinaryExpr = true;
		objectList = exp;
		return getStringFromBinaryExpr(exp);	
	}

	public static StringLiteral getStringLiteral() {
		return stringLiteral;
	}
	
	public static Object[] getBinaryObjectList() {
		Object[] auxObjectList = objectList;
		objectList = null;
		return auxObjectList;
	}
	
	public static boolean getIsBinaryExpr() {
		boolean aux = isBinaryExpr;
		isBinaryExpr = false;
		return aux;
	}
	
	public static void clearObjectList() {
		objectList = null;
	}
}
