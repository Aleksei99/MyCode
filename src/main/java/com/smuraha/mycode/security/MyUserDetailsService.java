package com.smuraha.mycode.security;

import com.smuraha.mycode.dao.model.MyAuthority;
import com.smuraha.mycode.dao.model.User;
import com.smuraha.mycode.dao.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email);
        Set<MyAuthority> authorities;
        if (Objects.isNull(user)) {
            log.debug("User with email " + email + "not found");
            throw new UsernameNotFoundException("User with email " + email + "not found");
        } else
            authorities = new HashSet<>(List.of(user.getMyAuthority()));
        log.info("Авторизация пользователя "+email);
        return new UserForLoad(
                user.getEmail(), user.getPassword(), authorities
        );
    }
}
