package com.opnworks.vaadin.i18n.service_impl;

import java.util.Locale;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.opnworks.vaadin.i18n.I18NAware;
import com.opnworks.vaadin.i18n.I18NMessageProvider;
import com.opnworks.vaadin.i18n.I18NService;

/**
 * The I18NServiceImpl Unit Test
 * 
 * @author Pedro Rodriguez ( OpnWorks )
 */
@RunWith(JMock.class)
public class I18NServiceImplTest {

	protected static final String TEST_KEY_1 = "TEST_KEY_1";

	private Mockery context = new JUnit4Mockery();

	private I18NMessageProvider i18NMessageProvider;

	private I18NService i18NService;

	private I18NAware i18NAware;

	@Before
	public void setup() {
		i18NMessageProvider = context.mock(I18NMessageProvider.class);
		i18NService = new I18NServiceImpl(i18NMessageProvider);
		i18NAware = context.mock(I18NAware.class);
	}

	@Test
	public void testRegisterI18NAware() {
		i18NService.registerI18NAware(i18NAware);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRegisterI18NAwareWithSomethingelseThanI18NAware() {
		i18NService.registerI18NAware(this);
	}

	@Test
	public void testSetLocale() {

		context.checking(new Expectations() {
			{
				one(i18NMessageProvider).setLocale(Locale.ENGLISH);
				one(i18NAware).i18NUpdate(i18NService);
			}
		});

		i18NService.registerI18NAware(i18NAware);

		i18NService.setLocale(Locale.ENGLISH);
	}

}
