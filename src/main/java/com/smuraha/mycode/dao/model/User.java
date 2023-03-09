package com.smuraha.mycode.dao.model;

import com.smuraha.mycode.customAnnotation.UniqueEmail;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User extends BaseEntity{

    @Column(unique = true)
    @UniqueEmail
    private String email;
    private String password;
    private Role role;
}
