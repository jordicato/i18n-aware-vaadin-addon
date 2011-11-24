package com.opnworks.vaadin.i18n.plugins;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.File;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import com.opnworks.vaadin.i18n.converter.MainPackRenamed;

/**
 * Goal which touches a timestamp file.
 * 
 * @goal touch
 * 
 * @phase process-sources
 */
public class MavenConverter extends AbstractMojo {
	/**
	 * Location of the file.
	 * 
	 * @parameter expression="${basedir}"
	 * @required
	 */
	private File basedir;
	/**
	 * @parameter dirsrc
	 * @required
	 */
	private String dirsrc = "";
	/**
	 * @parameter dirdst
	 * @required
	 */
	private String dirdst = "";
	/**
	 * @parameter basesrcpack
	 * @required
	 */
	private String basesrcpack = "";
	/**
	 * @parameter basedstpack
	 * @required
	 */
	private String basedstpack = "";
	/**
	 * @parameter extractlits
	 */
	private boolean extractlits = false;

	public void execute() throws MojoExecutionException {

		if (dirsrc == null || dirdst == null || basesrcpack == null || basedstpack == null || dirsrc.length() == 0 || dirdst.length() == 0 || basesrcpack.length() == 0
				|| basedstpack.length() == 0)
			throw new MojoExecutionException("dirsrc, dirdst,basesrcpack and basedstpack are required");
		if (dirdst.equals(dirsrc) &&   basesrcpack.indexOf(basedstpack) >= 0)
			throw new MojoExecutionException("if dirsrc equals dirdst,  basesrcpack cannot be prefix of basedstpack or viceversa");
		String xsrc = basedir.getAbsolutePath() + "/src/main/java";
		String xdst = basedir.getAbsolutePath() + "/src/main/java";
		if (!dirsrc.trim().equals(".") && !dirsrc.trim().equals("/")) {
			xsrc = xsrc + dirsrc;
		}
		if (!dirdst.trim().equals(".") && !dirdst.trim().equals("/")) {
			xdst = xdst + dirdst;
		}
		try {
			MainPackRenamed.main(new String[] { "-dirsrc", xsrc, "-dirdst", xdst, "-basesrcpack", basesrcpack, "-basedstpack", basedstpack, "-extractlits",
					Boolean.toString(extractlits) });
		} catch (Exception e) {
			throw new MojoExecutionException("Error " + e);
		}

	}
}
