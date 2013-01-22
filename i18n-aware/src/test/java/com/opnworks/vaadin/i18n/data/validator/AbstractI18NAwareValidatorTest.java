package com.opnworks.vaadin.i18n.data.validator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Locale;

import com.opnworks.vaadin.i18n.AbstractI18NTest;
import com.opnworks.vaadin.i18n.I18NAwareValidator;

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

		i18NService.setLocale(Locale.ENGLISH);
		i18NServiceAwareValidator.i18NUpdate(i18NService);

		assertThat(test.getActualValue(), is(i18NService.getMessage(test.getKey(), i18NService.getMessage(test.getFieldKey()))));

		i18NService.setLocale(Locale.FRENCH);
		i18NServiceAwareValidator.i18NUpdate(i18NService);

		assertThat(test.getActualValue(), is(i18NService.getMessage(test.getKey(), i18NService.getMessage(test.getFieldKey()))));
	}

}
