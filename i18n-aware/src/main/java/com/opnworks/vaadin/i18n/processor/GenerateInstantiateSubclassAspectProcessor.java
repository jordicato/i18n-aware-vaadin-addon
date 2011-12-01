package com.opnworks.vaadin.i18n.processor;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Constructor;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.FileObject;
import javax.tools.StandardLocation;

@SupportedAnnotationTypes({ "com.opnworks.vaadin.i18n.processor.GenerateInstantiateSubclassAspect" })
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class GenerateInstantiateSubclassAspectProcessor extends
		AbstractProcessor {

	public final static Map<String, String> primitiveNameToWrapperName = new HashMap<String, String>();
	static {
		primitiveNameToWrapperName.put("boolean", "Boolean");
		primitiveNameToWrapperName.put("byte", "Byte");
		primitiveNameToWrapperName.put("short", "Short");
		primitiveNameToWrapperName.put("char", "Character");
		primitiveNameToWrapperName.put("int", "Integer");
		primitiveNameToWrapperName.put("long", "Long");
		primitiveNameToWrapperName.put("float", "Float");
		primitiveNameToWrapperName.put("double", "Double");
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations,
			RoundEnvironment roundEnv) {

		for (TypeElement te : annotations) {

			for (Element e : roundEnv.getElementsAnnotatedWith(te))
				processAnnotation(e);
		}

		return true;
	}

	private void processAnnotation(Element element) {

		GenerateInstantiateSubclassAspect annotation = element
				.getAnnotation(GenerateInstantiateSubclassAspect.class);

		String packageName = element.getEnclosingElement().toString();
		String className = element.getSimpleName().toString();

		generateInstantiateSubclassAspect(annotation.superclassFullName(),
				packageName, className);
	}

	private void generateInstantiateSubclassAspect(String superclassFullName,
			String packageName, String className) {

		Messager messager = processingEnv.getMessager();
		Filer filer = processingEnv.getFiler();

		try {
			String aspectName = className + "Aspect";

			FileObject fileObject = filer.createResource(
					StandardLocation.SOURCE_OUTPUT, packageName, aspectName
							+ ".aj");

			Writer writer = fileObject.openWriter();

			writer.write("/* Generated on " + new Date() + " */\n");

			writer.write("package " + packageName + ";\n");

			writer.write("public aspect " + aspectName + " {\n");

			Class<?> clazz = Class.forName(packageName + "." + className);

			if (superclassFullName == null || superclassFullName.isEmpty()) {
				superclassFullName = clazz.getSuperclass().getName();
			}

			Constructor<?>[] constructors = clazz.getDeclaredConstructors();

			for (int i = 0; i < constructors.length; i++) {
				generateConstructorPointcut(writer, superclassFullName, clazz,
						constructors[i].getParameterTypes(), constructors[i].getExceptionTypes() );
			}

			writer.write("}\n");

			writer.flush();
			writer.close();

		} catch (Exception e) {
			messager.printMessage(Diagnostic.Kind.ERROR, e.getMessage());
			// e.printStackTrace();
		}

	}

	private void generateConstructorPointcut(Writer writer,
			String superclassFullName, Class<?> subclass,
			Class<?>[] constructorParamTypes, Class<?>[] constructorExceptionTypes ) throws IOException {

		String pointcutName = createPointcutName(superclassFullName,
				constructorParamTypes);

		writer.write("private pointcut " + pointcutName + "() : call ("
				+ superclassFullName + ".new("
				+ createTypesAsList(constructorParamTypes) + ")) ;\n");

		writer.write(superclassFullName + " around ()");
		
		if( constructorExceptionTypes != null && constructorExceptionTypes.length > 0 ) {
			writer.write(" throws " + createTypesAsList(constructorExceptionTypes) );
		}

		writer.write(" : " + pointcutName + "()");
		
		writer.write(" {\n");

		writer.write("return new " + subclass.getSimpleName() + "( "
				+ createParams(constructorParamTypes) + " );\n");

		writer.write("}\n");
	}

	private String createPointcutName(String superclassFullName,
			Class<?>[] constructorParamTypes) {

		StringBuilder result = new StringBuilder("constructor");

		if (constructorParamTypes != null && constructorParamTypes.length > 0) {

			for (int i = 0; i < constructorParamTypes.length; i++) {
				result.append("_");
				result.append(constructorParamTypes[i].getSimpleName());
			}
		}

		return result.toString();
	}

	private String createTypesAsList(Class<?>[] constructorParamTypes) {

		StringBuilder result = new StringBuilder();

		if (constructorParamTypes != null && constructorParamTypes.length > 0) {

			for (int i = 0; i < constructorParamTypes.length; i++) {
				if (result.length() > 0) {
					result.append(",");
				}
				result.append(constructorParamTypes[i].getName().replace('$',
						'.'));
			}
		}

		return result.toString();
	}

	private String createParams(Class<?>[] constructorParamTypes) {

		StringBuilder result = new StringBuilder();

		if (constructorParamTypes != null && constructorParamTypes.length > 0) {

			for (int i = 0; i < constructorParamTypes.length; i++) {
				if (result.length() > 0) {
					result.append(",");
				}
				result.append("("
						+ createParamCast(constructorParamTypes[i].getName())
						+ ")(thisJoinPoint.getArgs()[" + i + "])");
			}
		}

		return result.toString();
	}

	private String createParamCast(String paramClassName) {

		String wrapperName = primitiveNameToWrapperName.get(paramClassName);

		if (wrapperName != null) {
			return wrapperName;
		}

		return paramClassName.replace('$', '.');
	}
}
