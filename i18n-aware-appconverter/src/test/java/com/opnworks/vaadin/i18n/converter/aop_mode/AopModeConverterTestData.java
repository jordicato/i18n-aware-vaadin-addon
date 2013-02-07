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

	public File getBundleDirCopy() {
		return bundleDirCopy;
	}

	public String getDefaultLanguage() {
		return defaultLanguage;
	}

	public File getExpectedBundleDir() {
		return expectedBundleDir;
	}

	public File getExpectedSourceDir() {
		return expectedSourceDir;
	}

	public String getResourceBaseName() {
		return resourceBaseName;
	}

	public File getSourceDirCopy() {
		return sourceDirCopy;
	}

	public String getTestcaseName() {
		return testcaseName;
	}

	public boolean isRollback() {
		return rollback;
	}

	public void setBundleDirCopy(File bundleDirCopy) {
		this.bundleDirCopy = bundleDirCopy;
	}

	public void setDefaultLanguage(String defaultLanguage) {
		this.defaultLanguage = defaultLanguage;
	}

	public void setExpectedBundleDir(File expectedBundleDir) {
		this.expectedBundleDir = expectedBundleDir;
	}

	public void setExpectedSourceDir(File expectedSourceDir) {
		this.expectedSourceDir = expectedSourceDir;
	}

	public void setResourceBaseName(String resourceBaseName) {
		this.resourceBaseName = resourceBaseName;
	}

	public void setRollback(boolean rollback) {
		this.rollback = rollback;
	}

	public void setSourceDirCopy(File sourceDirCopy) {
		this.sourceDirCopy = sourceDirCopy;
	}

	public void setTestcaseName(String testcaseName) {
		this.testcaseName = testcaseName;
	}
}
