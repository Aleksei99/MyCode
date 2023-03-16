package com.smuraha.mycode.dao.model;

import com.smuraha.mycode.customAnnotation.UniqueEmail;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User extends BaseEntity{

    @Column(unique = true)
    @UniqueEmail
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private MyAuthority myAuthority;

    @OneToMany(mappedBy = "user")
    private List<CodeSample> samples;

    public User(String email, String pass, MyAuthority authority) {
        this.email = email;
        this.password=pass;
        this.myAuthority=authority;
    }
}
