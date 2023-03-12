package com.smuraha.mycode.dao.repo;

import com.smuraha.mycode.dao.model.Image;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<Image, Long> {
    Image getImageByName(String name);
}