package com.opnworks.vaadin.i18n;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Locale;

import com.opnworks.vaadin.i18n.service_impl.I18NAwareMessageParametersHelper;

/**
 * The Abstract I18N Unit Test
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class AbstractI18NTest {

	public interface I18NAwareTest {

		String getActualValue();

		String getKey();

		Object[] getParams();
	}

	protected static final String TEST_KEY_1 = "TEST_KEY_1";
	protected static final String TEST_KEY_2 = "TEST_KEY_2";

	protected static final String TEST_KEY_3 = "TEST_KEY_3";

	protected static I18NService i18NService;

	static {
		// i18NService = new I18NServiceImpl(new ResourceBundleI18NMessageProvider("test-messages"));
		// I18NServiceSingleton.getInstance().setInitParams("test-messages", Locale.ENGLISH);
		I18NServiceSingleton.getInstance().setInitParams("test-messages", Locale.ENGLISH);
		// i18NService = I18NServiceSingleton.getInstance().getI18NServive();
	}

	public <T extends I18NAware> void assertI18NAwareMessagePresent(Class<T> clazz, int paramPos, Class<?>... paramTypes) throws SecurityException,
			NoSuchMethodException {

		assertThat(clazz, notNullValue());

		assertI18NAwareMessagePresent(clazz.getConstructor(paramTypes), paramPos);
	}

	public <T extends I18NAware> void assertI18NAwareMessagePresent(Class<T> clazz, String methodName, int paramPos, Class<?>... paramTypes)
			throws SecurityException, NoSuchMethodException {

		assertThat(clazz, notNullValue());

		assertI18NAwareMessagePresent(clazz.getMethod(methodName, paramTypes), paramPos);
	}

	public <T extends I18NAware> void assertI18NAwareMessagePresent(Constructor<T> constructor, int paramPos) {

		int[] i18NAwareMessageParameterPositions = I18NAwareMessageParametersHelper.getI18NAwareMessageParameters(constructor);

		assertThat(i18NAwareMessageParameterPositions, notNullValue());

		assertThat(Arrays.binarySearch(i18NAwareMessageParameterPositions, paramPos), greaterThanOrEqualTo(0));
	}

	public <T extends I18NAware> void assertI18NAwareMessagePresent(Method method, int paramPos) {

		int[] i18NAwareMessageParameterPositions = I18NAwareMessageParametersHelper.getI18NAwareMessageParameters(method);

		assertThat(i18NAwareMessageParameterPositions, notNullValue());

		assertThat(Arrays.binarySearch(i18NAwareMessageParameterPositions, paramPos), greaterThanOrEqualTo(0));
	}

	protected void performTest(I18NAware i18NAware, I18NAwareTest... tests) {

		I18NServiceSingleton.getInstance().getI18NServive().setLocale(Locale.ENGLISH);
		i18NAware.i18NUpdate(I18NServiceSingleton.getInstance().getI18NServive());

		for (I18NAwareTest test : tests ) {
			assertThat(test.getActualValue(), is(I18NServiceSingleton.getInstance().getI18NServive().getMessage(test.getKey(), test.getParams())));
		}

		I18NServiceSingleton.getInstance().getI18NServive().setLocale(Locale.FRENCH);
		i18NAware.i18NUpdate(I18NServiceSingleton.getInstance().getI18NServive());

		for (I18NAwareTest test : tests ) {
			assertThat(test.getActualValue(), is(I18NServiceSingleton.getInstance().getI18NServive().getMessage(test.getKey(), test.getParams())));
		}
	}

}
