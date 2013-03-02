package com.opnworks.vaadin.i18n.converter.aop_mode;

import japa.parser.JavaParser;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.ImportDeclaration;
import japa.parser.ast.body.BodyDeclaration;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.ConstructorDeclaration;
import japa.parser.ast.body.FieldDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.TypeDeclaration;
import japa.parser.ast.body.VariableDeclarator;
import japa.parser.ast.expr.ArrayCreationExpr;
import japa.parser.ast.expr.ArrayInitializerExpr;
import japa.parser.ast.expr.AssignExpr;
import japa.parser.ast.expr.BinaryExpr;
import japa.parser.ast.expr.BooleanLiteralExpr;
import japa.parser.ast.expr.CastExpr;
import japa.parser.ast.expr.ClassExpr;
import japa.parser.ast.expr.ConditionalExpr;
import japa.parser.ast.expr.EnclosedExpr;
import japa.parser.ast.expr.Expression;
import japa.parser.ast.expr.FieldAccessExpr;
import japa.parser.ast.expr.MethodCallExpr;
import japa.parser.ast.expr.NameExpr;
import japa.parser.ast.expr.NullLiteralExpr;
import japa.parser.ast.expr.ObjectCreationExpr;
import japa.parser.ast.expr.StringLiteralExpr;
import japa.parser.ast.expr.UnaryExpr;
import japa.parser.ast.expr.VariableDeclarationExpr;
import japa.parser.ast.stmt.BlockStmt;
import japa.parser.ast.stmt.BreakStmt;
import japa.parser.ast.stmt.CatchClause;
import japa.parser.ast.stmt.ContinueStmt;
import japa.parser.ast.stmt.DoStmt;
import japa.parser.ast.stmt.ExpressionStmt;
import japa.parser.ast.stmt.ForStmt;
import japa.parser.ast.stmt.ForeachStmt;
import japa.parser.ast.stmt.IfStmt;
import japa.parser.ast.stmt.Statement;
import japa.parser.ast.stmt.SwitchEntryStmt;
import japa.parser.ast.stmt.SwitchStmt;
import japa.parser.ast.stmt.SynchronizedStmt;
import japa.parser.ast.stmt.ThrowStmt;
import japa.parser.ast.stmt.TryStmt;
import japa.parser.ast.stmt.WhileStmt;
import japa.parser.ast.type.ReferenceType;
import japa.parser.ast.type.Type;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.lang.model.type.PrimitiveType;

import com.opnworks.vaadin.i18n.I18NAwareMessage;
import com.opnworks.vaadin.i18n.converter.ConverterException;
import com.opnworks.vaadin.i18n.converter.main.CommandLineOutput;

/**
 * Class to get all vaadin widgets captions key
 * 
 * @author Sandy Rodriguez Garcia ( opnworks )
 * 
 */
public class KeyConverter {
	public class Key {
		private String key;
		private String completeKey;
		private String value;
		@SuppressWarnings("unused")
		private String fullClassName;
		private int appearances;
		private int suffix;
		private int maxSuffixClass;
		private boolean keep;
		private boolean loadFromBundle;

		public Key(String key, String value, String fullClassName, int suffix, int maxSuffixClass) {
			if (suffix > 0) {
				this.completeKey = key + "_" + suffix;
			}
			else {
				this.completeKey = key;
			}
			this.key = key;
			this.fullClassName = fullClassName;
			this.value = value;
			this.suffix = suffix;
			this.maxSuffixClass = maxSuffixClass;
			this.appearances = 0;
			this.keep = false;
			this.loadFromBundle = false;
		}

		public int getAppearances() {
			return this.appearances;
		}

		public String getCompleteKey() {
			return this.completeKey;
		}

		public String getKey() {
			return this.key;
		}

		public int getSuffix() {
			return this.suffix;
		}

		public String getValue() {
			return this.value;
		}

		public void setSuffixClass(int suffix) {
			this.maxSuffixClass = suffix;
		}

		private void decreaseAppearances(int count) {
			this.appearances = this.appearances - count;
		}

		private int getMaxSuffixClass() {
			return this.maxSuffixClass;
		}

		private void increaseAppearances() {
			this.appearances++;
		}

		private boolean getKeep() {
			return this.keep;
		}

		private void setKeep(boolean keep) {
			this.keep = keep;
		}

		private boolean getIsLoadFromBundle() {
			return this.loadFromBundle;
		}

		private void setLoadFromBundle(boolean firstExecution) {
			this.loadFromBundle = firstExecution;
		}

	}

	class StringValue {
		private String id;
		private String value;

		public StringValue(String id, String value) {
			this.id = id;
			this.value = value;
		}

		public String getId() {
			return this.id;
		}

		public String getValue() {
			return this.value;
		}
	}

	class VaadinVars {
		private String id;
		private String type;

		public VaadinVars(String id, String type) {
			this.id = id;
			this.type = type;
		}

		public String getId() {
			return this.id;
		}

		public String getType() {
			return this.type;
		}
	}

	private static String PREFIX_I18NAWARE_CLASS = "com.opnworks.vaadin.i18n.ui.I18N";
	private CommandLineOutput commandLineOutput = new CommandLineOutput();
	private boolean optionChangeKey;
	private String javaFileName;
	private String javaFileFullClassName;
	private String[] stringToDiscard = { "<a href=", "alert(", "../", "http://" };
	private List<Key> listKey;
	private List<StringValue> lisStringValue = new ArrayList<StringValue>();
	private List<VaadinVars> listVaadinVars = new ArrayList<VaadinVars>();
	private List<ImportDeclaration> lidtarget;
	private ResourceBundle bundle = null;

	public KeyConverter() {
		listKey = new ArrayList<Key>();
	}

	// To obtain the Key object that contain a certain key
	public Key getKey(String key) {
		for (Key k : listKey ) {
			if (k.getKey().equals(key)) {
				return k;
			}
		}
		return null;
	}

	// Its determine is name corresponds to a Vaadin UI class
	private boolean isVaadinComponent(String name) {
		return getMatchingI18NClass(name) != null;
	}
	
	// Its return the vaadin var in declared in source that matches with id param
	private VaadinVars getVaadinVar(String id) {
		if (!id.equals("")) {
			for (VaadinVars vaadinVar : listVaadinVars ) {
				if (vaadinVar.getId().equals(id.toString())) {
					return vaadinVar;
				}
			}
		}
		return null;
	}

	// Its return any I18NAware class that matches with the vaadin clasName param, otherwise return a null class
	private Class<?> getMatchingI18NClass(String className) {
		Class<?> clas = null;
		if (!className.isEmpty()) {
			String classNameAux = PREFIX_I18NAWARE_CLASS.endsWith(".") ? PREFIX_I18NAWARE_CLASS.substring(0, PREFIX_I18NAWARE_CLASS.length() - 1)
					+ className : (PREFIX_I18NAWARE_CLASS + className);
			try {
				clas = Class.forName(classNameAux);
			}
			catch (ClassNotFoundException e) {
				ConverterException converterException = new ConverterException(e);
				commandLineOutput.getOutput().println("Class: " + classNameAux + " not found, cause: " + converterException.getCause());
			}
		}
		return clas;
	}

	// Its determine if some parameter constructor types is String
	private boolean existStringParamType(Class<?>[] parameterTypes) {
		for (Class<?> type : parameterTypes ) {
			try {
				if (type == Class.forName("java.lang.String")) {
					return true;
				}
			}
			catch (ClassNotFoundException e) {
				ConverterException converterException = new ConverterException(e);
				commandLineOutput.getOutput().println("Class: " + type.getName() + " not found, cause: " + converterException.getCause());
			}
		}
		return false;
	}

	// Its determine if some args in expression are instance of StringLiteralExpr
	private boolean thereIsStringArgs(List<Expression> args) {
		if (args != null) {
			for (Expression expr : args ) {
				if (expr instanceof StringLiteralExpr) {
					return true;
				}
			}
		}
		return false;
	}

	// Its determine if some parameter is marked with @I18NAwareMessage
	private boolean containsI18NAwareMessage(Annotation[] parameterAnnotation) {
		for (Annotation parameterAnnotation1 : parameterAnnotation ) {
			if (parameterAnnotation1 instanceof I18NAwareMessage) {
				return true;
			}
		}
		return false;
	}

	// Find the positions of @I18NAwareMessage parameters
	private List<Integer> getI18NAwareMessageParamsPositions(MethodCallExpr method) {
		List<Integer> paramPositions = new ArrayList<Integer>();
		// If there is not variable ui vaadin declared in the source, the method called in (method parameter) 
		// is not invoked by any variable ui vaadin, logically. getI18NAwareMessageParamsPositions(MethodCallExpr method)
		// is applied for the methods invoked by variables ui vaadin, previously "listVaadinVars" is filled with all
		// ui vaadin vars in the source that is analyzing in this moment. Its necessary to know what is the type of 
		// the variable that is invoking the method in process for to analyze their corresponding "I18NAvare ui class".
		if (listVaadinVars.isEmpty() || !thereIsStringArgs(method.getArgs())) {
			return paramPositions;
		}
		String methodName = method.getName();
		String methodInvokedByVar = method.getScope() == null ? "" : method.getScope().toString();
		String vaadinClass = getVaadinVar(methodInvokedByVar) == null ? "" : getVaadinVar(methodInvokedByVar).getType();
		Class<?> i18nClass = getMatchingI18NClass(vaadinClass);
		if ((i18nClass == null) ? true : (i18nClass.getMethods() == null) ? true : !(i18nClass.getMethods().length > 0)) {
			return paramPositions;
		}
		for (Method singleMethod : i18nClass.getMethods() ) {
			if (singleMethod.getName().equals(methodName) && (singleMethod.getParameterAnnotations() != null)
					&& ((singleMethod.getParameterTypes() == null) ? false : existStringParamType(singleMethod.getParameterTypes()))) {
				for (int index = 0; index < singleMethod.getParameterAnnotations().length; index++ ) {
					Annotation[] parameterAnnotations = singleMethod.getParameterAnnotations()[index];
					if (containsI18NAwareMessage(parameterAnnotations)) {
						paramPositions.add(index);
					}
				}
				return paramPositions;
			}
		}
		return paramPositions;
	}

	// Its determine if some parameters in constructor contains literals params marked by the @I18NAwareMessage annotation
	private List<Integer> getI18NAwareMessageParamsPositions(ObjectCreationExpr method) {
		List<Integer> paramPositions = new ArrayList<Integer>();
		if (!thereIsStringArgs(method.getArgs())) {
			return paramPositions;
		}
		String methodName = PREFIX_I18NAWARE_CLASS + method.getType().toString();
		Class<?> i18nClass = getMatchingI18NClass(method.getType().toString());
		if ((i18nClass == null) ? true : (i18nClass.getConstructors() == null) ? true : !(i18nClass.getConstructors().length > 0)) {
			return paramPositions;
		}
		for (Constructor<?> singleConstructor : i18nClass.getConstructors() ) {
			if ((singleConstructor.getName().equals(methodName))
					&& ((singleConstructor.getParameterTypes() == null) ? false : (existStringParamType(singleConstructor.getParameterTypes()))
							&& (singleConstructor.getParameterTypes().length == method.getArgs().size()))) {
				for (int index = 0; index < singleConstructor.getParameterAnnotations().length; index++ ) {
					Annotation[] parameterAnnotations = singleConstructor.getParameterAnnotations()[index];
					if (containsI18NAwareMessage(parameterAnnotations)) {
						paramPositions.add(index);
					}
				}
				return paramPositions;
			}
		}
		return paramPositions;
	}

	// Its keeps in listKey only the keys that are used in source
	public void restructurelistKey() {
		// It was necessary to make it of this way because eliminating directly from the listKey doesn't work well
		List<Key> auxlistKey = new ArrayList<Key>();
		for (int i = 0; i < listKey.size(); i++ ) {
			if (listKey.get(i).getKeep()) {
				auxlistKey.add(listKey.get(i));
			}
		}
		listKey.clear();
		listKey = auxlistKey;
	}

	// Its determine if exist a bundle with resourceName in resourcePath and in this case it is loaded
	public boolean existBundle(String resourcePath, String resourceName) {
		try {
			File file = new File(resourcePath);
			URL[] url = { file.toURI().toURL() };
			ClassLoader loader = new URLClassLoader(url);
			bundle = ResourceBundle.getBundle(resourceName, Locale.getDefault(), loader);
		}
		catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}

	public Key getCompleteKey(String key) {
		for (Key k : listKey ) {
			if (k.getCompleteKey().equals(key)) {
				return k;
			}
		}
		return null;
	}

	public List<Key> getListKey() {
		restructurelistKey();
		return listKey;
	}

	// Its return the suffix for any key in bundle in case of having it
	public int getSuffix(String key) {
		String suffix = "";
		for (int i = key.length(); i > 0; i-- ) {
			String s = key.substring(i - 1, i);
			if (s.equals("_")) {
				break;
			}
			else {
				suffix = s + suffix;
			}
		}
		try {
			return Integer.parseInt(suffix);
		}
		catch (Exception e) {
			return -1;
		}
	}

	// Its determine if a param is a key
	public boolean isKey(String key) {
		if ((key.length() < 25) & (!key.contains("_"))) {
			return false;
		}
		for (int i = 0; i < key.length(); i++ ) {
			String ss = key.substring(i, i + 1);
			if (("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_.".indexOf(ss) < 0)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Entry point to process every class file
	 * 
	 * @param filename
	 *            class filename
	 * @return the converted i18n-aware class
	 * @throws Exception
	 */
	public String proccessJavaFile(String filename) throws Exception {
		lidtarget = new ArrayList<ImportDeclaration>();
		// creates an input stream for the file to be parsed
		FileInputStream in = new FileInputStream(filename);
		CompilationUnit cutarget/* , xcufactory */;
		try {
			// parse the file
			cutarget = JavaParser.parse(in);
		}
		catch (Exception e) {
			throw e;
		}
		finally {
			in.close();
		}

		if (cutarget.getImports() != null) {
			for (ImportDeclaration id : cutarget.getImports() ) {
				String name = id.getName().toString();
				if (name.startsWith("com.vaadin.ui.")) {
					lidtarget.add(id);
				}
			}
		}

		if (cutarget.getPackage() != null) {
			javaFileFullClassName = cutarget.getPackage().getName().toString() + "." + javaFileName + ".";
		}
		else {
			javaFileFullClassName = javaFileName + ".";
		}

		List<TypeDeclaration> types = cutarget.getTypes();

		for (TypeDeclaration type : types ) {
			processType(type);
		}

		return cutarget.toString();
	}

	public void proccessProject(File dirBaseSrc, String projectPath, String pathBundle, String bundleName, String defaultLanguage) {

		if (listKey.isEmpty()) {
			boolean exist = existBundle(pathBundle, bundleName);
			if (exist) {
				updatelistKeyWithBundle();
			}
		}

		for (File filesrc : dirBaseSrc.listFiles() ) {
			if (filesrc.isDirectory()) {
				proccessProject(filesrc, projectPath, pathBundle, bundleName, defaultLanguage);
			}
			else {
				try {
					if (filesrc.getName().endsWith(".java")) {
						commandLineOutput.getOutput().println(filesrc.toString());

						lisStringValue.clear();

						javaFileName = filesrc.getName().replaceAll(".java", "");

						String classJava = proccessJavaFile(filesrc.getAbsolutePath());
						changeJavaClass(classJava, filesrc.getAbsolutePath());

						for (Key localkey : listKey ) {
							// if (!isInKeyList(localkey.key, generallistKey)){
							commandLineOutput.getOutput().println(localkey.completeKey + " = " + localkey.getValue());
							// System.out.println(localkey.completeKey + "_" + localkey.getSuffix() + " = " + localkey.getValue());
							// addKeyToGenerallistKey(localkey.key);
							// generallistKey.add(localkey);
							// }
						}
					}
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	// Its remove the suffix for any key
	public String removeSuffix(String key) {
		String suffix = String.valueOf(getSuffix(key));
		if (!suffix.equals("-1")) {
			return key.replaceAll("_" + suffix, "");
		}
		return key;
	}

	public void setChangeOptionKey(boolean opt) {
		this.optionChangeKey = opt;
	}

	public boolean getChangeOptionKey() {
		return this.optionChangeKey;
	}

	// Stores values of all String variables in each class
	private void addStringVarValue(String id, String value) {
		if (value.length() > 0) {
			if (!isValueInStringValueList(value)) {
				StringValue newStringValue = new StringValue(id, value);
				lisStringValue.add(newStringValue);
			}
		}
	}

	// Its determine if exist a va in listVaadinVars with id equal name param
	private boolean isVarInVaadinVarsList(String name) {
		for (VaadinVars v : listVaadinVars ) {
			if (v.getId().equals(name)) {
				return true;
			}
		}
		return false;
	}

	// Its add the vaadin vars in source to listVaadinVars
	private void addVaadinVars(String id, String type) {
		if (isVaadinComponent(type)) {
			if (!isVarInVaadinVarsList(id)) {
				VaadinVars newVar = new VaadinVars(id, type);
				listVaadinVars.add(newVar);
			}
		}
	}

	private void addKeyFromBundle(String key, String value) {
		int suffix = getSuffix(key);
		String auxKey = key;
		if (suffix > -1) {
			auxKey = removeSuffix(key);
		}
		else {
			suffix = 0;
		}

		Key newKey = new Key(auxKey, value, javaFileFullClassName, suffix, 0);
		newKey.decreaseAppearances(2);
		newKey.setLoadFromBundle(true);
		listKey.add(newKey);
		updateSuffixMax(auxKey, listKey);
	}

	public void updatelistKeyWithBundle() {
		Enumeration<String> bundleKeys = bundle.getKeys();

		while (bundleKeys.hasMoreElements()) {
			String key = bundleKeys.nextElement();
			String value = bundle.getString(key);
			addKeyFromBundle(key, value);
		}
	}

	// Its determine if a key was generated by this utilitarian
	private boolean isKeyGeneratedBySystem(String key) {
		String auxKey = generateKey(getCompleteKey(key).getValue());
		if (removeSuffix(key).equals(auxKey)) {
			return true;
		}
		return false;
	}

	private String spliKey(String key, int size) {
		if (key.length() > size) {
			return key.substring(0, size);
		}
		return key;
	}

	private void sumSuffix(String key, int count, List<Key> list) {
		for (Key k : list ) {
			if (k.getKey().equals(key)) {
				k.setSuffixClass(count);
			}
		}
	}

	@SuppressWarnings("unused")
	private void updateAppearances(String key) {
		for (Key k : listKey ) {
			if (k.getKey().equals(key)) {
				k.increaseAppearances();
			}
		}
	}

	// Update the state of the suffixes for each Key objects
	private void updateSuffixMax(String key, List<Key> list) {
		if (!list.isEmpty()) {
			int count = 0;
			for (Key k : list ) {
				if (k.getKey().equals(key)) {
					count++;
				}
			}
			if (count > 1) {
				sumSuffix(key, count - 1, list);
				count = 0;
			}
		}
	}

	// Its determine if a literal parameter in source is an String to discard
	private boolean isStringToDiscard(String name) {
		for (String element : stringToDiscard ) {
			if (name.contains(element)) {
				return true;
			}
		}
		return false;
	}

	// Its determine if a String param is translatable
	public boolean isTranslatable(StringLiteralExpr key) {
		if (!(key instanceof StringLiteralExpr) && (isStringToDiscard(key.getValue()))) {
			return false;
		}
		int count = 0;
		for (int i = 0; i < key.getValue().length(); i++ ) {
			String ss = key.getValue().substring(i, i + 1);
			if (("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(ss) < 0)) {
				count++;
			}
		}
		return (key.getValue().length() - count) > 2;
	}

	// Its return the original value corresponding to a key from bundle
	private void returnValueFromKey(StringLiteralExpr key) {
		if (isKey(key.getValue()) ? (isInKeyList(key.getValue())) : false) {
			if (!isKeyGeneratedBySystem(key.getValue())) {
				getCompleteKey(key.getValue()).setKeep(true);
			}
			key.setValue(getCompleteKey(key.getValue()).getValue());
		}
	}

	// Its add a new key generated to listKey and Literal parameter in source
	private void addKey(Key newKey, StringLiteralExpr key) {
		if (!getChangeOptionKey()) {
			key.setValue(newKey.getCompleteKey());
			listKey.add(newKey);
			getCompleteKey(newKey.getCompleteKey()).setKeep(true);
		}
		else {
			key.setValue(newKey.getValue());
			listKey.add(newKey);
			getCompleteKey(newKey.getCompleteKey()).setKeep(true);
		}
	}

	// Its add a generated key to listKey and bundle
	private void addGeneratedKeyToBundleAndSource(StringLiteralExpr key, String gKey, String value) {
		if (getKey(gKey) != null) {
			Key keyAux = getKey(gKey);
			Key newKey = new Key(gKey, value, javaFileFullClassName, keyAux.getMaxSuffixClass() + 1, keyAux.getMaxSuffixClass() + 1);
			addKey(newKey, key);
			updateSuffixMax(gKey, listKey);
		}
		else {
			Key newKey = new Key(gKey, value, javaFileFullClassName, 0, 0);
			newKey.setKeep(true);
			addKey(newKey, key);
		}
	}

	// It generates keys of around 30 characters
	private String generateKey(String caption) {
		String key = detectDelimiters(caption);
		String finalKey = spliKey(key, 30);

		if (finalKey.startsWith(".")) {
			finalKey = finalKey.replaceFirst(".", "");
		}
		if (finalKey.endsWith(".")) {
			finalKey = finalKey.substring(0, finalKey.length() - 1);
		}

		int _flag = 0;

		String finalKeyAux = "";

		for (int i = 0; i < finalKey.length(); i++ ) {

			String c = String.valueOf(finalKey.toCharArray()[i]);

			if (c.equals("_")) {
				_flag++;
			}
			else {
				_flag = 0;
			}

			if (_flag < 2) {
				finalKeyAux = finalKeyAux + c;
			}
		}

		if (finalKeyAux.endsWith("_")) {
			finalKey = finalKeyAux.substring(0, finalKeyAux.length() - 1);
		}
		else {
			finalKey = finalKeyAux;
		}

		if (finalKey.startsWith("_")) {
			finalKey = finalKey.replaceFirst("_", "");
		}

		for (int i = finalKey.length(); i > 0; i-- ) {
			String c = String.valueOf(finalKey.toCharArray()[i - 1]);
			if (c.equals("_")) {
				String subS = finalKey.substring(i, finalKey.length());
				if (isNumberParameter(subS)) {
					finalKey = finalKey + "x";
				}
				break;
			}
		}
		return javaFileFullClassName + finalKey; // + keyNumber;
	}

	// Its process a literal generating their corresponding key
	private void processKey(StringLiteralExpr key) {
		boolean insert = false;
		String value = key.getValue();
		String gKey = key.getValue();
		if (!isKey(gKey)) {
			insert = true;
			value = key.getValue().replace("\\n", "//n").replace("\"", "'");
			gKey = generateKey(gKey);
			if (isInKeyList(gKey)) {
				getCompleteKey(gKey).setKeep(true);
				if (getCompleteKey(gKey).getIsLoadFromBundle()) {
					key.setValue(gKey);
					insert = false;
				}
			}
		}
		else if ((isInKeyList(gKey))) {
			getCompleteKey(key.getValue()).setKeep(true);
		}

		if (insert) {
			addGeneratedKeyToBundleAndSource(key, gKey, value);
		}
	}

	// Its add the corresponding key to a literal to listKey, if the literal already is a key, return the corresponding text to source
	private void processLiteral(StringLiteralExpr key) {
		if ((key.getValue().length() > 0) ? (isTranslatable(key)) : false) {
			if (!getChangeOptionKey()) {
				processKey(key);
			}
			else {
				returnValueFromKey(key);
			}
		}
	}

	// Its change the original java class source by the modified java class
	private void changeJavaClass(String content, String path) {
		try {
			File javaClassDst = new File(path);
			FileOutputStream dest = new FileOutputStream(javaClassDst);
			dest.write(content.getBytes());
			dest.close();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Its clear the language bundle
	public void clearBundleFile(String path) {
		File delete = new File(path + ".properties");
		if (delete.exists()) {
			delete.delete();
		}
		try {
			new FileWriter(new File(path + ".properties"), true);
		}
		catch (IOException e) {
		}
	}

	// Its detect if a key contains delimiters (") and in this case eliminates them
	private String detectDelimiters(String caption) {
		String newCaption = spliKey(caption, 50);
		for (int i = 0; i < caption.length(); i++ ) {
			String ss = caption.substring(i, i + 1);
			if (("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_".indexOf(ss) < 0)) {
				newCaption = newCaption.replace(ss, "_");
			}
		}

		if (newCaption.endsWith("_")) {
			newCaption = newCaption.substring(0, newCaption.length() - 1);
		}

		return newCaption;
	}

	/**
	 * Process method arguments
	 * 
	 * @param largs
	 */
	private void processLiteralExprParam(ObjectCreationExpr exp) {
		List<Integer> paramsPositions = getI18NAwareMessageParamsPositions(exp);
		if (!paramsPositions.isEmpty()) {
			for (Integer pos : paramsPositions ) {
				processLiteral((StringLiteralExpr) exp.getArgs().get(pos));
			}
		}
	}

	// It determines if a text is assigned to a variable of String type in the class
	private boolean isValueInStringValueList(String value) {
		for (StringValue s : lisStringValue ) {
			if (s.getValue().equals(value)) {
				return true;
			}
		}
		return false;
	}

	// Its determine if listKey contains a Key with name "key"
	private boolean isInKeyList(String key) {
		// listKey.contains(k);
		for (Key k : listKey ) {
			if (k.getCompleteKey().equals(key)) {
				return true;
			}
		}
		return false;
	}

	// Its determine is any parameter in source is a number
	private boolean isNumberParameter(String parameter) {
		try {
			Float.parseFloat(parameter);
			return true;
		}
		catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	// Process arguments for all methods called in source
	private void processArgs(MethodCallExpr methodCallE) {
		List<Expression> largs = methodCallE.getArgs();
		if (largs != null) {
			List<Integer> paramsPositions = getI18NAwareMessageParamsPositions(methodCallE);
			if (!paramsPositions.isEmpty()) {
				for (Integer pos : paramsPositions ) {
					processLiteral((StringLiteralExpr) largs.get(pos));
				}
			}
			if (largs.size() > paramsPositions.size()) {
				for (int i = 0; (i < largs.size()) && !(paramsPositions.contains(i)); i++ ) {
					if (largs.get(i) instanceof ObjectCreationExpr) {
						ObjectCreationExpr exp = (ObjectCreationExpr) largs.get(i);
						processLiteralExprParam(exp);
					}
					else if (largs.get(i) instanceof MethodCallExpr) {
						processArgs((MethodCallExpr) largs.get(i));
					}
				}
			}
		}
	}

	/**
	 * Process every expression
	 * 
	 * @param expression
	 *            expression
	 * @return
	 */
	private Expression processExpression(Expression expression) {
		if (expression == null) {
			return null;
		}

		if (expression instanceof AssignExpr) {
			AssignExpr ae = (AssignExpr) expression;

			if (ae.getTarget() instanceof NameExpr) {
				if (ae.getValue() instanceof ObjectCreationExpr) {
					// varName = ae.getTarget().toString();
					ObjectCreationExpr exp = (ObjectCreationExpr) ae.getValue();
					processLiteralExprParam(exp);
				}
				else if (ae.getValue() instanceof MethodCallExpr) {
					// varName = ae.getTarget().toString();
					processArgs((MethodCallExpr) ae.getValue());
				}
				/*
				 * else if (ae.getValue() instanceof StringLiteralExpr) { addKey((StringLiteralExpr) ae.getValue(), optionChangeKey); }
				 */
			}
		}
		else if (expression instanceof VariableDeclarationExpr) {
			VariableDeclarationExpr vde = (VariableDeclarationExpr) expression;
			Type varType = vde.getType();
			if (vde.getType() instanceof ReferenceType) {
				for (VariableDeclarator vd : vde.getVars() ) {
					// varName = vd.getId().getName();
					addVaadinVars(vd.getId().getName(), varType.toString());
					addStringVarValue(vd.getId().getName(), vd.getInit().toString());
					if (vd.getInit() != null) {
						if (vd.getInit() instanceof MethodCallExpr) {
							processArgs((MethodCallExpr) vd.getInit());
						}
						else if (vd.getInit() instanceof ObjectCreationExpr) {
							ObjectCreationExpr exp = (ObjectCreationExpr) vd.getInit();
							processLiteralExprParam(exp);
						}
					}
				}
			}
		}
		else if (expression instanceof MethodCallExpr) {
			// varName = expression.toString().split(((MethodCallExpr) expression).getName())[0].replace(".", "");
			processArgs((MethodCallExpr) expression);
		}
		else if (expression instanceof CastExpr) {
			CastExpr ce = (CastExpr) expression;
			if (ce.getExpr() instanceof ObjectCreationExpr) {
				ObjectCreationExpr exp = (ObjectCreationExpr) ce.getExpr();
				processLiteralExprParam(exp);
			}
			else if (ce.getExpr() instanceof MethodCallExpr) {
				// varName = expression.toString().split(((MethodCallExpr) expression).getName())[0].replace(".", ""); processArgs((MethodCallExpr)
				// ce.getExpr());
			}
		}
		else if (expression instanceof ArrayCreationExpr) {
			ArrayCreationExpr ace = (ArrayCreationExpr) expression;
			if (ace.getInitializer() != null) {
				ArrayInitializerExpr aie = ace.getInitializer();
				if (aie.getValues() != null) {
					for (int i = 0; i < aie.getValues().size(); i++ ) {
						Expression expr = aie.getValues().get(i);
						if (expr != null) {
							aie.getValues().set(i, processExpression(expr));
						}
					}
				}
			}
		}
		else if (expression instanceof ObjectCreationExpr) {
			ObjectCreationExpr exp = (ObjectCreationExpr) expression;
			processLiteralExprParam(exp);
		}
		else if (expression instanceof ClassExpr) {
			// ClassExpr ce = (ClassExpr) expression;
			// ReferenceType rt = (ReferenceType) ce.getType();
			// ClassOrInterfaceType coi = (ClassOrInterfaceType) rt.getType();
		}
		else if (expression instanceof EnclosedExpr) {
			processExpression(((EnclosedExpr) expression).getInner());
		}
		else if (expression instanceof ConditionalExpr) {
			ConditionalExpr ce = (ConditionalExpr) expression;
			ce.setCondition(processExpression(ce.getCondition()));
			ce.setThenExpr(processExpression(ce.getThenExpr()));
			ce.setElseExpr(processExpression(ce.getElseExpr()));
		}
		/*
		 * else if (expression instanceof NameExpr || expression instanceof BinaryExpr || expression instanceof FieldAccessExpr || expression
		 * instanceof UnaryExpr || expression instanceof NullLiteralExpr || expression instanceof BooleanLiteralExpr) { // do nothing return
		 * expression; }
		 */
		else if (expression instanceof NameExpr) {
			// return expression;
		}
		else if (expression instanceof BinaryExpr) {
			// processBinaryExpr((BinaryExpr) expression);
		}
		else if (expression instanceof FieldAccessExpr) {
			// return expression;
		}
		else if (expression instanceof UnaryExpr) {
			// return expression;
		}
		else if (expression instanceof NullLiteralExpr) {
			// return expression;
		}
		else if (expression instanceof BooleanLiteralExpr) {
			// return expression;
		}
		else if (expression instanceof StringLiteralExpr) {
			processLiteral((StringLiteralExpr) expression);
		}
		else {
			throw new RuntimeException("Expression not supported " + expression.getClass());
		}
		return expression;
	}

	private boolean isBinaryExprOfLiterals(BinaryExpr exp) {

		Expression expLeft = ((BinaryExpr) exp).getLeft();
		Expression expRight = ((BinaryExpr) exp).getRight();

		if (!(expRight instanceof StringLiteralExpr)) {
			return false;
		}
		else {
			while (expLeft instanceof BinaryExpr) {

				expRight = ((BinaryExpr) expLeft).getRight();
				if (!(expRight instanceof StringLiteralExpr)) {
					return false;
				}
				else {
					expLeft = ((BinaryExpr) expLeft).getLeft();
				}
			}
			if (!(expLeft instanceof StringLiteralExpr)) {
				return false;
			}
		}

		return true;
	}

	@SuppressWarnings("unused")
	private BinaryExpr processBinaryExpr(BinaryExpr exp) {

		if (isBinaryExprOfLiterals(exp)) {
			processBinary(exp);
		}

		return exp;
	}

	private BinaryExpr processBinary(BinaryExpr exp) {

		Expression expLeft = ((BinaryExpr) exp).getLeft();
		Expression expRight = ((BinaryExpr) exp).getRight();

		if (expLeft instanceof BinaryExpr) {
			processBinary((BinaryExpr) expLeft);
		}
		else if (expLeft instanceof StringLiteralExpr) {
			processLiteral((StringLiteralExpr) expLeft);
		}

		if (expRight instanceof StringLiteralExpr) {
			processLiteral((StringLiteralExpr) expRight);
		}

		return exp;
	}

	/**
	 * Process every member
	 * 
	 * @param member
	 *            member to process
	 */
	private void processMember(BodyDeclaration member) {
		if (member instanceof FieldDeclaration) {

			FieldDeclaration fd = (FieldDeclaration) member;

			if (!(fd.getType() instanceof PrimitiveType)) {
				if (fd.getType().toString().equals("String")) {
					for (VariableDeclarator vd : fd.getVariables() ) {
						if (vd.getInit() != null) {
							if ((!vd.getInit().toString().contains("+")) & (!vd.getInit().toString().contains("null"))) {
								String v = ((StringLiteralExpr) vd.getInit()).getValue();
								if (!isValueInStringValueList(v)) {
									addStringVarValue(vd.getId().toString(), v);
									// processLiteral((StringLiteralExpr) vd.getInit(), optionChangeKey);
									String str = ((StringLiteralExpr) vd.getInit()).getValue();
									if (isKey(str)) {
										if (isInKeyList(str)) {
											getKey(str).setKeep(true);
										}
									}
								}
							}
						}
					}
				}
				else {
					for (VariableDeclarator vd : fd.getVariables() ) {
						addVaadinVars(vd.getId().toString(), fd.getType().toString());
						if (vd.getInit() != null) {
							if (vd.getInit() instanceof ObjectCreationExpr) {

								ObjectCreationExpr exp = (ObjectCreationExpr) vd.getInit();

								List<BodyDeclaration> anonymousClassBody = exp.getAnonymousClassBody();

								if (anonymousClassBody != null) {
									for (BodyDeclaration member1 : anonymousClassBody ) {
										processMember(member1);
									}
								}
								else {
									processLiteralExprParam(exp);
								}

							}
						}
					}
				}
			}
		}
		else if (member instanceof ClassOrInterfaceDeclaration) {
			for (BodyDeclaration member1 : ((ClassOrInterfaceDeclaration) member).getMembers() ) {
				processMember(member1);
			}
		}
		else if (member instanceof MethodDeclaration || member instanceof ConstructorDeclaration) {

			BlockStmt blockStmk = null;
			// List<Parameter> params;
			if (member instanceof MethodDeclaration) {
				MethodDeclaration method = (MethodDeclaration) member;
				blockStmk = method.getBody();
				// params = method.getParameters();
			}
			else {
				ConstructorDeclaration method = (ConstructorDeclaration) member;
				// constructorName = method.getName();
				blockStmk = method.getBlock();
				// params = method.getParameters();
			}

			processBlockStmt(blockStmk);
		}
	}

	/**
	 * Process every statement. Maybe not all Java statements be supported
	 * 
	 * @param statement
	 *            statement to process
	 */
	private void processStmt(Statement statement) {
		if (statement instanceof ExpressionStmt) {
			ExpressionStmt es = (ExpressionStmt) statement;
			processExpression(es.getExpression());
		}
		else if (statement instanceof BlockStmt) {
			BlockStmt bs = (BlockStmt) statement;
			processBlockStmt(bs);
		}
		else if (statement instanceof SynchronizedStmt) {
			SynchronizedStmt bs = (SynchronizedStmt) statement;
			BlockStmt bs1 = (BlockStmt) bs.getBlock();
			processBlockStmt(bs1);
		}
		else if (statement instanceof SwitchStmt) {
			SwitchStmt bs = (SwitchStmt) statement;
			for (SwitchEntryStmt swe : bs.getEntries() ) {
				if (swe.getStmts() != null) {
					for (Statement ss : swe.getStmts() ) {
						processStmt(ss);
					}
				}
			}
		}
		else if (statement instanceof IfStmt) {
			IfStmt bs = (IfStmt) statement;
			if (bs.getThenStmt() != null) {
				if (bs.getThenStmt() instanceof BlockStmt) {
					BlockStmt bs0 = (BlockStmt) bs.getThenStmt();
					processBlockStmt(bs0);
				}
				else if (bs.getThenStmt() instanceof IfStmt) {
					processStmt(bs.getThenStmt());
				}
				else {
					throw new RuntimeException("Not supported");
				}
			}
			if (bs.getElseStmt() != null) {
				if (bs.getElseStmt() instanceof BlockStmt) {
					BlockStmt bs0 = (BlockStmt) bs.getElseStmt();
					processBlockStmt(bs0);
				}
				else if (bs.getElseStmt() instanceof IfStmt) {
					processStmt(bs.getElseStmt());
				}
				else {
					throw new RuntimeException("Not supported");
				}
			}
		}
		/*
		 * else if (statement instanceof ExplicitConstructorInvocationStmt) { ExplicitConstructorInvocationStmt bs =
		 * (ExplicitConstructorInvocationStmt) statement; processArgs(bs.getArgs()); } else if (statement instanceof ReturnStmt) { ReturnStmt bs =
		 * (ReturnStmt) statement; // bs.setExpr(processExpression(bs.getExpr())); processExpression(bs.getExpr()); }
		 */
		else if (statement instanceof ForeachStmt) {
			ForeachStmt bs = (ForeachStmt) statement;
			BlockStmt bs1 = (BlockStmt) bs.getBody();
			processBlockStmt(bs1);
		}
		else if (statement instanceof WhileStmt) {
			WhileStmt bs = (WhileStmt) statement;
			BlockStmt bs1 = (BlockStmt) bs.getBody();
			processBlockStmt(bs1);
		}
		else if (statement instanceof DoStmt) {
			DoStmt bs = (DoStmt) statement;
			BlockStmt bs1 = (BlockStmt) bs.getBody();
			processBlockStmt(bs1);
		}
		else if (statement instanceof TryStmt) {
			TryStmt bs = (TryStmt) statement;
			BlockStmt bs1 = (BlockStmt) bs.getTryBlock();
			processBlockStmt(bs1);
			bs1 = (BlockStmt) bs.getFinallyBlock();
			processBlockStmt(bs1);
			if (bs.getCatchs() != null) {
				for (CatchClause cc : bs.getCatchs() ) {
					bs1 = (BlockStmt) cc.getCatchBlock();
					processBlockStmt(bs1);
				}
			}
		}
		else if (statement instanceof ForStmt) {
			ForStmt bs = (ForStmt) statement;
			BlockStmt bs1 = (BlockStmt) bs.getBody();
			processBlockStmt(bs1);
		}
		else if (statement instanceof ThrowStmt || statement instanceof BreakStmt || statement instanceof ContinueStmt) {
			// do nothing (for now)
		}
		else {
			// throw new RuntimeException("Not supported stmt " + statement.getClass());
		}
	}

	/**
	 * Process every type
	 * 
	 * @param type
	 */
	private void processType(TypeDeclaration type) {
		List<BodyDeclaration> members = type.getMembers();
		for (BodyDeclaration member : members ) {
			processMember(member);
		}
	}

	/**
	 * proccess a block statement
	 * 
	 * @param blockStmt
	 */
	void processBlockStmt(BlockStmt blockStmt) {
		if (blockStmt != null && blockStmt.getStmts() != null) {
			for (Statement s : blockStmt.getStmts() ) {
				processStmt(s);
			}
		}
	}
}
