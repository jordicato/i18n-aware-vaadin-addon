package com.opnworks.vaadin.i18n.data.util;

import java.util.ArrayList;
import java.util.List;

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
	private static List<StringLiteral> stringLiteralList = new ArrayList<I18NCountLiterals.StringLiteral>();
			
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
		stringLiteralList.add(stringLiteral);
		return literal;
	}
	
	public static List<StringLiteral> getStringLiteralList() {
		return stringLiteralList;
	}
	
	public static StringLiteral getStringLiteral() {
		return stringLiteral;
	}	
	
	public static StringLiteral getStringLiteralFromList(String literal) {
		for (StringLiteral sl : stringLiteralList) {
			if (sl.getValue().equals(literal)) {
				return sl;
			}
		}
		return null;
	}
}
