package com.smuraha.mycode.service;

import com.smuraha.mycode.dao.model.MyAuthority;
import com.smuraha.mycode.dao.model.User;
import com.smuraha.mycode.dao.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public void saveNewUser(String email, String password) {
        User user = new User(email, bCryptPasswordEncoder.encode(password), MyAuthority.USER);
        userRepository.save(user);
    }

    @Override
    public void resetPassword(String email, String password) {
        User user = userRepository.findUserByEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save(user);
    }
}
