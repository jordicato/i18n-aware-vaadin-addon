package com.askvikrant.noticeboard.model;

import java.io.Serializable;

public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name = null;

    public String getName() {
        return name;
    }

    public void setName(String s) {
        name = s;
    }
}
