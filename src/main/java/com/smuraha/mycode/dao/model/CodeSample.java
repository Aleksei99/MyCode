package com.smuraha.mycode.dao.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CodeSample extends BaseEntity{

    @Column(length = 10_000)
    private String code;
    @Column(length = 20_000)
    private String description;

    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;

}
