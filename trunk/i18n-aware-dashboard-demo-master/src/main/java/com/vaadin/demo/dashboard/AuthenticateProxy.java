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
        /*Properties properties = new Properties();        
        InputStream input = null;
        try {        	
        	input = new FileInputStream(new File("config.properties"));
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
        }*/

		flag = true;
        paramAuthUser = "Sandy";
        paramAuthPassword = "Qwerty5";
        paramProxySet = "true";
        paramProxyHost = "200.55.147.68";
        paramProxyPort = "443";
		
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
