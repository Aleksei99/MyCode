package com.smuraha.mycode.dao.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Verification extends BaseEntity{

    private String token;
    private String email;
    private String action;
    private LocalDateTime validTo;
    private boolean used;
}
