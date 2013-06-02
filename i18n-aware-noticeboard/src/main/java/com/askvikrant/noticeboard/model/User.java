package com.askvikrant.noticeboard.model;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String ROOT_USER_ID = "root";

    public static final String ROOT_USER_NAME = "Root";

    public static final String ROLE_GUEST = "GUEST";

    public static final String ROLE_EXECUTIVE = "EXECUTIVE";

    public static final String ROLE_ROOT = "ROOT";

    private String userId = null;

    private String name = null;

    private String password = null;

    private String role = null;

    private String department = null;

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getDepartment() {
        return department;
    }

    public void setUserId(String s) {
        userId = s;
    }

    public void setName(String s) {
        name = s;
    }

    public void setPassword(String s) {
        password = s;
    }

    public void setRole(String s) {
        role = s;
    }

    public void setDepartment(String s) {
        department = s;
    }
}
