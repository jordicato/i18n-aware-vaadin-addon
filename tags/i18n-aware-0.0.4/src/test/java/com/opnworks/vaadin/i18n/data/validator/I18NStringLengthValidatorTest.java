package com.opnworks.vaadin.i18n.data.validator;

import org.junit.Test;

import com.opnworks.vaadin.i18n.data.validator.I18NStringLengthValidator;

/**
 * The I18NStringLengthValidator Unit Tests
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
public class I18NStringLengthValidatorTest extends
		AbstractI18NAwareValidatorTest {

	@Test
	public void testConstructorErrorMessageKeyAndFieldNameKey() {

		final I18NStringLengthValidator i18NStringLengthValidator = new I18NStringLengthValidator(
				TEST_KEY_1, TEST_KEY_2, 1, 1, true);

		performTest(i18NStringLengthValidator, new I18NAwareValidatorTest() {

			public String getActualValue() {
				return i18NStringLengthValidator.getErrorMessage();
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

		final I18NStringLengthValidator i18NStringLengthValidator = new I18NStringLengthValidator(
				TEST_KEY_2, TEST_KEY_2, 1, 1, true);

		i18NStringLengthValidator.setErrorMessageKey(TEST_KEY_1);

		performTest(i18NStringLengthValidator, new I18NAwareValidatorTest() {

			public String getActualValue() {
				return i18NStringLengthValidator.getErrorMessage();
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
