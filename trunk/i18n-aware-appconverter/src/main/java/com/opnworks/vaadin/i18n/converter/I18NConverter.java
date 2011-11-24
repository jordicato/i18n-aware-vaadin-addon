package com.opnworks.vaadin.i18n.converter;

import japa.parser.JavaParser;
import japa.parser.ast.Comment;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.ImportDeclaration;
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
import japa.parser.ast.stmt.EmptyStmt;
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
import japa.parser.ast.type.ReferenceType;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.opnworks.vaadin.i18n.I18NAware;
import com.opnworks.vaadin.i18n.I18NAwareCaption;
import com.opnworks.vaadin.i18n.I18NAwareValue;
import com.opnworks.vaadin.i18n.service_impl.I18NAwareFactory;
import com.opnworks.vaadin.i18n.service_impl.I18NAwareMessageParametersHelper;
import com.opnworks.vaadin.i18n.ui.I18NCustomComponent;

/**
 * Class to rewrite standard Vaadin apps to I18N-aware apps You give the src
 * paths of original and converted apps. This class erases all dst exixting
 * files and coverts every file form the original app to the dst equivalent
 * 
 * @author aperez (Innovasoft Proyectos y Servicios)
 * 
 */
public class I18NConverter {
	private class I18NSupportReplacement {
		String methodorig, methodrepl;
		int posnstring;
		Class<?> replacement;

		I18NSupportReplacement(Class<?> replacement, String methodorig, String methodrepl, int posnstring) {
			this.replacement = replacement;
			this.methodorig = methodorig;
			this.methodrepl = methodrepl;
			this.posnstring = posnstring;
		}
	}

	private static int contadorLiterales = 0;

	public static List<String> literales = new ArrayList<String>();

	/**
	 * Renames a package to a new name accordig to rules: prefixsrcpackage and
	 * prefixdstpackage A call with ("a.c.b","a","ew") will return "ew.c.b"
	 * 
	 * @param currentpackage
	 *            original package
	 * @param prefixSrcPackage
	 *            prefix to be renamed
	 * @param prefixDstPackage
	 *            target package prefix to rename
	 * @return renamed package. If no prefixsrcpackage is matched, returns
	 *         equals currentpackage
	 */
	static String getCurrentDstPackage(String currentpackage, String prefixSrcPackage, String prefixDstPackage) {
		String currentdtspackage = currentpackage;
		if (currentpackage.indexOf(prefixSrcPackage) == 0) {
			currentdtspackage = prefixDstPackage + currentpackage.substring(prefixSrcPackage.length());
		}
		return currentdtspackage;
	}

	private boolean extractlits = false;
	private boolean huboModificaciones = false;
	private String i18nreplacementpreffix = "i18n:";
	private final I18NSupportReplacement[] i18nsr = new I18NSupportReplacement[] { new I18NSupportReplacement(I18NAwareCaption.class, "setCaption", "setCaptionMessage", 0),
			new I18NSupportReplacement(I18NAwareValue.class, "setValue", "setValueMessage", 0), new I18NSupportReplacement(null, "addTab", "addTab", 1) };
	private List<String> lidfactory = new ArrayList<String>();
	private List<ImportDeclaration> lidfactoryd = new ArrayList<ImportDeclaration>();
	private List<ImportDeclaration> lidtarget;
	private String prefixDstPackage = null;
	private String prefixSrcPackage = null;
	private boolean renamepackage = false;
	private boolean replaceI18nMethods = true;

	public I18NConverter() {

	}

	/**
	 * adds a literal to the literals list
	 * 
	 * @param expr
	 */
	private void addLiteral(StringLiteralExpr expr) {
		String s = expr.getValue();
		s = " " + s + " ";
		for (int i = 1; i < s.length() - 1; i++) {
			String ss = s.substring(i, i + 1);
			if ("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(ss) < 0) {
				s = s.substring(0, i) + "_" + s.substring(i + 1);
			}
		}
		s = s.trim() + "." + (contadorLiterales++) + "=" + expr.getValue();
		literales.add(s);
	}

	/**
	 * process variable declarator
	 * 
	 * @param vd
	 *            variable declarator to process
	 */
	private void changeVaadinVarDeclaratorNewReqPrecheck(VariableDeclarator vd) {
		if (vd.getInit() != null) {
			if (vd.getInit() instanceof ObjectCreationExpr) {
				vd.setInit(vaadinObjectCreationNoReqPrecheck((ObjectCreationExpr) vd.getInit()));
				huboModificaciones = true;
			} else if (vd.getInit() instanceof MethodCallExpr) {
				MethodCallExpr mce0 = (MethodCallExpr) vd.getInit();
				processArgs(mce0.getArgs());
			}
		}
	}

	/**
	 * extracts literals from a constructor
	 * 
	 * @param largs
	 * @param i18nclassname
	 */
	private void extractLiterals(List<Expression> largs, String i18nclassname) {
		boolean anyStringLiteralExpr = false;
		for (int i = 0; i < largs.size(); i++) {
			if (largs.get(i) instanceof StringLiteralExpr) {
				anyStringLiteralExpr = true;
				break;
			}
		}
		if (!anyStringLiteralExpr)
			return;
		try {
			Class<I18NAware> clazz = (Class<I18NAware>) Class.forName("com.opnworks.vaadin.i18n.ui." + i18nclassname);
			int[] anns = I18NAwareMessageParametersHelper.getI18NAwareMessageParameters(getClassConstructor(clazz, largs));
			if (anns == null)
				return;
			for (int indexann = 0; indexann < anns.length; indexann++) {
				if (largs.get(anns[indexann]) instanceof StringLiteralExpr) {
					addLiteral((StringLiteralExpr) largs.get(anns[indexann]));
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Identifies wich constructor is being used. LOT OF WORK PENDING
	 * 
	 * @param clazz
	 * @param largs
	 * @return
	 */
	private Constructor getClassConstructor(Class<I18NAware> clazz, List<Expression> largs) {
		// this is quite complicated.
		// 1st we look for the constructors with shuch number of parameters
		Constructor[] ac = clazz.getConstructors();
		List<Constructor> lc = new ArrayList<Constructor>();
		for (Constructor c : ac) {
			if (c.getParameterTypes().length == largs.size()) {
				lc.add(c);
			}
		}
		if (lc.size() == 1)
			return lc.get(0);
		// the easy search didn't worked
		for (Iterator<Constructor> it = lc.iterator(); it.hasNext();) {
			Constructor c = it.next();
			for (int i = 0; i < c.getParameterTypes().length; i++) {
				Expression e = largs.get(i);
				Class cl = c.getParameterTypes()[i];
				if (cl == getExpressionClass(e)) {
					continue;
				} else {
					it.remove();
					break;
				}
			}
		}
		if (lc.size() == 1)
			return lc.get(0);
		else
			throw new RuntimeException("Constructor not found");
	}

	/**
	 * Returns the class of an expression. LOTS OF WORK TO DO
	 * 
	 * @param expr
	 * @return
	 */
	private Class getExpressionClass(Expression expr) {
		if (expr instanceof StringLiteralExpr) {
			return String.class;
		} else
			return null;
	}

	/**
	 * Gets the I18N equivalent class for a given vaadin ui componet class name
	 * 
	 * @param vaadinName
	 *            vaadin ui component class name
	 * @return
	 */
	private String getI18NCompositeName(String vaadinName) {
		if (vaadinName != null) {
			if (vaadinName.startsWith("com.vaadin.ui.")) {
				vaadinName = vaadinName.replace("com.vaadin.ui.", "");
			}
			vaadinName = "I18N" + vaadinName;
			for (int index = 0; index < lidfactoryd.size(); index++) {
				ImportDeclaration id = lidfactoryd.get(index);
				if (id.getName().toString().endsWith(vaadinName)) {
					return vaadinName;
				}
			}
		}
		return null;
	}

	/**
	 * Gets the extraction of literals setting
	 * 
	 * @return
	 */
	public boolean isExtractlits() {
		return extractlits;
	}

	private I18NSupportReplacement isI18Method(MethodCallExpr mce) {
		if (mce.getArgs() != null && mce.getArgs().size() > 0)
			for (I18NSupportReplacement is : i18nsr) {
				if (is.methodorig.equals(mce.getName()) && mce.getArgs().size() > is.posnstring && mce.getArgs().get(is.posnstring) instanceof StringLiteralExpr) {
					StringLiteralExpr sle = (StringLiteralExpr) mce.getArgs().get(is.posnstring);
					if (sle.getValue().startsWith(i18nreplacementpreffix))
						return is;
				}
			}
		return null;
	}

	/**
	 * Checks if a given class name is a vaadon ui component
	 * 
	 * @param name
	 * @return
	 */
	private boolean isVaadinCompositeNameSupported(String name) {
		return getI18NCompositeName(name) != null;
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
		huboModificaciones = false;
		CompilationUnit cutarget/* , xcufactory */;
		try {
			// parse the file
			cutarget = JavaParser.parse(in);
		} catch (Exception e) {
			throw e;
		} finally {
			in.close();
		}
		// Obtener la lista de imports de componentes vaadin soportados en
		// factory
		lidfactory = new ArrayList<String>();
		lidfactoryd = new ArrayList<ImportDeclaration>();
		// Añadir el import de factory
		ImportDeclaration idf = new ImportDeclaration();
		NameExpr nef = new NameExpr(I18NAwareFactory.class.toString().replace("class ", ""));
		idf.setName(nef);
		lidfactoryd.add(idf);
		idf = new ImportDeclaration();
		nef = new NameExpr(I18NCustomComponent.class.toString().replace("class ", ""));
		idf.setName(nef);
		lidfactoryd.add(idf);

		// sacar la lista de i18n ui components para agregar a lista de imports
		// y de nombres
		for (Method m : I18NAwareFactory.class.getMethods()) {
			if (m.getName().startsWith("new")) {
				String i18nname = m.getReturnType().toString().replace("class com.vaadin.ui.", "com.opnworks.vaadin.i18n.ui.I18N");
				if (!lidfactory.contains(i18nname) && i18nname.startsWith("com.opnworks.vaadin.i18n.ui.I18N")) {
					lidfactory.add(i18nname);
					ImportDeclaration id = new ImportDeclaration();
					NameExpr ne = new NameExpr(i18nname);
					id.setName(ne);
					lidfactoryd.add(id);
				}
			}
		}
		if (cutarget.getImports() != null)
			for (ImportDeclaration id : cutarget.getImports()) {
				String name = id.getName().toString();
				if (name.startsWith("com.vaadin.ui.")) {
					for (ImportDeclaration idfac : lidfactoryd) {
						if (idfac.getName().toString().equals(name.replace("com.vaadin.ui.", "com.opnworks.vaadin.i18n.ui.I18N"))) {
							lidtarget.add(id);
							break;
						}
					}
				}
			}

		List<Comment> lcomments = cutarget.getComments();
		List<TypeDeclaration> types = cutarget.getTypes();

		if (renamepackage) {
			String cp = cutarget.getPackage().getName().toString();
			String dp = getCurrentDstPackage(cp, prefixSrcPackage, prefixDstPackage);
			if (!cp.equals(dp)) {
				cutarget.getPackage().setName(new NameExpr(dp));
				huboModificaciones = true;
			}
			if (cutarget.getImports() != null)
				for (ImportDeclaration id : cutarget.getImports()) {
					String name = id.getName().toString();
					dp = getCurrentDstPackage(name, prefixSrcPackage, prefixDstPackage);
					if (!name.equals(dp)) {
						id.setName(new NameExpr(dp));
						huboModificaciones = true;
					}
				}
		}
		// ahora miramos en cada clase
		for (TypeDeclaration type : types) {
			processType(type);
		}

		// add i18n declarations
		if (huboModificaciones) {
			if (cutarget.getImports() == null) {
				cutarget.setImports(new ArrayList<ImportDeclaration>());
			}
			for (ImportDeclaration id : lidfactoryd) {
				boolean found = false;

				for (ImportDeclaration idtarget : cutarget.getImports()) {
					if (id.getName().toString().equals(idtarget.getName().toString())) {
						found = true;
						break;
					}
				}
				if (!found)
					cutarget.getImports().add(id);
			}
		} else {
			cutarget.toString();
		}
		// prints the changed compilation unit

		return cutarget.toString();
	}

	/**
	 * Process method arguments
	 * 
	 * @param largs
	 */
	private void processArgs(List<Expression> largs) {
		if (largs != null) {
			for (int i = 0; i < largs.size(); i++) {
				if (largs.get(i) instanceof ObjectCreationExpr) {
					largs.set(i, vaadinObjectCreationNoReqPrecheck((ObjectCreationExpr) largs.get(i)));
				} else if (largs.get(i) instanceof MethodCallExpr) {
					processArgs(((MethodCallExpr) largs.get(i)).getArgs());
				}
			}
		}
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
					ae.setValue(vaadinObjectCreationNoReqPrecheck((ObjectCreationExpr) ae.getValue()));
				} else if (ae.getValue() instanceof MethodCallExpr) {
					processArgs(((MethodCallExpr) ae.getValue()).getArgs());
				}
			}
		} else if (expression instanceof VariableDeclarationExpr) {
			VariableDeclarationExpr vde = (VariableDeclarationExpr) expression;
			if (vde.getType() instanceof ReferenceType) {
				ReferenceType rt = (ReferenceType) vde.getType();
				if (rt.getType() instanceof ClassOrInterfaceType) {
					ClassOrInterfaceType coi2 = (ClassOrInterfaceType) rt.getType();
					String newClass = getI18NCompositeName(coi2.getName());

					if (newClass != null) {
						// coi2.setName(newClass);
						for (VariableDeclarator vdd : vde.getVars()) {
							changeVaadinVarDeclaratorNewReqPrecheck(vdd);
						}
					}
				}
			}
		} else if (expression instanceof MethodCallExpr) {
			processArgs(((MethodCallExpr) expression).getArgs());
			if (replaceI18nMethods) {
				processReplaceI18nMethods((MethodCallExpr) expression);
			}
		} else if (expression instanceof CastExpr) {
			CastExpr ce = (CastExpr) expression;
			if (ce.getExpr() instanceof ObjectCreationExpr) {
				ce.setExpr(vaadinObjectCreationNoReqPrecheck((ObjectCreationExpr) ce.getExpr()));
			} else if (ce.getExpr() instanceof MethodCallExpr) {
				processArgs(((MethodCallExpr) ce.getExpr()).getArgs());
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
			return vaadinObjectCreationNoReqPrecheck((ObjectCreationExpr) expression);
		} else if (expression instanceof ClassExpr) {
			ClassExpr ce = (ClassExpr) expression;
			ReferenceType rt = (ReferenceType) ce.getType();
			ClassOrInterfaceType coi = (ClassOrInterfaceType) rt.getType();
			if (isVaadinCompositeNameSupported(coi.getName())) {
				String newclassname = getI18NCompositeName(coi.getName());
				coi.setName(newclassname);
				huboModificaciones = true;

			}
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
		if (member instanceof ClassOrInterfaceDeclaration) {
			ClassOrInterfaceDeclaration coid = (ClassOrInterfaceDeclaration) member;
			if (coid != null && coid.getExtends() != null) {
				for (ClassOrInterfaceType oneextend : coid.getExtends()) {
					String newExtend = getI18NCompositeName(oneextend.getName());
					if (newExtend != null) {
						oneextend.setName(newExtend);
						huboModificaciones = true;
					}
				}
				for (BodyDeclaration member1 : ((ClassOrInterfaceDeclaration) member).getMembers()) {
					processMember(member1);
				}
			}
		} else if (member instanceof FieldDeclaration) {
			FieldDeclaration fd = (FieldDeclaration) member;
			if (isVaadinCompositeNameSupported(fd.getType().toString())) {
				ReferenceType rt = (ReferenceType) fd.getType();
				ClassOrInterfaceType coi = (ClassOrInterfaceType) rt.getType();
				String newclassname = getI18NCompositeName(coi.getName());

				// coi.setName(newclassname);
				for (VariableDeclarator vd : fd.getVariables()) {
					changeVaadinVarDeclaratorNewReqPrecheck(vd);
				}
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
			// don't change params
			// if (params != null) {
			// for (int i = 0; i < params.size(); i++) {
			// if (params.get(i).getType() instanceof ReferenceType) {
			// ReferenceType rt = (ReferenceType) params.get(i).getType();
			// ClassOrInterfaceType coi = (ClassOrInterfaceType) rt.getType();
			// String newname = getI18NCompositeName(coi.getName());
			//
			// if (newname != null && !newname.equals(coi.getName())) {
			// coi.setName(newname);
			// }
			// }
			// }
			// }
			processBlockStmt(blockStmk);
		} else if (member instanceof ClassOrInterfaceDeclaration) {
			processType((ClassOrInterfaceDeclaration) member);
			// for (BodyDeclaration member1 : ((ClassOrInterfaceDeclaration)
			// member).getMembers())
			// processMember(member1);
		}
	}

	/**
	 * This function replaces every 1st argument in a method call expression
	 * that begins with the value of i18nreplacementpreffix. Besides, if the
	 * method is replaceable, it is replaced with it's replacement and a casting
	 * 
	 * @param mce
	 */
	void processReplaceI18nMethods(MethodCallExpr mce) {
		if (mce.getScope() instanceof NameExpr) {
			I18NSupportReplacement isr = isI18Method(mce);
			if (isr != null) {
				mce.setName(isr.methodrepl);
				StringLiteralExpr sle = (StringLiteralExpr) mce.getArgs().get(isr.posnstring);
				sle.setValue(sle.getValue().substring(i18nreplacementpreffix.length()));
				if (isr.replacement != null) {
					EnclosedExpr ee = new EnclosedExpr();
					CastExpr ce = new CastExpr();
					ce.setExpr(mce.getScope());
					ReferenceType rt = new ReferenceType();
					ClassOrInterfaceType coi = new ClassOrInterfaceType(isr.replacement.getName());
					rt.setType(coi);
					ce.setType(rt);
					ee.setInner(ce);
					mce.setScope(ee);
				}
			}
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
		} else if (statement instanceof EmptyStmt) {
			// nothing to do
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
			throw new RuntimeException("Not supported stmt " + statement.getClass());
		}
	}

	/**
	 * Process every type
	 * 
	 * @param type
	 */
	private void processType(TypeDeclaration type) {
		if (type instanceof ClassOrInterfaceDeclaration) {
			ClassOrInterfaceDeclaration coid = (ClassOrInterfaceDeclaration) type;
			if (coid != null && coid.getExtends() != null)
				for (ClassOrInterfaceType oneextend : coid.getExtends()) {
					String newExtend = getI18NCompositeName(oneextend.getName());
					if (newExtend != null) {
						oneextend.setName(newExtend);
						huboModificaciones = true;
					}
				}
		} else {
			throw new RuntimeException("Type no soportado " + type.getClass());
		}
		List<BodyDeclaration> members = type.getMembers();
		for (BodyDeclaration member : members) {
			processMember(member);
		}
	}

	/**
	 * enables or disables literal extraction
	 * 
	 * @param extractlits
	 */
	public void setExtractlits(boolean extractlits) {
		this.extractlits = extractlits;
	}

	/**
	 * This method enables package rename of destination classes. This has
	 * nothing to do with the file pathname. The pathname will be changed
	 * accordingly by the calling class
	 * 
	 * @param prefixSrcPackage
	 * @param prefixDstPackage
	 */
	public void setRenameBasePackage(String prefixSrcPackage, String prefixDstPackage) {
		this.prefixSrcPackage = prefixSrcPackage;
		this.prefixDstPackage = prefixDstPackage;
		renamepackage = prefixSrcPackage != null && prefixDstPackage != null && !prefixSrcPackage.equals(prefixDstPackage);
	}

	/**
	 * 
	 * @param oce
	 *            converts a Vaadin obtect creation into a a I18N object using
	 *            the factory
	 * @return
	 */
	private Expression vaadinObjectCreationNoReqPrecheck(ObjectCreationExpr oce) {
		Expression expr = oce;
		String newname = getI18NCompositeName(oce.getType().getName());
		if (newname != null) {
			ClassOrInterfaceType coi = new ClassOrInterfaceType(newname);
			oce.setType(coi);
			// MethodCallExpr mce = new MethodCallExpr();
			// mce.setName("I18NAwareFactory.new" + oce.getType().getName());
			// mce.setArgs(oce.getArgs());
			// expr = mce;
			huboModificaciones = true;
		}
		// if (isVaadinCompositeNameSupported(oce.getType().getName())) {
		// oce.getType().setName("I18N" + oce.getType().getName());
		// // MethodCallExpr mce = new MethodCallExpr();
		// // mce.setName("I18NAwareFactory.new" + oce.getType().getName());
		// // mce.setArgs(oce.getArgs());
		// // expr = mce;
		// huboModificaciones = true;
		// }
		processArgs(oce.getArgs());
		if (extractlits)
			if (newname != null && oce.getArgs() != null && oce.getArgs().size() > 0) {
				extractLiterals(oce.getArgs(), newname);
			}
		if (oce.getAnonymousClassBody() != null) {
			List<BodyDeclaration> members = oce.getAnonymousClassBody();
			for (BodyDeclaration member : members) {
				processMember(member);
			}
		}
		return expr;
	}
}
