package com.smuraha.mycode.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserForLoad extends User {
    public UserForLoad(String username,
                       String password,
                       Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}
