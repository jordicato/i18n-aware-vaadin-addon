package com.vaadin.demo.dashboard;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Properties;

import org.apache.commons.io.IOUtils;

import com.vaadin.server.VaadinRequest;
import com.vaadin.util.CurrentInstance;

public class AuthenticateProxy {

	private String paramAuthUser;
	private String paramAuthPassword;
	private String paramProxySet;
	private String paramProxyHost;
	private String paramProxyPort;
	
	public AuthenticateProxy() {
		try {
			autenticate();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void autenticate() throws IOException {
		boolean flag = false;
        Properties properties = new Properties();        
        InputStream input = null;
        File baseDirectory = CurrentInstance.get(VaadinRequest.class).getService().getBaseDirectory();       
        try {        	
        	input = new FileInputStream(new File(baseDirectory.getAbsolutePath().toString() + "/WEB-INF/classes/config.properties"));
        	properties.load(input);
        	input.close();
            paramAuthUser = properties.getProperty("authUser");
            paramAuthPassword = properties.getProperty("authPassword");
            paramProxySet = properties.getProperty("proxySet");
            paramProxyHost = properties.getProperty("proxyHost");
            paramProxyPort = properties.getProperty("proxyPort");
            flag = true;
        } catch (FileNotFoundException e) {
            System.out.println("Missing config file: " + e.getMessage());
        } finally {
            IOUtils.closeQuietly(input);
        }
		
        if ((flag) && (paramProxySet != null ? Boolean.parseBoolean(paramProxySet) : false)) {
            paramAuthUser = paramAuthUser != null ? paramAuthUser : "";
            paramAuthPassword = paramAuthPassword != null ? paramAuthPassword : "";
            Authenticator.setDefault(new Authenticator() {

                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(paramAuthUser, paramAuthPassword.toCharArray());
                }
            });
            paramProxyHost = paramProxyHost != null ? paramProxyHost : "127.0.0.1";
            paramProxyPort = paramProxyPort != null ? paramProxyPort : "3128";
            System.setProperty("proxySet", paramProxySet);
            //System.setProperty("http.proxyUser", paramAuthUser);
            //System.setProperty("http.proxyPassword", paramAuthPassword);
            System.setProperty("http.proxyHost", paramProxyHost);
            System.setProperty("http.proxyPort", paramProxyPort);
        }
		
	}
}
