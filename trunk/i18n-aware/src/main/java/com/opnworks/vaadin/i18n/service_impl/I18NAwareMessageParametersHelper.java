package com.opnworks.vaadin.i18n.service_impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.opnworks.vaadin.i18n.I18NAware;
import com.opnworks.vaadin.i18n.I18NAwareMessage;

/**
 * The I18NAwareMessageParameters Helper class
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NAwareMessageParametersHelper {

	/**
	 * Return the positions of I18NAwareMessage parameters in a constructor
	 * 
	 * @param clazz
	 * @param constructorParamTypes
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	public static <T extends I18NAware> int[] getI18NAwareMessageParameters(
			Class<T> clazz, Class<?>... constructorParamTypes)
			throws SecurityException, NoSuchMethodException {

		return getI18NAwareMessageParameters(clazz
				.getConstructor(constructorParamTypes));
	}

	/**
	 * Return the positions of I18NAwareMessage parameters in a constructor
	 * 
	 * @param constructor
	 */
	public static <T extends I18NAware> int[] getI18NAwareMessageParameters(
			Constructor<T> constructor) {

		return getI18NAwareMessageParameters(constructor
				.getParameterAnnotations());
	}

	/**
	 * Return the positions of I18NAwareMessage parameters in a method
	 * 
	 * @param clazz
	 * @param methodName
	 * @param paramTypes
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	public static <T extends I18NAware> int[] getI18NAwareMessageParameters(
			Class<T> clazz, String methodName, Class<?>... paramTypes)
			throws SecurityException, NoSuchMethodException {

		return getI18NAwareMessageParameters(clazz.getMethod(methodName,
				paramTypes));
	}

	/**
	 * Return the positions of I18NAwareMessage parameters in a method
	 * 
	 * @param method
	 */
	public static int[] getI18NAwareMessageParameters(Method method) {

		return getI18NAwareMessageParameters(method.getParameterAnnotations());
	}

	private static int[] getI18NAwareMessageParameters(
			Annotation[][] parameterAnnotations) {

		if (parameterAnnotations == null) {
			return null;
		}

		List<Integer> paramPositions = new ArrayList<Integer>();

		for (int i = 0; i < parameterAnnotations.length; i++) {
			if (containsI18NAwareMessageAnnotation(parameterAnnotations[i])) {
				paramPositions.add(i);
			}
		}

		int[] result = new int[paramPositions.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = paramPositions.get(i);
		}

		return result;
	}

	private static boolean containsI18NAwareMessageAnnotation(
			Annotation[] annotations) {

		if (annotations == null) {
			return false;
		}

		for (int i = 0; i < annotations.length; i++) {
			if (annotations[i].annotationType().equals(I18NAwareMessage.class)) {
				return true;
			}
		}

		return false;

	}

}
