package com.opnworks.vaadin.i18n.support;

import com.opnworks.vaadin.i18n.I18NServiceSingleton;

/**
 * The I18NExpressions
 * 
 * @author Sandy Rodriguez Garcia ( OpnWorks )
 */
public class I18NExpressions {

	private Object[] objectList;

	public I18NExpressions(Object... objects) {
		objectList = objects;
	}

	public void setObjectlist(Object[] objectList) {
		this.objectList = objectList;
	}

	public Object[] getObjectlist() {
		return objectList;
	}

	public String getStringFinal() {
		return getStringFromBinaryExpr();
	}

	public String getStringFromBinaryExpr() {
		if (objectList == null) {
			return null;
		}
		else {
			String stringFinal = "";

			for (Object obj : objectList ) {
				if (obj != "") {
					if (isKey(obj.toString())) {
						stringFinal = stringFinal + I18NServiceSingleton.getInstance().getI18NServive().getMessage(obj.toString());
					}
					else {
						stringFinal = stringFinal + obj.toString();
					}
				}
			}
			return stringFinal;
		}
	}

	public static boolean isKey(String key) {
		if (key == null) {
			return false;
		}
		if (key.contains(" ")) {
			return false;
		}
		if (!(key.contains("_") || key.contains("."))) {
			return false;
		}
		return true;
	}
}
