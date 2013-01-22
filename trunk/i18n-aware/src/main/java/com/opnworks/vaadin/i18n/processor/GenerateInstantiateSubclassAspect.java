package com.opnworks.vaadin.i18n.processor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The GenerateInstantiateSubclassAspect annotation
 * 
 * @author Pedro Rodriguez
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface GenerateInstantiateSubclassAspect {
	String superclassFullName() default "";
}
