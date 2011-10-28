package com.opnworks.vaadin.i18n.data.validator;

import org.junit.Test;

/**
 * The I18NEmailValidator Unit Tests
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NEmailValidatorTest extends AbstractI18NAwareValidatorTest {

	@Test
	public void testConstructorErrorMessageKeyAndFieldNameKey() {

		final I18NEmailValidator i18NEmailValidator = new I18NEmailValidator(
				TEST_KEY_1, TEST_KEY_2);

		performTest(i18NEmailValidator, new I18NAwareValidatorTest() {

			public String getActualValue() {
				return i18NEmailValidator.getErrorMessage();
			}

			public String getKey() {
				return TEST_KEY_1;
			}

			public String getFieldKey() {
				return TEST_KEY_2;
			}
		});

	}

	@Test
	public void testSetErrorMessageKey() {

		final I18NEmailValidator i18NEmailValidator = new I18NEmailValidator(
				TEST_KEY_2, TEST_KEY_2);

		i18NEmailValidator.setErrorMessageKey(TEST_KEY_1);

		performTest(i18NEmailValidator, new I18NAwareValidatorTest() {

			public String getActualValue() {
				return i18NEmailValidator.getErrorMessage();
			}

			public String getKey() {
				return TEST_KEY_1;
			}

			public String getFieldKey() {
				return TEST_KEY_2;
			}
		});

	}

}
