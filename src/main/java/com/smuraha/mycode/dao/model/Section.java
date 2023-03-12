package com.smuraha.mycode.dao.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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

    @OneToOne
    @JoinColumn(name = "section_image_id")
    private Image sectionImage;
    private int imageWidth;
    private int imageHeight;
}
