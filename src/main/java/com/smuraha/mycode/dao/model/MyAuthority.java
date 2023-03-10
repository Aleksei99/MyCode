package com.smuraha.mycode.dao.model;

import org.springframework.security.core.GrantedAuthority;

public enum MyAuthority implements GrantedAuthority {
    ADMIN,
    USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
