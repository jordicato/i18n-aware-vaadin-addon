package com.opnworks.vaadin.i18n.data.validator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Locale;

import com.opnworks.vaadin.i18n.AbstractI18NTest;
import com.opnworks.vaadin.i18n.I18NAwareValidator;
import com.opnworks.vaadin.i18n.I18NServiceSingleton;

/**
 * The Abstract I18NAwareValidator Unit Test
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class AbstractI18NAwareValidatorTest extends AbstractI18NTest {

	public interface I18NAwareValidatorTest {

		String getActualValue();

		String getFieldKey();

		String getKey();
	}

	protected void performTest(I18NAwareValidator i18NServiceAwareValidator, I18NAwareValidatorTest test) {

		I18NServiceSingleton.getInstance().getI18NServive().setLocale(Locale.ENGLISH);
		i18NServiceAwareValidator.i18NUpdate(I18NServiceSingleton.getInstance().getI18NServive());

		assertThat(
				test.getActualValue(),
				is(I18NServiceSingleton.getInstance().getI18NServive()
						.getMessage(test.getKey(), I18NServiceSingleton.getInstance().getI18NServive().getMessage(test.getFieldKey()))));

		I18NServiceSingleton.getInstance().getI18NServive().setLocale(Locale.FRENCH);
		i18NServiceAwareValidator.i18NUpdate(I18NServiceSingleton.getInstance().getI18NServive());

		assertThat(
				test.getActualValue(),
				is(I18NServiceSingleton.getInstance().getI18NServive()
						.getMessage(test.getKey(), I18NServiceSingleton.getInstance().getI18NServive().getMessage(test.getFieldKey()))));
	}

}
