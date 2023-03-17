package com.smuraha.mycode.service;

import com.smuraha.mycode.dao.model.User;

public interface UserService {
    User findUserByEmail(String email);
    void saveNewUser(String email,String password);

    void resetPassword(String email, String password);
}
