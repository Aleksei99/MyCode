package com.smuraha.mycode.dao.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "images")
public class Image extends BaseEntity{

    private String name;
    private String contentType;
    private byte[] data;
}
