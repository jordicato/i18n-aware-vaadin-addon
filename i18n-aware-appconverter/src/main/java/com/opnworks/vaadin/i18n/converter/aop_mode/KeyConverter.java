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
import japa.parser.ast.stmt.ExplicitConstructorInvocationStmt;
import japa.parser.ast.stmt.ExpressionStmt;
import japa.parser.ast.stmt.ForStmt;
import japa.parser.ast.stmt.ForeachStmt;
import japa.parser.ast.stmt.IfStmt;
import japa.parser.ast.stmt.ReturnStmt;
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
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.lang.model.type.PrimitiveType;

import com.opnworks.vaadin.i18n.converter.main.CommandLineOutput;

/**
 * Class to get all vaadin widgets captions key
 * 
 * @author opnworks
 * 
 */
public class KeyConverter {
	@SuppressWarnings("unused")
	public class Tkey {
		private String key;
		private String completeKey;
		private String value;
		private String fullClassName;
		private int appearances;
		private int suffix;
		private int maxSuffixClass;

		public Tkey(String key, String value, String fullClassName, int suffix, int maxSuffixClass) {
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

		private String getFullClassName() {
			return this.fullClassName;
		}

		private int getKeyCount() {
			return this.maxSuffixClass + 1;
		}

		private int getMaxSuffixClass() {
			return this.maxSuffixClass;
		}

		private void increaseAppearances() {
			this.appearances++;
		}
	}

	class TStringValue {
		private String id;
		private String value;

		public TStringValue(String id, String value) {
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

	private CommandLineOutput commandLineOutput = new CommandLineOutput();
	private boolean optionChangeKey;
	private String javaFileName;
	private String javaFileFullClassName;
	// private String varName;
	private String[] validMethods = { "setCaption", "setDescription", "addComponent", "showNotification", "setDescriptionMessage", "addTab",
			"setItemCaption", "setCaptionMessage", "showNotification", "setValue", "addOrderToContainer", "RuntimeException", "addItem",
			"showComponent", "setValue", "setInputPrompt", "getWindow()", "addAction" };
	private String[] validClasses = { "EmailValidator", "StringLengthValidator", "ShortcutListener", "Action", "Object[]", "Command()", "Command",
			"ShortcutListener" };
	private String[] stringToDiscard = { "<a href=", "alert(", "../" };
	private List<Tkey> listKey = new ArrayList<Tkey>();
	private List<TStringValue> listStringValue = new ArrayList<TStringValue>();
	private List<ImportDeclaration> lidtarget;
	private ResourceBundle bundle = null;

	public KeyConverter() {

	}

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

	public boolean getChangeOptionKey() {
		return this.optionChangeKey;
	}

	// To obtain the Tkey object that contain a certain key
	public Tkey getKey(String key) {
		for (Tkey k : listKey ) {
			if (k.getKey().equals(key) | k.getCompleteKey().equals(key)) {
				return k;
			}
		}
		return null;
	}

	public List<Tkey> getListKey() {
		return listKey;
	}

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

	public boolean isKey(String key) {
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
		// Obtener la lista de imports de componentes vaadin soportados en cada clase
		if (cutarget.getImports() != null) {
			for (ImportDeclaration id : cutarget.getImports() ) {
				String name = id.getName().toString();
				if (name.startsWith("com.vaadin.ui.")) {
					lidtarget.add(id);
				}
				else if (isValidClass(name)) {
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

		// ahora miramos en cada clase
		for (TypeDeclaration type : types ) {
			processType(type);
		}

		return cutarget.toString();
	}

	public void proccessProject(File dirBaseSrc, String projectPath, String pathBundle, String bundleName) {

		if (listKey.isEmpty()) {
			boolean exist = existBundle(pathBundle, bundleName);

			if (exist) {
				updateListKeyWithBundle();
				deleteBundleFile(pathBundle + "/" + bundleName);
			}
		}

		for (File filesrc : dirBaseSrc.listFiles() ) {
			if (filesrc.isDirectory()) {
				proccessProject(filesrc, projectPath, pathBundle, bundleName);
			}
			else {
				try {
					if (filesrc.getName().endsWith(".java")) {
						commandLineOutput.getOutput().println(filesrc.toString());

						listStringValue.clear();

						javaFileName = filesrc.getName().replaceAll(".java", "");

						String classJava = proccessJavaFile(filesrc.getAbsolutePath());
						changeJavaClass(classJava, filesrc.getAbsolutePath());

						for (Tkey localkey : listKey ) {
							// if (!isInKeyList(localkey.key, generalListKey)){
							commandLineOutput.getOutput().println(localkey.completeKey + " = " + localkey.getValue());
							// System.out.println(localkey.completeKey + "_" + localkey.getSuffix() + " = " + localkey.getValue());
							// addKeyToGeneralListKey(localkey.key);
							// generalListKey.add(localkey);
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

	public void updateListKeyWithBundle() {
		Enumeration<String> bundleKeys = bundle.getKeys();

		while (bundleKeys.hasMoreElements()) {
			String key = bundleKeys.nextElement();
			String value = bundle.getString(key);
			addKeyFromBundle(key, value);

		}

	}

	private void addKey(StringLiteralExpr key, boolean option) {
		try {
			if (key.getValue().length() > 0) {
				boolean insert = true;
				if (key instanceof StringLiteralExpr) {
					if (!isStringToDiscard(key.getValue())) {
						if (!option) {
							String value = key.getValue();
							String gKey = key.getValue();

							if (!isKey(gKey)) {
								value = key.getValue().replace("\\n", "//n");
								gKey = generateKey(gKey);
							}
							else {
								if (!gKey.startsWith(javaFileFullClassName)) {
									gKey = javaFileFullClassName + gKey;
									updateAppearances(gKey);
									if (isInKeyList(gKey, listKey)) {
										if (getKey(gKey).getKeyCount() > getKey(gKey).getAppearances()) {
											insert = true;
										}
									}
								}
								else {
									String keyWithoutSuffix = removeSuffix(gKey);
									if (isInKeyList(keyWithoutSuffix, listKey)) {
										updateAppearances(keyWithoutSuffix);
										if (getKey(keyWithoutSuffix).getKeyCount() > getKey(keyWithoutSuffix).getAppearances()) {
											insert = false;
										}
									}
									else {
										Tkey newKey = new Tkey(gKey, value, javaFileFullClassName, 0, 0);
										listKey.add(newKey);
									}
								}
							}

							if (insert) {
								int select = valueAndKeyInList(gKey, value, listKey);

								switch (select) {
									case 1: {
										Tkey keyAux = getKey(gKey);

										Tkey newKey = new Tkey(gKey, value, javaFileFullClassName, keyAux.getMaxSuffixClass() + 1,
												keyAux.getMaxSuffixClass() + 1);

										key.setValue(newKey.getCompleteKey());

										listKey.add(newKey);
										updateSuffixMax(gKey, listKey);
									}
										;
									break;
									case 2: {
										Tkey newKey = new Tkey(gKey, value, javaFileFullClassName, 0, 0);
										key.setValue(newKey.getCompleteKey());

										listKey.add(newKey);
									}
										;
									break;
								}
							}
						}
						else {
							if (isKey(key.getValue())) {
								if (isInKeyList(key.getValue(), listKey)) {
									key.setValue(getKey(key.getValue()).getValue());
								}
							}
						}
					}
				}
			}
		}
		catch (Exception e) {
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

		Tkey newKey = new Tkey(auxKey, value, javaFileFullClassName, suffix, 0);
		newKey.decreaseAppearances(2);
		listKey.add(newKey);
		updateSuffixMax(auxKey, listKey);
	}

	// Obtains the value of each String variable declared in the class
	/*
	 * private String getValueById(String id) { for (TStringValue s : listStringValue ) { if (s.getId().equals(id)) { return s.getValue(); } } return
	 * ""; }
	 */

	// Stores values of all String variables in each class
	private void addStringVarValue(String id, String value) {
		if (value.length() > 0) {
			if (!isValueInStringValueList(value)) {
				TStringValue newStringValue = new TStringValue(id, value);
				listStringValue.add(newStringValue);
			}

		}
	}

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

	private void deleteBundleFile(String path) {
		File delete = new File(path + ".properties");
		if (delete.exists()) {
			delete.delete();
		}
	}

	private String detectDelimiters(String caption) {
		String newCaption = splitKey(caption, 50);
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

	private StringLiteralExpr extactExprCaption(ObjectCreationExpr exp) {

		Type type = exp.getType();
		if (isVaadinComponent(type.toString())) {

			boolean flag = false;

			try {
				flag = !exp.getArgs().isEmpty();
			}
			catch (Exception e) {
				// TODO: handle exception
			}

			if (flag) {
				if (exp.getArgs().get(0) instanceof StringLiteralExpr) {
					if (!isNumberParameter(exp.getArgs().get(0).toString())) {
						return ((StringLiteralExpr) exp.getArgs().get(0));
					}
				}
				else if (exp.getArgs().get(0) instanceof BinaryExpr) {
					processBinaryExpr((BinaryExpr) exp.getArgs().get(0));
				}
				else if (isIdInStringValueList(exp.getArgs().get(0).toString())) {
					// addKey(new StringLiteralExpr(getValueById(exp.getArgs().get(0).toString())), optionChangeKey);
				}
			}
		}
		return new StringLiteralExpr();
	}

	// It generates keys of around 30 characters
	private String generateKey(String caption) {
		String key = detectDelimiters(caption);
		// String keyNumber = String.valueOf(generateKeyNumber(caption));
		String finalKey = splitKey(key, 30);

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

		return javaFileFullClassName + finalKey; // + keyNumber;
	}

	// It determines if a string is an ID of String variable in the class
	private boolean isIdInStringValueList(String id) {
		for (TStringValue s : listStringValue ) {
			if (s.getId().equals(id)) {
				return true;
			}
		}
		return false;
	}

	private boolean isInKeyList(String key, List<Tkey> list) {
		// listKey.contains(k);
		for (Tkey k : list ) {
			if (k.getKey().equals(key) | k.getCompleteKey().equals(key)) {
				return true;
			}
		}
		return false;
	}

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

	private boolean isStringToDiscard(String name) {
		boolean is = false;
		for (String element : stringToDiscard ) {
			if (name.contains(element)) {
				is = true;
			}
		}
		return is;
	}

	private boolean isVaadinComponent(String name) {

		for (ImportDeclaration id : lidtarget ) {
			if (id.getName().getName().equals(name)) {
				return true;
			}
			else if (("I18N" + id.getName().getName()).equals(name)) {
				return true;
			}
		}
		return false;
	}

	private boolean isValidClass(String name) {
		boolean is = false;
		for (String validClasse : validClasses ) {
			if (name.endsWith(validClasse)) {
				is = true;
			}
		}
		return is;
	}

	/*
	 * private int generateKeyNumber(String caption) { int sum = 0; for (int i = 0; i < caption.length(); i++ ) { int I = caption.charAt(i); sum = sum
	 * + I; } return sum; }
	 */

	private boolean isValidMethod(String name) {
		boolean is = false;
		for (String validMethod : validMethods ) {
			if (validMethod.equals(name)) {
				is = true;
			}
		}
		return is;
	}

	// It determines if any Tkey object contains "value"
	private boolean isValueInKeyList(String value, List<Tkey> list) {
		// listKey.contains(k);
		for (Tkey k : list ) {
			if (k.getValue().equals(value)) {
				return true;
			}
		}
		return false;
	}

	// It determines if a text is assigned to a variable of String type in the class
	private boolean isValueInStringValueList(String value) {
		for (TStringValue s : listStringValue ) {
			if (s.getValue().equals(value)) {
				return true;
			}
		}
		return false;
	}

	private void processArgs(List<Expression> largs) {
		if (largs != null) {
			for (int i = 0; i < largs.size(); i++ ) {
				if (largs.get(i) instanceof ObjectCreationExpr) {
					ObjectCreationExpr exp = (ObjectCreationExpr) largs.get(i);
					addKey(extactExprCaption(exp), optionChangeKey);
				}
				else if (largs.get(i) instanceof MethodCallExpr) {
					processArgs(((MethodCallExpr) largs.get(i)).getArgs(), ((MethodCallExpr) largs.get(i)).getName());
				}
				else if (largs.get(i) instanceof StringLiteralExpr) {
					if (!isNumberParameter(largs.get(i).toString())) {
						addKey((StringLiteralExpr) largs.get(i), optionChangeKey);
					}
				}
			}
		}
	}

	private void processArgs(List<Expression> largs, String methodName) {
		if (isValidMethod(methodName)) {
			if (largs != null) {
				for (int i = 0; i < largs.size(); i++ ) {
					if (largs.get(i) instanceof ObjectCreationExpr) {
						ObjectCreationExpr exp = (ObjectCreationExpr) largs.get(i);
						addKey(extactExprCaption(exp), optionChangeKey);
					}
					else if (largs.get(i) instanceof MethodCallExpr) {
						processArgs(((MethodCallExpr) largs.get(i)).getArgs(), ((MethodCallExpr) largs.get(i)).getName());
					}
					else if (largs.get(i) instanceof StringLiteralExpr) {
						if (!isNumberParameter(largs.get(i).toString())) {
							addKey((StringLiteralExpr) largs.get(i), optionChangeKey);
						}
					}
					else if (largs.get(i) instanceof BinaryExpr) {
						processBinaryExpr((BinaryExpr) largs.get(i));
					}
					else if (isIdInStringValueList(largs.get(i).toString())) {
						// addKey(new StringLiteralExpr(getValueById(largs.get(i).toString())), optionChangeKey);
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
					addKey(extactExprCaption(exp), optionChangeKey);
				}
				else if (ae.getValue() instanceof MethodCallExpr) {
					// varName = ae.getTarget().toString();
					processArgs(((MethodCallExpr) ae.getValue()).getArgs(), ((MethodCallExpr) ae.getValue()).getName());
				}
			}
		}
		else if (expression instanceof VariableDeclarationExpr) {
			VariableDeclarationExpr vde = (VariableDeclarationExpr) expression;

			if (vde.getType() instanceof ReferenceType) {

				for (VariableDeclarator vd : vde.getVars() ) {
					// varName = vd.getId().getName();
					if (vd.getInit() != null) {
						if (vd.getInit() instanceof MethodCallExpr) {
							processArgs(((MethodCallExpr) vd.getInit()).getArgs(), ((MethodCallExpr) vd.getInit()).getName());
						}
						else if (vd.getInit() instanceof ObjectCreationExpr) {
							ObjectCreationExpr exp = (ObjectCreationExpr) vd.getInit();
							addKey(extactExprCaption(exp), optionChangeKey);
						}
					}
				}
			}
		}
		else if (expression instanceof MethodCallExpr) {
			// varName = expression.toString().split(((MethodCallExpr) expression).getName())[0].replace(".", "");
			processArgs(((MethodCallExpr) expression).getArgs(), ((MethodCallExpr) expression).getName());
		}
		else if (expression instanceof CastExpr) {
			CastExpr ce = (CastExpr) expression;
			if (ce.getExpr() instanceof ObjectCreationExpr) {
				ObjectCreationExpr exp = (ObjectCreationExpr) ce.getExpr();
				addKey(extactExprCaption(exp), optionChangeKey);
			}
			else if (ce.getExpr() instanceof MethodCallExpr) {
				// varName = expression.toString().split(((MethodCallExpr) expression).getName())[0].replace(".", "");
				processArgs(((MethodCallExpr) ce.getExpr()).getArgs(), ((MethodCallExpr) ce.getExpr()).getName());
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
			addKey(extactExprCaption(exp), optionChangeKey);
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
			//return expression;
		}
		else if (expression instanceof BinaryExpr) {
			processBinaryExpr((BinaryExpr) expression);
		}
		else if (expression instanceof FieldAccessExpr) {
			//return expression;
		}
		else if (expression instanceof UnaryExpr) {
			//return expression;
		}
		else if (expression instanceof NullLiteralExpr) {
			//return expression;
		}
		else if (expression instanceof BooleanLiteralExpr) {
			//return expression;
		}
		else if (expression instanceof StringLiteralExpr) {
			addKey((StringLiteralExpr) expression, optionChangeKey);
		}
		else {
			throw new RuntimeException("Expression not supported " + expression.getClass());
		}
		return expression;
	}

	private BinaryExpr processBinaryExpr(BinaryExpr exp) {
	
		Expression expLeft = ((BinaryExpr) exp).getLeft();
		Expression expRight = ((BinaryExpr) exp).getRight();
		
		if (expLeft instanceof BinaryExpr) {
			processBinaryExpr((BinaryExpr) expLeft);
		}
		else if (expLeft instanceof StringLiteralExpr) {
			String expString = ((StringLiteralExpr) expLeft).getValue();
			System.out.println(expString);
			addKey((StringLiteralExpr) expLeft, optionChangeKey);
		}
		
		if (expRight instanceof StringLiteralExpr) {
			String expString = ((StringLiteralExpr) expRight).getValue();
			System.out.println(expString);
			addKey((StringLiteralExpr) expRight, optionChangeKey);
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
									addKey((StringLiteralExpr) vd.getInit(), optionChangeKey);
								}
							}
						}
					}
				}
				else {
					for (VariableDeclarator vd : fd.getVariables() ) {
						if (vd.getInit() != null) {
							if (vd.getInit() instanceof ObjectCreationExpr) {
								ObjectCreationExpr exp = (ObjectCreationExpr) vd.getInit();
								addKey(extactExprCaption(exp), optionChangeKey);
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
		else if (statement instanceof ExplicitConstructorInvocationStmt) {
			ExplicitConstructorInvocationStmt bs = (ExplicitConstructorInvocationStmt) statement;
			processArgs(bs.getArgs());
		}
		else if (statement instanceof ReturnStmt) {
			ReturnStmt bs = (ReturnStmt) statement;
			//bs.setExpr(processExpression(bs.getExpr()));
			processExpression(bs.getExpr());
		}
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

	private String splitKey(String key, int size) {
		if (key.length() > size) {
			return key.substring(0, size);
		}
		return key;
	}

	private void sumSuffix(String key, int count, List<Tkey> list) {
		for (Tkey k : list ) {
			if (k.getKey().equals(key)) {
				k.setSuffixClass(count);
			}
		}
	}

	private void updateAppearances(String key) {
		for (Tkey k : listKey ) {
			if (k.getKey().equals(key)) {
				k.increaseAppearances();
			}
		}
	}

	// Update the state of the suffixes for each Tkey objects
	private void updateSuffixMax(String key, List<Tkey> list) {
		if (!list.isEmpty()) {
			int count = 0;
			for (Tkey k : list ) {
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

	// It determines if an Tkey object contains a certain key and value
	private int valueAndKeyInList(String key, String value, List<Tkey> list) {
		int v = 0;
		if (isInKeyList(key, list)) {
			v = 1;
			if (isValueInKeyList(value, list)) {
				v = 1;
			}
		}
		else {
			v = 2;
		}
		return v;
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
