package com.smuraha.mycode.dao.model;

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
public class Section extends BaseEntity{

    private String name;
    private String sectionHeader;
    private byte[] sectionImage;
    private int imageWidth;
    private int imageHeight;
}
