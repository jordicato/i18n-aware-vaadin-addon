package com.opnworks.vaadin.i18n.converter.aop_mode;

import java.io.File;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class AopModeConverterTestData implements TestRule {

	private String testcaseName;

	private String resourceBaseName;
	private String defaultLanguage;
	private boolean rollback;

	private File sourceDirCopy;
	private File bundleDirCopy;

	private File expectedSourceDir;
	private File expectedBundleDir;

	@Override
	public Statement apply(Statement base, Description description) {
		return new AopModeConverterStatement(base, this);
	}

	public String getTestcaseName() {
		return testcaseName;
	}

	public void setTestcaseName(String testcaseName) {
		this.testcaseName = testcaseName;
	}

	public String getResourceBaseName() {
		return resourceBaseName;
	}

	public void setResourceBaseName(String resourceBaseName) {
		this.resourceBaseName = resourceBaseName;
	}

	public String getDefaultLanguage() {
		return defaultLanguage;
	}

	public void setDefaultLanguage(String defaultLanguage) {
		this.defaultLanguage = defaultLanguage;
	}

	public boolean isRollback() {
		return rollback;
	}

	public void setRollback(boolean rollback) {
		this.rollback = rollback;
	}

	public File getSourceDirCopy() {
		return sourceDirCopy;
	}

	public void setSourceDirCopy(File sourceDirCopy) {
		this.sourceDirCopy = sourceDirCopy;
	}

	public File getBundleDirCopy() {
		return bundleDirCopy;
	}

	public void setBundleDirCopy(File bundleDirCopy) {
		this.bundleDirCopy = bundleDirCopy;
	}

	public File getExpectedSourceDir() {
		return expectedSourceDir;
	}

	public void setExpectedSourceDir(File expectedSourceDir) {
		this.expectedSourceDir = expectedSourceDir;
	}

	public File getExpectedBundleDir() {
		return expectedBundleDir;
	}

	public void setExpectedBundleDir(File expectedBundleDir) {
		this.expectedBundleDir = expectedBundleDir;
	}
}
