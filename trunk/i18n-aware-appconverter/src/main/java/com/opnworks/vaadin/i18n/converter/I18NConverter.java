package com.opnworks.vaadin.i18n.converter;

import japa.parser.JavaParser;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.ImportDeclaration;
import japa.parser.ast.PackageDeclaration;
import japa.parser.ast.body.BodyDeclaration;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.ConstructorDeclaration;
import japa.parser.ast.body.FieldDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.Parameter;
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
import japa.parser.ast.type.ClassOrInterfaceType;
import japa.parser.ast.type.PrimitiveType;
import japa.parser.ast.type.ReferenceType;
import japa.parser.ast.type.Type;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.data.util.filter.IsNull;

/**
 * Class to get all vaadin widgets captions key
 * 
 * @author opnworks
 * 
 */
public class I18NConverter {

	/**
	 * @param currentpackage
	 *            original package
	 * @param prefixSrcPackage
	 *            prefix to be renamed
	 */
	static String getCurrentDstPackage(String currentpackage, String prefixSrcPackage, String prefixDstPackage) {
		String currentdtspackage = currentpackage;
		if (currentpackage.indexOf(prefixSrcPackage) == 0) {
			currentdtspackage = prefixDstPackage + currentpackage.substring(prefixSrcPackage.length());
		}
		return currentdtspackage;
	}


	class Tkey
	{
		String key;
		String value;
		String fullClassName;
		int suffix;
		int maxSuffixClass;
			
		public Tkey(String key, String value, String fullClassName, int suffix, int maxSuffixClass) {
			this.key = key;
			this.fullClassName = fullClassName;
			this.value = value;
			this.suffix = suffix;
			this.maxSuffixClass = maxSuffixClass;
		}		
		public void setSuffixClass(int suffix){
			this.maxSuffixClass = suffix;
		}
	}
		
	class TStringValue
	{
		String id;
		String value;		
		public TStringValue(String id, String value) {
			this.id = id;
			this.value = value;
		}		
	}
	
	private String javaFileName;
	private String javaFileFullClassName;
	private String[] validMethods = {"setCaption","setDescription", "addComponent", "showNotification", "setDescriptionMessage"};
	private List<Tkey> listKey = new ArrayList<Tkey>();
	private List<Tkey> generalListKey = new ArrayList<Tkey>();
	private List<TStringValue> listStringValue = new ArrayList<TStringValue>();
	private List<ImportDeclaration> lidtarget;
	
	public I18NConverter() {

	}
	
	
	private boolean isInKeyList(String key, List<Tkey> list){		
		//listKey.contains(k);
		for (Tkey k : list){
			if (k.key.equals(key)){
				return true;
			}
		}
		return false;		
	}

	//Determina si un objeto Tkey contiene un value
	private boolean isValueInKeyList(String value, List<Tkey> list){		
		//listKey.contains(k);
		for (Tkey k : list){
			if (k.value.equals(value)){
				return true;
			}
		}
		return false;		
	}
	
	//Determina si un objeto Tkey contiene una determinada key y value
	private int valueAndKeyInList(String key, String value, List<Tkey> list){
		int v = 0;
		if (isInKeyList(key, list)){
			v = 1;
			if (isValueInKeyList(value, list)){
				v = 2;
			}
		} else {
			v = 3;
		}
		
		return v;
		
	}
	
	private void sumSuffix(String key, int count, List<Tkey> list){
		for (Tkey k : list){
			if (k.key.equals(key)){
				k.setSuffixClass(count);
			}
		}
	}
	
	//Actualizar el estado de los sufijos para cada Tkey
	private void updateSuffixMax(String key, List<Tkey> list){
		if (!list.isEmpty()){
			int count = 0;
			for (Tkey k : list){
				if (k.key.equals(key)){
					count++;
				}
			}
			sumSuffix(key, count, list);
			count = 0;
		}		
	}
	
	/*private int existSuffix(String caption){
		int pos = 0;
		int num = 0;
		if (caption.contains("_")){
			pos = caption.indexOf("_"); 
			num = Integer.parseInt(caption.substring(pos+1, caption.length()));
		}	
		
		return num;
	}*/
	
	//Para obtener los objeto Tkey que contienen una determinada key
	public Tkey getKey(String key){
		for (Tkey k : listKey){
			if (k.key.equals(key)){
				return k;
			}
		}
		return null;
	}
	
	//Adiciona las llaves creadas
	private void addKey(String key){
		if (key.length() > 1){
			String gKey = generateKey(key);
			int select = valueAndKeyInList(gKey, key, listKey);
			
			
			switch (select) {
				case 1: {					
					Tkey keyAux = getKey(gKey);
					
					Tkey newKey = new Tkey(gKey,key,javaFileFullClassName,keyAux.maxSuffixClass+1,keyAux.maxSuffixClass+1);
					updateSuffixMax(gKey,listKey);
					listKey.add(newKey);					
					int a = 9;
					
				};break;
				case 2: {
					
				};break;
				case 3: {
					Tkey newKey = new Tkey(gKey,key,javaFileFullClassName,0,0);
					listKey.add(newKey);					
				};break;
					
			}

		}
		
	}	

	//Determina si un texto esta asignado a una variable de tipo String en la clase
	private boolean isValueInStringValueList(String value){		
		for (TStringValue s : listStringValue){
			if (s.value.equals(value)){
				return true;
			}
		}
		return false;		
	}
	
	//Determina si una cadena es un ID de variable de tipo String en la clase
	private boolean isIdInStringValueList(String id){		
		for (TStringValue s : listStringValue){
			if (s.id.equals(id)){
				return true;
			}
		}
		return false;		
	}

	//Obtiene el valor de cada variable de tipo String valida declarada en la clase
	private String getValueById(String id){		
		for (TStringValue s : listStringValue){
			if (s.id.equals(id)){
				return s.value;
			}
		}
		return "";
	}
	
	//Almacena todos los valores de las variables de tipo String en cada clase
	private void addStringVarValue(String id, String value){
		if (value.length() > 0){
			if (!isValueInStringValueList(value)){
				TStringValue newStringValue = new TStringValue(id,value);
				listStringValue.add(newStringValue);
			}
		
		}		
	}
	
	/*private String getCompositeName(String vaadinName) {
		if (vaadinName != null) {
			if (vaadinName.startsWith("com.vaadin.ui.")){
				return vaadinName;
			}				
		}
		return null;
	}*/
	
	public List<Tkey> getGeneralListKey(){
		return generalListKey;
	}
	
	public List<Tkey> getListKey(){
		return listKey;
	}

	
	public void proccessProject(File dirBaseSrc, String path){
		
		for (File filesrc : dirBaseSrc.listFiles()) {
			if (filesrc.isDirectory()) {
				proccessProject(filesrc, path);
			} else {
				try {

					if (filesrc.getName().endsWith(".java")) {
						System.out.println(filesrc.toString());
						
						listStringValue.clear();
						
						javaFileName = filesrc.getName().replaceAll(".java", "");
						
						proccessJavaFile(filesrc.getAbsolutePath());	
												

						for (Tkey localkey : listKey){
							//if (!isInKeyList(localkey.key, generalListKey)){
								System.out.println(localkey.key + "_" + localkey.suffix + " = " + localkey.value);
								//addKeyToGeneralListKey(localkey.key);
								//generalListKey.add(localkey);
							//}
						}						
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}		
	}

	private String detectDelimiters(String caption){
		boolean caracterExtranno = false;
		String newCaption = splitKey(caption, 50);
        for (int i = 0; i < caption.length() - 1; i++) {
        	String ss = caption.substring(i, i + 1);
			if (("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ.".indexOf(ss) < 0)) {
				if (!caracterExtranno){
					newCaption = newCaption.replace(ss, ".");
					caracterExtranno = true;
				} else {
					newCaption = newCaption.replace(ss, "");
				}
			} else {
				caracterExtranno = false;
			}
		}

        return newCaption;
	}

	private String splitKey(String key, int size){
		if (key.length() > size){
			return key.substring(0, size);
		}
		
		return key;
	}
	//Genera llaves de 30 caractéres
	private String generateKey(String caption){
		String key = detectDelimiters(caption);
		
		String finalKey = splitKey(key, 30);
		
        if (finalKey.startsWith(".")){
        	finalKey = finalKey.replaceFirst(".", "");
        }
        if (finalKey.endsWith(".")){
        	finalKey = finalKey.substring(0, finalKey.length()-1);
        } 
		
		return finalKey;
		
	}
	
	/**
	 * Entry point to process every class file
	 * 
	 * @param filename
	 *            class filename
	 * @return the converted i18n-aware class
	 * @throws Exception
	 */
	public void proccessJavaFile(String filename) throws Exception {
		lidtarget = new ArrayList<ImportDeclaration>();
		//listKey.clear();
		// creates an input stream for the file to be parsed
		FileInputStream in = new FileInputStream(filename);
		CompilationUnit cutarget/* , xcufactory */;
		try {
			// parse the file
			cutarget = JavaParser.parse(in);
		} catch (Exception e) {
			throw e;
		} finally {
			in.close();
		}
		// Obtener la lista de imports de componentes vaadin soportados en cada clase

		if (cutarget.getImports() != null)
			for (ImportDeclaration id : cutarget.getImports()) {
				String name = id.getName().toString();
				if (name.startsWith("com.vaadin.ui.")) {
					lidtarget.add(id);
				}
			}		
		
		javaFileFullClassName = cutarget.getPackage().getName().toString().replace(".", "_") + "_" + javaFileName + "_";
		
		List<TypeDeclaration> types = cutarget.getTypes();

		// ahora miramos en cada clase
		for (TypeDeclaration type : types) {
			processType(type);
		}

	}

	private boolean isVaadinComponent(String name){
		
		for (ImportDeclaration id : lidtarget){
			if (id.getName().getName().equals(name)){
				return true;
			} else if (("I18N"+id.getName().getName()).equals(name)){
				return true;
			}
		}		
		return false;		
	}
	
	/**
	 * Process method arguments
	 * 
	 * @param largs
	 */
	
	private String extactExprCaption(ObjectCreationExpr exp){
		
		Type type = exp.getType();
			if (isVaadinComponent(type.toString())){
			
				boolean flag = false;
				
				try {
					flag = !exp.getArgs().isEmpty();
				} catch (Exception e) {
					// TODO: handle exception					
				}				
				
				if (flag){
					if (exp.getArgs().get(0) instanceof StringLiteralExpr){						
						return ((StringLiteralExpr) exp.getArgs().get(0)).getValue();
					} else if (isIdInStringValueList(exp.getArgs().get(0).toString())) {
						addKey(getValueById(exp.getArgs().get(0).toString()));
					}
				}
			}
		return "";
	}
	
	private boolean isValidMethod(String name){
		boolean is = false;
		for (int i = 0; i < validMethods.length-1; i++){
			if (validMethods[i].equals(name)){
				is = true;
			}
		}
		return is;
	}
	
	private void processArgs(List<Expression> largs, String methodName) {
		if (isValidMethod(methodName)){	
			if (largs != null) {
				for (int i = 0; i < largs.size(); i++) {
					if (largs.get(i) instanceof ObjectCreationExpr) {
						ObjectCreationExpr exp = (ObjectCreationExpr) largs.get(i);
						addKey(extactExprCaption(exp));

					} else if (largs.get(i) instanceof MethodCallExpr) {
						processArgs(((MethodCallExpr) largs.get(i)).getArgs(), ((MethodCallExpr) largs.get(i)).getName());
						
	
					} else if (largs.get(i) instanceof StringLiteralExpr){
						addKey(((StringLiteralExpr) largs.get(i)).getValue());						
					} else if (isIdInStringValueList(largs.get(i).toString())){
						addKey(getValueById(largs.get(i).toString()));
					}
				}
			}
		} 	
	}
	
	private void processArgs(List<Expression> largs) {
		if (largs != null) {
				for (int i = 0; i < largs.size(); i++) {
					if (largs.get(i) instanceof ObjectCreationExpr) {
						ObjectCreationExpr exp = (ObjectCreationExpr) largs.get(i);
						addKey(extactExprCaption(exp));

					} else if (largs.get(i) instanceof MethodCallExpr) {
						processArgs(((MethodCallExpr) largs.get(i)).getArgs(), ((MethodCallExpr) largs.get(i)).getName());
						
	
					} else if (largs.get(i) instanceof StringLiteralExpr){
						addKey(((StringLiteralExpr) largs.get(i)).getValue());						
					}
				}
			}	
	}

	private String removeQuotation(String param){
		if (param.length() > 2){			
			return param.substring(1, param.length()-1);
		}
		return "";
	}
	
	/**
	 * proccess a block statement
	 * 
	 * @param blockStmt
	 */
	void processBlockStmt(BlockStmt blockStmt) {
		if (blockStmt != null && blockStmt.getStmts() != null) {
			for (Statement s : blockStmt.getStmts()) {
				processStmt(s);
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
		if (expression == null)
			return null;
		
		if (expression instanceof AssignExpr) {
			AssignExpr ae = (AssignExpr) expression;
			if (ae.getTarget() instanceof NameExpr) {
				if (ae.getValue() instanceof ObjectCreationExpr) {
					
					ObjectCreationExpr exp = (ObjectCreationExpr) ae.getValue();
					addKey(extactExprCaption(exp));
					
				} else if (ae.getValue() instanceof MethodCallExpr) {
					processArgs(((MethodCallExpr) ae.getValue()).getArgs(), ((MethodCallExpr) ae.getValue()).getName());
				}
			}
		} else if (expression instanceof VariableDeclarationExpr) {
			VariableDeclarationExpr vde = (VariableDeclarationExpr) expression;
			
			if (vde.getType() instanceof ReferenceType) {

				for (VariableDeclarator vd : vde.getVars()) {
					if (vd.getInit() != null) {
						if (vd.getInit() instanceof ObjectCreationExpr) {
							
							ObjectCreationExpr exp = (ObjectCreationExpr) vd.getInit();
							addKey(extactExprCaption(exp));

						}
					}
				}
			}
		} else if (expression instanceof MethodCallExpr) {
		
			processArgs(((MethodCallExpr) expression).getArgs(), ((MethodCallExpr) expression).getName());

		} else if (expression instanceof CastExpr) {
			CastExpr ce = (CastExpr) expression;
			if (ce.getExpr() instanceof ObjectCreationExpr) {
				
				ObjectCreationExpr exp = (ObjectCreationExpr) ce.getExpr();
				addKey(extactExprCaption(exp));
				
			} else if (ce.getExpr() instanceof MethodCallExpr) {
				processArgs(((MethodCallExpr) ce.getExpr()).getArgs(), ((MethodCallExpr) ce.getExpr()).getName());
			}
		} else if (expression instanceof ArrayCreationExpr) {
			ArrayCreationExpr ace = (ArrayCreationExpr) expression;
			if (ace.getInitializer() != null) {
				ArrayInitializerExpr aie = ace.getInitializer();
				if (aie.getValues() != null)
					for (int i = 0; i < aie.getValues().size(); i++) {
						Expression expr = aie.getValues().get(i);
						if (expr != null)
							aie.getValues().set(i, processExpression(expr));
					}
			}
		} else if (expression instanceof ObjectCreationExpr) {
			
			ObjectCreationExpr exp = (ObjectCreationExpr) expression;
			addKey(extactExprCaption(exp));
			
		} else if (expression instanceof ClassExpr) {
			ClassExpr ce = (ClassExpr) expression;
			ReferenceType rt = (ReferenceType) ce.getType();
			ClassOrInterfaceType coi = (ClassOrInterfaceType) rt.getType();

		} else if (expression instanceof EnclosedExpr) {
			processExpression(((EnclosedExpr) expression).getInner());
		} else if (expression instanceof ConditionalExpr) {
			ConditionalExpr ce = (ConditionalExpr) expression;
			ce.setCondition(processExpression(ce.getCondition()));
			ce.setThenExpr(processExpression(ce.getThenExpr()));
			ce.setElseExpr(processExpression(ce.getElseExpr()));
		} else if (expression instanceof NameExpr || expression instanceof StringLiteralExpr || expression instanceof BinaryExpr || expression instanceof FieldAccessExpr
				|| expression instanceof UnaryExpr || expression instanceof NullLiteralExpr || expression instanceof BooleanLiteralExpr) {
			// do nothing
			return expression;
		} else {
			throw new RuntimeException("Expression not supported " + expression.getClass());
		}
		return expression;
	}
	
	/**
	 * Process every member
	 * 
	 * @param member
	 *            member to process
	 */
	private void processMember(BodyDeclaration member) {
		if (member instanceof FieldDeclaration){	
			
			FieldDeclaration fd = (FieldDeclaration) member;
			
			if (!(fd.getType() instanceof PrimitiveType)){
				if (fd.getType().toString().equals("String")){
					for (VariableDeclarator vd : fd.getVariables()) {
						if (vd.getInit() != null) {
							if ((!vd.getInit().toString().contains("+")) & (!vd.getInit().toString().contains("null"))){
								String v = ((StringLiteralExpr) vd.getInit()).getValue();
								if (!isValueInStringValueList(v)){									
									addStringVarValue(vd.getId().toString(), v);
								}
							}
						}
					}
				} else {
					for (VariableDeclarator vd : fd.getVariables()) {
						if (vd.getInit() != null) {
							if (vd.getInit() instanceof ObjectCreationExpr) {
								
								ObjectCreationExpr exp = (ObjectCreationExpr) vd.getInit();
								addKey(extactExprCaption(exp));
		
							}
						}
					}
				}
			}
			
		}else if (member instanceof ClassOrInterfaceDeclaration) {
				for (BodyDeclaration member1 : ((ClassOrInterfaceDeclaration) member).getMembers()) {
					processMember(member1);
				}				
		} else if (member instanceof MethodDeclaration || member instanceof ConstructorDeclaration) {

			BlockStmt blockStmk;
			List<Parameter> params;
			if (member instanceof MethodDeclaration) {
				MethodDeclaration method = (MethodDeclaration) member;
				blockStmk = method.getBody();
				params = method.getParameters();
			} else {
				ConstructorDeclaration method = (ConstructorDeclaration) member;
				blockStmk = method.getBlock();
				params = method.getParameters();
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
			
		} else if (statement instanceof BlockStmt) {
			BlockStmt bs = (BlockStmt) statement;
			processBlockStmt(bs);
		} else if (statement instanceof SynchronizedStmt) {
			SynchronizedStmt bs = (SynchronizedStmt) statement;
			BlockStmt bs1 = (BlockStmt) bs.getBlock();
			processBlockStmt(bs1);	
		} else if (statement instanceof SwitchStmt) {
			SwitchStmt bs = (SwitchStmt) statement;
			for (SwitchEntryStmt swe : bs.getEntries()) {
				if (swe.getStmts() != null)
					for (Statement ss : swe.getStmts()) {
						processStmt(ss);
					}
			}
		} else if (statement instanceof IfStmt) {
			IfStmt bs = (IfStmt) statement;
			if (bs.getThenStmt() != null) {
				if (bs.getThenStmt() instanceof BlockStmt) {
					BlockStmt bs0 = (BlockStmt) bs.getThenStmt();
					processBlockStmt(bs0);
				} else if (bs.getThenStmt() instanceof IfStmt) {
					processStmt(bs.getThenStmt());
				} else {
					throw new RuntimeException("Not supported");
				}
			}
			if (bs.getElseStmt() != null) {
				if (bs.getElseStmt() instanceof BlockStmt) {
					BlockStmt bs0 = (BlockStmt) bs.getElseStmt();
					processBlockStmt(bs0);
				} else if (bs.getElseStmt() instanceof IfStmt) {
					processStmt(bs.getElseStmt());
				} else {
					throw new RuntimeException("Not supported");
				}
			}
		} else if (statement instanceof ExplicitConstructorInvocationStmt) {
			ExplicitConstructorInvocationStmt bs = (ExplicitConstructorInvocationStmt) statement;
			processArgs(bs.getArgs());
		} else if (statement instanceof ReturnStmt) {
			ReturnStmt bs = (ReturnStmt) statement;
			bs.setExpr(processExpression(bs.getExpr()));
		} else if (statement instanceof ForeachStmt) {
			ForeachStmt bs = (ForeachStmt) statement;
			BlockStmt bs1 = (BlockStmt) bs.getBody();
			processBlockStmt(bs1);
		} else if (statement instanceof WhileStmt) {
			WhileStmt bs = (WhileStmt) statement;
			BlockStmt bs1 = (BlockStmt) bs.getBody();
			processBlockStmt(bs1);
		} else if (statement instanceof TryStmt) {
			TryStmt bs = (TryStmt) statement;
			BlockStmt bs1 = (BlockStmt) bs.getTryBlock();
			processBlockStmt(bs1);
			bs1 = (BlockStmt) bs.getFinallyBlock();
			processBlockStmt(bs1);
			if (bs.getCatchs() != null) {
				for (CatchClause cc : bs.getCatchs()) {
					bs1 = (BlockStmt) cc.getCatchBlock();
					processBlockStmt(bs1);
				}
			}
		} else if (statement instanceof ForStmt) {
			ForStmt bs = (ForStmt) statement;
			BlockStmt bs1 = (BlockStmt) bs.getBody();
			processBlockStmt(bs1);
		} else if (statement instanceof ThrowStmt || statement instanceof BreakStmt || statement instanceof ContinueStmt) {
			// do nothing (for now)
		} else {
			//throw new RuntimeException("Not supported stmt " + statement.getClass());
		}
	}

	/**
	 * Process every type
	 * 
	 * @param type
	 */
	private void processType(TypeDeclaration type) {
		List<BodyDeclaration> members = type.getMembers();
		for (BodyDeclaration member : members) {
			processMember(member);
		}
	}

}
