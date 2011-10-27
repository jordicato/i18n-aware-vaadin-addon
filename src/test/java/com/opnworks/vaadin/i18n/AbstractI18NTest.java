package com.opnworks.vaadin.i18n;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Locale;

import com.opnworks.vaadin.i18n.service_impl.I18NServiceImpl;
import com.opnworks.vaadin.i18n.service_impl.ResourceBundleI18NMessageProvider;

/**
 * The Abstract I18N Unit Test
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class AbstractI18NTest {

	protected static final String TEST_KEY_1 = "TEST_KEY_1";
	protected static final String TEST_KEY_2 = "TEST_KEY_2";
	protected static final String TEST_KEY_3 = "TEST_KEY_3";

	protected static I18NService i18NService;

	static {
		i18NService = new I18NServiceImpl(
				new ResourceBundleI18NMessageProvider("test-messages"));
	}

	protected void performTest(I18NAware i18NAware, I18NAwareTest... tests) {

		i18NService.setLocale(Locale.ENGLISH);
		i18NAware.i18NUpdate(i18NService);

		for (int i = 0; i < tests.length; i++) {
			assertThat(
					tests[i].getActualValue(),
					is(i18NService.getMessage(tests[i].getKey(),
							tests[i].getParams())));
		}

		i18NService.setLocale(Locale.FRENCH);
		i18NAware.i18NUpdate(i18NService);

		for (int i = 0; i < tests.length; i++) {
			assertThat(
					tests[i].getActualValue(),
					is(i18NService.getMessage(tests[i].getKey(),
							tests[i].getParams())));
		}
	}

	public interface I18NAwareTest {

		String getActualValue();

		String getKey();

		Object[] getParams();
	}

}
